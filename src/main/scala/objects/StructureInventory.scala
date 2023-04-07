package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard
import sfml.system.Vector2
import game.Game
import objects.Resource

class StructureInventory() extends StatedGameObject("game/castle_inventory.png",50,70) {
    position = Vector2(Game.width - 180,Game.height/10)
    scale = Vector2(3f,3f)
    
    val inv = new Inventory()

    var is_displayed : Boolean = false


    val stone_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.STONE))
    stone_display.position = Vector2(position.x+85,position.y + 22)
    stone_display.characterSize_=(40)
    val wood_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.WOOD))
    wood_display.position = Vector2(position.x+85,position.y + 77)
    wood_display.characterSize_=(40)
    val coin_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.COIN))
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
