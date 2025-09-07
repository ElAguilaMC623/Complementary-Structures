package net.elaguilamc623.complementary_structures.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;

public class ObsidianSwordItem extends SwordItem {
    public ObsidianSwordItem(Item.Properties properties) {
        super(Tiers.NETHERITE, 3, -2.4F, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));

        if (target.getType() == EntityType.ENDERMAN) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
        }

        if (target.getType() == EntityType.ENDERMAN ||
                target.getType() == EntityType.ENDERMITE ||
                target.getType() == EntityType.SHULKER) {
            if (attacker instanceof Player player) {
                target.hurt(DamageSource.playerAttack(player), 2.5F);
            }
        }

        stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}