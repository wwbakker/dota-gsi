import com.github.mrbean355.dota2.hero.SpectatedHero
import com.github.mrbean355.dota2.player.SpectatedPlayer

object State {
  type PlayerId = String
  type TeamName = String
  
  var heroMap: Map[PlayerId, SpectatedHero] = Map.empty
  var playerMap: Map[TeamName, Seq[SpectatedPlayer]] = Map.empty
}
