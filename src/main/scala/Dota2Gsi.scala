import com.github.mrbean355.dota2.hero.{SpectatedHero, SpectatedHeroImpl}
import com.github.mrbean355.dota2.player.SpectatedPlayer
import com.github.mrbean355.dota2.server.GameStateServer

import scala.jdk.CollectionConverters.*
import scala.util.Try
import Suggestion.steamIdList
import State.*

object Dota2Gsi extends App {

  GameStateServer
    .create(44444)
    .setSpectatingListener { gameState =>
        val players: Map[TeamName, Seq[SpectatedPlayer]] = gameState.getPlayers.asScala.map { case (a, b) => (a, b.asScala.toSeq) }.toMap
      // uncomment to view the player ids that are in the game.
      //        players.values.flatten.foreach(p => println(s"${p.getSteamId} = ${p.getName}"))
        if (steamIdList(State.playerMap) != steamIdList(players)) {
          print("*")
          State.heroMap = gameState.getHeroes.asScala.toMap
          State.playerMap = gameState.getPlayers.asScala.map { case (a, b) => (a, b.asScala.toSeq) }.toMap
          Suggestion.suggest(State.heroMap, State.playerMap)
        }
    }.start()


}
