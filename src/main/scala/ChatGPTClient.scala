import os.Path
import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import sttp.model.MediaType

import java.io.File

object ChatGPTClient {

  private val chatGptKey: Path = os.home / ".dota-gsi" / "chatgpt"

  def sendRequest(friendlyHeroes: Seq[String], enemyHeroes: Seq[String]): Unit =
    friendlyHeroes.headOption match
      case None => ()
      case Some(friendlyHeroName) =>

        val prompt =
          s"I am playing $friendlyHeroName. The following heroes are on the enemy team: ${enemyHeroes.mkString(", ")}. " +
          "Which items that are relevant for my hero, can I buy to counter these enemy heroes?"


        val client = SimpleHttpClient()
        val key = os.read(chatGptKey)
        val response = client.send(
          basicRequest
            .post(uri"https://api.openai.com/v1/chat/completions")
            .contentType(MediaType.ApplicationJson)
            .auth.bearer(key)
            .body(
              s"""
                |{
                |     "model": "gpt-3.5-turbo",
                |     "messages": [
                |       {"role": "system", "content": "You are a Dota 2 veteran, a top player."},
                |       {"role": "user", "content": "$prompt"}
                |     ],
                |     "temperature": 0.7
                |   }
                |""".stripMargin
            )
        )

        println(response)
}
