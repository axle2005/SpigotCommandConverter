package io.github.axle2005.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.text.Text;
import io.github.axle2005.SpigotCommandConverter;

public class Pex2 implements CommandExecutor {

	SpigotCommandConverter plugin;

	public Pex2(SpigotCommandConverter plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {
		if ((src instanceof Player) && !src.hasPermission("permissionsex")) {
			src.sendMessage(Text.of("You do not have permission to use this command"));
			return CommandResult.empty();
		} else {
			String name = "";
			String group = "";
			String command = "permissions";

			String[] args = arguments.getOne("remaining").toString().split(" ");
			
			// Create substrings to remove excess characters added. (9)
			if (args[0].equals("Optional[user") || args[0].equals("Optional[group")) {
				args[0] = args[0].substring(9);
				args[args.length - 1] = args[args.length - 1].substring(0, args[args.length - 1].length() - 1);
			}
			
			for (int i=0;i<=args.length-1;i++) {
				plugin.getLogger().info(args[i]);
			}
			if ((args[0].equals("user") || args[0].equals("group")) && !args[2].equals("parent") && !args[2].equals("options")) {
				if (args.length == 5 && args[2].equals("group")) {
					name = args[1];
					group = args[4];
					{
						if (args[3].equals("add") || args[3].equals("remove")) {
							command = "permissions " + args[0] + " " + name + " parent " + args[3] + " " + group;

						}
					}
				}
				if (args.length == 4 && (args[2].equals("add") || args[2].equals("remove"))) {
					name = args[1];
					{
						if (args[2].equals("add")) {
							command = "permissions " + args[0] + " " + name + " permission " + args[3] + " true";

						} else if (args[2].equals("remove")) {
							command = "permissions " + args[0] + " " + name + " permission " + args[3] + " false";

						}
					}
				}
			} else {
				String array = "";
				command = "permissions";
				for (int i=0;i<=args.length-1;i++) {
					array = array.concat(" " + args[i]);
				}
				command = command.concat(array);
			}

			plugin.getLogger().info(command);
			CommandResult r = Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
			return r;

		}

	}
}
