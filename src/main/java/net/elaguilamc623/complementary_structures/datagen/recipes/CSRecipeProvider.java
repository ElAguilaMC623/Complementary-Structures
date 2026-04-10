package net.elaguilamc623.complementary_structures.datagen.recipes;

import net.elaguilamc623.complementary_core.datagen.recipes.CCRecipeProvider;
import net.elaguilamc623.complementary_structures.registry.CSBlocks;
import net.elaguilamc623.complementary_structures.registry.CSItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CSRecipeProvider extends CCRecipeProvider {

    public CSRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        oreCompactRecipe(
                consumer,
                CSItems.PRISMARINE_INGOT.get(),
                CSBlocks.PRISMARINE_BLOCK.get(),
                "prismarine"
        );

        oreUncompactRecipe(
                consumer,
                CSBlocks.PRISMARINE_BLOCK.get(),
                CSItems.PRISMARINE_INGOT.get(),
                "prismarine"
        );

        toolRecipe(
                consumer,
                CSItems.PRISMARINE.getSword().get(),
                CSItems.PRISMARINE_INGOT.get(),
                Tags.Items.RODS_WOODEN,
                "X",
                "X",
                "#",
                "prismarine_ingot"
        );

        toolRecipe(
                consumer,
                CSItems.PRISMARINE.getAxe().get(),
                CSItems.PRISMARINE_INGOT.get(),
                Tags.Items.RODS_WOODEN,
                "XX",
                "X#",
                " #",
                "prismarine_ingot"
        );

        toolRecipe(
                consumer,
                CSItems.PRISMARINE.getPickaxe().get(),
                CSItems.PRISMARINE_INGOT.get(),
                Tags.Items.RODS_WOODEN,
                "XXX",
                " # ",
                " # ",
                "prismarine_ingot"
        );

        toolRecipe(
                consumer,
                CSItems.PRISMARINE.getShovel().get(),
                CSItems.PRISMARINE_INGOT.get(),
                Tags.Items.RODS_WOODEN,
                "X",
                "#",
                "#",
                "prismarine_ingot"
        );

        toolRecipe(
                consumer,
                CSItems.PRISMARINE.getHoe().get(),
                CSItems.PRISMARINE_INGOT.get(),
                Tags.Items.RODS_WOODEN,
                "XX",
                " #",
                " #",
                "prismarine_ingot"
        );
    }
}