package com.github.NeRdTheNed.Punch2Prime.Mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(BlockTNT.class)
public abstract class TntBlockMixin extends Block {

	// Dummy constructor, I'm not sure if this is how it's supposed to be done or if I'm bad at Mixins.
	public TntBlockMixin(Material mat) {
		super(mat);
	}

	@Inject(method = "<init>()V", at = @At("RETURN"))
	private void init(CallbackInfo a) {
		setDefaultState(getDefaultState().withProperty(BlockTNT.EXPLODE, true));
	}

	// Mixin hacks :/ Overrides used to fix the vanilla bug of a TNT item dropping when the TNT is activated by punching it.
	// I've attempted to retain "compatibility" with other mods by checking if the block is specifically a TNT block, so any other blocks extending the TNT block shouldn't be affected.
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (state.getBlock() == Blocks.TNT) && state.getValue(BlockTNT.EXPLODE) ? Items.AIR : super.getItemDropped(state, rand, fortune);
	}

	// More Mixin hacks: always place TNT blocks with the EXPLODE state set to true
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(meta).withProperty(BlockTNT.EXPLODE, true);
	}
}
