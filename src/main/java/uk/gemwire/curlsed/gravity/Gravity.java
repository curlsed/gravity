package uk.gemwire.curlsed.gravity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Gravity.MODID)
public class Gravity
{
    public static final String MODID = "gravity";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);

    public static final DeferredBlock<Block> GRAVITY_PLATE = BLOCKS.register("gravity_plate", GravityPlate::new);
    public static final DeferredItem<BlockItem> GRAVITY_PLATE_ITEM = ITEMS.registerSimpleBlockItem("gravity_plate", GRAVITY_PLATE);

    public static final DeferredHolder<MobEffect, GravityEffect> GRAVITY_EFFECT = EFFECTS.register("gravity_change", GravityEffect::new);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GRAVITY_TAB = CREATIVE_MODE_TABS.register("gravity_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gravityItems"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> GRAVITY_PLATE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GRAVITY_PLATE_ITEM.get());
            }).build());

    public Gravity(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        EFFECTS.register(modEventBus);

    }
}
