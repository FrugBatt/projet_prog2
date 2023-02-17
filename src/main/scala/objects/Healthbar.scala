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

class Healthbar() extends AnimatedGameObject("game/hp.png", 12, 73, Array(1,1,1,1,1,1,1,1,1,1)) {

    def regen(v : Int) : Unit {
        animationState = 0.max(animationState - v)
    }

    def damage(v : Int) : Unit {
        animationState = 9.max(animationState + v)
    }
}