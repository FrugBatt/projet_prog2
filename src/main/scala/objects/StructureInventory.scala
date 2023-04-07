package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard
import sfml.system.Vector2
import game.Game
import objects.Resource
import events.Control

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

  override def init() : Unit = {
    super.init()

    Control.castleUp.addListener(castleUp)
    Control.castleDown.addListener(castleDown)
    Control.castleLeft.addListener(retrieve)
    Control.castleRight.addListener(store)
  }

  def castleUp(start : Boolean) : Unit = {
    if (start) {
      state = (state - 1).max(0) 
    }
  }

  def castleDown(start : Boolean) : Unit = {
    state = (state + 1).min(2) 
  }

  def retrieve(start : Boolean) : Unit = {
    if (start) {
      if (state == 0 && inv.amount(ResourceType.STONE) > 0)  {
        inv.remove(ResourceType.STONE,1)
        PersonalInventory.inventory.add(ResourceType.STONE,1)
      } else if (state == 1 && inv.amount(ResourceType.WOOD) > 0)  {
        inv.remove(ResourceType.WOOD,1)
        PersonalInventory.inventory.add(ResourceType.WOOD,1)
      } else if (state == 2 && inv.amount(ResourceType.COIN) > 0)  {
        inv.remove(ResourceType.COIN,1)
        PersonalInventory.inventory.add(ResourceType.COIN,1)
      }
    }
  }

  def store(start : Boolean) : Unit = {
    if (start) {
      if (state == 0 && PersonalInventory.inventory.amount(ResourceType.STONE) > 0) {
        inv.add(ResourceType.STONE,1)
        PersonalInventory.inventory.remove(ResourceType.STONE,1)
      } else if (state == 1 && PersonalInventory.inventory.amount(ResourceType.WOOD) > 0) {
        inv.add(ResourceType.WOOD,1)
        PersonalInventory.inventory.remove(ResourceType.WOOD,1)
      } else if (state == 2 && PersonalInventory.inventory.amount(ResourceType.COIN) > 0) {
        inv.add(ResourceType.COIN,1)
        PersonalInventory.inventory.remove(ResourceType.COIN,1)
      }
    }
  }
}
