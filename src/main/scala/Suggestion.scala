import com.github.mrbean355.dota2.hero.SpectatedHero
import com.github.mrbean355.dota2.map.Team
import com.github.mrbean355.dota2.player.SpectatedPlayer
import State.*

object Suggestion:

  def steamIdList(players: Map[TeamName, Seq[SpectatedPlayer]]): Seq[String] =
    players
      .values
      .toSeq
      .flatten
      .map(_.getSteamId)

  def friendlyHeroName(internalName: String): String =
    internalName
      .replace("npc_dota_hero_", "")
      .replace('_', ' ')

//  val wesselId = 21842016
  val arteezy = "76561198059151539"
  val quinn = "76561198181931958"
  val friendlySteamIds: Seq[String] = List(arteezy, quinn)

  def suggest(heroMap: Map[PlayerId, SpectatedHero],
              playerMap: Map[TeamName, Seq[SpectatedPlayer]]): Unit =

    val friendlyPlayers: Seq[SpectatedPlayer] =
      playerMap.values.flatten
        .filter(p => friendlySteamIds.contains(p.getSteamId))
        .toSeq

    // bepaal onze heroes
    val friendlyHeroNames: Seq[String] = // friendly players
      friendlyPlayers
        .flatMap(player => heroMap.get(player.getId))
        .map(_.getName)
        .map(friendlyHeroName)

    // bepaal enemy team
    val friendlyTeam: Option[Team] = friendlyPlayers.headOption.map(_.getTeam)
    // bepaal enemy team heroes

    val enemyPlayers: Seq[SpectatedPlayer] =
      playerMap.values.flatten.filter(p => !friendlyTeam.contains(p.getTeam)).toSeq

    val enemyHeroNames: Seq[String] =
      enemyPlayers
        .flatMap(player => heroMap.get(player.getId))
        .map(_.getName)
        .map(friendlyHeroName)

    // stuur request naar ChatGPT
    println("EnemyHeroes: " + enemyHeroNames.mkString(", "))
    println("FriendlyHeroes: " + friendlyHeroNames.mkString(", "))

    ChatGPTClient.sendRequest(friendlyHeroNames, enemyHeroNames)