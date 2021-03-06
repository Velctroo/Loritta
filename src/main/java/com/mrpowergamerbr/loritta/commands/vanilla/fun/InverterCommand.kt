package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.google.common.collect.ImmutableMap
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import java.awt.Color
import java.util.*

class InverterCommand : CommandBase() {
	override fun getLabel(): String {
		return "inverter"
	}

	override fun getDescription(): String {
		return "Inverte a cor de uma imagem"
	}

	override fun getExample(): List<String> {
		return Arrays.asList("http://i.imgur.com/KbHXmKO.png", "@Loritta", "\uD83D\uDC4C")
	}

	override fun getDetailedUsage(): Map<String, String> {
		return ImmutableMap.builder<String, String>()
				.put("mensagem", "Usuário sortudo")
				.build()
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		val image = LorittaUtils.getImageFromContext(context, 0)

		if (!LorittaUtils.isValidImage(context, image)) { return }

		for (x in 0..image.width - 1) {
			for (y in 0..image.height - 1) {
				val rgba = image.getRGB(x, y)
				var col = Color(rgba, true)
				col = Color(
						255 - col.red,
						255 - col.green,
						255 - col.blue)
				image.setRGB(x, y, col.rgb)
			}
		}

		context.sendFile(image, "invertido.png", context.getAsMention(true))
	}
}