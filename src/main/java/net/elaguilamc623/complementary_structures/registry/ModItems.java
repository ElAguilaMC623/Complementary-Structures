package net.elaguilamc623.complementary_structures.registry;

import net.elaguilamc623.complementary_structures.Complementary_Structures;
import net.elaguilamc623.complementary_structures.item.*;
import net.elaguilamc623.complementary_structures.item.properties.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Complementary_Structures.MOD_ID);


    public static final RegistryObject<Item> PRISMARINE_INGOT = ITEMS.register("prismarine_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PRISMARINE_SWORD = ITEMS.register("prismarine_sword",
            () -> new PrismarineSwordProperties(new Item.Properties()));

    public static final RegistryObject<Item> PRISMARINE_AXE = ITEMS.register("prismarine_axe",
            () -> new PrismarineAxeProperties());

    public static final RegistryObject<Item> PRISMARINE_PICKAXE = ITEMS.register("prismarine_pickaxe",
            () -> new PrismarinePickaxeProperties());

    public static final RegistryObject<Item> PRISMARINE_SHOVEL = ITEMS.register("prismarine_shovel",
            () -> new PrismarineShovelProperties());

    public static final RegistryObject<Item> PRISMARINE_HOE = ITEMS.register("prismarine_hoe",
            () -> new PrismarineHoeProperties());

    public static final RegistryObject<Item> KING_BLADE = ITEMS.register("king_blade", () ->
            new SwordItem(KingBladeTier.ModTiers.KING_BLADE, 0, -2.5F,
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
            new ObsidianSwordProperties(new Item.Properties().fireResistant())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}