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

class Healthbar(player : PlayerState) extends StatedGameObject("game/hp.png", 73, 12) {

    def getHealth() = player match {
        case PlayerState.P1 => PersonalInventory.healthP1
        case PlayerState.P2 => PersonalInventory.healthP2
    }

    override def update(): Unit = {
        super.update()

        state = 10 - getHealth()

        if(state == 10) {
          Game.scenes = Game.scenes :+ GameOverScene
        }
    }
}
