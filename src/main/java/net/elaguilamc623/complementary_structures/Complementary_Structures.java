package net.elaguilamc623.complementary_structures;

import com.mojang.logging.LogUtils;
import net.elaguilamc623.complementary_structures.registry.ModBlocks;
import net.elaguilamc623.complementary_structures.registry.ModItems;
import net.elaguilamc623.complementary_structures.registry.ModPotions;
import net.elaguilamc623.complementary_structures.registry.ModStructurePlacements;
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
@Mod(Complementary_Structures.MOD_ID)
public class Complementary_Structures
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "complementary_structures";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Complementary_Structures() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Esto registra los items de ModItems
        ModItems.register(modEventBus);
        // Esto registra las pociones de ModPotions
        ModPotions.register(modEventBus);
        // Esto registra los bloques de ModBlocks
        ModBlocks.register(modEventBus);

        ModStructurePlacements.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }


    // Esto registra en que Creative Tabs esta cada item
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PRISMARINE_INGOT);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.PRISMARINE_SWORD);
            event.accept(ModItems.PRISMARINE_AXE);
            event.accept(ModItems.OBSIDIAN_SWORD);
            event.accept(ModItems.KING_BLADE);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.PRISMARINE_AXE);
            event.accept(ModItems.PRISMARINE_PICKAXE);
            event.accept(ModItems.PRISMARINE_SHOVEL);
            event.accept(ModItems.PRISMARINE_HOE);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.PRISMARINE_BLOCK);
        }
    }
}
