package prog2
package objects

import graphics.ResourceManager

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

class Image(loc : String) extends GameObject {

  var sprite : Sprite = _

  def init() : Unit = {
    ResourceManager.load_resource(loc)

    sprite = ResourceManager.get_sprite(loc)
  }

  def update() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    states.transform *= transform
    sprite.draw(target, states)
  }

  override def close() : Unit = {
    sprite.close()
    ResourceManager.close(loc)
  }

}
