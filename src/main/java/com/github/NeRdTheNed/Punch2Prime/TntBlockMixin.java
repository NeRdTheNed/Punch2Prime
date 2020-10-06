package com.github.NeRdTheNed.Punch2Prime;

import net.minecraft.block.Block;
import net.minecraft.block.TntBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin extends Block {

	//Dummy constructor, I'm not sure if this is how it's supposed to be done or if I'm bad at Mixins.
	public TntBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(CallbackInfo info) {
		setDefaultState(getDefaultState().with(TntBlock.UNSTABLE, true));
		System.out.println("Punch 2 Prime: Newly placed TNT will now be punchable!");
	}
}
