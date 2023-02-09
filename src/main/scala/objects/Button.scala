package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

class Button(resource : String, width : Int, height : Int, onClick : Unit => Unit) extends GameObject {
  
  var state = 0 // 0 : IDLE, 1 : HOVER, 2 : CLICKED
  var sprite : Sprite = _
  def textureRect : Rect[Int] = {
    val y = state * height
    return Rect[Int](0, y, width, height)
  }

  def init() : Unit = {
    ResourceManager.load_resource(resource)

    sprite = ResourceManager.get_sprite(resource)
  }

  def update() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    states.transform *= transform
    sprite.draw(target, states)
  }

  override def close() : Unit = {
    sprite.close()
    ResourceManager.close(resource)
  }

  override def onMouseButtonPressed(e : Event.MouseButtonPressed) : Unit = {
    if (sprite.globalBounds.contains(e.x, e.y)) state = 2
  }

  override def onMouseButtonReleased(e : Event.MouseButtonReleased) : Unit = {
    if (state == 2 && sprite.globalBounds.contains(e.x, e.y)) state = 1
    else if (state == 2 && sprite.globalBounds.contains(e.x, e.y)) state = 0
  }

  override def onMouseMoved(e : Event.MouseMoved) : Unit = {
    if (state == 0 && sprite.globalBounds.contains(e.x, e.y)) state = 1
    else if (state == 1 && !sprite.globalBounds.contains(e.x, e.y)) state = 0
  }

}
