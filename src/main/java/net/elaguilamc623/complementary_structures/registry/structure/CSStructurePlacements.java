package net.elaguilamc623.complementary_structures.registry.structure;

import net.elaguilamc623.complementary_structures.structures.placement.AdvancedStructurePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class CSStructurePlacements {
    public static final DeferredRegister<StructurePlacementType<?>> PLACEMENTS =
            DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, "complementary_structures");

    public static final RegistryObject<StructurePlacementType<AdvancedStructurePlacement>> ADVANCED_PLACEMENT =
            PLACEMENTS.register("advanced_structure_placement", () -> () -> AdvancedStructurePlacement.CODEC);

    public static void register(IEventBus bus) {
        PLACEMENTS.register(bus);
    }
}