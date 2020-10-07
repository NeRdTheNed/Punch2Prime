package com.github.NeRdTheNed.Punch2Prime;

import net.minecraft.block.TntBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin {
	@ModifyArg(
		method = "<init>",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/state/State;with(Lnet/minecraft/state/property/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"),
		index = 1
	)
	private Comparable modifyDefaultValue(Property property, Comparable defaultValue) {
		return property == TntBlock.UNSTABLE ? true : defaultValue;
	}
}
