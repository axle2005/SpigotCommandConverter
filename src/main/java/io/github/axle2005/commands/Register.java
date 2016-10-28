package io.github.axle2005.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import io.github.axle2005.SpigotCommandConverter;

public class Register {

	public Register(SpigotCommandConverter plugin) {
		CommandSpec pex = CommandSpec.builder().permission("permissionsex").description(Text.of("Pex user commands"))
				.arguments(
						// GenericArguments.onlyOne(GenericArguments.string(Text.of("group|user"))),
						GenericArguments.remainingJoinedStrings(Text.of("remaining")))
				.executor(new Pex2(plugin)).build();
		
		CommandSpec eco = CommandSpec.builder().permission("totaleconomy.command.adminpay").description(Text.of("Spigot Eco Command"))
				.arguments(
						// GenericArguments.onlyOne(GenericArguments.string(Text.of("group|user"))),
						GenericArguments.remainingJoinedStrings(Text.of("<give><player><amount>")))
				.executor(new Eco(plugin)).build();

		Sponge.getCommandManager().register(plugin, pex, "scc", "pex");
		Sponge.getCommandManager().register(plugin, eco, "eco");

	}
}
