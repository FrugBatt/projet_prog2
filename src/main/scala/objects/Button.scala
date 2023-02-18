package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

class Button(resource : String, width : Int, height : Int, onClick : () => Unit) extends StatedGameObject(resource, width, height) {
  
  // var state = 0 // 0 : IDLE, 1 : HOVER, 2 : CLICKED
  override def onMouseButtonPressed(e : Event.MouseButtonPressed) : Unit = {
    if (bounds.contains(e.x, e.y)) {
      state = 2
      onClick()
    }
  }

  override def onMouseButtonReleased(e : Event.MouseButtonReleased) : Unit = {
    if (bounds.contains(e.x, e.y)) state = 1
    else state = 0
  }

  override def onMouseMoved(e : Event.MouseMoved) : Unit = {
    if (state < 2) {
      if(bounds.contains(e.x, e.y)) state = 1
      else state = 0
    } else {
      if(bounds.contains(e.x, e.y)) state = 2
      else state = 3
    }
  }
}
