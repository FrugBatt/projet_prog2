package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

class Button(resource : String, width : Int, height : Int, onClick : () => Unit) extends GameObject(resource) {
  
  var state = 0 // 0 : IDLE, 1 : HOVER, 2 : CLICKED
  def textureRect : Rect[Int] = {
    val y = state * height
    return Rect[Int](0, y, width, height)
  }

  def update() : Unit = {
    sprite.textureRect = textureRect
  }

  override def onMouseButtonPressed(e : Event.MouseButtonPressed) : Unit = {
    if (bounds.contains(e.x, e.y)) {
      state = 2
      onClick()
    }
  }

  override def onMouseButtonReleased(e : Event.MouseButtonReleased) : Unit = {
    if (state == 2 && bounds.contains(e.x, e.y)) state = 1
    else if (state == 2) state = 0
  }

  override def onMouseMoved(e : Event.MouseMoved) : Unit = {
    if (state == 0 && bounds.contains(e.x, e.y)) state = 1
    else if (state == 1 && !bounds.contains(e.x, e.y)) state = 0
  }

}
