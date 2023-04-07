package prog2
package objects

import scala.math._
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

class Enemy[target <: GameObject](context: Scene, hp: Int, drop: () => Resource, resource: String, width: Int, height: Int, animationLen : Array[Int], animationTime : Long, hp_x_offset: Int, val target_id : Int = 0) extends EntityGameObject(hp, drop, resource, width, height, animationLen, animationTime, hp_x_offset) {

  val rand = new scala.util.Random
  var chasing : Option[target] = None
  val chasing_range : Float = 200f
  var roaming : Option[Vector2[Float]] = None
  var roaming_since : Long = 0L
  var roaming_time : Long = rand.nextLong(3000L)
  val attack_delay : Long = 1000L
  var anim_time : Option[Long] = None
  var last_attack : Long = System.currentTimeMillis()


  def animate(animationState : Int) : Unit = {
    state = animationState
    animationIteration = 0
    anim_time = Some(System.currentTimeMillis())
  }


  def roam() = { //deciding whether or not should the enemy roam, and in what direction and for how long
        roaming_since = System.currentTimeMillis()
        roaming_time = rand.nextLong(3000L)
        if(rand.nextInt(2) == 1) {
            roaming = None
            state = 0 //animation for standing still
        }
        else {
            val angle = rand.nextFloat()*6.3f
            roaming = Some(Vector2(cos(angle).toFloat,sin(angle).toFloat))
        }

    }

  override def update(): Unit = {
    super.update()

    if(anim_time.isDefined) {if(System.currentTimeMillis() - anim_time.get > animationTime * 7) {
      state = 0
      anim_time = None
    }}

    else if (chasing.isDefined) {
            val x = (chasing.get.position.x - position.x)
            val y = (chasing.get.position.y - position.y)
            if (x > 0) state = 3 //animation for moving to the right
            else state = 4 //animation for moving to the left
            val norm : Float = 2*((sqrt(x*x + y*y)).toFloat)
            context.safe_move(this, x/norm, y/norm)

            if (norm > chasing_range) {
                chasing = None
            }
        }

    else if (roaming.isDefined) {     //priority: attacking > chasing > roaming
      context.safe_move(this, roaming.get.x/5, roaming.get.y/5)
      if (roaming.get.x > 0) state = 3 //animation for moving to the right
      else state = 4 //animation for moving to the left
    }
    if (System.currentTimeMillis() - roaming_since > roaming_time) roam()


    if(System.currentTimeMillis() - last_attack > attack_delay) {
      last_attack = System.currentTimeMillis()
      context.trigger_all(this.trigger_box, objs => {
        val opt = objs.find(o => o.id == target_id)
        if(opt.isDefined) {
          chasing = Some(opt.get.asInstanceOf[target])
          if(opt.get.position.x > position.x) animate(1)  //attacks towards its right
          else animate(2)                                 // attacks towards its left
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
