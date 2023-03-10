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
import objects.Resource
import events._
import scene._

class Pouleto(context: Scene) extends EntityGameObject(5, () => new Resource("game/meat.png", 3, ResourceType.MEAT), "game/chicken.png", 16, 17, Array(8,8,8),50L) {
    
    val rand = new scala.util.Random
    var roaming : Option[Vector2[Float]] = None
    var roaming_since : Long = 0L
    var roaming_time : Long = rand.nextLong(5000L)
    var fleeing : Option[SpriteGameObject] = None
    var fleeing_since : Long = 0L

    override def collision_box = Some(Rect[Float](position.x + 2, position.y + 7, 12, 7))
    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

    override def update() = {

        super.update()

        if (fleeing.isDefined) {
            val x = (this.position.x - fleeing.get.position.x)
            val y = (this.position.y - fleeing.get.position.y)
            if (x > 0) state = 1
            else state = 2
            val norm : Float = 3*((sqrt(x*x + y*y)).toFloat)
            context.safe_move(this, x/norm, y/norm)

            if (System.currentTimeMillis() - fleeing_since > 4000L) {
                fleeing = None
                state = 0
            }
        }
        else if (roaming.isDefined) {

            context.safe_move(this, roaming.get.x/5, roaming.get.y/5)
            if (roaming.get.x > 0) state = 1
            else state = 2
            
        }
        if (System.currentTimeMillis() - roaming_since > roaming_time) roam()

    }

    def roam() = {
        roaming_since = System.currentTimeMillis()
        if(rand.nextInt(2) == 1) {
            roaming = None
            state = 0
        }
        else {
            val angle = rand.nextFloat()*6.3f
            roaming = Some(Vector2(cos(angle).toFloat,sin(angle).toFloat))
        }

    }
    override def attack(dmg: Int, attacker : SpriteGameObject) = {
        

        fleeing = Some(attacker)
        fleeing_since = System.currentTimeMillis()

        super.attack(dmg,attacker)
    }
}
