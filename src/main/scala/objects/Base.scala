package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.Inventory
import events._
import scene.HudScene

class Base(hud: HudScene, owner: King) extends AnimatedGameObject("game/castle.png", 51, 48,Array(8,8,8,8)){
    
    override def id = 2

    override def collision_box = Some(Rect[Float](position.x + 1, position.y + 24, 47, 23))
    override def trigger_box = Some(Rect[Float](position.x - 5, position.y - 5, 61, 58))

    val inventory = new StructureInventory(40)

    def inv_display() : Unit = {
        if (!inventory.is_displayed){
            inventory.is_displayed = true
            hud.add(inventory)
            hud.add(inventory.stone_display)
            hud.add(inventory.wood_display)
            hud.add(inventory.coin_display)
            hud.add(inventory.hp_display)
        }
    }

    def inv_close() : Unit = {
        if (inventory.is_displayed){
            inventory.is_displayed = false
            hud.del(inventory)
            hud.del(inventory.stone_display)
            hud.del(inventory.wood_display)
            hud.del(inventory.coin_display)
            hud.del(inventory.hp_display)
        }
    }

    override def update() = {
        super.update()
        state = (40 - inventory.hp)/10
    }

    override def damage(dmg: Int, attacker : SpriteGameObject) : AttackResponse = {
        inventory.hp = (inventory.hp - dmg).max(0)
        inventory.state = inventory.state%4 + 4
        if (inventory.hp == 0) {
            owner.has_castle = false
            return AttackKilled(None)
        }
        else return AttackSuccess()
    }
}
