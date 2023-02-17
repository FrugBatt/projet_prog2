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
import game.Game

class King extends AnimatedGameObject("game/king.png", 16, 17, Array(8,8,8,8,8)) {

  object Direction {
    var up : Boolean = false
    var down : Boolean = false
    var right : Boolean = false
    var left : Boolean = false
  }

  val w : Float = this.sprite.textureRect.width
  val h : Float = this.sprite.textureRect.height

  override def update(): Unit = {
    super.update() 

    val x = this.position.x
    val y = this.position.y 

    if(Direction.up && y > 0) this.position = (x, y - 1)
    if(Direction.left && x > 0) this.position = (x - 1, y)
    if(Direction.down && y < (Game.height)) this.position = (x, y + 1)
    if(Direction.right && x < (Game.width)) this.position = (x + 1 , y)
  }

  def attack() : Unit = {
    println("ATTACK!!!!")
  }

  override def onKeyPressed(e : Event.KeyPressed) : Unit = {
    if (e.code == Keyboard.Key.KeyZ) {
      Direction.up = true
      animationState = 4
    } else if (e.code == Keyboard.Key.KeyQ) {
      Direction.left = true
      animationState = 3
    } else if (e.code == Keyboard.Key.KeyS) {
      Direction.down = true
      animationState = 2
    } else if (e.code == Keyboard.Key.KeyD) {
      Direction.right = true
      animationState = 1
    } else if (e.code == Keyboard.Key.KeySpace) {
      attack()
    }
  }

  override def onKeyReleased(e : Event.KeyReleased) : Unit = {

      if (e.code == Keyboard.Key.KeyZ) Direction.up = false
      if (e.code == Keyboard.Key.KeyS) Direction.down = false
      if (e.code == Keyboard.Key.KeyD) Direction.right = false
      if (e.code == Keyboard.Key.KeyQ) Direction.left = false
      if (! (Direction.up || Direction.down || Direction.left || Direction.right)) animationState = 0

  }

}
