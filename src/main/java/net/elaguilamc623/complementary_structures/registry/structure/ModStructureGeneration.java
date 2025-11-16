package net.elaguilamc623.complementary_structures.registry.structure;

import net.elaguilamc623.complementary_structures.structures.JigsawStructuresGenerationChecker;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModStructureGeneration {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "complementary_structures");

    public static final RegistryObject<StructureType<JigsawStructuresGenerationChecker>> ADVANCED_STRUCTURE =
            STRUCTURES.register("jigsaw_generation_checker", () -> () -> JigsawStructuresGenerationChecker.CODEC.codec());

    public static void register(IEventBus bus) {
        STRUCTURES.register(bus);
    }
}