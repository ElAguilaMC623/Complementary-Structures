package net.elaguilamc623.complementary_structures.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elaguilamc623.complementary_structures.registry.structure.ModStructureGeneration;
import net.elaguilamc623.complementary_structures.structures.worldgen.ComplementJigsawAssembler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import java.util.Optional;

public class JigsawStructuresGenerationChecker extends Structure {

    public static final MapCodec<JigsawStructuresGenerationChecker> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(s -> s.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(s -> s.size),
            Codec.BOOL.fieldOf("cannot_spawn_in_liquid").orElse(false).forGetter(s -> s.cannotSpawnInLiquid),
            Codec.intRange(1, 100).optionalFieldOf("terrain_height_check_radius").forGetter(s -> s.terrainHeightCheckRadius),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_terrain_height_range").forGetter(s -> s.allowedTerrainHeightRange)
    ).apply(instance, JigsawStructuresGenerationChecker::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final boolean cannotSpawnInLiquid;
    public final Optional<Integer> terrainHeightCheckRadius;
    public final Optional<Integer> allowedTerrainHeightRange;

    public JigsawStructuresGenerationChecker(StructureSettings settings,
                                             Holder<StructureTemplatePool> startPool,
                                             int size,
                                             boolean cannotSpawnInLiquid,
                                             Optional<Integer> terrainHeightCheckRadius,
                                             Optional<Integer> allowedTerrainHeightRange) {
        super(settings);
        this.startPool = startPool;
        this.size = size;
        this.cannotSpawnInLiquid = cannotSpawnInLiquid;
        this.terrainHeightCheckRadius = terrainHeightCheckRadius;
        this.allowedTerrainHeightRange = allowedTerrainHeightRange;
    }

    protected boolean spawningChecks(GenerationContext context, BlockPos blockPos) {
        ChunkPos chunkPos = context.chunkPos();

        if (this.cannotSpawnInLiquid) {
            BlockPos center = chunkPos.getMiddleBlockPosition(0);
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(center.getX(), center.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
            NoiseColumn column = context.chunkGenerator().getBaseColumn(center.getX(), center.getZ(), context.heightAccessor(), context.randomState());
            BlockState topBlock = column.getBlock(center.getY() + landHeight);
            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        if (terrainHeightCheckRadius.isPresent() && allowedTerrainHeightRange.isPresent()) {
            int maxHeight = Integer.MIN_VALUE;
            int minHeight = Integer.MAX_VALUE;
            int radius = terrainHeightCheckRadius.get();

            for (int x = chunkPos.x - radius; x <= chunkPos.x + radius; x++) {
                for (int z = chunkPos.z - radius; z <= chunkPos.z + radius; z++) {
                    int height = context.chunkGenerator().getBaseHeight((x << 4) + 7, (z << 4) + 7, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
                    maxHeight = Math.max(maxHeight, height);
                    minHeight = Math.min(minHeight, height);
                }
            }

            if (maxHeight - minHeight > allowedTerrainHeightRange.get()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        int offsetY = 0;
        BlockPos pos = new BlockPos(context.chunkPos().getMinBlockX(), offsetY, context.chunkPos().getMinBlockZ());

        if (!spawningChecks(context, pos)) {
            return Optional.empty();
        }

        return ComplementJigsawAssembler.assemble(
                context,
                this.startPool,
                this.size,
                pos,
                ModStructureGeneration.ADVANCED_STRUCTURE.get()
        );
    }

    @Override
    public StructureType<?> type() {
        return ModStructureGeneration.ADVANCED_STRUCTURE.get();
    }
}