package alg

import Debug
import model.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.math.truncate

class Predictor {

    fun draw(unit: model.Unit, game: Game, debug: Debug) {
        //game.level.tiles.forEachIndexed { i, a -> a.forEachIndexed { j, t -> debug.draw(CustomData.Rect(Vec2Float(i.toFloat(), j.toFloat()), Vec2Float(1f, 1f), ColorFloat(255f, 255f, 255f, 0.3f))) } }
        val q = LinkedBlockingQueue<Pair<Int, Int>>()
        val visited = mutableSetOf<Pair<Int, Int>>()

        val centerQuad = Vec2Float(truncate(unit.position.x).toFloat(), truncate(unit.position.y + 0.9f).toFloat())

        val first = Pair(centerQuad.x.toInt(), centerQuad.y.toInt())
        q.add(first)
        visited.add(first)

        while (!q.isEmpty()) {
            val current = q.poll()
            //debug.draw(CustomData.Rect(Vec2Float(current.first.toFloat(), current.second.toFloat()), Vec2Float(1f,1f), ColorFloat(1f, 1f, 1f, 0.4f)))
            val up = Pair(current.first, current.second + 1)
            if (game.level.tiles[up.first][up.second] != Tile.WALL && !visited.contains(up)) {
                q.add(up)
                visited.add(up)
            }
            val down = Pair(current.first, current.second - 1)
            if (game.level.tiles[down.first][down.second] != Tile.WALL && !visited.contains(down)) {
                q.add(down)
                visited.add(down)
            }
            val right = Pair(current.first - 1, current.second)
            if (game.level.tiles[right.first][right.second] != Tile.WALL && !visited.contains(right)) {
                q.add(right)
                visited.add(right)
            }
            val left = Pair(current.first + 1, current.second)
            if (game.level.tiles[left.first][left.second] != Tile.WALL && !visited.contains(left)) {
                q.add(left)
                visited.add(left)
            }
        }

        //debug.draw(CustomData.Rect(centerQuad, Vec2Float(1f,1f), ColorFloat(1f, 1f, 1f, 0.3f)))
        val ground = mutableListOf<Pair<Int, Int>>()
        visited.forEach {
            val tile = game.level.tiles[it.first][it.second]
            val downTile = game.level.tiles[it.first][it.second-1]
            if (tile == Tile.EMPTY && (downTile == Tile.EMPTY || downTile == Tile.JUMP_PAD) || tile == Tile.PLATFORM) {
                return@forEach
            }
            ground.add(it)
        }

        ground.forEach {
            debug.draw(CustomData.Rect(Vec2Float(it.first.toFloat(), it.second.toFloat()), Vec2Float(1f,1f), ColorFloat(1f, 1f, 1f, 0.3f)))
        }


    }
}