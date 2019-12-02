import model.ColorFloat
import model.CustomData
import model.Vec2Float
import kotlin.random.Random

class CellFill {
    companion object {
        fun fill(game: model.Game, debug: Debug) {
            game.level.tiles.forEachIndexed { i, t -> t.forEachIndexed { j, _ ->
                debug.draw(CustomData.Rect(Vec2Float(i.toFloat(), j.toFloat()), Vec2Float(1.0f, 1.0f), ColorFloat(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 0.3f)))
            } }
        }
    }
}