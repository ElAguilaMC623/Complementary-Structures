package net.elaguilamc623.complementary_structures.structures.pieces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import java.util.Optional;

public class ComplementJigsawAssembler {
    public static Optional<Structure.GenerationStub> assemble(
            Structure.GenerationContext context,
            Holder<StructureTemplatePool> startPool,
            int size,
            BlockPos pos,
            StructureType<?> structureType
    ) {
        return JigsawPlacement.addPieces(
                context,
                startPool,
                Optional.empty(),
                size,
                pos,
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                size
        );
    }
}