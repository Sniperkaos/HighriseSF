package me.cworldstar.highriseSF;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.commands.IMCommand;
import me.cworldstar.highriseSF.impl.events.HighriseSFTickEvent;
import me.cworldstar.highriseSF.impl.handlers.PlayerAttackHandler;
import me.cworldstar.highriseSF.impl.items.HighriseSFItems;
import me.cworldstar.highriseSF.impl.items.UURecipes;
import me.cworldstar.highriseSF.impl.items.armor.BerserkerArmorSet;
import me.cworldstar.highriseSF.impl.items.armor.NaturalArmorSet;
import me.cworldstar.highriseSF.impl.listeners.BlockBreakListener;
import me.cworldstar.highriseSF.impl.listeners.DurabilityDamageListener;
import me.cworldstar.highriseSF.impl.listeners.HighriseSFTickListener;
import me.cworldstar.highriseSF.impl.listeners.PlayerCraftListener;
import me.cworldstar.highriseSF.impl.protocols.CustomArmorDurabilityProtocol;

public class HighriseSF extends JavaPlugin implements SlimefunAddon {

	private GitHubBuildsUpdater updater;
	protected int slimefunTickCount = 0;
	
	private long lastSlimefunTick = 0L;
	private double slimefunTickDelta = 0D;
	
	public double getSFTickDelta() {
		return slimefunTickDelta;
	}
	
	public long getLastSFTick() {
		return lastSlimefunTick;
	}
	
	
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
	
    public static int SFTickCount() {
        return get().slimefunTickCount;
    }
    
    public static void tickSF() {
    	get().slimefunTickCount += 1;
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
        	HighriseSF.log("Registering HighriseSF Items");
        	new HighriseSFItems(); //-- initialize the HighriseSFItems class, which will handle every SF item in the plugin.
        	HighriseSF.log("Done");
        	
        	//-- register UU recipes before registry finalization (IMPORTANT!)
        	HighriseSF.log("Registering UU Recipes");
        	new UURecipes();
        	HighriseSF.log("Done");
        	
        	//-- Register armor sets
        	HighriseSF.log("Registering Armor Sets");
        	new NaturalArmorSet();
        	new BerserkerArmorSet();
        	HighriseSF.log("Done");
   

        	HighriseSF.log("Finalizing Registry");
        	Registry.finalizeRegistry(); //-- finalize the registry, creating every item.
        	HighriseSF.log("Done");
    	} catch(Throwable e) {
    		e.printStackTrace();
    	}
   	
    	//-- initialize config
    	Config cfg = new Config(this);
    	configs.put("main", cfg);
    	if(cfg.getBoolean("options.auto-update")) {
    		try {
    			//updater.start();
    		} catch(Error e) {
    			e.printStackTrace();
    		}
    	}
    	
    	getCommand("itemmeta").setExecutor(new IMCommand());
    	
    	//-- Register our handlers
    	PlayerAttackHandler.registerListener();
    	BlockBreakListener.register();
    	PlayerCraftListener.register();
    	HighriseSFTickListener.register();
    	DurabilityDamageListener.register(DurabilityDamageListener.class);
    	CustomArmorDurabilityProtocol.start();
    	
    	Bukkit.getServer().getAsyncScheduler().runAtFixedRate(
        		plugin, 
        		(ScheduledTask task) -> {
        			
        			if(Slimefun.instance() == null) return;
        			
        			if(!(HighriseSF.this.lastSlimefunTick == 0)) {
            			HighriseSF.this.slimefunTickDelta = (System.currentTimeMillis() - HighriseSF.this.lastSlimefunTick) / Slimefun.getTickerTask().getTickRate();
        			}
        			HighriseSFTickEvent event = new HighriseSFTickEvent();
        			sync(new Runnable() {
						@Override
						public void run() {
							Bukkit.getServer().getPluginManager().callEvent(event);							
						} 
        			});
        			HighriseSF.tickSF();
        			HighriseSF.this.lastSlimefunTick = System.currentTimeMillis();
        		}, 
        		0L, 
        		Slimefun.getTickerTask().getTickRate(), 
        		TimeUnit.MILLISECONDS
        	);
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

	public static NamespacedKey key(String string) {
		return namespacedKey(string);
	}

	public static void log(String string) {
		HighriseSF.get().getLogger().log(Level.INFO, string);
	}

}
