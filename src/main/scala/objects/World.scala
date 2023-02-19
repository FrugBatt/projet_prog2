package prog2
package objects

import graphics.ResourceManager

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

class World() extends SpriteGameObject("game/terrain.png") {

  override def collision_box = None
  override def trigger_box = None
    
  def update() : Unit = {}
    
}
