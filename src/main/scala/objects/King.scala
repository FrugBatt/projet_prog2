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

  override def update(): Unit = {
    super.update() 

    var movX = 0
    var movY = 0

    if(Direction.up) movY -= 1
    if(Direction.left) movX -= 1
    if(Direction.down) movY += 1
    if(Direction.right) movX += 1

    if (movX != 0 || movY != 0) context.safe_move(this, movX, movY)

    if (interacting_castle.isDefined){
      val x = position.x - interacting_castle.get.position.x
      val y = position.y - interacting_castle.get.position.y
      if (sqrt(x*x + y*y) > castle_range) {
        interacting_castle.get.inv_close()
        interacting_castle = None
      }
    }
  }

  override def onKeyPressed(e : Event.KeyPressed) : Unit = {
    if (e.code == Keyboard.Key.KeyZ) {
      Direction.up = true
      state = 4
    } else if (e.code == Keyboard.Key.KeyQ) {
      Direction.left = true
      state = 3
    } else if (e.code == Keyboard.Key.KeyS) {
      Direction.down = true
      state = 2
    } else if (e.code == Keyboard.Key.KeyD) {
      Direction.right = true
      state = 1
    } else if (e.code == Keyboard.Key.KeyI) {
      context.trigger(this.trigger_box, objs => objs.foreach(o => if(o.isInstanceOf[Base]){
        interacting_castle = Some(o.asInstanceOf[Base])
        interacting_castle.get.inv_display()}))
    }else if (e.code == Keyboard.Key.KeyC) {
      build()
    } else if (e.code == Keyboard.Key.KeyLeft) {
      if(interacting_castle.isDefined) interacting_castle.get.retrieve()
    } else if (e.code == Keyboard.Key.KeyRight) {
      if(interacting_castle.isDefined) interacting_castle.get.store()
    } else if (e.code == Keyboard.Key.KeySpace) {
      context.trigger(this.trigger_box, objs => objs.foreach(o => if(!o.isInstanceOf[King]){o.attack(2,this) match {
        case a : AttackKilled =>
          context.del(o)
          if (a.drop.isDefined) context.add(a.drop.get)
        case _ => ()
      }}))
    } else if (e.code == Keyboard.Key.KeyE) {
      context.trigger(this.trigger_box, objs => objs.foreach(o => o.interact() match {
        case a : ResourceCollectAction => 
          context.del(o)
          a.resourceType match {
            case ResourceType.WOOD =>
              Inventory.wood += 10
            case ResourceType.STONE => Inventory.stone += 10
            case ResourceType.COIN => Inventory.coin += 10
            case ResourceType.MEAT => Inventory.health = (Inventory.health + 3).min(10).max(0)
          }
        case _ => ()
      }))
    }
  }

  override def attack(dmg: Int, attacker: SpriteGameObject): AttackResponse = {

    Inventory.health = (Inventory.health - dmg).max(0)
    if (Inventory.health == 0) return AttackKilled(None)
    return AttackSuccess()
  }

  def build() : Unit = {
    if (!has_castle){
      if (Inventory.wood >= 4 && Inventory.stone >= 10 && Inventory.coin >= 1) {
        val castle = new Base(hud)
        castle.position = (this.position.x, this.position.y + sprite.textureRect.height)
        castle.update()
        context.add(castle)
        Inventory.wood -= 4
        Inventory.stone -= 10
        Inventory.coin -= 1
        has_castle = true
      }
      else println("not enough resources")
    }
    else println("you can only have one castle")
}


  override def onKeyReleased(e : Event.KeyReleased) : Unit = {

      if (e.code == Keyboard.Key.KeyZ) Direction.up = false
      if (e.code == Keyboard.Key.KeyS) Direction.down = false
      if (e.code == Keyboard.Key.KeyD) Direction.right = false
      if (e.code == Keyboard.Key.KeyQ) Direction.left = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) state = 0

  }

}
