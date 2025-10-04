package violetker13.Hex.HexLag.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getInt
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.getLong
import at.petrak.hexcasting.api.casting.getNumOrVec
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.casting.actions.eval.OpEval
import at.petrak.hexcasting.xplat.IXplatAbstractions

import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class hexlag_delay : SpellAction {
    override val argc = 1

    override fun execute(
        args: List<Iota>,
        env: CastingEnvironment
    ): SpellAction.Result {

        val patterns = args.getList(0, argc).toList() // список Iota
        val ticks = args.getLong(1, argc).toLong() // число тиков
        val mediaCost = (MediaConstants.DUST_UNIT / 8)

        return SpellAction.Result(
            Spell(env.castingEntity as? ServerPlayerEntity, patterns, ticks),
            mediaCost,
            emptyList()
        )



    }

    private inner class Spell(
        val player: ServerPlayerEntity?,
        val patterns: List<Iota>,
        val ticks: Long
    ) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val executor = Executors.newSingleThreadScheduledExecutor()
            executor.schedule({
                val eval : OpEval





                player?.sendMessage(Text.of("Через 5 секунд!"))
            }, ticks*50, TimeUnit.MILLISECONDS)
        }
    }
}
