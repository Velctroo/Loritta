package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.ImageUtils
import net.dv8tion.jda.core.MessageBuilder
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.io.File
import javax.imageio.ImageIO

class PrimeirasPalavrasCommand : CommandBase() {
	override fun getLabel(): String {
		return "primeiraspalavras"
	}

	override fun getDescription(): String {
		return "Ai meu deus... as primeiras palavras do bebê!"
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		if (context.args.isNotEmpty()) {
			val str = context.args.joinToString(" ")

			val bi = ImageIO.read(File(Loritta.FOLDER + "tirinha_baby.png")) // Primeiro iremos carregar o nosso template

			val baseGraph = bi.graphics

			(baseGraph as Graphics2D).setRenderingHint(
					RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

			baseGraph.setColor(Color(0, 0, 0, 255))

			val font = Font("Arial", Font.BOLD, 32)

			baseGraph.setFont(font)

			val quaseFalando = str[0] + "... " + str[0] + "..."

			ImageUtils.drawTextWrap(quaseFalando, 4, 5 + font.size, 236, 0, baseGraph.getFontMetrics(), baseGraph)

			ImageUtils.drawTextWrapSpaces(str, 4, 277 + font.size, 342, 0, baseGraph.getFontMetrics(), baseGraph)

			val builder = MessageBuilder().append(context.getAsMention(true))

			context.sendFile(bi, "tirinha_baby.png", builder.build())
		} else {
			this.explain(context)
		}
	}
}