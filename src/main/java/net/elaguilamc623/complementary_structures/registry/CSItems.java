package net.elaguilamc623.complementary_structures.registry;

import net.elaguilamc623.complementary_core.utils.weapons.ToolSetBuilder;
import net.elaguilamc623.complementary_structures.ComplementaryStructures;
import net.elaguilamc623.complementary_structures.item.custom.*;
import net.elaguilamc623.complementary_structures.item.tiers.CSTiers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class CSItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ComplementaryStructures.MOD_ID);

    public static final RegistryObject<Item> PRISMARINE_INGOT = ITEMS.register("prismarine_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KING_BLADE = ITEMS.register("king_blade", () ->
            new SwordItem(CSTiers.KING_BLADE, 0, -2.5F,
                    new Item.Properties().fireResistant()
            ) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 140, 1));
                    target.setSecondsOnFire(3);
                    stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    return true;
                }
            }
    );

    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () ->
            new ObsidianSwordItem(new Item.Properties().fireResistant())
    );

    public static final ToolSetBuilder PRISMARINE =
            ToolSetBuilder.create("prismarine", ITEMS, Tiers.DIAMOND)
                    .customSword((tier, props) -> new PrismarineSwordItem(props))
                    .customAxe((tier, props) -> new PrismarineAxeItem())
                    .customPickaxe((tier, props) -> new PrismarinePickaxeItem())
                    .customShovel((tier, props) -> new PrismarineShovelItem())
                    .customHoe((tier, props) -> new PrismarineHoeItem());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        PRISMARINE.register();
    }
}