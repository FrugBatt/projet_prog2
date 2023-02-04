package prog2
package objects

import graphics.ResourceManager

import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

class Image(loc : String) extends GameObject {

  def init() : Unit = {
    ResourceManager.load_resource(loc)
  }

  def update() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    ResourceManager.get_sprite(loc).draw(target, states)
  }

}
