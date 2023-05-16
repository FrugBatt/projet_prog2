package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard
import sfml.system.Vector2
import game.Game
import objects._
import events.Control

class StructureInventory(maxhp : Int) extends StatedGameObject("game/castle_inventory.png",50,90) {
  position = Vector2(Game.width - 180,Game.height/10)
  scale = Vector2(3f,3f)

  
  val inv = new Inventory()

  var is_displayed : Boolean = false

  var hp : Int = maxhp
  val stone_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.STONE))
  stone_display.position = Vector2(position.x+85,position.y + 22)
  stone_display.characterSize_=(40)
  val wood_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.WOOD))
  wood_display.position = Vector2(position.x+85,position.y + 77)
  wood_display.characterSize_=(40)
  val coin_display = new UpdatableTextGameObject(() => inv.amount(ResourceType.COIN))
  coin_display.position = Vector2(position.x+85,position.y + 143)
  coin_display.characterSize_=(40)
  val hp_display = new UpdatableTextGameObject(() => hp, "hp: ", "/40")
  hp_display.position =Vector2(position.x+15,position.y + 190)
  hp_display.characterSize_=(25)

  override def init() : Unit = {
    super.init()

    Control.uiUp.addListener(invUp)
    Control.uiDown.addListener(invDown)
    Control.uiLeft.addListener(retrieve)
    Control.uiRight.addListener(store)
  }

  override def close() : Unit = {
    super.close()

    Control.uiUp.removeListener(invUp)
    Control.uiDown.removeListener(invDown)
    Control.uiLeft.removeListener(retrieve)
    Control.uiRight.removeListener(store)
  }

  def invUp(start : Boolean) : Unit = {
    if (start) {
      state = (state%4 - 1).max(0)
      if (hp < maxhp) state += 4
    }
  }

  def invDown(start : Boolean) : Unit = {
    if (start) {
    state = (state%4 + 1).min(3)
    if (hp < maxhp) state += 4
    }
  }

  def retrieve(start : Boolean) : Unit = {
    if (start) {
      if (state%4 == 0 && inv.amount(ResourceType.STONE) > 0)  {
        inv.remove(ResourceType.STONE,1)
        PersonalInventory.inventory.add(ResourceType.STONE,1)
      } else if (state%4 == 1 && inv.amount(ResourceType.WOOD) > 0)  {
        inv.remove(ResourceType.WOOD,1)
        PersonalInventory.inventory.add(ResourceType.WOOD,1)
      } else if (state%4 == 2 && inv.amount(ResourceType.COIN) > 0)  {
        inv.remove(ResourceType.COIN,1)
        PersonalInventory.inventory.add(ResourceType.COIN,1)
      }
    }
  }

  def store(start : Boolean) : Unit = {
    if (start) {
      if (state%4 == 0 && PersonalInventory.inventory.amount(ResourceType.STONE) > 0) {
        inv.add(ResourceType.STONE,1)
        PersonalInventory.inventory.remove(ResourceType.STONE,1)
      } else if (state%4 == 1 && PersonalInventory.inventory.amount(ResourceType.WOOD) > 0) {
        inv.add(ResourceType.WOOD,1)
        PersonalInventory.inventory.remove(ResourceType.WOOD,1)
      } else if (state%4 == 2 && PersonalInventory.inventory.amount(ResourceType.COIN) > 0) {
        inv.add(ResourceType.COIN,1)
        PersonalInventory.inventory.remove(ResourceType.COIN,1)
      }
        else if (state == 7 && PersonalInventory.inventory.amount(ResourceType.STONE) >= 4 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 2) {
          hp = (hp+10).min(maxhp)
          if (hp == maxhp) state = state - 4
          PersonalInventory.inventory.remove(ResourceType.STONE,4)
          PersonalInventory.inventory.remove(ResourceType.WOOD,2)
        }
    }
  }
}
