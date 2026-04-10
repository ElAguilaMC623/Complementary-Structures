package net.elaguilamc623.complementary_structures.item.tiers;

import net.elaguilamc623.complementary_core.utils.tiers.TierBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;

public class CSTiers {

    public static final Tier KING_BLADE = TierBuilder.create("king_blade")
            .level(4)
            .durability(2561)
            .speed(1.5F)
            .attackDamage(9.5F)
            .enchantability(20)
            // .incorrectBlocks(BlockTags.NEEDS_DIAMOND_TOOL)
            .repairItem(() -> Items.NETHERITE_INGOT)
            .build();
}
