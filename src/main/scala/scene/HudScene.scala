package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2
import scala.collection.mutable.ListBuffer

import objects.*

class HudScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  

  def init() : Unit = {
    val hp = new Healthbar()
    val hudcamera = new Camera(window,width,height, 1f)

    hp.scale = Vector2(5f,5f)
    PersonalInventory.position = Vector2(0,height/10)
    PersonalInventory.scale = Vector2(5f,5f)

    val stone_amount = new UpdatableTextGameObject(() => PersonalInventory.inventory.amount(ResourceType.STONE))
    stone_amount.position = Vector2(150, 112)

    val wood_amount = new UpdatableTextGameObject(() => PersonalInventory.inventory.amount(ResourceType.WOOD))
    wood_amount.position = Vector2(150, 200)

    val coin_amount = new UpdatableTextGameObject(() => PersonalInventory.inventory.amount(ResourceType.COIN))
    coin_amount.position = Vector2(150, 300)

    objects = Vector(hudcamera, PersonalInventory, hp, stone_amount, wood_amount, coin_amount)
  }

}
