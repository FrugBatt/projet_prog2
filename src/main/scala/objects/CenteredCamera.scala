package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View

import sfml.system.Vector2

class CenteredCamera(val window : RenderTarget, val width : Int, val height : Int, val obj : SpriteGameObject) extends GameObject {
  
  def init() : Unit = {}

  def update() : Unit = {
    val v = View(Rect[Float](0f, 0f, width, height))
    v.zoom(0.2f)
    v.center = obj.position
    window.view = v
  }

  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {}

}
