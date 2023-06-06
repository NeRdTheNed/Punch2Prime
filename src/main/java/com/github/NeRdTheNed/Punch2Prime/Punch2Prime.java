package com.github.NeRdTheNed.Punch2Prime;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Punch2Prime.MOD_ID, version = "1.0.2", acceptableRemoteVersions = "*")
public class Punch2Prime {
	public static final String MOD_ID = "punch2prime";

	public static Logger modLog;

	public static boolean isMixinsLoaded = false;

	@EventHandler
	public final void init(FMLPreInitializationEvent event) {
		modLog = event.getModLog();

		if (!isMixinsLoaded) {
			modLog.warn("Punch 2 Prime mixins were not loaded, TNT will likely not be punchable.");
		} else {
			modLog.info("Newly placed TNT will now be punchable!");
		}
	}
}
