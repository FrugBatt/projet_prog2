package prog2
package objects

import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.system.Vector2
import game.Game
import scene._
import objects.Resource
import objects.ResourceType
import objects.King
import events._
import scala.compiletime.ops.long

class Enemy(context: Scene) extends EntityGameObject(10, () => new Resource("game/coin.png", 2, ResourceType.COIN),"game/enemy.png", 18, 22, Array(2), animationTime = 800L) {

  val attack_delay : Long = 1000L
  var last_attack : Long = System.currentTimeMillis()

  override def collision_box = Some(Rect[Float](position.x + 4, position.y, 6, sprite.textureRect.height))
  override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

  override def update(): Unit = {
    super.update()

    if(System.currentTimeMillis() - last_attack > attack_delay) {
      last_attack = System.currentTimeMillis()
      context.trigger(this.trigger_box, objs => {
        val opt = objs.find(o => o.isInstanceOf[King])
        if(opt.isDefined) opt.get.attack(1) match {
          case _ : AttackKilled =>
            context.del(opt.get)
          case _ => ()
        }
      })
    }
  }
}
