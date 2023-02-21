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
import objects.Inventory
import scene.HudScene

class Healthbar(hud: HudScene) extends StatedGameObject("game/hp.png", 73, 12) {

    override def update(): Unit = {
        super.update()

        state = 10 - Inventory.health

        if(state == 10) hud.gameover()
    }
}