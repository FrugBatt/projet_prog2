package prog2
package objects

import scene._
import objects._
import game.Game
import sfml.system.Vector2
import sfml.graphics.RenderStates
import sfml.graphics.RenderTarget

import sfml.graphics.Rect

class Spawner(context: Scene, min_time : Long, max_time : Long) extends GameObject {

  var spawn_zone = Rect[Float](0f, 0f, 256f, 256f)

  val rand = new scala.util.Random

  def random() : Float = {
    (rand.between(15,85).toFloat)/100
  }

  var last_spawn : Long = System.currentTimeMillis()
  var next_spawn_delay : Long = rand.nextLong(max_time - min_time) + min_time

  override def update() : Unit = {
    if (System.currentTimeMillis() - last_spawn > next_spawn_delay) {
      val entity = {
        val r = rand.nextInt(100)
        if(r <= 40) new Ogre(context)
        else if (r <= 90) new Goblin(context)
        else new Pouleto(context)
      }
      entity.position = Vector2(spawn_zone.left + spawn_zone.width*random(),spawn_zone.top + spawn_zone.height*random())
      context.add(entity)
      last_spawn = System.currentTimeMillis()
      next_spawn_delay = rand.nextLong(max_time - min_time) + min_time

    }
  }

  override def collision_box = None
  override def trigger_box = None
  override def init() = {}
  override def close () = {}
  
  override def draw(target : RenderTarget, states : RenderStates) : Unit = {}
}
