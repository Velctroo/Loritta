package com.mrpowergamerbr.loritta.utils.translate

import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder

object GoogleTranslateUtils {
	/**
	 * Traduz uma mensagem de uma língua para outra
	 *
	 * Criado por Kurimatzu
	 *
	 * @param message
	 * @param from
	 * @param to
	 * @return A mensagem traduzida, caso seja null, quer dizer que falhou ao traduzir a mensagem
	 */
	fun translate(message: String, from: String, to: String): String? {
		var message = message
		try {
			message = URLEncoder.encode(message, "UTF-8")
			val url = URL("http://translate.googleapis.com/translate_a/single?client=gtx&sl=" + from + "&tl="
					+ to + "&dt=t&q=" + message + "&ie=UTF-8&oe=UTF-8")
			val uc = url.openConnection()
			uc.setRequestProperty("User-Agent", "Mozilla/5.0")
			val bufferedReader = BufferedReader(InputStreamReader(uc.getInputStream(), "UTF-8"))
			var inputLine: String
			val builder = StringBuilder()

			for (inputLine in bufferedReader.lines()) {
				builder.append(inputLine)
			}

			val s = builder.toString()
			val sb = StringBuilder()

			// Como parsear coisas não-JSON usando JSON? Sexta, no Globo Repórter.
			// Na verdade o website NÃO retorna um JSON válido, e nós NÃO devemos parsear assim!
			// Mas como o código não fui eu que fiz, eu só resolvi dar uma melhorada para corrigir
			// um pequeno bug que o texto não era pegado 100%.
			//
			// Ou seja, isto é algo que funciona que NÃO deveria funcionar, e que provavelmente só
			// funciona porque o JSON do Java é lenient e não strict!
			val jsonArray = JSONArray(s)
			val jsonArray2 = jsonArray.get(0) as JSONArray
			for (obj in jsonArray2.toList()) {
				if (obj is List<*>) {
					val listInsideArray = obj as List<Any>
					sb.append(listInsideArray[0])
				}
			}

			bufferedReader.close()
			return sb.toString()
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return null
	}
}