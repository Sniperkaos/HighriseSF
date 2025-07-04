package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.highriseSF.impl.builders.ItemStackBuilder;
import me.cworldstar.highriseSF.utils.FormatUtils;

public class HighriseSFItemStacks {
	public static final CustomItemStack TEST_ITEM_STACK = new CustomItemStack(new ItemStack(Material.AMETHYST_BLOCK), FormatUtils.formatString("&5UU Matter Generator"), "", "&7> &7Generates &5UU matter&7 every &c1024&7 machine ticks.", "&7> &7Ticks once every &c3&7 slimefun ticks.");
	public static final SlimefunItemStack TEST_ITEM_SF_STACK = new SlimefunItemStack("TEST_ITEM", TEST_ITEM_STACK);
	public static final SlimefunItemStack UU_MATTER_STACK = new SlimefunItemStack("HRSF_UU_MATTER", new ItemStackBuilder(Material.MAGENTA_DYE).setName("<gradient:#FBA3F5:#FF23FD>UU Matter</gradient>").setLore(new String[] {"<gradient:#FBE6F5:#FF23FD:#8C3063:#AAFDBA>The building block of the universe.</gradient>"}).get());
	public static final SlimefunItemStack SMALL_COMPACT_MACHINE_ITEM = new SlimefunItemStack("HRSF_COMPACT_MACHINE_S", new ItemStackBuilder(Material.LIGHT_GRAY_STAINED_GLASS).get(), "&fSmall Compact Machine", "", "&f> Contains a small 8x8 dimension.");
	public static final SlimefunItemStack COMPACT_MACHINE_IO_ITEM = new SlimefunItemStack("HRSF_COMPACT_MACHINE_IO", new ItemStackBuilder(Material.CRYING_OBSIDIAN).get(), "&fCompact Machine IO", "", "&f> Allows items to be moved between the compact", "&fmachine and the dimension.");
	
	//-- thorium
	public static final SlimefunItemStack THORIUM_DUST = new SlimefunItemStack("HRSF_THORIUM_DUST", new ItemStackBuilder(Material.FLINT).setName("&7Thorium Dust").setLore(new String[] {
		"",
		"&7Can be found inside large deposits of stone.",
		LoreBuilder.radioactive(Radioactivity.LOW),
		LoreBuilder.HAZMAT_SUIT_REQUIRED
	}).get());
	
	public static final SlimefunItemStack THORIUM_ALLOY = new SlimefunItemStack("HRSF_THORIUM_ALLOY", new ItemStackBuilder(Material.NETHERITE_INGOT).setName("<gradient:#6B6B6B:#B65454>Thorium Alloy</gradient>").setLore(new String[] {
			"",
			"&7Very strong.",
			LoreBuilder.radioactive(Radioactivity.HIGH),
			LoreBuilder.HAZMAT_SUIT_REQUIRED
		}).get());
	
