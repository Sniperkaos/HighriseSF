package me.cworldstar.highriseSF.impl.items.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.WorldUtils;
import me.cworldstar.highriseSF.impl.abstracts.AbstractTickingMenuBlock;
import me.cworldstar.highriseSF.impl.builders.ItemStackBuilder;
import me.cworldstar.highriseSF.impl.compactmachines.CompactMachineData;
import me.cworldstar.highriseSF.impl.compactmachines.CompactMachineHandler;
import me.cworldstar.highriseSF.impl.compactmachines.CompactMachineWorldBuilder;
import me.cworldstar.highriseSF.utils.BlockStorageHelper;
import me.cworldstar.highriseSF.utils.FormatUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.kyori.adventure.text.TextComponent;

public class AbstractCompactMachine extends AbstractTickingMenuBlock {

	protected static ItemStack TELEPORT_ITEM = new ItemStackBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("&6> Click to teleport!").setLore(new String[] {"", "&e<- Compact Machine Input", "&6Compact Machine Output ->"}).get();
	protected static ItemStack TELEPORT_DENIED_ITEM = new ItemStackBuilder(Material.RED_STAINED_GLASS_PANE).setName("&c> The dimension is being created.").setLore(new String[] {"", "&4Please wait while the dimension is being generated."}).get();
	
	public AbstractCompactMachine(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe, int machineSize) {
		super(itemGroup, item, id, recipeType, recipe);
		
		addItemHandler(new BlockPlaceHandler(false) {
			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				if(e.getBlock().getLocation().getWorld().hasMetadata("HRSF_COMPACT_MACHINE")) {
					e.getPlayer().sendMessage(FormatUtils.formatAndCast("&6> You cannot place a compact machine inside of a compact machine."));
					e.setCancelled(true);
					return;
				}
		
				ItemStack itemInHand = e.getItemInHand();
				ItemMeta meta = itemInHand.getItemMeta();
				PersistentDataContainer container = meta.getPersistentDataContainer();
				if(container.has(HighriseSF.namespacedKey("HRSF_CMACHINE_ID"))) {
					BlockStorage.addBlockInfo(e.getBlock(), "HRSF_CMACHINE_ID", container.get(HighriseSF.namespacedKey("HRSF_CMACHINE_ID"), PersistentDataType.STRING));
				} else {
					//-- create a new world
					UUID dim_id = UUID.randomUUID();
					String world_id = "compact_machine-".concat(dim_id.toString().concat("-").concat(e.getPlayer().getName()));
					BlockStorage.addBlockInfo(e.getBlock(), "HRSF_CMACHINE_ID", world_id);
					new BukkitRunnable() {
						@Override
						public void run() {
							World world = CompactMachineWorldBuilder.createCompactMachineWorld(e.getPlayer(), world_id, machineSize);
							world.setMetadata("HRSF_COMPACT_MACHINE", new FixedMetadataValue(HighriseSF.get(), true));
						}
					}.runTask(HighriseSF.get());
				}
			}
		});
		
		addItemHandler(new BlockBreakHandler(false, false) {
			@Override
			public void onPlayerBreak(BlockBreakEvent e, ItemStack i2, List<ItemStack> drops) {
				e.setDropItems(false);
				drops.clear();
				ItemStack item = AbstractCompactMachine.this.getItem().clone();
				
				if(item == null) {
					e.getPlayer().sendMessage(FormatUtils.formatAndCast("&6> The compact machine was unable to break. Machine not found in drops? If you see this, contact a developer."));
					e.setCancelled(true);
					return;
				}
				
				JsonObject BlockStorageData = BlockStorageHelper.getBlockStorage(e.getBlock());
				String CMachineID = BlockStorageData.get("HRSF_CMACHINE_ID").getAsString();
				
				ItemMeta meta = item.getItemMeta();
				if(meta == null) {
					meta = Bukkit.getItemFactory().getItemMeta(item.getType());
				}

				meta.getPersistentDataContainer().set(HighriseSF.namespacedKey("HRSF_CMACHINE_ID"), PersistentDataType.STRING, CMachineID);
				
				if(!meta.hasLore()) {
					meta.lore(Arrays.asList(new TextComponent[] {
							(TextComponent) FormatUtils.formatAndCast(""),
							(TextComponent) FormatUtils.formatAndCast("&6&nCompact Machine ID:"),
							(TextComponent) FormatUtils.formatAndCast("&7" + CMachineID)
					}));
				}
				
				List<TextComponent> lore = meta.lore().stream().map(value -> (TextComponent) value).collect(Collectors.toList());
				lore.add((TextComponent) FormatUtils.formatAndCast(""));
				lore.add((TextComponent) FormatUtils.formatAndCast("&6&nCompact Machine ID:"));
				lore.add((TextComponent) FormatUtils.formatAndCast("&7" + CMachineID));
				meta.lore(lore);
				item.setItemMeta(meta);
				BlockStorage.clearBlockInfo(e.getBlock());
				
				Location location = e.getBlock().getLocation();
				location.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), item);
				
			}
		});
		
	}
	
	@Override
	public void onNewInstance(BlockMenu menu, Block b) {
		
		JsonObject BlockStorageData = BlockStorageHelper.getBlockStorage(b);
		String CMachineID = BlockStorageData.get("HRSF_CMACHINE_ID").getAsString();
		CompactMachineHandler.registerCompactMachineAt(CMachineID, new CompactMachineData(b.getWorld(), menu, b, CMachineID));
		
		menu.addMenuClickHandler(4, new AdvancedMenuClickHandler() {
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				return false;
			}

			@Override
			public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
				new BukkitRunnable() {
					@Override
					public void run() {
						JsonObject BlockStorageData = BlockStorageHelper.getBlockStorage(b);
						String CMachineID = BlockStorageData.get("HRSF_CMACHINE_ID").getAsString();
						World toTeleport = WorldUtils.loadWorld(CMachineID);
						Bukkit.getServer().getWorlds().forEach(world -> {
							HighriseSF.get().getLogger().log(Level.INFO, world.getName());
						});
						p.teleport(toTeleport.getSpawnLocation().add(0, 1, 0));
					}
				}.runTask(HighriseSF.get());
				
				return false;
			}
		});	
	}

	@Override
	protected void tick(Block b, BlockMenu menu) {
		if(menu.hasViewer() && menu.getItemInSlot(4).isSimilar(TELEPORT_DENIED_ITEM)) {
			JsonObject BlockStorageData = (JsonObject) JsonParser.parseString(BlockStorage.getBlockInfoAsJson(b));
			String CMachineID = BlockStorageData.get("HRSF_CMACHINE_ID").getAsString();
			CompactMachineData data = CompactMachineHandler.getData(CMachineID);
			if(data == null) {
				CompactMachineHandler.registerCompactMachineAt(CMachineID, new CompactMachineData(b.getWorld(), menu, b, CMachineID));
			}
			if(WorldUtils.loadWorld(CMachineID) == null) {
				HighriseSF.get().getLogger().log(Level.INFO, "World " + CMachineID + " does not exist.");
				return;
			} else if(menu.getItemInSlot(4).isSimilar(TELEPORT_DENIED_ITEM)) {
				menu.replaceExistingItem(4, TELEPORT_ITEM);
			}
		}
	}

	@Override
	public void setup(BlockMenuPreset preset) {
		preset.drawBackground(new int[] {0,8,4});
		preset.addItem(4, TELEPORT_DENIED_ITEM);	
	}

	@Override
	public int[] getInputSlots() {
		return new int[] {1,2,3};
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] {5,6,7};
	}

}
