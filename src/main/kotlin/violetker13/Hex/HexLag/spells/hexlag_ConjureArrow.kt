package violetker13.Hex.HexLag.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.OperationResult
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.casting.actions.eval.OpEval
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class hexlag_ConjureArrow() : Action {
    override fun operate(
        env: CastingEnvironment,
        image: CastingImage,
        continuation: SpellContinuation
    ): OperationResult {
        if (image.stack.size == 0)
            throw MishapNotEnoughArgs(0, image.stack.size)
        val mediaCost = (MediaConstants.CRYSTAL_UNIT * 10)

        val stack = image.stack.toMutableList()
        val top = stack.removeLast()

        if (top !is Vec3Iota)
            throw MishapInvalidIota.ofType(top, 0, "vector")

        val pos = top.vec3
        val player = env.castingEntity as? ServerPlayerEntity ?: throw MishapBadCaster()
        val world = player.world
        val arrow = ArrowEntity(world, player)
        arrow.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED
        arrow.setPos(pos.x, pos.y, pos.z)

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