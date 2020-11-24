package com.github.NeRdTheNed.Punch2Prime;

import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.TntBlock;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin extends Block {

	// Dummy constructor, I'm not sure if this is how it's supposed to be done or if I'm bad at Mixins.
	public TntBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(CallbackInfo a) {
		setDefaultState(getDefaultState().with(TntBlock.UNSTABLE, true));
		LogManager.getLogger("Punch 2 Prime").info("Newly placed TNT will now be punchable!");
	}
}
