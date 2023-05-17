package prog2
package objects

import graphics.ResourceManager

import scala.math._
import sfml.window.Mouse
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
import sfml.graphics.View

class Soldier() extends PlayerControlledEntity(10,() => new Resource("game/coin.png", 2, ResourceType.COIN),"game/soldier.png",19,25,Array(16,6,6),120L){

    var mouse_position = Vector2[Float](0,0)

    val target_id = 3
    val random = new scala.util.Random
    var chasing : Option[EntityGameObject] = None
    val chasing_range : Float = 140f
    val attack_delay : Long = 1000L
    var anim_time : Option[Long] = None
    var last_attack : Long = System.currentTimeMillis()

    scale = Vector2[Float](0.7f,0.7f)
    health.scale = Vector2[Float](0.7f,0.7f)
    
    var destination : Option[Vector2[Float]]= None

    override def id = 1
    override def trigger_box = Some(Rect[Float](position.x-4, position.y-4, 8 + sprite.textureRect.width * 0.7f, 8 + sprite.textureRect.height * 0.7f))

    override def update() = {
        super.update()


        if(anim_time.isDefined) {if(System.currentTimeMillis() - anim_time.get > animationTime * 7) {
        state = 0
        anim_time = None
        }}


        else if(destination.isDefined){
            val prevx = position.x
            val prevy = position.y
            val dirx = destination.get.x - prevx
            val diry = destination.get.y - prevy
            if (dirx.abs < 1 && diry.abs < 1) {
                destination = None
                state = 0
            }
            else {
                if (dirx > 0) state = 1
                else state = 2
                val norm = sqrt(dirx*dirx + diry*diry).toFloat
                GameScene.safe_move(this,dirx/(3*norm),diry/(3*norm))
            }
        }

        else if (chasing.isDefined) {
            val x = (chasing.get.position.x - position.x)
            val y = (chasing.get.position.y - position.y)
            if (x > 0) state = 1 //animation for moving to the right
            else state = 2 //animation for moving to the left
            val norm : Float = 2*((sqrt(x*x + y*y)).toFloat)
            if (norm > 5) GameScene.safe_move(this, x/norm, y/norm)
            if (norm > chasing_range || !(GameScene.objects.contains(chasing.get))) {
                chasing = None
                state = 0
            }
        }

        if(System.currentTimeMillis() - last_attack > attack_delay) {
        last_attack = System.currentTimeMillis()
        GameScene.trigger_all(this.trigger_box, objs => {
        val opt = objs.find(o => o.id == target_id)
        if(opt.isDefined) {
            chasing = Some(opt.get.asInstanceOf[EntityGameObject])
            if(opt.get.position.x > position.x) animate(1)  //attacks towards its right
            else animate(2)                                 // attacks towards its left
            opt.get.damage(1,this) match {
            case a : AttackKilled =>
                GameScene.del(opt.get)
                if (a.drop.isDefined) GameScene.add(a.drop.get)
                chasing = None
            case _ => ()
            }
        }
      })
    }

    }
    override def order(start: Boolean) = {
        if(start && selected){
            destination = Some(Vector2[Float](mouse_position.x + 4*random.nextGaussian().toFloat, mouse_position.y + 4*random.nextGaussian().toFloat))
        }
    }

    override def select(): Unit = {
        super.select()
    }

    override def unselect(): Unit = {
        super.unselect()
    }



    def animate(animationState : Int) : Unit = {
        state = animationState
        animationIteration = 0
        anim_time = Some(System.currentTimeMillis())
    }

    def getView(x : Int) : View = {
      if (Game.single) GameScene.cameras(0).v
      else if (x < Game.window.size.x/2) GameScene.cameras(0).v
      else GameScene.cameras(1).v
    }
    override def onMouseMoved(e : Event.MouseMoved) = {
      mouse_position = Game.window.mapPixelToCoords(Vector2(e.x, e.y), getView(e.x))
    }

}
