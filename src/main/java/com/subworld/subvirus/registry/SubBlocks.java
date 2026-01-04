package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.world.blocks.SubFacingBlockRaziv;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SubBlocks {
    public static final Block INFECTED_ORE = register(
            "infected_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().strength(5.0F,5.0F),
            true,true
    );
    public static final Block UNCERTAIN_BLOCK = register(
            "uncertain_block",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().strength(4.0F,4.0F),
            true,true
    );
    public static final Block UNCERTAIN_LOG = register(
            "uncertain_log",
            PillarBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD).requiresTool().strength(4.0F,4.0F),
            true,true
    );
    public static final Block UNCERTAIN_PLANKS = register(
            "uncertain_planks",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD).requiresTool().strength(4.0F,4.0F),
            true,true
    );

    public static final Block raziv1 = register( ///  myron
            "raziv1",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision(),
            true,true
    );
    public static final Block raziv2 = register( /// flaim
            "raziv2",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision(),
            true,true
    );
    public static final Block raziv3 = register( ///  guest
            "raziv3",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision(),
            true,true
    );
    public static final Block raziv4 = register( ///  ten4ik
            "raziv4",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision(),
            true,true
    );
    public static final Block raziv5 = register( /// zx
            "raziv5",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision(),
            true,true
    );


    public static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem, boolean shouldRegisterItemToTab) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        Block b = Registry.register(Registries.BLOCK, blockKey, block);
        if (shouldRegisterItemToTab) {
            SubItemGroups.SUB_TAB.add(b.asItem());
        }
        return b;
    }

    public static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(SubVirus.MOD_ID, name));
    }

    public static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
    }
    public static void registerModBlocks() {
        SubVirus.LOGGER.debug("Registering blocks for" + SubVirus.MOD_ID);
    }
}



