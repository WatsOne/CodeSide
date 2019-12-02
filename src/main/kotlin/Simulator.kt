import model.*

class Simulator {
    private val yDeltaPath = 10.0 / 60
    private val yDeltaTime = 1.0 / 60

    val player = model.Unit()
    lateinit var tiles: Array<Array<Tile>>

    fun reset(myPlayer: model.Unit, game: Game) {
        tiles = game.level.tiles
        player.position = myPlayer.position
        player.jumpState = myPlayer.jumpState
    }

    fun tick(action: UnitAction, debug: Debug, draw: Boolean) {
        val path = action.velocity / 60

        val xBefore = player.position.x
        val yBefore = player.position.y

        if (action.velocity > 0) {
            //чекаем ПРАВЫЙ верхний и нижний угол, надо посмотреть что следующие тайлы стена или не стена
            val xInt = (player.position.x + 0.45 + path).toInt()
            if (tiles[xInt][player.position.y.toInt()] == Tile.WALL ||
                    tiles[xInt][(player.position.y + 1.8).toInt()] == Tile.WALL) {
                player.position.x = xInt.toDouble() - 0.45
            } else {
                player.position.x += path
            }
        } else {
            //чекаем ЛЕВЫЙ верхний и нижний угол, надо посмотреть что следующие тайлы стена или не стена
            val xInt = (player.position.x - 0.45 + path).toInt()
            if (tiles[xInt][player.position.y.toInt()] == Tile.WALL ||
                    tiles[xInt][(player.position.y + 1.8).toInt()] == Tile.WALL) {
                player.position.x = xInt.toDouble() + 1.45
            } else {
                player.position.x += path
            }
        }

        if (action.jump && player.jumpState.canJump || player.jumpState.maxTime > 0) {
            //летим вверх
            if (player.jumpState.maxTime == 0.0) {
                player.jumpState.maxTime = 0.55
            }
            val yInt = (player.position.y + 1.8 + yDeltaPath).toInt()
            if (tiles[(player.position.x + 0.45).toInt()][yInt] == Tile.WALL ||
                    tiles[(player.position.x - 0.45).toInt()][yInt] == Tile.WALL) {
                player.position.y = yInt - 1.8
                player.jumpState.canJump = false
                player.jumpState.maxTime = 0.0
            } else {
                player.position.y += yDeltaPath
                player.jumpState.maxTime -= yDeltaTime
            }
        } else {
            //падаем
            val yInt = (player.position.y - yDeltaPath).toInt()
            if (tiles[(player.position.x + 0.45).toInt()][yInt] == Tile.WALL ||
                    tiles[(player.position.x - 0.45).toInt()][yInt] == Tile.WALL) {
                player.position.y = yInt.toDouble() + 1.0
                player.jumpState.canJump = false
            } else {
                player.position.y -= yDeltaPath
            }
        }

        if (draw) {
            debug.draw(CustomData.Line(Vec2Float(xBefore.toFloat(), yBefore.toFloat()), Vec2Float(player.position.x.toFloat(), player.position.y.toFloat()), 0.2f, ColorFloat(1.0f, 0.0f, 0.0f, 1.0f)))
        }
    }

    private fun onFall(): Boolean {
        val y = (player.position.y - 10.0/60/100).toInt()
        return tiles[(player.position.x - 0.45).toInt()][y] == Tile.EMPTY || tiles[(player.position.x + 0.45).toInt()][y] == Tile.EMPTY
    }
}