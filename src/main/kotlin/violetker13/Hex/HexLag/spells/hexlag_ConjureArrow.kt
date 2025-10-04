package violetker13.Hex.HexLag.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.casting.actions.eval.OpEval
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class hexlag_ConjureArrow() : SpellAction {
    override val argc = 0;

    override fun execute(
        args: List<Iota>,
        env: CastingEnvironment
    ): SpellAction.Result {

        val vector = args.getVec3(0, argc)
        val mediaCost = (MediaConstants.CRYSTAL_UNIT * 10)
        return SpellAction.Result(
            Spell(env.castingEntity as? ServerPlayerEntity, vector),
            mediaCost,
            emptyList()
        )



    }
    private inner class Spell(
        val player: ServerPlayerEntity?,
        val vector: Vec3d
    ) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val player = this.player ?: return
            val world = player.world

            val arrow = ArrowEntity(world)

            // Направляем стрелу в нужный вектор (или туда, куда смотрит игрок)
            arrow.setVelocity(vector.x, vector.y, vector.z, 3.0f, 1.0f)

            // Или можно использовать направление взгляда игрока:
            // arrow.setVelocity(player, player.pitch, player.yaw, 0.0f, 3.0f, 1.0f)

            // Спавним стрелу в мире
            world.spawnEntity(arrow)
        }
    }

}