package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View

import sfml.system.Vector2

class CenteredCamera(window : RenderTarget, width : Int, height : Int, zoom : Float, val obj : SpriteGameObject, side : Side) extends Camera(window, width, height, zoom, side) {
  
  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    v.center = obj.position
    super.draw(target, states)
  }

}
