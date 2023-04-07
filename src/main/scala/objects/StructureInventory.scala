package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard
import sfml.system.Vector2
import game.Game

class StructureInventory() extends StatedGameObject("game/castle_inventory.png",50,70) {
    position = Vector2(Game.width - 180,Game.height/10)
    scale = Vector2(3f,3f)
    

    var is_displayed : Boolean = false

    var wood = 0
    var stone = 0
    var coin = 0

    val stone_display = new UpdatableTextGameObject(() => stone)
    stone_display.position = Vector2(position.x+85,position.y + 22)
    stone_display.characterSize_=(40)
    val wood_display = new UpdatableTextGameObject(() => wood)
    wood_display.position = Vector2(position.x+85,position.y + 77)
    wood_display.characterSize_=(40)
    val coin_display = new UpdatableTextGameObject(() => coin)
    coin_display.position = Vector2(position.x+85,position.y + 143)
    coin_display.characterSize_=(40)

    override def onKeyPressed(e: Event.KeyPressed) : Unit = {

        if (e.code == Keyboard.Key.KeyUp) {
            state = (state - 1).max(0) 
        }
        if (e.code == Keyboard.Key.KeyDown) {
            state = (state + 1).min(2) 
        }
    }

}
