package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.world.entities.InfectedTntEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class SubEntities {
    public static final EntityType<InfectedTntEntity> INFECTED_TNT = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SubVirus.MOD_ID, "infected_tnt"),
            EntityType.Builder.create((EntityType<InfectedTntEntity> type, net.minecraft.world.World world) -> new InfectedTntEntity(type, world), SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .maxTrackingRange(1)
                    .trackingTickInterval(10)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(SubVirus.MOD_ID, "infected_tnt")))
    );

    public static void registerModEntities() {
        SubVirus.LOGGER.debug("Registering entities for " + SubVirus.MOD_ID);
    }
}
