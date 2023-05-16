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

class EntityHP(maxhp: Int) extends StatedGameObject("game/entityhp.png", 10, 1) {

  var hp = maxhp
  var show = true

  override def collision_box = None


  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    if(show) sprite.draw(target, states)
  }


  override def update() : Unit = {
    super.update()

    state = (10f * (1f - (hp.toFloat/maxhp.toFloat))).toInt
  }

}
