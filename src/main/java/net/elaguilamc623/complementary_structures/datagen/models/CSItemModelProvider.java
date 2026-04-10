package net.elaguilamc623.complementary_structures.datagen.models;

import net.elaguilamc623.complementary_core.datagen.models.CCItemModelProvider;
import net.elaguilamc623.complementary_structures.ComplementaryStructures;
import net.elaguilamc623.complementary_structures.registry.CSItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CSItemModelProvider extends CCItemModelProvider {

    public CSItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ComplementaryStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(CSItems.PRISMARINE_INGOT);

        handheldItem(CSItems.PRISMARINE.getSword());
        handheldItem(CSItems.PRISMARINE.getAxe());
        handheldItem(CSItems.PRISMARINE.getPickaxe());
        handheldItem(CSItems.PRISMARINE.getShovel());
        handheldItem(CSItems.PRISMARINE.getHoe());
        handheldItem(CSItems.KING_BLADE);
        handheldItem(CSItems.OBSIDIAN_SWORD);
    }
}