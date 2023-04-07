package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import events._

enum ResourceType {
  case WOOD
  case STONE
  case COIN
  case MEAT
}

class Resource(resource : String, val value: Int = 1, val kind: ResourceType) extends SpriteGameObject(resource){
  
  override def collision_box = None

  def update() : Unit = {}

  override def interact(action : InteractionAction) : InteractionResponse = {
    action match {
      case _ : ResourceCollectAction => return ResourceCollectResponse(kind)
      case _ => return NoAction()
    }
  }

}



