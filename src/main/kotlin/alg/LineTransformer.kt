package alg

import Debug
import model.ColorFloat
import model.CustomData
import model.Vec2Float
import kotlin.math.abs

@Suppress("DuplicatedCode")
class LineTransformer {

    //Алгоритм Брезенхэма
    fun drawLine(debug: Debug, x1: Int, y1: Int, x2: Int, y2: Int) {
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
                debug.draw(CustomData.Rect(Vec2Float(xx.toFloat(), yy.toFloat()), Vec2Float(1f, 1f), ColorFloat(1.0f, 0f, 0f, 1.0f)))
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
                debug.draw(CustomData.Rect(Vec2Float(xx.toFloat(), yy.toFloat()), Vec2Float(1f,1f), ColorFloat(1.0f, 0f, 0f, 1.0f)))
                if (yy == y2) break
                yy += iy
                d  += dx2
                if (d > dy) {
                    xx += ix
                    d  -= dy2
                }
            }
        }
    }
}