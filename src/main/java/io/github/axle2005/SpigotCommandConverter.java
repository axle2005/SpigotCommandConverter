package io.github.axle2005;

import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;
import com.google.inject.Inject;

import io.github.axle2005.commands.Pex;
import io.github.axle2005.commands.Register;

@Plugin(id = "spigotcommandconverter", name = "SpigotCommandConverter", version = "0.0.1")
public class SpigotCommandConverter {

	@Inject
	private Logger log;

	@Listener
	public void initialization(GameStartingServerEvent event) {
		// Still needs economy

		new Register(this);
	}

	// spare code.
	// @Listener(order=Order.FIRST)
	public void onCommandSend(SendCommandEvent event) {
		String command = event.getCommand();
		String arguments = event.getArguments();

		if (command.equals("pex")) {
			event.setCancelled(true);
			Optional<Player> player = event.getCause().first(Player.class);
			new Pex(this, command, arguments, player);
		}

	}

	public Logger getLogger() {
		return log;
	}
}
