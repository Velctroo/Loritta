package com.mrpowergamerbr.loritta.commands.vanilla.music

import com.mrpowergamerbr.loritta.LorittaLauncher
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtilsKotlin
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.react.GenericMessageReactionEvent

class MusicInfoCommand : CommandBase() {
	override fun getLabel(): String {
		return "tocando"
	}

	override fun getDescription(): String {
		return "Fala a música que está tocando agora."
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.MUSIC
	}

	override fun requiresMusicEnabled(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		val manager = LorittaLauncher.getInstance().getGuildAudioPlayer(context.guild)
		if (manager.player.playingTrack == null) {
			context.sendMessage(context.getAsMention(true) + "Nenhuma música está tocando... Que tal tocar uma? `+tocar música`")
		} else {
			val embed = LorittaUtilsKotlin.createTrackInfoEmbed(context)
			val message = context.sendMessage(embed)
			context.metadata.put("currentTrack", manager.scheduler.currentTrack) // Salvar a track atual
			message.addReaction("\uD83E\uDD26").complete()
			message.addReaction("\uD83D\uDD22").complete();
		}
	}

	override fun onCommandReactionFeedback(context: CommandContext, e: GenericMessageReactionEvent, msg: Message) {
		LorittaUtilsKotlin.handleMusicReaction(context, e, msg)
	}
}