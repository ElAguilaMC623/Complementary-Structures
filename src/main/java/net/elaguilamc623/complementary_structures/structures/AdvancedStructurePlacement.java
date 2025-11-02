package net.elaguilamc623.complementary_structures.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.elaguilamc623.complementary_structures.registry.ModStructurePlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import java.util.List;
import java.util.Optional;

public class AdvancedStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<AdvancedStructurePlacement> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf(
                    "locate_offset", Vec3i.ZERO).forGetter(AdvancedStructurePlacement::getLocateOffset),
            FrequencyReductionMethod.CODEC.optionalFieldOf(
                    "frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(AdvancedStructurePlacement::getFrequencyReductionMethod),
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf(
                    "frequency", 1.0F).forGetter(AdvancedStructurePlacement::getFrequency),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf(
                    "salt").forGetter(AdvancedStructurePlacement::getSalt),
            ExclusionZone.CODEC.optionalFieldOf(
                    "exclusion_zone").forGetter(AdvancedStructurePlacement::getExclusionZone),
            CustomExclusionZone.CODEC.optionalFieldOf(
                    "custom_exclusion_zone").forGetter(AdvancedStructurePlacement::getCustomExclusionZone),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf(
                    "spacing").forGetter(RandomSpreadStructurePlacement::spacing),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf(
                    "separation").forGetter(RandomSpreadStructurePlacement::separation),
            RandomSpreadType.CODEC.optionalFieldOf(
                    "spread_type", RandomSpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::spreadType)
    ).apply(instance, AdvancedStructurePlacement::new));

    private final Optional<CustomExclusionZone> customExclusionZone;

    public AdvancedStructurePlacement(
            Vec3i locateOffset,
            FrequencyReductionMethod frequencyReductionMethod,
            float frequency,
            int salt,
            Optional<ExclusionZone> exclusionZone,
            Optional<CustomExclusionZone> customExclusionZone,
            int spacing,
            int separation,
            RandomSpreadType spreadType
    ) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.customExclusionZone = customExclusionZone;
    }

    public Vec3i getLocateOffset() {
        return this.locateOffset();
    }

    public FrequencyReductionMethod getFrequencyReductionMethod() {
        return this.frequencyReductionMethod();
    }

    public float getFrequency() {
        return this.frequency();
    }

    public int getSalt() {
        return this.salt();
    }

    public Optional<ExclusionZone> getExclusionZone() {
        return this.exclusionZone();
    }

    public Optional<CustomExclusionZone> getCustomExclusionZone() {
        return this.customExclusionZone;
    }

    private List<Holder<StructureSet>> getRelevantSets() {
        return this.customExclusionZone
                .map(zone -> zone.sets().stream().toList())
                .orElse(List.of());
    }

    @Override
    public boolean isStructureChunk(ChunkGeneratorStructureState state, int chunkX, int chunkZ) {
        boolean baseValid = super.isStructureChunk(state, chunkX, chunkZ);
        if (!baseValid) return false;

        if (customExclusionZone.isPresent()) {
            CustomExclusionZone zone = customExclusionZone.get();
            int radius = zone.radius();

            for (Holder<StructureSet> holder : zone.sets()) {
                if (state.hasStructureChunkInRange(holder, chunkX, chunkZ, radius)) {
                    System.out.println("[AdvancedPlacement] Chunk rechazado por proximidad a " +
                            holder.unwrapKey().map(ResourceKey::location).orElse(new ResourceLocation("unknown")));
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public StructurePlacementType<?> type() {
        return ModStructurePlacements.ADVANCED_PLACEMENT.get();
    }
}