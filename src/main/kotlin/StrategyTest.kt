import alg.LineTracer
import alg.AreaSearcher
import model.*

class StrategyTest {
    private val areasHolder = AreaSearcher()
    private val tracer = LineTracer()

    private fun init(unit: model.Unit, game: Game) {
        areasHolder.init(unit, game)
    }

    fun getAction(unit: model.Unit, game: Game, debug: Debug): UnitAction {
        if (game.currentTick == 0) {
            init(unit, game)
        }

        var enemy = unit
        game.units.forEach {
            if (it.playerId != unit.playerId) {
                enemy = it
            }
        }

        areasHolder.areas.forEach {
            var color = if (tracer.canTrace(game, it.first, it.second, enemy.position.x.toInt(), enemy.position.y.toInt())) ColorFloat(0.0f, 1.0f, 0f, 0.3f) else ColorFloat(1.0f, 0.0f, 0f, 0.3f)
            debug.draw(CustomData.Line(Vec2Float(it.first + 0.5f, it.second + 0.5f), Vec2Float(enemy.position.x.toFloat(), enemy.position.y.toFloat()), 0.1f, color))
            color = if (tracer.canTrace(game, it.first, it.second, enemy.position.x.toInt(), (enemy.position.y + 1.8f).toInt())) ColorFloat(0.0f, 1.0f, 0f, 0.3f) else ColorFloat(1.0f, 0.0f, 0f, 0.3f)
            debug.draw(CustomData.Line(Vec2Float(it.first + 0.5f, it.second + 0.5f), Vec2Float(enemy.position.x.toFloat(), enemy.position.y.toFloat() + 1.8f), 0.1f, color))
        }

//        val tile = game.level.tiles[unit.position.x - 0.45][2]
        debug.draw(CustomData.Log(">>>> tick: ${game.currentTick}"))
        debug.draw(CustomData.Log(">>>> y: ${unit.position.y}"))
        debug.draw(CustomData.Log(">>>> next: ${unit.position.x - 10.0/60}"))
        val y = (unit.position.y - 10.0/60/100).toInt()
        debug.draw(CustomData.Log(">>>> fall1: ${game.level.tiles[(unit.position.x - 0.45).toInt()][y] == Tile.EMPTY}"))
        debug.draw(CustomData.Log(">>>> fall1: ${game.level.tiles[(unit.position.x + 0.45).toInt()][y] == Tile.EMPTY}"))
        val action = UnitAction()
        action.velocity = -10.0
        action.jump = false
        action.jumpDown = false
        action.aim = Vec2Double(10.0,10.0)
        action.shoot = false
        action.swapWeapon = false
        action.plantMine = false
//        predictor.draw(unit, game, debug)
//        caster.drawLine(debug,0,0,10,25)
//        debug.draw(CustomData.Line(Vec2Float(0f, 0.0f), Vec2Float(10f,25f), 0.1f, ColorFloat(0.0f, 1.0f, 0f, 1.0f)))
        return action
    }
}