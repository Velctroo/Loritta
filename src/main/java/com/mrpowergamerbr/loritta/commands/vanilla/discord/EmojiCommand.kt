package com.mrpowergamerbr.loritta.commands.vanilla.discord

import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import net.dv8tion.jda.core.MessageBuilder

class EmojiCommand : CommandBase() {
	override fun getDescription(): String {
		return "Veja emojis em um tamanho que você não precise usar uma lupa para tentar entender eles!"
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.DISCORD
	}

	override fun getUsage(): String {
		return "emoji"
	}

	override fun getExample(): List<String> {
		return listOf("😏")
	}

	override fun getLabel(): String {
		return "emoji"
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		if (context.args.size == 1) {
			var emoji = context.args[0]

			if (emoji.startsWith(":") && emoji.endsWith(":")) { // Emoji customizado?
				// Sim!
				val customEmotes = context.message.emotes
				if (!customEmotes.isEmpty()) {
					val emote = customEmotes[0]
					val emojiUrl = emote.imageUrl

					try {
						val emojiImage = LorittaUtils.downloadImage(emojiUrl);
						context.sendFile(emojiImage, "emoji.png", MessageBuilder().append(" ").build())
					} catch (e: Exception) {
						e.printStackTrace()
					}
				}
			} else {
				// Na verdade é um emoji padrão...
				var value = LorittaUtils.toUnicode(emoji.codePointAt(0)) // Vamos usar codepoints porque emojis
				value = value.substring(2) // Remover coisas desnecessárias
				try {
					val emojiImage = LorittaUtils.downloadImage("https://twemoji.maxcdn.com/2/72x72/$value.png")
					context.sendFile(emojiImage, "emoji.png", MessageBuilder().append(" ").build())
				} catch (e: Exception) {
					e.printStackTrace()
				}
			}
		} else {
			context.explain()
		}
	}
}