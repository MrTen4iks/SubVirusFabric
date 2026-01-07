package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.registry.materials.SubArmorMaterials;
import com.subworld.subvirus.registry.materials.SubItemMaterials;
import com.subworld.subvirus.world.items.armor.HazmatArmorElementItem;
import com.subworld.subvirus.world.items.armor.HazmatArmorPackItem;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.minecraft.item.Items.register;


public class SubItems {
    /// ------------ === +++ ||| Items ||| +++ === -----------

    public static final Item INFECTED_RAW = register("infected_raw", Item::new, new Item.Settings(),true);
    public static final Item INFECTED_INGOT = register("infected_ingot", Item::new, new Item.Settings(),true);
    public static final Item INFECTED_DUST = register("infected_dust", Item::new, new Item.Settings(),true);
    public static final Item INFECTED_STICK = register("infected_stick", Item::new, new Item.Settings(),true);


    /// ------------ === +++ ||| Tools ||| +++ === ------------


    public static final Item UNCERTAIN_PICKAXE = register("uncertain_pickaxe", Item::new,
            new Item.Settings().enchantable(15).pickaxe(SubItemMaterials.INFECTED_TOOL_MATERIAL, 1, -2.8f)
            ,true
    );


    public static final Item UNCERTAIN_SWORD = register("uncertain_sword", Item::new,
            new Item.Settings().enchantable(15).sword(SubItemMaterials.INFECTED_TOOL_MATERIAL, 14, -2.4f)
            ,true
    );


    /// ------------ === +++ ||| Foods ||| +++ === ------------


    public static final ConsumableComponent PANACEA_FOOD_CONSUMABLE_COMPONENT =
            ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(SubEffects.PANACEA, 6 * 20, 0), 1.0f)).build();
    public static final FoodComponent PANACEA_FOOD_COMPONENT = new
            FoodComponent.Builder().alwaysEdible().build();

    public static final Item PANACEA_STEW =
            register("panacea_stew",
                    Item::new,
                    new Item.Settings().food(PANACEA_FOOD_COMPONENT, PANACEA_FOOD_CONSUMABLE_COMPONENT)
                    , true
            );


    /// ------------ === +++ ||| ARMOR ||| +++ === ------------

    public static final Item HAZMAT_SUIT_HELMET = register(
            "hazmat_suit_helmet",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
            ,true
    );
    public static final Item HAZMAT_SUIT_CHESTPLATE = register("hazmat_suit_chestplate",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
            ,true
    );
    public static final Item HAZMAT_SUIT_LEGGINGS = register(
            "hazmat_suit_leggings",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
            ,true
    );

    public static final Item HAZMAT_SUIT_BOOTS = register(
            "hazmat_suit_boots",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
            ,true
    );

    public static final Item HAZMAT_SUIT_PACK = register(
            "hazmat_suit_pack",
            HazmatArmorPackItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.BODY.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
            ,true
    );
    

    /// ------------ === +++ ||| UTILS ||| +++ === ------------

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings, boolean addToTab) {

        Item item = register(name, itemFactory, settings);

        // adding to tab
        if(addToTab) SubItemGroups.SUB_TAB.add(item);

        return item;
    }
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings, boolean addToTab, boolean registerLore) {

        Item item = register(name, itemFactory, settings, addToTab);

        // adding custom lore
        if (registerLore) {
            ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, lines) -> {
                if (itemStack.isOf(item)) {
                    lines.add(Text.translatable("item." + SubVirus.MOD_ID + "." + name + ".description"));
                }
            });
        }
        return item;
    }

    public static void registerModItems() {

        SubVirus.LOGGER.debug("Регестрация предметов для" + SubVirus.MOD_ID);
    }
}
