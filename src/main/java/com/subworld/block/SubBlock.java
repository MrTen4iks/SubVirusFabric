package com.subworld.block;

import com.subworld.Subteam;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SubBlock {
    public static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
            RegistryKey<Block> blockKey = keyOfBlock(name);
            Block block = blockFactory.apply(settings.registryKey(blockKey));
            if (shouldRegisterItem) {
                RegistryKey<Item> itemKey = keyOfItem(name);

                BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
                Registry.register(Registries.ITEM, itemKey, blockItem);
            }

            return Registry.register(Registries.BLOCK, blockKey, block);
        }

        public static RegistryKey<Block> keyOfBlock(String name) {
            return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Subteam.MOD_ID, name));
        }

    public static RegistryKey<Item> keyOfItem(String name) {
            return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Subteam.MOD_ID, name));
        }
    public static final Block INFECTED_ORE = register(
            "infected_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool(),
            true
    );







    public static void registerModBlocks() {
        Subteam.LOGGER.debug("Регестрация предметов для" + Subteam.MOD_ID);


    }
}



