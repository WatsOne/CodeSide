package alg

import Debug
import model.*
import kotlin.math.abs

@Suppress("DuplicatedCode")
class LineTracer {

    //Алгоритм Брезенхэма
    fun canTrace(game: Game, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        var d = 0
        val dy = abs(y2 - y1)
        val dx = abs(x2 - x1)
        val dy2 = dy shl 1
        val dx2 = dx shl 1
        val ix = if (x1 < x2)  1 else -1
        val iy = if (y1 < y2)  1 else -1
        var xx = x1
        var yy = y1

        if (dy <= dx) {
            while (true) {
                //debug.draw(CustomData.Rect(Vec2Float(xx.toFloat(), yy.toFloat()), Vec2Float(1f, 1f), ColorFloat(1.0f, 0f, 0f, 1.0f)))
                if (game.level.tiles[xx][yy] == Tile.WALL) {
                    return false
                }
                if (xx == x2) break
                xx += ix
                d  += dy2
                if (d > dx) {
                    yy += iy
                    d  -= dx2
                }
            }
        }
        else {
            while (true) {
                //debug.draw(CustomData.Rect(Vec2Float(xx.toFloat(), yy.toFloat()), Vec2Float(1f,1f), ColorFloat(1.0f, 0f, 0f, 1.0f)))
                if (game.level.tiles[xx][yy] == Tile.WALL) {
                    return false
                }
                if (yy == y2) break
                yy += iy
                d  += dx2
                if (d > dy) {
                    xx += ix
                    d  -= dy2
                }
            }
        }

        return true
    }
}