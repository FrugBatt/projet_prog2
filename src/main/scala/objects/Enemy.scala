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

class Enemy(context: Scene) extends EntityGameObject(10, () => new Resource("game/coin.png", 2, ResourceType.COIN),"game/enemy.png", 38, 23, Array(8,8,8,8,8), animationTime = 100L, hp_x_offset = 14) {

  val attack_delay : Long = 1000L
  var anim_time : Option[Long] = None
  var last_attack : Long = System.currentTimeMillis()

  override def collision_box = Some(Rect[Float](position.x + 10, position.y, 18, sprite.textureRect.height))
  override def trigger_box = Some(Rect[Float](position.x - 3, position.y - 3, sprite.textureRect.width + 6, sprite.textureRect.height + 6))

  def animate(animationState : Int) : Unit = {
    state = animationState
    animationIteration = 0
    anim_time = Some(System.currentTimeMillis())
  }

  override def update(): Unit = {
    super.update()

    if(anim_time.isDefined) if(System.currentTimeMillis() - anim_time.get > animationTime * 8) {
      state = 0
      anim_time = None
    }
    if(System.currentTimeMillis() - last_attack > attack_delay) {
      last_attack = System.currentTimeMillis()
      context.trigger(this.trigger_box, objs => {
        val opt = objs.find(o => o.isInstanceOf[King])
        if(opt.isDefined) {
          animate(1)
          opt.get.attack(1,this) match {
          case _ : AttackKilled =>
            context.del(opt.get)
          case _ => ()
          }
        }
      })
    }
  }
}
