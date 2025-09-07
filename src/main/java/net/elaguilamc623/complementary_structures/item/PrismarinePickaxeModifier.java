package net.elaguilamc623.complementary_structures.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

// Esto le da al objeto 2 propiedades, ser de Diamond Tier y estar en la tab del creativo de tools.
public class PrismarinePickaxeModifier extends PickaxeItem {
    public PrismarinePickaxeModifier() {
        super(Tiers.DIAMOND, 1, -2.8f, new Properties().tab(CreativeModeTab.TAB_TOOLS));
    }

    // Detecta si el jugador esta en agua
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide && selected && entity instanceof LivingEntity living) {
            // Respiración Aquatica
            living.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 0, true, false));

            // Haste II
            if (living.isInWater()) {
                living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 1, true, false));
            }
        }
    }
}