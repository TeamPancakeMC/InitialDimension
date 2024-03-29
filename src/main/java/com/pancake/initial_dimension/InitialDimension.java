package com.pancake.initial_dimension;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(InitialDimension.MOD_ID)
public class InitialDimension {
    public static final String MOD_ID = "initial_dimension";
    public static final Logger LOGGER = LogUtils.getLogger();
    public InitialDimension() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    public static class Config {
        private static final ForgeConfigSpec SPEC;
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec.ConfigValue<String> INITIAL_DIMENSION;

        static {
            BUILDER.push("Spawn Settings");
            INITIAL_DIMENSION = BUILDER
                    .comment("The initial dimension that players will spawn in")
                    .define("InitialDimension", "minecraft:overworld");
            BUILDER.pop();

            SPEC = BUILDER.build();
        }

        public static ResourceKey<Level> getDimension() {
            return ResourceKey.create(Registry.DIMENSION_REGISTRY, ResourceLocation.tryParse(INITIAL_DIMENSION.get()));
        }
    }
}
