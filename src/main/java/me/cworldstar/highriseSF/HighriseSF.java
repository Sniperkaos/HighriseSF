package me.cworldstar.highriseSF;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.items.HighriseSFItems;

public class HighriseSF extends JavaPlugin implements SlimefunAddon {

	private GitHubBuildsUpdater updater;
	
	private static HighriseSF plugin;
	private static Map<String, Config> configs = new HashMap<String, Config>();
	
	public static HighriseSF get() {
		return plugin;
	}
	
	public static void sync(Runnable r) {
		plugin.getServer().getScheduler().runTask(plugin, r);
	}
	
	public static void async(Consumer<ScheduledTask> r) {
		plugin.getServer().getAsyncScheduler().runNow(plugin, r);
	}
	
	public static Config getConfig(String configID) {
		return configs.get(configID);
	}
	
    @Override
    public void onEnable() {
    	
    	try {
        	updater = new GitHubBuildsUpdater(this, getFile(), "Sniperkaos/HighriseSF/master");
    	} catch(IllegalArgumentException e) {
    		e.printStackTrace();
    	}
    	
    	plugin = this;
    	try {
        	new HighriseSFItems(); //-- initialize the HighriseSFItems class, which will handle every SF item in the plugin.
        	Registry.finalizeRegistry(); //-- finalize the registry, creating every item.
    	} catch(Throwable e) {
    		e.printStackTrace();
    	}
    	
    	
    	//-- initialize config
    	Config cfg = new Config(this);
    	configs.put("main", cfg);
    	if(cfg.getBoolean("options.auto-update")) {
    		try {
    			updater.start();
    		} catch(Error e) {
    			e.printStackTrace();
    		}
    	}
    }

    @Override
    public void onDisable() {
    	
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Sniperkaos/HighriseSF/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

	public static NamespacedKey namespacedKey(String string) {
		return new NamespacedKey(plugin, string);
	}

}
