package net.elaguilamc623.complementary_structures.item.properties;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class PrismarineSwordModifier extends SwordItem {
    public PrismarineSwordModifier(Item.Properties properties) {
        super(Tiers.DIAMOND, 3, -2.4f, properties);
    }

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