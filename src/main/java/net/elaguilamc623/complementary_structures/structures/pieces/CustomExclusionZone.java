package net.elaguilamc623.complementary_structures.structures.pieces;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public record CustomExclusionZone(HolderSet<StructureSet> sets, int radius) {
    public static final Codec<CustomExclusionZone> CODEC = RecordCodecBuilder.create(builder
            -> builder.group(RegistryCodecs.homogeneousList(
                    ResourceKey.createRegistryKey(new ResourceLocation("minecraft", "worldgen/structure_set")),
                    StructureSet.DIRECT_CODEC
            ).fieldOf("sets").forGetter(CustomExclusionZone::sets),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("radius")
                    .forGetter(CustomExclusionZone::radius)
    ).apply(builder, CustomExclusionZone::new));
}
