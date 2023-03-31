package prog2
package objects

import events._
import objects.Resource
import sfml.graphics._
import sfml.system.Vector2

class ResourceDeposit(generator: ResourceGenerator, resource_type : ResourceType, resource: String, width: Int, height: Int) extends StatedGameObject(resource, width, height) {

    var quantity = 0

    position = Vector2(generator.position.x + generator.sprite.textureRect.width/2 , generator.position.y + generator.sprite.textureRect.height)
    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))
    override def collision_box = None

    override def update() : Unit = {
        super.update()
        state = quantity
        
    }

    override def interact() = {
    if (quantity > 0){
        quantity = quantity - 1
        return ResourceRetrievalAction(resource_type)
    }
    else
        return NoAction()
  }

}