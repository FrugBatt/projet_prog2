package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import events.ResourceCollectAction

enum ResourceType {
  case WOOD
  case STONE
}

class Resource(resource : String, val value: Int, val kind: ResourceType) extends SpriteGameObject(resource){
  
  override def collision_box = None

  def update() : Unit = {}

  override def interact() = {
    return ResourceCollectAction(kind)
  }

}



