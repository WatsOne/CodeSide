import alg.LineTransformer
import alg.Predictor
import model.*

class StrategyTest {
    val predictor = Predictor()
    val caster = LineTransformer()

    fun getAction(unit: model.Unit, game: Game, debug: Debug): UnitAction {
//        val tile = game.level.tiles[unit.position.x - 0.45][2]
        debug.draw(CustomData.Log(">>>> x: ${unit.position.x}"))
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
        predictor.draw(unit, game, debug)
        caster.drawLine(debug,0,0,10,25)
        debug.draw(CustomData.Line(Vec2Float(0f, 0.0f), Vec2Float(10f,25f), 0.1f, ColorFloat(0.0f, 1.0f, 0f, 1.0f)))
        return action
    }
}