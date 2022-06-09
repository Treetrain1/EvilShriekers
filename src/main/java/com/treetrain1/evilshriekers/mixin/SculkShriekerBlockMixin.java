package com.treetrain1.evilshriekers.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkShriekerBlock.class)
public class SculkShriekerBlockMixin {

	@Shadow
	@Final
	public static BooleanProperty CAN_SUMMON;

	@Shadow
	@Final
	public static BooleanProperty WATERLOGGED;

	@Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
	public void getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
		SculkShriekerBlock shrieker = SculkShriekerBlock.class.cast(this);
		cir.setReturnValue(shrieker.defaultBlockState().setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER).setValue(CAN_SUMMON, true));
	}
}
