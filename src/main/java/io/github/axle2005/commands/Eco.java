package io.github.axle2005.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.axle2005.SpigotCommandConverter;

public class Eco implements CommandExecutor {
	
	
	SpigotCommandConverter plugin;
	public Eco(SpigotCommandConverter plugin) {
		this.plugin = plugin;
	}

	// eco give $2 20000
	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {
		String command = "adminpay ";

		if ((src instanceof Player) && !src.hasPermission("totaleconomy.command.adminpay")) {
			src.sendMessage(Text.of("You do not have permission to use this command"));
			return CommandResult.empty();
		} else {
			String[] args = arguments.getOne("<give><player><amount>").toString().split(" ");
			args[0] = args[0].substring(9);
			args[args.length - 1] = args[args.length - 1].substring(0, args[args.length - 1].length() - 1);
			if (args.length == 3) {
				if (args[0].equals("give")) {
					command = "adminpay " + args[1]+" " + args[2];
				} else if (args[0].equals("take")) {
					command = "adminpay " + args[1] + " -" + args[2];
				} else {
					src.sendMessage(Text.of("eco <give><player><amount>"));
					return CommandResult.empty();
				}
				CommandResult r = Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
				return r;
			} else {
				src.sendMessage(Text.of("eco <give><player><amount>"));
				return CommandResult.empty();
			}

		}

	}

}
