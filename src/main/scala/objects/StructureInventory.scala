package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard

class StructureInventory() extends StatedGameObject("game/castle_invetory.png",50,70) {

    var is_displayed : Boolean = false

    var wood = 0
    var stone = 0
    var coin = 0

    val wood_display = new UpdatableTextGameObject(() => wood)
    val stone_display = new UpdatableTextGameObject(() => stone)
    val coin_display = new UpdatableTextGameObject(() => coin)


    override def onKeyPressed(e: Event.KeyPressed) : Unit = {

        if (e.code == Keyboard.Key.KeyUp) {
            state = (state - 1).max(0) 
        }
        if (e.code == Keyboard.Key.KeyDown) {
            state = (state + 1).min(2) 
        }
    }

}
