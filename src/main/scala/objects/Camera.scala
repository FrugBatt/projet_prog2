package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View

import sfml.window.Event

import sfml.system.Vector2

class Camera(val window : RenderTarget, val width : Int, val height : Int, val zoom : Float) extends GameObject {
  
  var v = {
    val v = View(Rect[Float](0f, 0f, width, height))
    v.zoom(zoom)
    v
  }

  def collision_box = None
  def trigger_box = None

  def init() : Unit = {}

  def update() : Unit = {}

  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    window.view = v
  }

  override def onResized(e : Event.Resized) : Unit = {
    val centerc = v.center
    v.size = (e.width, e.height)
    v.zoom(zoom)
    v.center = centerc
  }

}
