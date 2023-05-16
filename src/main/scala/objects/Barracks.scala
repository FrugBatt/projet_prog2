package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.PersonalInventory
import events._
import scene._



class Barracks(owner: King) extends Structure(owner, 10, "game/barracks.png", 35, 31, Array(4,4)) {


    override def collision_box = Some(Rect[Float](position.x + 3, position.y + 14, 30, 14))
    override def trigger_box = Some(Rect[Float](position.x-5,position.y-5,45,41))

    override def interact() = {
        if(PersonalInventory.inventory.amount(ResourceType.STONE) >= 5 && PersonalInventory.inventory.amount(ResourceType.COIN) >= 2){
            val soldier = new Soldier()
            soldier.position = (GameScene.king.position.x, GameScene.king.position.y + GameScene.king.sprite.textureRect.height)
            GameScene.add(soldier)
            PersonalInventory.inventory.remove(ResourceType.STONE,5)
            PersonalInventory.inventory.remove(ResourceType.COIN,2)
        }
    }

}