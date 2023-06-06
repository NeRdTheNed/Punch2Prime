package com.github.NeRdTheNed.Punch2Prime;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class Connector implements IMixinConnector {

	@Override
	public void connect() {
		Mixins.addConfiguration("punch2prime.mixins.json");
		Punch2Prime.isMixinsLoaded = true;
	}

}
