package violetker13.Hex.Hextra.spells


import at.petrak.hexcasting.api.casting.castables.Action

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.OperationResult
import at.petrak.hexcasting.api.casting.eval.env.PlayerBasedCastEnv
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughMedia
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds

import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.server.network.ServerPlayerEntity
import violetker13.Hex.Hextra.Entities.HexxyArrowEntity



class hextra_ConjureArrow : Action {
    override fun operate(
        env: CastingEnvironment,
        image: CastingImage,
        continuation: SpellContinuation
    ): OperationResult {
        if (env !is PlayerBasedCastEnv) throw MishapBadCaster()

        if (image.stack.isEmpty())  throw MishapNotEnoughArgs(1, image.stack.size)
        val stack = image.stack.toMutableList()
        val cost = 10L * MediaConstants.CRYSTAL_UNIT

        if (env.extractMedia(cost, false) > 0) throw MishapNotEnoughMedia(cost)



        val top = stack.removeAt(stack.size - 1)
        if (top !is Vec3Iota) throw MishapInvalidIota.ofType(top, 0, "vector")

        val pos = top.vec3
        env.assertVecInRange(pos)
        val player = env.castingEntity as? ServerPlayerEntity ?: throw MishapBadCaster()
        val world = player.world

        val arrow = HexxyArrowEntity(world, player).apply {
            setPos(pos.x, pos.y, pos.z)
            pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED
            setPierceLevel(1)
        }

        world.spawnEntity(arrow)







        stack.add(EntityIota(arrow))
        return OperationResult(
            image.copy(stack = stack),
            listOf(),
            continuation,
            HexEvalSounds.SPELL
        )
    }


}