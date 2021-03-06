package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.google.common.collect.ImmutableMap
import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import net.dv8tion.jda.core.MessageBuilder
import java.awt.Image
import java.io.File
import java.util.*
import javax.imageio.ImageIO

class DrakeCommand : CommandBase() {
	override fun getLabel(): String {
		return "drake"
	}

	override fun getDescription(): String {
		return "Cria um meme do Drake usando dois usuários da sua guild!"
	}

	override fun getExample(): List<String> {
		return Arrays.asList("", "@Loritta @MrPowerGamerBR")
	}

	override fun getDetailedUsage(): Map<String, String> {
		return ImmutableMap.builder<String, String>()
				.put("usuário1", "*(Opcional)* Usuário sortudo")
				.put("usuário2", "*(Opcional)* Usuário sortudo")
				.build()
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		val bi = ImageIO.read(File(Loritta.FOLDER + "drake.png")) // Primeiro iremos carregar o nosso template
		val graph = bi.graphics

		run {
			val avatarImg = LorittaUtils.getImageFromContext(context, 0)

			if (!LorittaUtils.isValidImage(context, avatarImg)) {
				return
			}

			var image: Image = avatarImg;

			image = avatarImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH)
			graph.drawImage(image, 150, 0, null)
		}

		run {
			var avatarImg = LorittaUtils.getImageFromContext(context, 1)

			if (!LorittaUtils.isValidImage(context, avatarImg)) {
				return
			}

			var image: Image = avatarImg;

			image = avatarImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH)
			graph.drawImage(image, 150, 150, null)
		}

		val builder = MessageBuilder()
		builder.append(context.getAsMention(true))
		context.sendFile(bi, "meme.png", builder.build())
	}
}