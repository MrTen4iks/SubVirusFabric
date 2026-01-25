package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.world.blocks.InfectedTntBlock;
import com.subworld.subvirus.world.blocks.SubFacingBlockRaziv;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.*;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.List;
import java.util.function.Function;

public class SubBlocks {
    public static final BlockItemPair INFECTED_ORE = register(
            "infected_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().strength(5.0F,5.0F),
            new Item.Settings(),true
    );
    public static final BlockItemPair INFECTED_DARK_ORE = register(
            "infected_dark_ore",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().strength(7.0F,7.0F),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_BLOCK = register(
            "uncertain_block",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).requiresTool().strength(4.0F,4.0F),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_LOG = register(
            "uncertain_log",
            PillarBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD).strength(4.0F,4.0F),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_PLANKS = register(
            "uncertain_planks",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD).requiresTool().strength(4.0F,4.0F),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_DIRT = register(
            "uncertain_dirt",
            Block::new,
            AbstractBlock.Settings.copy(Blocks.DIRT),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_GRASS_BLOCK = register(
            "uncertain_grass_block",
            GrassBlock::new,
            AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_DIRT_PATH = register(
            "uncertain_dirt_path",
            DirtPathBlock::new,
            AbstractBlock.Settings.copy(Blocks.DIRT_PATH),
            new Item.Settings(),true
    );
    public static final BlockItemPair UNCERTAIN_TNT = register(
            "uncertain_tnt",
            InfectedTntBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS).strength(0.0F,0.0F),
            new Item.Settings(),true
    );

    public static final BlockItemPair raziv1 = register( ///  myron
            "raziv1",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );
    public static final BlockItemPair raziv2 = register( /// flaim
            "raziv2",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );
    public static final BlockItemPair raziv3 = register( ///  guest
            "raziv3",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );
    public static final BlockItemPair raziv4 = register( ///  ten4ik
            "raziv4",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );
    public static final BlockItemPair raziv5 = register( /// zx
            "raziv5",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );
    public static final BlockItemPair raziv6 = register( /// Лэй
            "raziv6",
            SubFacingBlockRaziv::new,
            AbstractBlock.Settings.create().nonOpaque().noCollision().luminance(state -> 15),
            new Item.Settings(),true,true
    );

    //Пожалуста этот шницель фаршен код был написан в попыхах и главное что работает а щас мне нах лень что то переписывать и вообще нужно было с нуля абстракцию настраивать и аналог дефферед регистра иначе я хз как этот макоронный код впорядок привести
    public static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(SubVirus.MOD_ID, name));
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        return Registry.register(Registries.BLOCK, blockKey, block);
    }
    public static BlockItemPair register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings,  Item.Settings is, boolean shouldRegisterItemToTab) {
        // register block
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(SubVirus.MOD_ID, name));
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        Block b = Registry.register(Registries.BLOCK, blockKey, block);

        // register item
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        BlockItem blockItem = new BlockItem(block, is.registryKey(itemKey).useBlockPrefixedTranslationKey());

        Item i = Registry.register(Registries.ITEM, itemKey, blockItem);

        //register to tab
        if (shouldRegisterItemToTab) {
            SubItemGroups.SUB_TAB.add(b.asItem());
        }

        return new BlockItemPair(b,i);
    }
    public static BlockItemPair register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings,  Item.Settings is, boolean shouldRegisterItemToTab, boolean shouldRegisterLore) {
        // register block
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(SubVirus.MOD_ID, name));
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        Block b = Registry.register(Registries.BLOCK, blockKey, block);

        // register item
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        BlockItem blockItem = new BlockItem(block, is.registryKey(itemKey).useBlockPrefixedTranslationKey());
        Item i = Registry.register(Registries.ITEM, itemKey, blockItem);

        //register to tab
        if (shouldRegisterItemToTab) {
            SubItemGroups.SUB_TAB.add(b.asItem());
        }

        // register lore
        if (shouldRegisterLore) {
            ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, lines) -> {
                if (itemStack.isOf(i)) {
                    addDescriptionLines("item." + SubVirus.MOD_ID + "." + name + ".description", lines);
                }
            });
        }
        return new BlockItemPair(b,i);
    }
    private static void addDescriptionLines(String baseKey, List<Text> lines) {
        int insertIndex = Math.min(1, lines.size());
        for (int i = 0; i < 4; i++) {
            String key = i == 0 ? baseKey : baseKey + (i + 1);
            if (I18n.hasTranslation(key)) {
                lines.add(insertIndex, Text.translatable(key));
                insertIndex++;
            }
        }
    }

    public record BlockItemPair(Block block, Item item){}
    public static void registerModBlocks() {
        SubVirus.LOGGER.debug("Registering blocks for" + SubVirus.MOD_ID);
    }


}
