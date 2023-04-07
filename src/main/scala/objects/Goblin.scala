package prog2
package objects

import scene.Scene
import objects.King

import sfml.graphics.Rect
import sfml.system.Vector2

class Goblin(context: Scene) extends Enemy[Base](context,10, () => new Resource("game/coin.png", 2, ResourceType.COIN),"game/goblin.png", 16, 18, Array(5,8,8,8,8), animationTime = 100L, hp_x_offset = 3, target_id = 2){

    override def collision_box = Some(Rect[Float](position.x + 3, position.y, 10, sprite.textureRect.height))
    override def trigger_box = Some(Rect[Float](position.x - 3, position.y - 3, sprite.textureRect.width + 6, sprite.textureRect.height + 6))

}