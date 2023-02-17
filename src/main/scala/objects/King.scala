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

    if(Direction.up && this.position.y > 0) this.position = (this.position.x, this.position.y - 1)
    if(Direction.left && this.position.x > 0) this.position = (this.position.x - 1, this.position.y)
    if(Direction.down && this.position.y < (Game.height)) this.position = (this.position.x, this.position.y + 1)
    if(Direction.right && this.position.x < (Game.width)) this.position = (this.position.x + 1 , this.position.y)
  }

  def attack() : Unit = {
    println("ATTACK!!!!")
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
    } else if (e.code == Keyboard.Key.KeySpace) {
      attack()
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
