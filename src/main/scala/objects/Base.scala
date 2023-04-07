package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.Inventory
import events._
import scene.HudScene

class Base(hud: HudScene) extends AnimatedGameObject("game/castle.png", 51, 48,Array(8,8,8,8)){
    
    override def id = 2

    override def collision_box = Some(Rect[Float](position.x + 1, position.y + 24, 47, 23))
    override def trigger_box = Some(Rect[Float](position.x - 5, position.y - 5, 61, 58))

    val inventory = new StructureInventory(40)

    def retrieve() : Unit = {{
        if (inventory.state == 0 && inventory.inv.amount(ResourceType.STONE) > 0)  {
            inventory.inv.remove(ResourceType.STONE,1)
            PersonalInventory.inventory.add(ResourceType.STONE,1)
    }
        else if (inventory.state == 1 && inventory.inv.amount(ResourceType.WOOD) > 0)  {
            inventory.inv.remove(ResourceType.WOOD,1)
            PersonalInventory.inventory.add(ResourceType.WOOD,1)
    }
        else if (inventory.state == 2 && inventory.inv.amount(ResourceType.COIN) > 0)  {
            inventory.inv.remove(ResourceType.COIN,1)
            PersonalInventory.inventory.add(ResourceType.COIN,1)
    }
    }
}


    def store() : Unit = {
        if (inventory.state == 0 && PersonalInventory.inventory.amount(ResourceType.STONE) > 0) {
            inventory.inv.add(ResourceType.STONE,1)
            PersonalInventory.inventory.remove(ResourceType.STONE,1)
        }
        if (inventory.state == 1 && PersonalInventory.inventory.amount(ResourceType.WOOD) > 0) {
            inventory.inv.add(ResourceType.WOOD,1)
            PersonalInventory.inventory.remove(ResourceType.WOOD,1)
        }
        if (inventory.state == 2 && PersonalInventory.inventory.amount(ResourceType.COIN) > 0) {
            inventory.inv.add(ResourceType.COIN,1)
            PersonalInventory.inventory.remove(ResourceType.COIN,1)
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

    override def damage(dmg: Int, attacker : SpriteGameObject) : AttackResponse = {
        inventory.hp = (inventory.hp - dmg).max(0)
        println(dmg)
        println(inventory.hp)
        state = (40 - inventory.hp)/10
        if (inventory.hp == 0) return AttackKilled(None)
        else return AttackSuccess()
    }
}