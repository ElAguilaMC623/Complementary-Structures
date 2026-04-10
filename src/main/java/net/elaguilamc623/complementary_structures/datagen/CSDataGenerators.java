package net.elaguilamc623.complementary_structures.datagen;

import net.elaguilamc623.complementary_structures.ComplementaryStructures;
import net.elaguilamc623.complementary_structures.datagen.models.CSBlockStateProvider;
import net.elaguilamc623.complementary_structures.datagen.models.CSItemModelProvider;
import net.elaguilamc623.complementary_structures.datagen.recipes.CSRecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ComplementaryStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CSDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(),
                new CSRecipeProvider(packOutput));

        generator.addProvider(event.includeClient(),
                new CSBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(),
                new CSItemModelProvider(packOutput, existingFileHelper));
    }
}