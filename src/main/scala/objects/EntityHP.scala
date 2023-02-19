


import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.system.Vector2
import game.Game

class EntityHP(entity: SpriteGameObject, maxhp: Int, x_offset: Int) extends StatedGameObject("game/entityhp.png", 10, 1) {

    var hp = maxhp

    override def update() : Unit = {
        super.update()

        state = (10 * (1 - (maxhp/hp).toFloat)).toInt

        this.position = (entity.position.x + x_offset, entity.position.y - 2)
    }

}