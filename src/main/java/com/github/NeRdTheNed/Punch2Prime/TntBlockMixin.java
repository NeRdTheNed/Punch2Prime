package com.github.NeRdTheNed.Punch2Prime;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin extends Block {

	// Dummy constructor, I'm not sure if this is how it's supposed to be done or if I'm bad at Mixins.
	public TntBlockMixin() {
		super(null);
	}

	// Fabric hacks :/ Overrides used to fix the vanilla bug of a TNT item dropping when the TNT is activated by punching it.
	// I've attempted to retain "compatibility" with other mods by checking if the block is specifically a TNT block, so any other blocks extending the TNT block shouldn't be affected.
	@Override
	public void harvest(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity be) {
		if (!state.getBlock().isEqualTo(Blocks.TNT)) {
			super.harvest(world, player, pos, state, be);
			return;
		}

		player.incrementStat(Stats.BLOCK_STATS[getIdByBlock(this)]);
		player.addExhaustion(0.025F);
	}

	/**
	 * Doesn't work, no clue why :(
	 *
	 * <pre>
	 * {@literal @}Inject(method = "<init>", at {@literal @}At("TAIL"))
	 * private void init(CallbackInfo a) {
	 * 	setDefaultState(stateManager.getDefaultState().with(TntBlock.EXPLODE, true));
	 * 	LogManager.getLogger("Punch 2 Prime").info("Newly placed TNT will now be punchable!");
	 * }
	 * </pre>
	 */

	// Hack to make the TNT block always explode when punched, as setting it as the default state doesn't work.
	// I've attempted to retain "compatibility" with other mods by checking if the block is specifically a TNT block, so any other blocks extending the TNT block shouldn't be affected.
	@ModifyArg(method = "onBreakByPlayer(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TntBlock;method_1034(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/LivingEntity;)V"))
	private BlockState method_1034(BlockState state) {
		if (state.getBlock().isEqualTo(Blocks.TNT)) {
			return state.with(TntBlock.EXPLODE, true);
		}

		return state;
	}
}
