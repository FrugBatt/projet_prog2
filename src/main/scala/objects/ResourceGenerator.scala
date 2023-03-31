package prog2
package objects

import events._
import objects.Resource
import sfml.graphics._
import sfml.system.Vector2

class ResourceGenerator(resource_type: ResourceType, resource: String, val pos: Vector2[Float] = Vector2(0,0)) extends SpriteGameObject(resource){
  var deposit : Option[ResourceDeposit] = None

  position = pos
  if (resource_type == ResourceType.WOOD) {
    deposit = Some(new ResourceDeposit(this, resource_type, "game/wood.png",24,22))
  }
  else {
    deposit = Some(new ResourceDeposit(this, resource_type, "game/rock.png",26,19))
  }

  override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))
  override def collision_box = None

  def update() : Unit = {}
  
  override def interact() = {
    if(deposit.isDefined){
    deposit.get.quantity = (deposit.get.quantity + 1).min(3)
    return ResourceHarvestAction(resource_type)
    }
    else return NoAction()
  }
}
