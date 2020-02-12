package me.puyodead.cosmiccrystals.Commands;

import me.puyodead.cosmiccrystals.CosmicCrystals;
import me.puyodead.cosmiccrystals.CosmicCrystalsUtils;
import me.puyodead.cosmiccrystals.Crystal;
import me.puyodead.cosmiccrystals.CrystalType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCrystalCommand implements CommandExecutor {

    private final CosmicCrystals cosmicCrystals = CosmicCrystals.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player player = sender instanceof Player ? (Player)sender : null;
        if(player != null) {
            // player is not null
            if (player.hasPermission("cosmiccrystals.givecrystal")) {
                final int l = args.length;
                if (l == 0) {
                    // TODO: GUI?
                    CosmicCrystalsUtils.sendPlayer(player, "&6CosmicCrystals v" + CosmicCrystals.plugin.getDescription().getVersion() + " by Puyodead1");
                    CosmicCrystalsUtils.sendPlayer(player, "&6=============================");
                    CosmicCrystalsUtils.sendPlayer(player, "&7/gc <enchant name> [amount] [level] [player]");
                    CosmicCrystalsUtils.sendPlayer(player, "&7If an amount is not specified, one is default");
                    CosmicCrystalsUtils.sendPlayer(player, "&7If a level is not specified, max is default");
                    CosmicCrystalsUtils.sendPlayer(player, "&7If a player is not specified, current player is default");
                    return false;
                } else if (l == 1) {
                    // player specified one argument, aka a crystal name
                    final String crystalName = args[0].toUpperCase();
                    final CrystalType crystalType = CrystalType.valueOf(crystalName);
                    if (crystalType != null) {
                        // crystal name is valid
                        player.getInventory().addItem(new Crystal(crystalType, crystalType.getCrystalMaxLevel()).getCrystalItem());
                        return true;
                    } else {
                        // invalid crystal name
                        CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid crystal name"));
                        return false;
                    }
                } else if (l == 2) {
                    //player specified crystal name and amount
                    final String crystalName = args[0].toUpperCase();
                    final int crystalAmount = Integer.parseInt(args[1]);
                    final CrystalType crystalType = CrystalType.valueOf(crystalName);
                    if (crystalType != null) {
                        // crystal name is valid
                        final ItemStack crystalItem = new Crystal(crystalType, crystalType.getCrystalMaxLevel()).getCrystalItem();
                        crystalItem.setAmount(crystalAmount);
                        player.getInventory().addItem(crystalItem);
                        return true;
                    } else {
                        // invalid crystal name
                        CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid crystal name"));
                        return false;
                    }
                } else if (l == 3) {
                    // player specified crystal name, amount, and the player to give it to
                    final String crystalName = args[0].toUpperCase();
                    final int crystalAmount = Integer.parseInt(args[1]);
                    final Player p = Bukkit.getServer().getPlayer(args[2]);

                    if (p != null) {
                        final CrystalType crystalType = CrystalType.valueOf(crystalName);
                        if (crystalType != null) {
                            // crystal name is valid
                            final ItemStack crystalItem = new Crystal(crystalType, crystalType.getCrystalMaxLevel()).getCrystalItem();
                            crystalItem.setAmount(crystalAmount);
                            p.getInventory().addItem(crystalItem);
                            return true;
                        } else {
                            // invalid crystal name
                            CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid crystal name"));
                            return false;
                        }
                    } else {
                        CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid player specified"));
                    }
                    return true;
                } else if (l == 4) {
                    // player specified crystal name, amount, the player to give it to, and the crystal level
                    final String crystalName = args[0].toUpperCase();
                    final int crystalAmount = Integer.parseInt(args[1]);
                    final int crystalLevel = Integer.parseInt(args[2]);
                    final Player p = Bukkit.getServer().getPlayer(args[3]);

                    if (p != null) {
                        final CrystalType crystalType = CrystalType.valueOf(crystalName);
                        if (crystalType != null) {
                            // crystal name is valid
                            final ItemStack crystalItem = new Crystal(crystalType, crystalLevel).getCrystalItem();
                            crystalItem.setAmount(crystalAmount);
                            p.getInventory().addItem(crystalItem);
                            return true;
                        } else {
                            // invalid crystal name
                            CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid crystal name"));
                            return false;
                        }
                    } else {
                        CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.invalid player specified"));
                        return false;
                    }
                }
            } else {
                CosmicCrystalsUtils.sendPlayer(player, CosmicCrystals.plugin.getConfig().getString("messages.no permission"));
                return false;
            }
        }
        return false;
    }
}
