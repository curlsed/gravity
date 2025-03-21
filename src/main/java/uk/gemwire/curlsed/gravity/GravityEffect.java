package uk.gemwire.curlsed.gravity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(modid = Gravity.MODID)
public class GravityEffect extends MobEffect {
    public GravityEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFFFF00FF);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        return true;
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        EntityDimensions dim = livingEntity.getDimensions(livingEntity.getPose());
        var diff = dim.height() - dim.eyeHeight();
        livingEntity.eyeHeight = 0 + diff;
    }

    @SubscribeEvent
    public static void effectExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance().is(Gravity.GRAVITY_EFFECT)) {
            LivingEntity livingEntity = event.getEntity();
            EntityDimensions dim = livingEntity.getDimensions(livingEntity.getPose());
            livingEntity.eyeHeight = dim.eyeHeight();
        }
    }
    @SubscribeEvent
    public static void effectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance().is(Gravity.GRAVITY_EFFECT)) {
            event.setCanceled(true);
        }
    }

}
