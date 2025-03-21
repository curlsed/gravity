package uk.gemwire.curlsed.gravity.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import uk.gemwire.curlsed.gravity.Gravity;

@EventBusSubscriber(modid = Gravity.MODID, value = Dist.CLIENT)
public class GravityPlayer {

    @SubscribeEvent
    public static final void viewportEvent(ViewportEvent.ComputeCameraAngles event) {
        Player player = Minecraft.getInstance().player;
        if (!player.hasEffect(Gravity.GRAVITY_EFFECT)) return;

        event.setRoll(180);
    }

    @SubscribeEvent
    public static final void frameEvent(RenderLevelStageEvent event) {
    }
}
