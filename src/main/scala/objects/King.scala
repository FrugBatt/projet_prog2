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

class King(context : Scene, hud : HudScene) extends AnimatedGameObject("game/king.png", 16, 17, Array(8,8,8,8,8)) {

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

    Control.moveForward.addListener(forward)
    Control.moveBackward.addListener(backward)
    Control.moveLeft.addListener(left)
    Control.moveRight.addListener(right)

    Control.castleInventory.addListener(castleInventory)

    Control.attack.addListener(attack)
    Control.harvest.addListener(harvest)
    Control.collect.addListener(collect)
    Control.build.addListener(build)
  }

  override def close() : Unit = {
    super.close()

    Control.moveForward.removeListener(forward)
    Control.moveBackward.removeListener(backward)
    Control.moveLeft.removeListener(left)
    Control.moveRight.removeListener(right)

    Control.castleInventory.removeListener(castleInventory)

    Control.attack.removeListener(attack)
    Control.harvest.removeListener(harvest)
    Control.collect.removeListener(collect)
    Control.build.removeListener(build)
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
        interacting_castle.get.inv_display()}))
    }
  }

  def attack (start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => if(!o.isInstanceOf[King]){o.damage(2,this) match {
        case a : AttackKilled =>
          context.del(o)
          if (a.drop.isDefined) context.add(a.drop.get)
        case _ => ()
      }}))
    }
  }

  def harvest(start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => o.interact(ResourceHarvestAction())))

    }
  }

  def collect(start : Boolean) : Unit = {
    if (start) {
      context.trigger_all(this.trigger_box, objs => objs.foreach(o => o.interact(ResourceCollectAction()) match {
        // case a : ResourceRetrievalAction => 
        //   a.resourceType match {
        //     case ResourceType.WOOD => PersonalInventory.inventory.add(ResourceType.WOOD, 1)
        //     case ResourceType.STONE => PersonalInventory.inventory.add(ResourceType.STONE, 1)
        //     case ResourceType.COIN => PersonalInventory.inventory.add(ResourceType.COIN, 1)
        //     case ResourceType.MEAT => PersonalInventory.health = (PersonalInventory.health + 3).min(10).max(0)
        // }
        case b : ResourceCollectResponse => 
          b.resourceType match {
            case ResourceType.WOOD => PersonalInventory.inventory.add(ResourceType.WOOD, 1)
            case ResourceType.STONE => PersonalInventory.inventory.add(ResourceType.STONE, 1)
            case ResourceType.COIN => {
              PersonalInventory.inventory.add(ResourceType.COIN, 1)
              context.del(o)
            }
            case ResourceType.MEAT => {
              PersonalInventory.health = (PersonalInventory.health + 3).min(10).max(0)
              context.del(o)
            }
          }
        case _ => ()
      }))
    }
  }

  override def damage(dmg: Int, attacker: SpriteGameObject): AttackResponse = {

    PersonalInventory.health = (PersonalInventory.health - dmg).max(0)
    if (PersonalInventory.health == 0) return AttackKilled(None)
    return AttackSuccess()
  }

  def build(start : Boolean) : Unit = {
    if (start) {
      if (!has_castle){
        if (PersonalInventory.inventory.amount(ResourceType.STONE) >= 10 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 4 && PersonalInventory.inventory.amount(ResourceType.COIN) >= 2) {
          val castle = new Base(hud,this)
          castle.position = (this.position.x, this.position.y + sprite.textureRect.height)
          castle.update()
          context.add(castle)
          PersonalInventory.inventory.remove(ResourceType.STONE,10)
          PersonalInventory.inventory.remove(ResourceType.WOOD,4)
          PersonalInventory.inventory.remove(ResourceType.COIN,2)
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