	public static final SlimefunItemStack LIVING_ALLOY = new SlimefunItemStack("HRSF_LIVING_ALLOY", new ItemStackBuilder(SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFhNTEwM2FmMzgxZjc4OThjNTFlNGQwOTMwZWZmMzNlOWRiMTAxYjkyOWQ4MWVlY2M0NGUzNGNhMTgxNzEwNCJ9fX0=")).setName("<bold><gradient:#11FF22:#065F0D:#11FF22:#11FF22:#065F0D>Living Alloy</gradient>").setLore(new String[] {
			"",
			"&7A living alloy, writhing and squirming in your grasp.",
			HostileItem.lore(20),
			HostileItem.warningString()
		}).get());
	
	public static final SlimefunItemStack BLOCK_OF_UU_MATTER = new SlimefunItemStack("HRSF_BLOCK_OF_UU_MATTER", new ItemStackBuilder(SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNmZmUyNmI1N2Y2YWZkMjBhMDcxOThlYmZmMjRhMjcyYWJlNjUxOGM2NzZhZjU0YjkzMjFiMDlkMTNkNmU2MyJ9fX0=")).setName("<gradient:#FBA3F5:#FF23FD>Block of UU Matter</gradient>").setLore(new String[] {
			"",
			"<gradient:#FBE6F5:#FF23FD:#8C3063:#AAFDBA>The building block of the universe.</gradient>"
	}).get());
	
	public static final CustomItemStack ITEM_GROUP_TOKENS = new CustomItemStack(Material.DIAMOND,"&fResearch Tokens","","&d> Click to open!");
	public static final CustomItemStack ITEM_GROUP_MACHINES = new CustomItemStack(Material.DROPPER,"&6Machines","","&d> Click to open!");
	public static final CustomItemStack ITEM_GROUP_MAGIC = new CustomItemStack(Material.ENCHANTED_BOOK,"&9Magic Items","","&d> Click to open!");
	public static final CustomItemStack ITEM_GROUP_ARMOR = new CustomItemStack(Material.CHAINMAIL_CHESTPLATE,"&eTools and Armors","","&d> Click to open!");
	public static final CustomItemStack ITEM_GROUP_MATERIALS = new CustomItemStack(Material.IRON_INGOT,"&7Materials","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_PETS = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk2N2QwMTUyY2YzMDU0YjEzNjc5NjE2YmNjOWRhOGVmYTM0ZjE1NTlhOGU4OTIwNDlhMTkwMGVlZDA3OGI4MCJ9fX0=");
	public static final SlimefunItemStack ITEM_GROUP_MOB_DROPS = new SlimefunItemStack("MOB_DROP_TOKEN", new CustomItemStack(Material.ZOMBIE_HEAD, "&6Mob Drops"));
	
	public static final SlimefunItemStack NANITE_SYNTHESIZER = new SlimefunItemStack(
			"NANITE_SYNTHESIZER",
			SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzgyZDU4ZTg5M2JmOWI4MjQzMzYyNmQwZTQ4MGU5Mzc1YjU4ZjNjMmRkZWY0NTBmNjE3YjYwZGJmNTA2Y2JiOSJ9fX0="),
			"&6&lNanite Synthesizer", 
			"", 
			"&e> Place this anywhere 5,000 blocks away from 0,0.", 
			"&e> RATE: 1 NANO PARTICLE/30 MINUTES", 
			"&c&nWARNING&r&e: If this machine reaches 600 heat,", 
			"&eit will violently explode!"	
	);
	
	public static final SlimefunItemStack NANO_PARTICLES = new SlimefunItemStack("NANO_PARTICLE", 
			SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU5NTI2MGRiMmIyOGRlYTcyYzJiNDI1MmExODZkNDFkNjk0YjBkN2M4NTliMmFhN2I4OTFjMzFmNTk4OWRiOCJ9fX0="),
			"&fNano Particle",
			"&e>Moving your way up in the world."
	);
	
	public static final SlimefunItemStack NANO_ALLOY = new SlimefunItemStack("NANO_ALLOY",
			SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQwYWVhODVlYjkzYTRhMDgwNGFmM2Q1OWEyZmViZTFjNDNhMDc2Njg1NTc0YzUzODg0MmU0M2EyMTA5MGMyYyJ9fX0="),
			"&fNano Alloy",
			"&e> Very impressive flex."
	);
	
	public static final SlimefunItemStack NANO_CORE = new SlimefunItemStack("NANO_CORE",
			SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdjOGIzYjBmN2RjMmMyY2E1YzExMjhlZGE0MTRmZmE4OWIzZDA0MWE4MWJlMmNlNjEwMzMwYjJhMzU3MjIzNiJ9fX0="),
			"&fNano Core",
			"&e> A core made from millions",
			"&e> of condensed nanites."
	);
	public static final SlimefunItemStack COSMIC_ALLOY_FORGE = new SlimefunItemStack("HRSF_COSMIC_ALLOY_FORGE", new ItemStackBuilder(SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNmZmUyNmI1N2Y2YWZkMjBhMDcxOThlYmZmMjRhMjcyYWJlNjUxOGM2NzZhZjU0YjkzMjFiMDlkMTNkNmU2MyJ9fX0=")).setName("<gradient:#FF23FD:#FFFFFF:#F7ADFB:#5A5C3D:#FBE6F5>Cosmic Alloy Forge</gradient>").setLore(new String[] {
			"",
			"<gradient:#FBE6F5:#FF23FD:#8C3063:#AAFDBA>The final stretch.</gradient>",
			LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
			LoreBuilder.speed(0.5F),
			LoreBuilder.powerPerSecond(15000),
			"",
			LoreBuilder.radioactive(Radioactivity.VERY_DEADLY)
	}).get());;
	
}
