package prog2

import sfml.graphics.Drawable
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

class World extends GameObject {

  var sprite : Sprite = _

  def init() : Unit =
    println("world init")
    sprite = ResourceManager.get_sprite("cat")

  def update() : Unit =
    println("updated")


  def draw(target: RenderTarget, states: RenderStates) : Unit =
    this.sprite.draw(target, states)
  
}
