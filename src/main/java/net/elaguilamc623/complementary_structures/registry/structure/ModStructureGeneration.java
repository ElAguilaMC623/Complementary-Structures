package net.elaguilamc623.complementary_structures.registry.structure;

import net.elaguilamc623.complementary_structures.structures.AdvancedStructureGenerationChecker;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModStructureGeneration {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "complementary_structures");

    public static final RegistryObject<StructureType<AdvancedStructureGenerationChecker>> ADVANCED_STRUCTURE =
            STRUCTURES.register("advanced_jigsaw_generation_checker", () -> () -> AdvancedStructureGenerationChecker.CODEC.codec());

    public static void register(IEventBus bus) {
        STRUCTURES.register(bus);
    }
}