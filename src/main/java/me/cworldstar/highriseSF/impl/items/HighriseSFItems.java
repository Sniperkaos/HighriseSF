package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.builders.ItemGroupBuilder;
import me.cworldstar.highriseSF.impl.items.blocks.CompactMachineIO;
import me.cworldstar.highriseSF.impl.items.blocks.SmallCompactMachine;
import me.cworldstar.highriseSF.impl.items.machines.CosmicAlloyForge;
import me.cworldstar.highriseSF.impl.items.pets.SamuraiPet;

public class HighriseSFItems {
		
	public HighriseSFItems() {
		ItemGroup MACHINE_GROUP = Registry.registerItemGroup(
				new ItemGroupBuilder().setItem(new CustomItemStack(new ItemStack(Material.WAXED_CUT_COPPER), "Machines", "", "")).setID("MACHINE_GROUP").build()
		);
		
		ItemGroup RESOURCE_GROUP = Registry.registerItemGroup(
				new ItemGroupBuilder().setItem(new CustomItemStack(new ItemStack(Material.IRON_INGOT), "Resources", "", "")).setID("RESOURCE_GROUP").build()
		);
		
		ItemGroup PET_GROUP = Registry.registerItemGroup(
				new ItemGroupBuilder().setItem(new CustomItemStack(new ItemStack(Material.PLAYER_HEAD), "Pets", "", "")).setID("PET_GROUP").build()
		);
		
		ItemGroup ARMOR_GROUP = Registry.registerItemGroup(
				new ItemGroupBuilder().setItem(new CustomItemStack(new ItemStack(Material.PLAYER_HEAD), "Armors", "", "")).setID("ARMOR_GROUP").build()
		);
		
		RecipeType COSMIC_ALLOY_FORGE_RECIPETYPE = new RecipeType(HighriseSF.key("COSMIC_ALLOY_FORGE"), HighriseSFItemStacks.COSMIC_ALLOY_FORGE);
		
		SlimefunItemStack PET_CATALYST = new SlimefunItemStack("PET_CATALYST", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0=","&ePet Catalyst");
		CustomItemStack SAMURAI_PET_ITEM = new CustomItemStack(SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI5ZWZlOWU2N2Y3ZmFjYmNlOTViNWVlM2E5Nzc5ZWFlZDM4OTc4ODRjNGNhY2I5MTAxN2Q2OWYzNDMzMTg0YiJ9fX0="), "&6Pets");
		
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP, HighriseSFItemStacks.THORIUM_DUST, RecipeType.NULL, null));
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP, HighriseSFItemStacks.THORIUM_ALLOY, RecipeType.SMELTERY, new ItemStack[] {HighriseSFItemStacks.THORIUM_DUST.asQuantity(3), SlimefunItems.URANIUM.asQuantity(2)}));
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP, HighriseSFItemStacks.BLOCK_OF_UU_MATTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK,HighriseSFItemStacks.UU_MATTER_STACK}));
		Registry.registerItem(new HostileItem(RESOURCE_GROUP, HighriseSFItemStacks.LIVING_ALLOY, COSMIC_ALLOY_FORGE_RECIPETYPE, new ItemStack[] {HighriseSFItemStacks.COSMIC_ALLOY_FORGE}, 20, 200));
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP, PET_CATALYST, RecipeType.NULL, null));
		Registry.registerItem(new CompactMachineIO(MACHINE_GROUP, HighriseSFItemStacks.COMPACT_MACHINE_IO_ITEM, RecipeType.NULL, null));
		Registry.registerItem(new CosmicAlloyForge(MACHINE_GROUP, HighriseSFItemStacks.COSMIC_ALLOY_FORGE, RecipeType.NULL, null));
		Registry.registerItem(new SmallCompactMachine(MACHINE_GROUP, HighriseSFItemStacks.SMALL_COMPACT_MACHINE_ITEM, RecipeType.NULL, null));
		Registry.registerItem(new me.cworldstar.highriseSF.impl.sf.items.TestItem(MACHINE_GROUP, HighriseSFItemStacks.TEST_ITEM_SF_STACK, RecipeType.NULL, null));
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP, HighriseSFItemStacks.UU_MATTER_STACK, RecipeType.NULL, null));
		Registry.registerItem(new SamuraiPet(PET_GROUP, new SlimefunItemStack("SAMURAI_PET", SAMURAI_PET_ITEM.asOne(), "&6Samurai Pet", "", "&7There is a 13% chance you will", "&7critically strike for 75% extra damage", "&7each time you attack.", "", "&e&nFavorite Food:", "&7> &a%favorite_food%", "", "&7&nFood:", "&a%food%&7/&c%max_food%"), "SAMURAI_PET", RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
				PET_CATALYST, new CustomItemStack(Material.NETHERITE_SWORD), new CustomItemStack(Material.DIAMOND_SWORD),
				new CustomItemStack(Material.IRON_SWORD), new CustomItemStack(Material.GOLDEN_SWORD), PET_CATALYST,
				SlimefunItems.SWORD_OF_BEHEADING, PET_CATALYST, SlimefunItems.SEISMIC_AXE
		}));
		Registry.registerItem(new SlimefunItem(RESOURCE_GROUP,PET_CATALYST, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
				SlimefunItems.BLANK_RUNE, SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3,
				SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.EGG), SlimefunItems.MAGIC_LUMP_3,
				SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3, SlimefunItems.BLANK_RUNE
		}));
		new CompressedItem(RESOURCE_GROUP, Material.DIAMOND_BLOCK).registerAll();
		new CompressedItem(RESOURCE_GROUP, Material.IRON_BLOCK).registerAll();
		new CompressedItem(RESOURCE_GROUP, Material.GOLD_BLOCK).registerAll();
	}
	
}
