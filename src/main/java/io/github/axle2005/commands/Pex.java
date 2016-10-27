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
			String command = "permissions";

			String[] args = arguments.getOne("remaining").toString().split(" ");

			// Create substrings to remove excess characters added. (9)
			args[0] = args[0].substring(9);
			args[args.length - 1] = args[args.length - 1].substring(0, args[args.length - 1].length() - 1);
			if (args[0].equals("user") || args[0].equals("group")) {
				if (args.length == 5 && args[2].equals("group")) {
					name = args[1];
					group = args[4];
					{
						if (args[3].equals("add") || args[3].equals("remove")) {
							command.concat(" " + args[0] + " " + name + " parent " + args[3] + " " + group);

						}
					}
				}
				if (args.length == 4 && (args[2].equals("add") || args[2].equals("remove"))) {
					name = args[1];
					{
						if (args[2].equals("add")) {
							command.concat(" " + args[0] + " " + name + " permission " + args[3] + " true");

						} else if (args[2].equals("remove")) {
							command.concat(" " + args[0] + " " + name + " permission " + args[3] + " false");

						}
					}
				}
			} else {
				String array = "";
				command = "permissions";
				for (String e : args) {
					array.concat(" " + e);
				}
				command.concat(array);
			}

			plugin.getLogger().info(command);
			CommandResult r = Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
			return r;

		}

	}
}
