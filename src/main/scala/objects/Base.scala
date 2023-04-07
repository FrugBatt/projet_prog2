package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.Inventory
import events._
import scene.HudScene

class Base(hud: HudScene) extends AnimatedGameObject("game/castle.png", 51, 48,Array(8)){
    
    override def collision_box = Some(Rect[Float](position.x + 1, position.y + 24, 47, 23))
    override def trigger_box = Some(Rect[Float](position.x - 5, position.y - 5, 61, 58))

    val inventory = new StructureInventory()

    def retrieve() : Unit = {{
        if (inventory.state == 0 && inventory.stone > 0)  {
            inventory.stone -= 1
            Inventory.stone += 1
    }
        else if (inventory.state == 1 && inventory.wood > 0)  {
            inventory.wood -= 1
            Inventory.wood += 1
    }
        else if (inventory.state == 2 && inventory.coin > 0)  {
            inventory.coin -= 1
            Inventory.coin +=1
    }
    }
}


    def store() : Unit = {
        if (state == 0 && Inventory.stone > 0) {
            inventory.stone += 1
            Inventory.stone -= 1
        }
        if (state == 1 && Inventory.wood > 0) {
            inventory.wood += 1
            Inventory.wood -= 1
        }
        if (state == 2 && Inventory.coin > 0) {
            inventory.coin += 1
            Inventory.coin -= 1
        }
    }


    def inv_display() : Unit = {
        if (!inventory.is_displayed){
            inventory.is_displayed = true
            hud.add(inventory)
            hud.add(inventory.stone_display)
            hud.add(inventory.wood_display)
            hud.add(inventory.coin_display)
        }
    }

    def inv_close() : Unit = {
        if (inventory.is_displayed){
            inventory.is_displayed = false
            hud.del(inventory)
            hud.del(inventory.stone_display)
            hud.del(inventory.wood_display)
            hud.del(inventory.coin_display)
        }
    }
}