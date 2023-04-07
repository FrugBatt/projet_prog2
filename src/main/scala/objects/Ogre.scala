package prog2
package objects

import scene.Scene
import objects.King

import sfml.graphics.Rect
import sfml.system.Vector2

class Ogre(context: Scene) extends Enemy[King](context,10, () => new Resource("game/coin.png", 2, ResourceType.COIN),"game/ogre.png", 38, 23, Array(8,8,8,8,8), animationTime = 100L, hp_x_offset = 14, target_id = 1){

    override def collision_box = Some(Rect[Float](position.x + 10, position.y, 18, sprite.textureRect.height))
    override def trigger_box = Some(Rect[Float](position.x - 3, position.y - 3, sprite.textureRect.width + 6, sprite.textureRect.height + 6))

}