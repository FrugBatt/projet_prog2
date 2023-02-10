package prog2
package objects

import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

class King extends AnimatedGameObject("game/king.png", 16, 17, Array(2,8,8)) {
    var moveup = false
    var moveleft = false
    var movedown = false
    var moveright = false

    override def update(): Unit = {
        super.update()
        if(moveup) {

            this.position = (this.position.x, this.position.y - 1)
        }
    }

    override def onKeyPressed(e : Event.KeyPressed) : Unit = {
        if (e.code == Keyboard.Key.KeyZ) {
            moveup = true
            animationState = 2
        }
    }

     override def onKeyReleased(e : Event.KeyReleased) : Unit = {
        if (e.code == Keyboard.Key.KeyZ) {
            moveup = false
            animationState = 0
        }
    }

}