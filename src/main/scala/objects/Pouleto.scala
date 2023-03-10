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
import objects.Resource
import events._
import scene._

class Pouleto(context: Scene) extends EntityGameObject(50, () => new Resource("game/meat.png", 3, ResourceType.MEAT), "game/chicken.png", 16, 17, Array(12)) {
    
    var roaming = false
    var fleeing : Option[SpriteGameObject] = None
    var fleeing_since : Long = 0L

    override def collision_box = Some(Rect[Float](position.x + 2, position.y + 7, 12, 7))
    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

    override def update() = {

        super.update()

        if (fleeing.isDefined) {
            val x = (this.position.x - fleeing.get.position.x)
            val y = (this.position.y - fleeing.get.position.y)
            val norm : Float = 5*((java.lang.Math.sqrt(x*x + y*y)).toFloat)
            context.safe_move(this, x/norm, y/norm)

            if (System.currentTimeMillis() - fleeing_since > 3000L) fleeing = None
        }

    }
    override def attack(dmg: Int, attacker : SpriteGameObject) = {
        

        fleeing = Some(attacker)
        fleeing_since = System.currentTimeMillis()

        super.attack(dmg,attacker)
    }
}
