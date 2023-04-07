package prog2
package objects

import events._
import objects.Resource
import sfml.graphics._
import sfml.system.Vector2

class ResourceGenerator(resource_type: ResourceType, resource: String, val pos: Vector2[Float] = Vector2(0,0)) extends SpriteGameObject(resource){

  position = pos

  val deposit : ResourceDeposit = {
    if (resource_type == ResourceType.WOOD) {
      new ResourceDeposit(this, resource_type, "game/wood.png",24,22)
    }
    else {
      new ResourceDeposit(this, resource_type, "game/rock.png",26,19)
    }
}

  override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))
  override def collision_box = None

  def update() : Unit = {}
  
  override def interact(action : InteractionAction) : InteractionResponse = {
    action match {
      case _ : ResourceHarvestAction => {
        deposit.quantity = (deposit.quantity + 1).min(3)
        return ResourceHarvestResponse(resource_type)
      }
      case _ => return NoAction()
    }
  }
}
