package com.treetrain1.evilshriekers.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkBlock.class)
public class SculkBlockMixin {

	@Inject(method = "getRandomGrowthState", at = @At("TAIL"), cancellable = true)
	private void getRandomGrowthState(LevelAccessor world, BlockPos pos, RandomSource random, boolean randomize, CallbackInfoReturnable<BlockState> cir) {
		BlockState blockState;
		if (random.nextInt(11) == 0) {
			blockState = Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(SculkShriekerBlock.CAN_SUMMON, Boolean.valueOf(true));
		} else {
			blockState = Blocks.SCULK_SENSOR.defaultBlockState();
		}

		cir.setReturnValue(blockState.hasProperty(BlockStateProperties.WATERLOGGED) && !world.getFluidState(pos).isEmpty()
				? blockState.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true))
				: blockState);
	}
}
