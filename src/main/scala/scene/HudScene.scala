package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2
import scala.collection.mutable.ListBuffer
import sfml.graphics.Color

import objects._
import game.Game

object HudScene extends Scene {
  

  val hpP2 = new Healthbar(PlayerState.P2)
  hpP2.scale = Vector2(5f,5f)
  hpP2.position = Vector2(Game.width - 400, 0)

  val invhudP2 = new InventorySprite()
  invhudP2.position = Vector2(Game.width - 250,Game.height/10)
  invhudP2.scale = Vector2(5f,5f)

  val stone_amountP2 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP2.amount(ResourceType.STONE))
  stone_amountP2.position = Vector2(Game.width - 100, 112)

  val wood_amountP2 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP2.amount(ResourceType.WOOD))
  wood_amountP2.position = Vector2(Game.width - 100, 200)

  val coin_amountP2 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP2.amount(ResourceType.COIN))
  coin_amountP2.position = Vector2(Game.width - 100, 300)

  val separator = new RectangleShapeGameObject(Color(0, 0, 0), Game.width / 2 - 3, 0, 6, Game.height)

  def init() : Unit = {

    val hpP1 = new Healthbar(PlayerState.P1)

    val hudcamera = new Camera(Game.window,Game.width,Game.height, 1f, Side.Full)

    cameras = Vector(hudcamera)

    hpP1.scale = Vector2(5f,5f)

    val invhudP1 = new InventorySprite()

    invhudP1.position = Vector2(0,Game.height/10)
    invhudP1.scale = Vector2(5f,5f)

    val stone_amountP1 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP1.amount(ResourceType.STONE))
    stone_amountP1.position = Vector2(150, 112)

    val wood_amountP1 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP1.amount(ResourceType.WOOD))
    wood_amountP1.position = Vector2(150, 200)

    val coin_amountP1 = new UpdatableTextGameObject(() => PersonalInventory.inventoryP1.amount(ResourceType.COIN))
    coin_amountP1.position = Vector2(150, 300)

    objects = Vector(invhudP1, hpP1, stone_amountP1, wood_amountP1, coin_amountP1)
  }

}
