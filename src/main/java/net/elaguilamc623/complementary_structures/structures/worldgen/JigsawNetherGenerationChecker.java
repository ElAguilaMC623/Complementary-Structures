package net.elaguilamc623.complementary_structures.structures.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elaguilamc623.complementary_structures.registry.structure.CSStructureGeneration;
import net.elaguilamc623.complementary_structures.structures.pieces.ComplementJigsawAssembler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class JigsawNetherGenerationChecker extends JigsawGenerationChecker {

    public static final MapCodec<JigsawNetherGenerationChecker> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool")
                            .forGetter(s -> s.startPool),
                    com.mojang.serialization.Codec.intRange(0, 30)
                            .fieldOf("size").forGetter(s -> s.size),
                    com.mojang.serialization.Codec.BOOL.fieldOf("cannot_spawn_in_liquid")
                            .orElse(false).forGetter(s -> s.cannotSpawnInLiquid),
                    com.mojang.serialization.Codec.intRange(1, 100)
                            .optionalFieldOf("terrain_height_check_radius")
                            .forGetter(s -> s.terrainHeightCheckRadius),
                    com.mojang.serialization.Codec.intRange(1, 1000)
                            .optionalFieldOf("allowed_terrain_height_range")
                            .forGetter(s -> s.allowedTerrainHeightRange)
            ).apply(instance, JigsawNetherGenerationChecker::new));

    public JigsawNetherGenerationChecker(StructureSettings settings,
                                         Holder<StructureTemplatePool> startPool,
                                         int size,
                                         boolean cannotSpawnInLiquid,
                                         Optional<Integer> terrainHeightCheckRadius,
                                         Optional<Integer> allowedTerrainHeightRange) {
        super(settings, startPool, size, cannotSpawnInLiquid, terrainHeightCheckRadius, allowedTerrainHeightRange);
    }

    @Override
    protected boolean spawningChecks(GenerationContext context, BlockPos blockPos) {
        NoiseColumn column = context.chunkGenerator().getBaseColumn(
                blockPos.getX(), blockPos.getZ(),
                context.heightAccessor(),
                context.randomState()
        );

        int minY = context.chunkGenerator().getMinY();
        int maxY = minY + context.chunkGenerator().getGenDepth();

        int bestHeight = -1;
        BlockPos bestPos = null;

        boolean inAir = false;
        int startY = -1;

        for (int y = minY; y <= maxY; y++) {
            BlockState state = column.getBlock(y);

            if (!inAir && state.isAir()) {
                inAir = true;
                startY = y;

            } else if (inAir && !state.isAir()) {
                int airHeight = y - startY;
                int candidateY = startY;

                if (candidateY > 100) {
                    inAir = false;
                    continue;
                }

                if (airHeight > bestHeight) {
                    bestHeight = airHeight;
                    bestPos = new BlockPos(blockPos.getX(), candidateY, blockPos.getZ());
                }
                inAir = false;
            }
        }

        if (bestPos == null) {
            return false;
        }

        return true;
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        BlockPos pos = new BlockPos(context.chunkPos().getMinBlockX(),
                context.chunkGenerator().getMinY(),
                context.chunkPos().getMinBlockZ());

        if (!spawningChecks(context, pos)) {
            return Optional.empty();
        }

        return ComplementJigsawAssembler.assemble(
                context,
                this.startPool,
                this.size,
                pos,
                CSStructureGeneration.JIGSAW_NETHER_STRUCTURE_GENERATION.get()

        );
    }

    @Override
    public StructureType<?> type() {
        return CSStructureGeneration.JIGSAW_NETHER_STRUCTURE_GENERATION.get();
    }
}