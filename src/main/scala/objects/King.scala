package prog2
package objects

import graphics.ResourceManager

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

class King(context : Scene) extends AnimatedGameObject("game/king.png", 16, 17, Array(8,8,8,8,8)) {

  object Direction {
    var up : Boolean = false
    var down : Boolean = false
    var right : Boolean = false
    var left : Boolean = false
  }

  val w : Float = this.sprite.textureRect.width
  val h : Float = this.sprite.textureRect.height

  override def init() : Unit = {
    super.init()

    Control.moveForward.addListener(forward)
    Control.moveBackward.addListener(backward)
    Control.moveLeft.addListener(left)
    Control.moveRight.addListener(right)

    Control.attack.addListener(attack)
    Control.collect.addListener(collect)
  }

  override def update(): Unit = {
    super.update() 

    var movX = 0
    var movY = 0

    if(Direction.up) movY -= 1
    if(Direction.left) movX -= 1
    if(Direction.down) movY += 1
    if(Direction.right) movX += 1

    if (movX != 0 || movY != 0) context.safe_move(this, movX, movY)
  }

  def forward(start : Boolean) : Unit = {
    if start {
      Direction.up = true
      state = 4
    } else {
      Direction.up = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def backward(start : Boolean) : Unit = {
    if start {
      Direction.down = true
      state = 2
    } else {
      Direction.down = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def left(start : Boolean) : Unit = {
    if start {
      Direction.left = true
      state = 3
    } else {
      Direction.left = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def right(start : Boolean) : Unit = {
    if start {
      Direction.right = true
      state = 1
    } else {
      Direction.right = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0
    }
  }

  def attack (start : Boolean) : Unit = {
    context.trigger_all(this.trigger_box, objs => objs.foreach(o => if(!o.isInstanceOf[King]){o.damage(2,this) match {
      case a : AttackKilled =>
        context.del(o)
        if (a.drop.isDefined) context.add(a.drop.get)
      case _ => ()
    }}))
  }

  def collect(start : Boolean) : Unit = {
    context.trigger_all(this.trigger_box, objs => objs.foreach(o => o.interact() match {
      case a : ResourceRetrievalAction => 
        a.resourceType match {
          case ResourceType.WOOD => PersonalInventory.inventory.add(ResourceType.WOOD, 1)
          case ResourceType.STONE => PersonalInventory.inventory.add(ResourceType.STONE, 1)
          case ResourceType.COIN => PersonalInventory.inventory.add(ResourceType.COIN, 1)
          case ResourceType.MEAT => PersonalInventory.health = (PersonalInventory.health + 3).min(10).max(0)
        }
      case b : ResourceCollectAction => 
        context.del(o)
        b.resourceType match {
          case ResourceType.WOOD => PersonalInventory.inventory.add(ResourceType.WOOD, 1)
          case ResourceType.STONE => PersonalInventory.inventory.add(ResourceType.STONE, 1)
          case ResourceType.COIN => PersonalInventory.inventory.add(ResourceType.COIN, 1)
          case ResourceType.MEAT => PersonalInventory.health = (PersonalInventory.health + 3).min(10).max(0)
        }
      case _ => ()
    }))
  }

  override def damage(dmg: Int, attacker: SpriteGameObject): AttackResponse = {

    PersonalInventory.health = (PersonalInventory.health - dmg).max(0)
    if (PersonalInventory.health == 0) return AttackKilled(None)
    return AttackSuccess()
  }
}
