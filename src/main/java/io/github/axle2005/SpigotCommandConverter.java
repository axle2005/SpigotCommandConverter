package io.github.axle2005;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import io.github.axle2005.commands.Register;



@Plugin(id = "spigotcommandconverter", name = "SpigotCommandConverter", version = "0.0.1")
public class SpigotCommandConverter {


	@Inject
	private Logger log;
	
	@Listener
	public void initialization(GameStartingServerEvent event) {
		//Still needs economy
		
		new Register(this);
	}
	
	public Logger getLogger() {
		return log;
	}
}
