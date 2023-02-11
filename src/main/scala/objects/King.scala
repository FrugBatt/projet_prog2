package prog2
package objects

import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import game.Game

class King extends AnimatedGameObject("game/king.png", 16, 17, Array(2,8,8)) {
    var moveup = false
    var moveleft = false
    var movedown = false
    var moveright = false
    var x : Float = 0
    var y : Float = 0
    var w : Float = this.sprite.textureRect.width
    var h : Float = this.sprite.textureRect.height
    override def update(): Unit = {
        super.update() 
        x = this.position.x
        y = this.position.y 
        if(moveup && y > 0) this.position = (x, y - 1)
        if(moveleft && x > 0) this.position = (x - 1, y)
        if(movedown && y < (Game.height - h)) this.position = (x, y + 1)
        if(moveright && x < (Game.width - w)) this.position = (x + 1 , y)
    }

    def attack() : Unit = {
        println("ATTACK!!!!")
    }

    override def onKeyPressed(e : Event.KeyPressed) : Unit = {
        if (e.code == Keyboard.Key.KeyZ) {
            moveup = true
            animationState = 2
        }
        if (e.code == Keyboard.Key.KeyQ) {
            moveleft = true
            animationState = 1
        }
        if (e.code == Keyboard.Key.KeyS) {
            movedown = true
            animationState = 2
        }
        if (e.code == Keyboard.Key.KeyD) {
            moveright = true
            animationState = 1
        }
        if (e.code == Keyboard.Key.KeySpace) attack()
    }

     override def onKeyReleased(e : Event.KeyReleased) : Unit = {
        if (e.code == Keyboard.Key.KeyZ || e.code == Keyboard.Key.KeyQ || e.code == Keyboard.Key.KeyS || e.code == Keyboard.Key.KeyD ) {
            moveup = false
            moveleft = false
            movedown = false
            moveright = false
            animationState = 0
        }
    }

}