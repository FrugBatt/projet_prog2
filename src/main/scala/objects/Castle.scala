package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.Inventory
import events._
import scene.HudScene

class Castle extends Structure(40, "game/castle.png", 51, 48,Array(8,8,8,8,8)){
    
    override def id = 2

    override def collision_box = Some(Rect[Float](position.x + 1, position.y + 24, 47, 23))
    override def trigger_box = Some(Rect[Float](position.x - 5, position.y - 5, 61, 58))

    val inventory = new StructureInventory(40)

    override def interact(player : PlayerState) = {
        if (inventory.is_displayed) inv_close()
        else inv_display()

        inventory.player = player
    }

    def inv_display() : Unit = {
        if (!inventory.is_displayed){
            inventory.is_displayed = true
            HudScene.add(inventory)
            HudScene.add(inventory.stone_display)
            HudScene.add(inventory.wood_display)
            HudScene.add(inventory.coin_display)
            HudScene.add(inventory.hp_display)
        }
    }

    def inv_close() : Unit = {
        if (inventory.is_displayed){
            inventory.is_displayed = false
            HudScene.del(inventory)
            HudScene.del(inventory.stone_display)
            HudScene.del(inventory.wood_display)
            HudScene.del(inventory.coin_display)
            HudScene.del(inventory.hp_display)
        }
    }

    override def update() = {
        super.update()
        if(built) state = (40 - inventory.hp)/10 + 1
    }

    override def damage(dmg: Int, attacker : SpriteGameObject) : AttackResponse = {
        inventory.hp = (inventory.hp - dmg).max(0)
        inventory.state = inventory.state%4 + 4
        if (inventory.hp == 0) {
            // owner.has_castle = false
            return AttackKilled(None)
        }
        else return AttackSuccess()
    }
}
