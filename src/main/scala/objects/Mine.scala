package prog2
package objects

import sfml.graphics.Rect
import objects.Resource
import objects.PersonalInventory
import events._
import scene.HudScene



class Mine extends Structure(10, "game/mine.png", 36, 28,Array(4,12)) {

    val random = new scala.util.Random
    val generation_rate : Long = 4000L
    var stone_count : Int = 0
    var gold_count : Int = 0 
    var last_generation : Long = System.currentTimeMillis()


    override def collision_box = Some(Rect[Float](position.x + 3, position.y + 8, 30, 16))
    override def trigger_box = Some(Rect[Float](position.x-5,position.y-5,46,38))


    override def update() = {
        super.update()

        if(System.currentTimeMillis() - last_generation > generation_rate){
            last_generation = System.currentTimeMillis()
            if(random.nextInt(100) < 95) stone_count = (stone_count + 1).min(20)
            else gold_count = (gold_count + 1).min(5)
        }
    }

    override def interact(player : PlayerState) = {
      player match {
        case PlayerState.P1 =>
          PersonalInventory.inventoryP1.add(ResourceType.STONE,stone_count)
          PersonalInventory.inventoryP1.add(ResourceType.COIN,gold_count)
        case PlayerState.P2 =>
          PersonalInventory.inventoryP2.add(ResourceType.STONE,stone_count)
          PersonalInventory.inventoryP2.add(ResourceType.COIN,gold_count)
      }
      stone_count = 0
      gold_count = 0
    }

}
