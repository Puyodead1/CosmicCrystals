package me.puyodead.enchantcrystals;

import me.puyodead.enchantcrystals.Commands.EnchantCrystalsCommand;
import me.puyodead.enchantcrystals.Commands.TabCompletion;
import me.puyodead.enchantcrystals.Events.AnvilPrepareEvent;
import me.puyodead.enchantcrystals.Events.CrystalUseEvent;
import me.puyodead.enchantcrystals.Events.ItemEnchantEvent;
import me.puyodead.enchantcrystals.Events.OpenInventoryEvent;
import me.puyodead.enchantcrystals.NMS.NMSBase;
import me.puyodead.enchantcrystals.NMS.NMS_v1_17_R1;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EnchantCrystals extends JavaPlugin {

    private static EnchantCrystals plugin;
    private static final String PREFIX = "&7[&dEnchantCrystals&7] ";
    private NMSBase nms;

    @Override
    public void onEnable() {
        plugin = this;
        nms = new NMS_v1_17_R1();

        EnchantCrystalsUtils.sendConsole(PREFIX + "&b=============================================================");

        initConfig();
        initEvents();
        initCommands();

        EnchantCrystalsUtils.sendConsole(PREFIX + "&d========================");
        EnchantCrystalsUtils.sendConsole(PREFIX + "&bAuthor: &ePuyodead1");
        EnchantCrystalsUtils.sendConsole(PREFIX + "&b" + this.getName() + " Version: &e" + Objects.requireNonNull(getServer().getPluginManager().getPlugin(this.getDescription().getName())).getDescription().getVersion());
        EnchantCrystalsUtils.sendConsole(PREFIX + "&bMinecraft Version: &e" + getServer().getVersion());
        EnchantCrystalsUtils.sendConsole(PREFIX + "&b=============================================================");
    }

    @Override
    public void onDisable() {
        //
    }

    public void initConfig() {
        final long STARTED = System.currentTimeMillis();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        EnchantCrystalsUtils.sendConsole(PREFIX + "&bLoaded Configuration &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void initEvents() {
        final long STARTED = System.currentTimeMillis();

        getServer().getPluginManager().registerEvents(new CrystalUseEvent(this), this);
        getServer().getPluginManager().registerEvents(new ItemEnchantEvent(this), this);
        getServer().getPluginManager().registerEvents(new OpenInventoryEvent(this), this);
        getServer().getPluginManager().registerEvents(new AnvilPrepareEvent(this), this);

        EnchantCrystalsUtils.sendConsole(PREFIX + "&bLoaded Events &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void initCommands() {
        final long STARTED = System.currentTimeMillis();
        final PluginCommand enchantcrystals = getCommand("enchantcrystals");

        enchantcrystals.setExecutor(new EnchantCrystalsCommand());
        enchantcrystals.setTabCompleter(new TabCompletion());

        EnchantCrystalsUtils.sendConsole(PREFIX + "&bLoaded Commands &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public static EnchantCrystals getPlugin() {
        return plugin;
    }

    public NMSBase getNMS() {
        return nms;
    }
}
