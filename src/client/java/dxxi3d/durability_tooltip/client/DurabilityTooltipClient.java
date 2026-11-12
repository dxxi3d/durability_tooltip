package dxxi3d.durability_tooltip.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurabilityTooltipClient implements ClientModInitializer {

	private static final String MOD_NAME = "Durability Tooltip";
	private static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitializeClient() {
		LOGGER.info("[{}] mod loaded!", MOD_NAME);
	}
}