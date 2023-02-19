package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View

import sfml.system.Vector2

class Camera(val window : RenderTarget, val width : Int, val height : Int) extends GameObject {
  
  val v = View(Rect[Float](0f, 0f, width, height))

  def init() : Unit = {}

  def update() : Unit = {}

  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    window.view = v
  }

}
