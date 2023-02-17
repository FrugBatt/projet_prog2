package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View

import sfml.system.Vector2

class Camera(val window : RenderTarget, val width : Int, val height : Int) {
  
  var position : Vector2[Float] = Vector2(100f, 100f)
  def position_=(x: Float, y: Float) = position = Vector2(x, y)

  def view() : Unit = {
    val v = View(Rect[Float](0f, 0f, width, height))
    v.zoom(0.2f)
    v.center = position
    window.view = v
  }

}
