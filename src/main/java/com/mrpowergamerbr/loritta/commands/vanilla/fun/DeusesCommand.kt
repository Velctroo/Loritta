package com.mrpowergamerbr.loritta.commands.vanilla.`fun`

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.utils.ImageUtils
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class DeusesCommand : CommandBase() {
	override fun getLabel(): String {
		return "deuses"
	}

	override fun getDescription(): String {
		return "Caralho, olha os Deuses mano!";
	}

	override fun getExample(): List<String> {
		return listOf("Quando você é nível 4 e vê pessoas de nível 100 jogando");
	}

	override fun getCategory(): CommandCategory {
		return CommandCategory.FUN;
	}

	override fun getUsage(): String {
		return "<texto>";
	}

	override fun needsToUploadFiles(): Boolean {
		return true
	}

	override fun run(context: CommandContext) {
		if (context.args.isNotEmpty()) {
			val template = ImageIO.read(File(Loritta.FOLDER + "deuses.png")); // Template
			val texto = context.args.joinToString(" ");

			// Vamos criar o nosso tempalte
			var image = BufferedImage(630, 830, BufferedImage.TYPE_INT_ARGB);
			var graphics = image.getGraphics() as java.awt.Graphics2D;
			graphics.color = Color.WHITE;
			graphics.fillRect(0, 0, 630, 830);
			graphics.color = Color.BLACK;
			graphics.drawImage(template, 0, 200, null);
			graphics.setRenderingHint(
					java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
					java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			var font = Font.createFont(0, File(Loritta.FOLDER + "mavenpro-bold.ttf")).deriveFont(42F);
			graphics.font = font;
			ImageUtils.drawTextWrapSpaces(texto, 2, 40, 630, 9999, graphics.fontMetrics, graphics);

			context.sendFile(image, "deuses.png", context.getAsMention(true));
		} else {
			context.explain()
		}
	}
}