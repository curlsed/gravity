package uk.gemwire.curlsed.gravity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import uk.gemwire.curlsed.gravity.Gravity;

@EventBusSubscriber(modid = Gravity.MODID, value = Dist.CLIENT)
public class GravityRendering {

    @SubscribeEvent
    public static void renderLivingPre(RenderLivingEvent.Pre<?,?> event) {
        if (!event.getEntity().hasEffect(Gravity.GRAVITY_EFFECT)) return;
        PoseStack stack = event.getPoseStack();
        LivingEntity entity = event.getEntity();
        stack.pushPose();
        stack.translate(0.0F, (entity.getBbHeight() + 0.1F) / entity.getScale(), 0.0F);
        stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
    }

    @SubscribeEvent
    public static void renderLivingPost(RenderLivingEvent.Post<?,?> event) {
        if (!event.getEntity().hasEffect(Gravity.GRAVITY_EFFECT)) return;
        event.getPoseStack().popPose();
    }

}
