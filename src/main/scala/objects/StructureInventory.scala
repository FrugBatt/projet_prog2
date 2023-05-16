package prog2
package objects

import sfml.window.Event
import scala.compiletime.ops.boolean
import sfml.window.Keyboard
import sfml.system.Vector2
import game.Game
import objects.Resource
import events.Control
import sfml.graphics._

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

  var player : PlayerState = PlayerState.P1

  override def init() : Unit = {
    super.init()
    
    // this.position = Vector2(Game.width - 180,Game.height/10)
    Control.castleUp.addListener(castleUp)
    Control.castleDown.addListener(castleDown)
    Control.castleLeft.addListener(retrieve)
    Control.castleRight.addListener(store)
  }

  override def update() : Unit = {
    super.update()

    player match {
      case PlayerState.P1 => this.position = Vector2(250,5 * Game.height/10)
      case PlayerState.P2 => this.position = Vector2(Game.width - 200, 5 * Game.height/10)
    }

    stone_display.position = Vector2(position.x+85,position.y + 22)
    wood_display.position = Vector2(position.x+85,position.y + 77)
    coin_display.position = Vector2(position.x+85,position.y + 143)
    hp_display.position =Vector2(position.x+15,position.y + 190)
  }

  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    super.draw(target,states)
  }

  override def close() : Unit = {
    super.close()

    Control.castleUp.removeListener(castleUp)
    Control.castleDown.removeListener(castleDown)
    Control.castleLeft.removeListener(retrieve)
    Control.castleRight.removeListener(store)
  }

  def castleUp(start : Boolean) : Unit = {
    if (start && is_displayed) {
      state = (state%4 - 1).max(0)
      if (hp < maxhp) state += 4
    }
  }

  def castleDown(start : Boolean) : Unit = {
    if (start && is_displayed) {
    state = (state%4 + 1).min(3)
    if (hp < maxhp) state += 4
    }
  }

  def retrieve(start : Boolean) : Unit = {
    if (start && is_displayed) {
      if (state%4 == 0 && inv.amount(ResourceType.STONE) > 0)  {
        inv.remove(ResourceType.STONE,1)

        player match {
          case PlayerState.P1 => PersonalInventory.inventoryP1.add(ResourceType.STONE,1)
          case PlayerState.P2 => PersonalInventory.inventoryP2.add(ResourceType.STONE,1)
        }
      } else if (state%4 == 1 && inv.amount(ResourceType.WOOD) > 0)  {
        inv.remove(ResourceType.WOOD,1)

        player match {
          case PlayerState.P1 => PersonalInventory.inventoryP1.add(ResourceType.WOOD,1)
          case PlayerState.P2 => PersonalInventory.inventoryP2.add(ResourceType.WOOD,1)
        }
      } else if (state%4 == 2 && inv.amount(ResourceType.COIN) > 0)  {
        inv.remove(ResourceType.COIN,1)

        player match {
          case PlayerState.P1 => PersonalInventory.inventoryP1.add(ResourceType.COIN,1)
          case PlayerState.P2 => PersonalInventory.inventoryP2.add(ResourceType.COIN,1)
        }
      }
    }
  }

  def store(start : Boolean) : Unit = {
    if (start && is_displayed) {
      player match {
        case PlayerState.P1 => storeP1()
        case PlayerState.P2 => storeP2()
      }
    }
  }

  def storeP1() : Unit = {
    if (state%4 == 0 && PersonalInventory.inventoryP1.amount(ResourceType.STONE) > 0) {
      inv.add(ResourceType.STONE,1)
      PersonalInventory.inventoryP1.remove(ResourceType.STONE,1)
    } else if (state%4 == 1 && PersonalInventory.inventoryP1.amount(ResourceType.WOOD) > 0) {
      inv.add(ResourceType.WOOD,1)
      PersonalInventory.inventoryP1.remove(ResourceType.WOOD,1)
    } else if (state%4 == 2 && PersonalInventory.inventoryP1.amount(ResourceType.COIN) > 0) {
      inv.add(ResourceType.COIN,1)
      PersonalInventory.inventoryP1.remove(ResourceType.COIN,1)
    } else if (state == 7 && PersonalInventory.inventoryP1.amount(ResourceType.STONE) >= 4 && PersonalInventory.inventoryP1.amount(ResourceType.WOOD) >= 2) {
      PersonalInventory.inventoryP1.remove(ResourceType.STONE,4)
      PersonalInventory.inventoryP1.remove(ResourceType.WOOD,2)
      hp = (hp + 10).min(maxhp)
      if (hp == maxhp) state = state - 4
    }
  }

  def storeP2() : Unit = {
    if (state%4 == 0 && PersonalInventory.inventoryP2.amount(ResourceType.STONE) > 0) {
      inv.add(ResourceType.STONE,1)
      PersonalInventory.inventoryP2.remove(ResourceType.STONE,1)
    } else if (state%4 == 1 && PersonalInventory.inventoryP2.amount(ResourceType.WOOD) > 0) {
      inv.add(ResourceType.WOOD,1)
      PersonalInventory.inventoryP2.remove(ResourceType.WOOD,1)
    } else if (state%4 == 2 && PersonalInventory.inventoryP2.amount(ResourceType.COIN) > 0) {
      inv.add(ResourceType.COIN,1)
      PersonalInventory.inventoryP2.remove(ResourceType.COIN,1)
    } else if (state == 7 && PersonalInventory.inventoryP2.amount(ResourceType.STONE) >= 4 && PersonalInventory.inventoryP2.amount(ResourceType.WOOD) >= 2) {
      PersonalInventory.inventoryP2.remove(ResourceType.STONE,4)
      PersonalInventory.inventoryP2.remove(ResourceType.WOOD,2)
      hp = (hp + 10).min(maxhp)
      if (hp == maxhp) state = state - 4
    }
  }
  // 
  //     if (state%4 == 0 && PersonalInventory.inventory.amount(ResourceType.STONE) > 0) {
  //       inv.add(ResourceType.STONE,1)

  //       player match {
  //         case PlayerState.P1 => PersonalInventory.inventoryP1.remove(ResourceType.STONE,1)
  //         case PlayerState.P2 => PersonalInventory.inventoryP2.remove(ResourceType.STONE,1)
  //       }
  //     } else if (state%4 == 1 && PersonalInventory.inventory.amount(ResourceType.WOOD) > 0) {
  //       inv.add(ResourceType.WOOD,1)

  //       player match {
  //         case PlayerState.P1 => PersonalInventory.inventoryP1.remove(ResourceType.WOOD,1)
  //         case PlayerState.P2 => PersonalInventory.inventoryP2.remove(ResourceType.WOOD,1)
  //       }
  //     } else if (state%4 == 2 && PersonalInventory.inventory.amount(ResourceType.COIN) > 0) {
  //       inv.add(ResourceType.COIN,1)

  //       player match {
  //         case PlayerState.P1 => PersonalInventory.inventoryP1.remove(ResourceType.COIN,1)
  //         case PlayerState.P2 => PersonalInventory.inventoryP2.remove(ResourceType.COIN,1)
  //       }
  //     } else if (state == 7 && PersonalInventory.inventory.amount(ResourceType.STONE) >= 4 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 2) {
  //       hp = (hp+10).min(maxhp)
  //       if (hp == maxhp) state = state - 4

  //       p match {
  //         case PlayerState.P1 => PersonalInventory.inventoryP1.remove(ResourceType.STONE,4)
  //           PersonalInventory.inventoryP1.remove(ResourceType.WOOD,2)
  //         case PlayerState.P2 => PersonalInventory.inventoryP2.remove(ResourceType.STONE,4)
  //           PersonalInventory.inventoryP2.remove(ResourceType.WOOD,2)
  //       }
  //     }
  //   }
  // }
}
