package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.LorittaUtils
import net.dv8tion.jda.core.MessageBuilder
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO

class SAMCommand : CommandBase() {
	override fun getLabel(): String {
		return "sam"
	}

	override fun getAliases(): List<String> {
		return listOf("southamericamemes")
	}

	override fun getDescription(): String {
		return "Adiciona uma marca da água do South America Memes em uma imagem"
	}

	override fun getExample(): List<String> {
		return listOf("https://cdn.discordapp.com/attachments/265632341530116097/297440837871206420/meme.png")
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		var div = 1.5

		if (context.args.size >= 2) {
			try {
				div = context.args[1].toDouble()
			} catch (e: Exception) {

			}
		}

		val image = LorittaUtils.getImageFromContext(context, 0)

		if (!LorittaUtils.isValidImage(context, image)) {
			return
		}

		var seloSouthAmericaMemes: Image = ImageIO.read(File(Loritta.FOLDER + "selo_sam.png"))

		val height = (image.height / div).toInt() // Baseando na altura
		seloSouthAmericaMemes = seloSouthAmericaMemes.getScaledInstance(height, height, Image.SCALE_SMOOTH)

		val x = Loritta.random.nextInt(0, Math.max(1, image.width - seloSouthAmericaMemes.getWidth(null)))
		val y = Loritta.random.nextInt(0, Math.max(1, image.height - seloSouthAmericaMemes.getHeight(null)))

		image.graphics.drawImage(seloSouthAmericaMemes, x, y, null)

		val builder = MessageBuilder()
		builder.append(context.getAsMention(true))
		context.sendFile(image, "south_america_memes.png", builder.build())
	}
}