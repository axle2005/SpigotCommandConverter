package io.github.axle2005.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.google.common.base.Optional;

import io.github.axle2005.SpigotCommandConverter;

public class Pex implements CommandExecutor {

	SpigotCommandConverter plugin;

	public Pex(SpigotCommandConverter plugin) {
		this.plugin = plugin;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {
		if ((src instanceof Player) && !src.hasPermission("scc.pex.user")) {
			src.sendMessage(Text.of("You do not have permission to use this command"));
			return CommandResult.empty();
		} else {
			String name = "";
			String group = "";
			String command ="";
			
			String[] args = arguments.getOne("remaining").toString().split(" ");
			
			//Create substrings to remove excess characters added. (9)
			args[0] = args[0].substring(3);
			args[args.length-1] = args[args.length-1].substring(0, args[args.length-1].length()-1);
			
			
			if (args.length == 5 && args[2].equals("group")) {
				name = args[1];
				group = args[4];
				{
					if (args[3].equals("add") || args[3].equals("remove")) {
						command = "permissions user " + name + " parent " + args[3] + " " + group;
						
					}
				}
			}
			plugin.getLogger().info(command);
			CommandResult r = Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
			return r;

		}

	}
}
