package net.elaguilamc623.complementary_structures.item;

import net.elaguilamc623.complementary_structures.Complementary_Structures;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Esta clase registra todos los items del mod
// Esto dice que estos items se registren con DeferredRegister en ITEMS, y crea el ForgeRegistry de ITEMS
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Complementary_Structures.MOD_ID);

    // Registra Prismarine Ingot
    // El RegistryObject registra un objeto el cual es un item debido a <Item>, llamado PRISMARINE_INGOT en el registro, y de id es de prismarine_ingot
    // La segunda linea le añade una propiedad que hace que este en la tab del creativo de MISC.
    public static final RegistryObject<Item> PRISMARINE_INGOT = ITEMS.register("prismarine_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    // Registra Prismarine Sword
    // Esto simplemente lo registra, pero no añade propiedades de ningún tipo, eso lo deja a PrismarineSwordModifier
    // Y esto pasa con el resto de registros de esta Java Class
    public static final RegistryObject<Item> PRISMARINE_SWORD = ITEMS.register("prismarine_sword",
            () -> new PrismarineSwordModifier());

    // Registra Prismarine Axe
    public static final RegistryObject<Item> PRISMARINE_AXE = ITEMS.register("prismarine_axe",
            () -> new PrismarineAxeModifier());

    // Registra Prismarine Pickaxe
    public static final RegistryObject<Item> PRISMARINE_PICKAXE = ITEMS.register("prismarine_pickaxe",
            () -> new PrismarinePickaxeModifier());

    // Registra Prismarine Shovel
    public static final RegistryObject<Item> PRISMARINE_SHOVEL = ITEMS.register("prismarine_shovel",
            () -> new PrismarineShovelModifier());

    // Registra Prismarine Hoe
    public static final RegistryObject<Item> PRISMARINE_HOE = ITEMS.register("prismarine_hoe",
            () -> new PrismarineHoeModifier());

    // Registra King Blade con una custom tier, la cual se encuentra en KingBladeTier
    public static final RegistryObject<Item> KING_BLADE = ITEMS.register("king_blade", () ->
            new SwordItem(
                    KingBladeTier.ModTiers.KING_BLADE,
                    0, -2.5F,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()
            ) {
        // Añade un efecto de Wither y Slowness al golpear a un mob además de prenderle en fuego durante 3 segundos.
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

    // Registra la Obsidian Sword, metiéndola en la categoria Combat del creativo, y las propiedades son definidas en ObsidianSwordItem
    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () ->
            new ObsidianSwordItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}