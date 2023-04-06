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
import objects.PersonalInventory
import scene.HudScene
import scene.GameOverScene

class Healthbar extends StatedGameObject("game/hp.png", 73, 12) {

    override def update(): Unit = {
        super.update()

        state = 10 - PersonalInventory.health

        if(state == 10) {
          Game.scenes = Game.scenes :+ GameOverScene
        }
    }
}
