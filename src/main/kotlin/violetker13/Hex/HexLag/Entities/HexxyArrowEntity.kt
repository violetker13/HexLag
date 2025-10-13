package violetker13.Hex.HexLag.Entities

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.pigment.FrozenPigment
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class HexxyArrowEntity : ArrowEntity {

    constructor(type: EntityType<out HexxyArrowEntity>, world: World) : super(type, world)
    constructor(world: World, shooter: LivingEntity) : super(world, shooter)
    private var tickCounter = 0
    override fun tick() {
        super.tick()



        if (!world.isClient) {
            val serverWorld = world as ServerWorld

            val spray = ParticleSpray.cloud(pos, 0.5, 2)
            val pigment = FrozenPigment.DEFAULT.get()
            spray.sprayParticles(serverWorld, pigment)

            tickCounter++


            if (tickCounter >= 200) {
                remove(RemovalReason.DISCARDED)
            }
        }

    }
}
