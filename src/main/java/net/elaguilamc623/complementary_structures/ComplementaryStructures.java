package net.elaguilamc623.complementary_structures;

import com.mojang.logging.LogUtils;
import net.elaguilamc623.complementary_structures.registry.*;
import net.elaguilamc623.complementary_structures.registry.structure.CSStructurePlacements;
import net.elaguilamc623.complementary_structures.registry.structure.CSStructureGeneration;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ComplementaryStructures.MOD_ID)
public class ComplementaryStructures
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "complementary_structures";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ComplementaryStructures() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CSItems.register(modEventBus);
        CSPotions.register(modEventBus);
        CSBlocks.register(modEventBus);

        CSStructurePlacements.register(modEventBus);
        CSStructureGeneration.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(CSItems.PRISMARINE_INGOT);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(CSItems.PRISMARINE.getSword().get());
            event.accept(CSItems.PRISMARINE.getAxe().get());
            event.accept(CSItems.OBSIDIAN_SWORD);
            event.accept(CSItems.KING_BLADE);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(CSItems.PRISMARINE.getAxe().get());
            event.accept(CSItems.PRISMARINE.getPickaxe().get());
            event.accept(CSItems.PRISMARINE.getShovel().get());
            event.accept(CSItems.PRISMARINE.getHoe().get());
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(CSBlocks.PRISMARINE_BLOCK.get());
        }
    }
}
