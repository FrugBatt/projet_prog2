package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect
import sfml.graphics.View
import sfml.Immutable

import sfml.window.Event

import sfml.system.Vector2

enum Side {
  case Left
  case Right
  case Full
}

class Camera(val window : RenderTarget, val width : Int, val height : Int, val zoom : Float, val side : Side) extends GameObject {
  
  var v = {
    val v = side match {
      case Side.Full => View(Rect[Float](0f, 0f, width, height))
      case _ => View(Rect[Float](0f, 0f, width / 2, height))
    }

    side match {
      case Side.Left => v.viewport = Rect[Float](0f, 0f, 0.5, 1)
      case Side.Right => v.viewport = Rect[Float](0.5f, 0f, 0.5, 1)
      case Side.Full => v.viewport = Rect[Float](0f, 0f, 1, 1)
    }

    v.zoom(zoom)
    v
  }

  def collision_box = None
  def trigger_box = None

  def init() : Unit = {}

  def update() : Unit = {}

  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    window.view = Immutable(v)
  }

  override def onResized(e : Event.Resized) : Unit = {
    side match {
      case Side.Full => v.size = (e.width, e.height)
      case _ => v.size = (e.width / 2, e.height)
    }
    v.zoom(zoom)
  }

}
