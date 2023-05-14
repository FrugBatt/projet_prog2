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

class Soldier() extends PlayerControlledEntity(10,() => new Resource("game/coin.png", 2, ResourceType.COIN),"game/soldier.png",19,25,Array(16,6,6),120L){

    val random = new scala.util.Random

    scale = Vector2[Float](0.7f,0.7f)
    health.scale = Vector2[Float](0.7f,0.7f)
    
    var destination : Option[Vector2[Float]]= None

    override def update() = {
        super.update()

        if(destination.isDefined){
            state = 1
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
    }
    override def order(start: Boolean) = {
        if(start && selected){
            val x = (GameScene.king.center.x + GameScene.camera.zoom*(Mouse.position.x - (Game.window.size.x/2))).toInt - 42
            val y = (GameScene.king.center.y + GameScene.camera.zoom*(Mouse.position.y - (Game.window.size.y/2))).toInt - 40
            destination = Some(Vector2[Float](x + 4*random.nextGaussian().toFloat, y + 4*random.nextGaussian().toFloat))
        }
    }

    override def select(): Unit = {
        super.select()
    }

    override def unselect(): Unit = {
        super.unselect()
    }

}