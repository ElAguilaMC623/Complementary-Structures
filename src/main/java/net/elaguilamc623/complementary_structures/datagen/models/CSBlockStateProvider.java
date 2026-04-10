package net.elaguilamc623.complementary_structures.datagen.models;

import net.elaguilamc623.complementary_core.datagen.models.CCBlockStateProvider;
import net.elaguilamc623.complementary_structures.ComplementaryStructures;
import net.elaguilamc623.complementary_structures.registry.CSBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CSBlockStateProvider extends CCBlockStateProvider {

    public CSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ComplementaryStructures.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CSBlocks.PRISMARINE_BLOCK);
    }
}