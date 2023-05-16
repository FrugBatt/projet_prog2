package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.PersonalInventory
import events._
import scene._



class Barracks extends Structure(10, "game/barracks.png", 35, 31, Array(4,4)) {


    override def collision_box = Some(Rect[Float](position.x + 3, position.y + 14, 30, 14))
    override def trigger_box = Some(Rect[Float](position.x-5,position.y-5,45,41))

    override def interact(player : PlayerState) = {
      player match {
        case PlayerState.P1 =>
          if(PersonalInventory.inventoryP1.amount(ResourceType.STONE) >= 5 && PersonalInventory.inventoryP1.amount(ResourceType.COIN) >= 2){
              val soldier = new Soldier()
              soldier.position = (GameScene.king1.position.x, GameScene.king1.position.y + GameScene.king1.sprite.textureRect.height)
              GameScene.add(soldier)
              PersonalInventory.inventoryP1.remove(ResourceType.STONE,5)
              PersonalInventory.inventoryP1.remove(ResourceType.COIN,2)
          }
        case PlayerState.P2 =>
          if(PersonalInventory.inventoryP2.amount(ResourceType.STONE) >= 5 && PersonalInventory.inventoryP2.amount(ResourceType.COIN) >= 2){
              val soldier = new Soldier()
              soldier.position = (GameScene.king2.position.x, GameScene.king2.position.y + GameScene.king2.sprite.textureRect.height)
              GameScene.add(soldier)
              PersonalInventory.inventoryP2.remove(ResourceType.STONE,5)
              PersonalInventory.inventoryP2.remove(ResourceType.COIN,2)
          }
      }
    }

}
