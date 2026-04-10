package net.elaguilamc623.complementary_structures.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CSPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, "complementary_structures");

    public static final RegistryObject<Potion> SWIFTNESS_III = POTIONS.register("swiftness_iii", () ->
            new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 260, 2)) // Speed III
    );

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
