package violetker13.Hex.HexLag.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.OperationResult
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.getInt
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.getLong
import at.petrak.hexcasting.api.casting.getNumOrVec
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.casting.actions.eval.OpEval
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds
import at.petrak.hexcasting.xplat.IXplatAbstractions

import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class hexlag_delay : Action {
    override fun operate(
        env: CastingEnvironment,
        image: CastingImage,
        continuation: SpellContinuation
    ): OperationResult {
        TODO("Not yet implemented")
    }


}
