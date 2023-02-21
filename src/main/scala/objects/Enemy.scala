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
import objects.ResourceType
import events._

class Enemy() extends AnimatedGameObject("game/enemy.png", 18, 22, Array(2), animationTime = 800L) {
    var hp = 10

    override def update(): Unit = {
        super.update()

        if(hpbar.isDefined) hpbar.get.hp = hp
    }

    override def collision_box = Some(Rect[Float](position.x + 4, position.y, 6, sprite.textureRect.height))

    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

    override def attack(dmg : Int) = {
        hp = (hp - dmg).max(0)
        if (hp == 0) {
            val r = new Resource("game/coin.png",2,ResourceType.COIN)
            r.position = position
            return AttackKilled(r)
        }
        return AttackSuccess()
    }
}