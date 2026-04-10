package net.elaguilamc623.complementary_structures.registry.structure;

import net.elaguilamc623.complementary_structures.structures.worldgen.JigsawGenerationChecker;
import net.elaguilamc623.complementary_structures.structures.worldgen.JigsawNetherGenerationChecker;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class CSStructureGeneration {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "complementary_structures");

    public static final RegistryObject<StructureType<JigsawGenerationChecker>> JIGSAW_STRUCTURE_GENERATION =
            STRUCTURES.register("jigsaw_generation_checker", () -> () -> JigsawGenerationChecker.CODEC.codec());

    public static final RegistryObject<StructureType<JigsawNetherGenerationChecker>> JIGSAW_NETHER_STRUCTURE_GENERATION =
            STRUCTURES.register("jigsaw_nether_generation_checker", () -> () -> JigsawNetherGenerationChecker.CODEC.codec());

    public static void register(IEventBus bus) {
        STRUCTURES.register(bus);
    }
}