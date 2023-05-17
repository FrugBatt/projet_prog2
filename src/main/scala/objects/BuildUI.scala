package prog2
package objects

import events._
import scene._
import game.Game
import objects.TextGameObject
import objects.PersonalInventory
import objects.King
import sfml.system.Vector2

class BuildUI(player : PlayerState) extends StatedGameObject("game/buildUI.png",110,62) {
  var active = false

  def king : King = player match {
    case PlayerState.P1 => GameScene.king1
    case PlayerState.P2 => GameScene.king2
  }

  scale = Vector2(3f,3f)
  position = Vector2(20, Game.height - 300)


  val castle_build = new TextGameObject("Castle       20    4     2", 25)
  // castle_build.position = Vector2(40, Game.height - 240)
  val barracks_build = new TextGameObject("Barracks   10    10   4", 25)
  // barracks_build.position = Vector2(40, Game.height - 203)
  val mine_build = new TextGameObject("Mine          15    8    2", 25)
  // mine_build.position = Vector2(40, Game.height - 166)

  player match {
    case PlayerState.P1 => position = Vector2(20, Game.height - 300)
      castle_build.position = Vector2(40, Game.height - 240)
      barracks_build.position = Vector2(40, Game.height - 203)
      mine_build.position = Vector2(40, Game.height - 166)
    case PlayerState.P2 => position = Vector2(Game.width - 350, Game.height - 300)
      castle_build.position = Vector2(Game.width - 350 + 20, Game.height - 240)
      barracks_build.position = Vector2(Game.width - 350 + 20, Game.height - 203)
      mine_build.position = Vector2(Game.width - 350 + 20, Game.height - 166)
  }



  override def init() : Unit = {
    super.init()


    Control.uiUp.addListener(up)
    Control.uiDown.addListener(down)
    Control.uiConfirm.addListener(build)
  }

  override def close() : Unit = {
    super.close()

    Control.uiUp.removeListener(up)
    Control.uiDown.removeListener(down)
    Control.uiConfirm.removeListener(build)
  }

  def uidisplay() : Unit = {
    HudScene.add(this)
    HudScene.add(castle_build)
    HudScene.add(barracks_build)
    HudScene.add(mine_build)
    active = true
  }

  def uiclose() : Unit = {
    HudScene.del(this)
    HudScene.del(castle_build)
    HudScene.del(barracks_build)
    HudScene.del(mine_build)
    active = false
  }

  def up(start: Boolean) : Unit = {
    if (start) state = (state-1).max(0)
  }
  def down(start: Boolean) : Unit = {
    if (start) state = (state+1).min(2)
  }

  def addResource(resource : ResourceType, amount : Int) : Unit = {
    player match {
      case PlayerState.P1 => PersonalInventory.inventoryP1.add(resource, amount)
      case PlayerState.P2 => PersonalInventory.inventoryP2.add(resource, amount)
    }
  }

  def amountResource(resource : ResourceType) : Int = {
    player match {
      case PlayerState.P1 => PersonalInventory.inventoryP1.amount(resource)
      case PlayerState.P2 => PersonalInventory.inventoryP2.amount(resource)
    }
  }

  def build(start: Boolean) : Unit = {
    if (start && active) {
      if(state == 0){
        if (!king.has_castle){
          if (amountResource(ResourceType.STONE) >= 20 && amountResource(ResourceType.WOOD) >= 4 && amountResource(ResourceType.COIN) >= 2) {
            val castle = new Castle()
            castle.position = (king.position.x, king.position.y + king.sprite.textureRect.height)
            castle.update()
            GameScene.add(castle)
            addResource(ResourceType.STONE, -20)
            addResource(ResourceType.WOOD, -4)
            addResource(ResourceType.COIN, -2)
            king.has_castle = true
          } else println("not enough resources")
        } else println("you can only have one castle")
      }

      if (state == 1) {
        if (amountResource(ResourceType.STONE) >= 10 && amountResource(ResourceType.WOOD) >= 10 && amountResource(ResourceType.COIN) >= 4) {
          val barracks = new Barracks()
          barracks.position = (king.position.x, king.position.y + king.sprite.textureRect.height)
          GameScene.add(barracks)
          addResource(ResourceType.STONE, -10)
          addResource(ResourceType.WOOD, -10)
          addResource(ResourceType.COIN, -4)
        } else println("not enough resources")
      }

      if (state == 2) {
        if (amountResource(ResourceType.STONE) >= 15 && amountResource(ResourceType.WOOD) >= 4 && amountResource(ResourceType.COIN) >= 0) {
          val mine = new Mine()
          mine.position = (king.position.x, king.position.y + king.sprite.textureRect.height)
          GameScene.add(mine)
          addResource(ResourceType.STONE, -15)
          addResource(ResourceType.WOOD, -8)
          addResource(ResourceType.COIN, -2)
        } else println("not enough resources")
      }
    }
  }
}
