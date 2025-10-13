package violetker13.Hex.HexLag

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import violetker13.Hex.HexLag.Entities.HexxyArrowEntity
import violetker13.Hex.HexLag.casting.HexLagPatterns

object HexLag : ModInitializer {
    const val MOD_ID = "hexlag"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        HexLagPatterns.init()
        val CUSTOM_ARROW: EntityType<HexxyArrowEntity> = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier(MOD_ID, "hexxyarrow_arrow"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC) { type, world ->
                HexxyArrowEntity(type, world)
            }
                .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                .trackRangeBlocks(4)
                .trackedUpdateRate(10)
                .build()
        )

    }
}