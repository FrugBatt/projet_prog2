package prog2
package objects

import graphics.ResourceManager

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

class World() extends GameObject() {

  var resource : String = "game/terrain.png"
  var sprites : Vector[SpriteGameObject] = Vector()

  var width : Int = 1

  override def collision_box = None
  override def trigger_box = None
  
  def load_sprite(name : String) : Sprite = {
    ResourceManager.load_resource(resource)
    return ResourceManager.get_sprite(resource)
  }

  override def init() : Unit = {

  }

  def update() : Unit = {}
    
  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    sprites.foreach(_.draw(target, states))
  }

  override def close() : Unit = {
  }

}
