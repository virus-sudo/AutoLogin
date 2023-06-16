package net.virus;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;

import net.minecraft.client.network.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class ExampleMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("autologin");
	public static boolean login = true;
	public static MinecraftClient MC = MinecraftClient.getInstance();
	public static ModConfig config = ModConfig.getInstance();
	String text = config.getText();
	List<String> ipAddress = config.getServerIPs();

	@Override
	public void onInitialize() {
		WorldRenderEvents.LAST.register((info) -> {
			if (MC.getCurrentServerEntry() != null && login && ipAddress.contains(MC.getCurrentServerEntry().address)) {
				MC.player.sendCommand(text);
				login = false;
			}
		});
	}
}