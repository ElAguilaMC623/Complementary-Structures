package net.elaguilamc623.complementary_structures.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class KingBladeTier {

    // Implementa una tier llamada KING_BLADE con 10.5 de daño, 1.5 de velocidad, 20 de encantabilidad y otras cosas.
    public enum ModTiers implements Tier {
        KING_BLADE(4, 2561, 9.5F, 1.5F, 20, Ingredient.of(Items.NETHERITE_INGOT));

        private final int level;
        private final int uses;
        private final float damage;
        private final float speed;
        private final int enchantment;
        private final Ingredient repair;

        ModTiers(int level, int uses, float damage, float speed, int enchantment, Ingredient repair) {
            this.level = level;
            this.uses = uses;
            this.damage = damage;
            this.speed = speed;
            this.enchantment = enchantment;
            this.repair = repair;
        }

        @Override public int getUses() { return uses; }
        @Override public float getSpeed() { return speed; }
        @Override public float getAttackDamageBonus() { return damage; }
        @Override public int getLevel() { return level; }
        @Override public int getEnchantmentValue() { return enchantment; }
        @Override public Ingredient getRepairIngredient() { return repair; }
    }
}
