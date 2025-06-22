package me.cworldstar.highriseSF.impl.abstracts;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class AbstractTickingMenuBlock extends AbstractMenuBlock {

	public AbstractTickingMenuBlock(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(itemGroup, item, id, recipeType, recipe);
		addItemHandler(new BlockTicker() {

            @Override
            public boolean isSynchronized() {
                return synchronous();
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                BlockMenu menu = BlockStorage.getInventory(b);
                if (menu != null) {
                    AbstractTickingMenuBlock.this.tick(b, menu);
                }
            }

		});
	}

	protected abstract void tick(Block b, BlockMenu menu);
	
    protected boolean synchronous() {
        return false;
    }

}
