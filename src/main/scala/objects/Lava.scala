package prog2
package objects

import events._
import scene._
import game.Game
import objects.TextGameObject
import objects.PersonalInventory
import objects.King
import sfml.system.Vector2
import sfml.graphics.Rect

object Lava extends StatedGameObject("game/lava.png",256,256){
  var progress : Float = 0f
  val start = System.currentTimeMillis()
  val delay : Long = 10000L

  override def collision_box = None

  override def update() = {
    super.update()

    progress = ((System.currentTimeMillis - start - delay).toFloat/500).max(0f)
    position = Vector2(progress - 256, 0f)

    GameScene.objects.foreach(obj => if (!(obj == this) && !(obj.isInstanceOf[King]) && obj.isInstanceOf[SpriteGameObject] && obj.asInstanceOf[SpriteGameObject].center.x < progress) GameScene.del(obj))

    if (progress > 0) {
      state = 1
      val diffX = position.x - GameScene.spawner.spawn_zone.left
      GameScene.spawner.spawn_zone = Rect[Float](position.x, GameScene.spawner.spawn_zone.top, GameScene.spawner.spawn_zone.width - diffX, GameScene.spawner.spawn_zone.height)
    }
  }
}
