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

class Healthbar() extends StatedGameObject("game/hp.png", 73, 12) {

    def regen(v : Int) : Unit = {
        state = 0.max(state - v)
    }

    def damage(v : Int) : Unit = {
        state = 9.max(state + v)
    }
}