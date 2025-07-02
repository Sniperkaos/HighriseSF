package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.highriseSF.impl.builders.ItemStackBuilder;
import me.cworldstar.highriseSF.utils.FormatUtils;

public class HighriseSFItemStacks {
	public static final ItemStack TEST_ITEM_STACK = CustomItemStack.create(new ItemStack(Material.AMETHYST_BLOCK), FormatUtils.formatString("&5UU Matter Generator"), "", "&7> &7Generates &5UU matter&7 every &c1024&7 machine ticks.", "&7> &7Ticks once every &c3&7 slimefun ticks.");
	public static final SlimefunItemStack TEST_ITEM_SF_STACK = new SlimefunItemStack("TEST_ITEM", TEST_ITEM_STACK);
	public static final SlimefunItemStack UU_MATTER_STACK = new SlimefunItemStack("HRSF_UU_MATTER", new ItemStackBuilder(Material.MAGENTA_DYE).setName("&#E536E0&lU&#E033E1&lU &#D62EE3&lM&#D12BE4&la&#CB28E4&lt&#C626E5&lt&#C123E6&le&#BC20E7&lr").setLore(new String[] {"", "&7> &#9A02DBT&#8C15DCh&#7F28DEe &#644FE0b&#5662E2u&#4975E3i&#3B89E4l&#2E9CE6d&#20AFE7i&#219FE1n&#228FDCg &#246FD0b&#245FCAl&#254FC5o&#263FBFc&#272FB9k &#3136B2o&#3539AFf &#3F40A8t&#4444A5h&#4847A2e &#524E9Bu&#48488Fn&#3E4182i&#333B76v&#29356Ae&#1F2E5Dr&#152851s&#0A2144e&#001B38."}).get());
	public static final SlimefunItemStack SMALL_COMPACT_MACHINE_ITEM = new SlimefunItemStack("HRSF_COMPACT_MACHINE_S", new ItemStackBuilder(Material.LIGHT_GRAY_STAINED_GLASS).get(), "&fSmall Compact Machine", "", "&f> Contains a small 8x8 dimension.");
	public static final SlimefunItemStack COMPACT_MACHINE_IO_ITEM = new SlimefunItemStack("HRSF_COMPACT_MACHINE_IO", new ItemStackBuilder(Material.CRYING_OBSIDIAN).get(), "&fCompact Machine IO", "", "&f> Allows items to be moved between the compact", "&fmachine and the dimension.");
	
	public static final ItemStack ITEM_GROUP_TOKENS = CustomItemStack.create(Material.DIAMOND,"&fResearch Tokens","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_MACHINES = CustomItemStack.create(Material.DROPPER,"&6Machines","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_MAGIC = CustomItemStack.create(Material.ENCHANTED_BOOK,"&9Magic Items","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_ARMOR = CustomItemStack.create(Material.CHAINMAIL_CHESTPLATE,"&eTools and Armors","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_MATERIALS = CustomItemStack.create(Material.IRON_INGOT,"&7Materials","","&d> Click to open!");
	public static final ItemStack ITEM_GROUP_PETS = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk2N2QwMTUyY2YzMDU0YjEzNjc5NjE2YmNjOWRhOGVmYTM0ZjE1NTlhOGU4OTIwNDlhMTkwMGVlZDA3OGI4MCJ9fX0=");
	public static final SlimefunItemStack ITEM_GROUP_MOB_DROPS = new SlimefunItemStack("MOB_DROP_TOKEN", CustomItemStack.create(Material.ZOMBIE_HEAD, "&6Mob Drops"));
	
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
	
}
