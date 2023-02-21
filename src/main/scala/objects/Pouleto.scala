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

class Pouleto() extends AnimatedGameObject("game/chicken.png", 16, 17, Array(12)) {
    var hp = 5
    
    override def update(): Unit = {
        super.update()

        if(hpbar.isDefined) hpbar.get.hp = hp
    }

    override def collision_box = Some(Rect[Float](position.x + 2, position.y + 7, 12, 7))
    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

    override def attack(dmg : Int) = {
        hp = (hp - dmg).max(0)
        if (hp == 0) {
            val r = new Resource("game/meat.png",3,ResourceType.MEAT)
            r.position = position
            return AttackKilled(r)
        }
        return AttackSuccess()
        
    }
}