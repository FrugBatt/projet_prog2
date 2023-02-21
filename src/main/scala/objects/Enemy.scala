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

class Enemy(context: Scene) extends AnimatedGameObject("game/enemy.png", 18, 22, Array(2), animationTime = 800L) {
    var hp = 10
    val attack_delay : Long = 1000L
    var last_attack : Long = System.currentTimeMillis()
    override def update(): Unit = {
        super.update()

        if(hpbar.isDefined) hpbar.get.hp = hp

        if(System.currentTimeMillis() - last_attack > attack_delay) {
            last_attack = System.currentTimeMillis()
            context.trigger(this.trigger_box, objs => {
                val opt = objs.find(o => o.isInstanceOf[King])
                if(opt.isDefined) opt.get.attack(1) match {
                    case _ : AttackKilled =>
                        context.del(opt.get)
                        println("game over")
                    case _ => ()
                }
            })
        }
    }

    override def collision_box = Some(Rect[Float](position.x + 4, position.y, 6, sprite.textureRect.height))

    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

    override def attack(dmg : Int) = {
        hp = (hp - dmg).max(0)
        if (hp == 0) {
            val r = new Resource("game/coin.png",2,ResourceType.COIN)
            r.position = position
            return AttackKilled(Some(r))
        }
        return AttackSuccess()
    }
}