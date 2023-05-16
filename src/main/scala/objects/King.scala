package prog2
package objects

import graphics.ResourceManager

import scala.math._
import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.system.Vector2
import events._
import scene.Scene
import scene.HudScene
import game.Game
import objects.Resource


enum PlayerState {
  case P1
  case P2
}

class King(context : Scene, p : PlayerState) extends AnimatedGameObject("game/king.png", 16, 17, Array(8,8,8,8,8)) {

  override def id = 1

  object Direction {
    var up : Boolean = false
    var down : Boolean = false
    var right : Boolean = false
    var left : Boolean = false
  }

  val w : Float = this.sprite.textureRect.width
  val h : Float = this.sprite.textureRect.height

  var has_castle : Boolean = false
  var interacting_castle : Option[Base] = None
  val castle_range : Float = 300f

  override def init() : Unit = {
    super.init()
    
    p match {
      case PlayerState.P1 =>
        Control.moveForwardP1.addListener(forward)
        Control.moveBackwardP1.addListener(backward)
        Control.moveLeftP1.addListener(left)
        Control.moveRightP1.addListener(right)

        Control.attackP1.addListener(attack)
        Control.harvestP1.addListener(harvest)
        Control.collectP1.addListener(collect)
        Control.buildP1.addListener(build)

        Control.castleInventoryP1.addListener(castleInventory)
      case PlayerState.P2 =>
        Control.moveForwardP2.addListener(forward)
        Control.moveBackwardP2.addListener(backward)
        Control.moveLeftP2.addListener(left)
        Control.moveRightP2.addListener(right)

        Control.attackP2.addListener(attack)
        Control.harvestP2.addListener(harvest)
        Control.collectP2.addListener(collect)
        Control.buildP2.addListener(build)

        Control.castleInventoryP2.addListener(castleInventory)
    }


  }

  override def close() : Unit = {
    super.close()
    
    p match {
      case PlayerState.P1 =>
        Control.moveForwardP1.removeListener(forward)
        Control.moveBackwardP1.removeListener(backward)
        Control.moveLeftP1.removeListener(left)
        Control.moveRightP1.removeListener(right)

        Control.attackP1.removeListener(attack)
        Control.harvestP1.removeListener(harvest)
        Control.collectP1.removeListener(collect)
        Control.buildP1.removeListener(build)

        Control.castleInventoryP1.removeListener(castleInventory)
      case PlayerState.P2 =>
        Control.moveForwardP2.removeListener(forward)
        Control.moveBackwardP2.removeListener(backward)
        Control.moveLeftP2.removeListener(left)
        Control.moveRightP2.removeListener(right)

        Control.attackP2.removeListener(attack)
        Control.harvestP2.removeListener(harvest)
        Control.collectP2.removeListener(collect)
        Control.buildP2.removeListener(build)

        Control.castleInventoryP2.removeListener(castleInventory)
    }


  }

  override def update(): Unit = {
    super.update() 



    var movX = 0
    var movY = 0

    if(Direction.up) movY -= 1
    if(Direction.left) movX -= 1
    if(Direction.down) movY += 1
    if(Direction.right) movX += 1

    if (movX != 0 || movY != 0) {
      context.safe_move(this, movX, movY)
      if (interacting_castle.isDefined){
        interacting_castle.get.inv_close()
        interacting_castle = None
      }
    }
  }

  def forward(start : Boolean) : Unit = {
    if (start) {
      Direction.up = true
      state = 4
    } else {
      Direction.up = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def backward(start : Boolean) : Unit = {
    if (start) {
      Direction.down = true
      state = 2
    } else {
      Direction.down = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def left(start : Boolean) : Unit = {
    if (start) {
      Direction.left = true
      state = 3
    } else {
      Direction.left = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def right(start : Boolean) : Unit = {
    if (start){
      Direction.right = true
      state = 1
    } else {
      Direction.right = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def castleInventory(start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => if(o.isInstanceOf[Base]){
        interacting_castle = Some(o.asInstanceOf[Base])
        interacting_castle.get.inventory.player = p
        interacting_castle.get.inv_display()
      }))
    }
  }

  def attack (start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => if(o.id == 3){o.damage(2,this) match {
        case a : AttackKilled =>
          context.del(o)
          if (a.drop.isDefined) context.add(a.drop.get)
        case _ => ()
      }}))
    }
  }

  def harvest(start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => o.interact(ResourceHarvestAction(p)) match{
        case a: ResourceHarvestResponse =>
          val axe = new AnimationObject(context,position.x-8,position.y-8,"game/axe.png",32,32,Array(8),60L)
          axe.animate(0)
        case _ => ()
      }
        ))
      

    }
  }

  def getInv(res : ResourceType) = p match {
    case PlayerState.P1 => PersonalInventory.inventoryP1.amount(res)
    case PlayerState.P2 => PersonalInventory.inventoryP2.amount(res)
  }

  def addingInv(res : ResourceType, amount : Int) = p match {
    case PlayerState.P1 => PersonalInventory.inventoryP1.add(res, amount)
    case PlayerState.P2 => PersonalInventory.inventoryP2.add(res, amount)
  }

  def getHealth() = p match {
    case PlayerState.P1 => PersonalInventory.healthP1
    case PlayerState.P2 => PersonalInventory.healthP2
  }

  def addingHealth(amount : Int) = p match {
    case PlayerState.P1 => PersonalInventory.healthP1 = (PersonalInventory.healthP1 + amount).min(10).max(0)
    case PlayerState.P2 => PersonalInventory.healthP2 = (PersonalInventory.healthP2 + amount).min(10).max(0)
  }

  def collect(start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => o.interact(ResourceCollectAction(p)) match {
        case b : ResourceCollectResponse => 
          b.resourceType match {
            case ResourceType.WOOD => addingInv(ResourceType.WOOD, 1)
            case ResourceType.STONE => addingInv(ResourceType.STONE, 1)
            case ResourceType.COIN => {
              addingInv(ResourceType.COIN, 1)
              context.del(o)
            }
            case ResourceType.MEAT => {
              addingHealth(3)
              context.del(o)
            }
          }
        case _ => ()
      }))
    }
  }

  override def damage(dmg: Int, attacker: SpriteGameObject): AttackResponse = {
    addingHealth(-dmg)
    if (getHealth() == 0) return AttackKilled(None)
    return AttackSuccess()
  }

  def build(start : Boolean) : Unit = {
    if (start) {
      if (!has_castle){
        if (getInv(ResourceType.STONE) >= 10 && getInv(ResourceType.WOOD) >= 4 && getInv(ResourceType.COIN) >= 2) {
          val castle = new Base(this)
          castle.position = (this.position.x, this.position.y + sprite.textureRect.height)
          castle.update()
          context.add(castle)
          addingInv(ResourceType.STONE, -10)
          addingInv(ResourceType.WOOD, -4)
          addingInv(ResourceType.COIN, -2)
          has_castle = true
        }
        else println("not enough resources")
      }
      else println("you can only have one castle")
    }
}


  override def onKeyReleased(e : Event.KeyReleased) : Unit = {

      if (e.code == Keyboard.Key.KeyZ) Direction.up = false
      if (e.code == Keyboard.Key.KeyS) Direction.down = false
      if (e.code == Keyboard.Key.KeyD) Direction.right = false
      if (e.code == Keyboard.Key.KeyQ) Direction.left = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0

  }
}
