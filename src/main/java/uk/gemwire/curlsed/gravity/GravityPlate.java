package uk.gemwire.curlsed.gravity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GravityPlate extends Block {
    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 0, 0, 0);
    public GravityPlate() {
        super(Properties.of());
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            MobEffectInstance gravity = new MobEffectInstance(Gravity.GRAVITY_EFFECT, 2, 0, true, false);
            livingEntity.addEffect(gravity);
        }
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BOTTOM_AABB;
    }
}
