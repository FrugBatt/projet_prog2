package prog2
package objects

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.system.Vector2
import game.Game
import scene._
import objects._
import events._
import scala.compiletime.ops.long



class Structure(owner: King, maxhp: Int, resource: String, width: Int, height: Int, animationNum: Array[Int]) extends AnimatedGameObject(resource,width,height,animationNum) {

    override def id = 5

    val build_time : Long = 2500L
    val build_start : Long = System.currentTimeMillis()
    var built = false



    override def update() = {
        super.update()

        if (!built && System.currentTimeMillis() - build_start > build_time) {
            state = 1
            built = true
        }
    }

    def interact() = {}

}