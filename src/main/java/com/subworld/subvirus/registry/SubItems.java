package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.registry.materials.SubArmorMaterials;
import com.subworld.subvirus.registry.materials.SubItemMaterials;
import com.subworld.subvirus.world.items.armor.HazmatArmorElementItem;
import com.subworld.subvirus.world.items.armor.HazmatArmorPackItem;
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
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.minecraft.item.Items.register;


public class SubItems {
    public static final Item INFECTED_RAW = registerWithTab("infected_raw", Item::new, new Item.Settings());
    public static final Item INFECTED_INGOT = registerWithTab("infected_ingot", Item::new, new Item.Settings());
    public static final Item INFECTED_DUST = registerWithTab("infected_dust", Item::new, new Item.Settings());
    public static final Item INFECTED_STICK = registerWithTab("infected_stick", Item::new, new Item.Settings());

    // ------------ === +++ ||| Tools ||| +++ === ------------


    public static final Item UNCERTAIN_PICKAXE = registerWithTab("uncertain_pickaxe", Item::new,
            new Item.Settings().enchantable(15).pickaxe(SubItemMaterials.INFECTED_TOOL_MATERIAL, 1, -2.8f));


    public static final Item UNCERTAIN_SWORD = registerWithTab("uncertain_sword", Item::new,
            new Item.Settings().enchantable(15).sword(SubItemMaterials.INFECTED_TOOL_MATERIAL, 14, -2.4f));


    // ------------ === +++ ||| Foods ||| +++ === ------------


    public static final ConsumableComponent PANACEA_FOOD_CONSUMABLE_COMPONENT =
            ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(SubEffects.PANACEA, 6 * 20, 0), 1.0f)).build();
    public static final FoodComponent PANACEA_FOOD_COMPONENT = new
            FoodComponent.Builder().alwaysEdible().build();

    public static final Item PANACEA_STEW =
            registerWithTab("panacea_stew",
                    Item::new,
                    new Item.Settings().food(PANACEA_FOOD_COMPONENT, PANACEA_FOOD_CONSUMABLE_COMPONENT)
            );


    // ------------ === +++ ||| ARMOR ||| +++ === ------------

    public static final Item HAZMAT_SUIT_HELMET = registerWithTab(
            "hazmat_suit_helmet",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_CHESTPLATE = registerWithTab("hazmat_suit_chestplate",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_LEGGINGS = registerWithTab(
            "hazmat_suit_leggings",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_BOOTS = registerWithTab(
            "hazmat_suit_boots",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_PACK = registerWithTab(
            "hazmat_suit_pack",
            HazmatArmorPackItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.BODY.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    

    // ------------ === +++ ||| UTILS ||| +++ === ------------
    public static Item registerWithTab(String name,Function<Item.Settings,Item> itemFactory,Item.Settings settings){
        Item i = register(name,itemFactory,settings);
        SubItemGroups.SUB_TAB.add(i);
        return i;
    }
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems() {
        SubVirus.LOGGER.debug("Регестрация предметов для" + SubVirus.MOD_ID);
    }
}
