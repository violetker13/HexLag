package violetker13.Hex.HexLag.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.api.misc.MediaConstants

import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

class hexlag_delay : SpellAction {
    override val argc = 1

    override fun execute(
        args: List<Iota>,
        env: CastingEnvironment
    ): SpellAction.Result {


        val mediaCost = MediaConstants.DUST_UNIT / 8 // Если не нужно тратить медиа

        return SpellAction.Result(
            Spell(env.castingEntity as? ServerPlayerEntity),
            mediaCost,
            env.castingEntity?.let { emptyList() } ?: emptyList()
        )
    }

    private inner class Spell(val player: ServerPlayerEntity?) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            player?.sendMessage(Text.of("Player nickname: ${player.name.string}"), false)
        }
    }
}
