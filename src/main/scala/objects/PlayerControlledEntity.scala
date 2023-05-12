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
import scene._
import objects.Resource
import objects.ResourceType
import objects.King
import events._
import scala.compiletime.ops.long


class PlayerControlledEntity(hp: Int, drop: () => Resource, resource: String, width: Int, height: Int, animationLen : Array[Int], animationTime : Long = 800L) extends EntityGameObject(hp,drop,resource,width,height,animationLen,animationTime) {

    var selected : Boolean = false

    override def collision_box = None

    def select() : Unit = {
        selected = true
        println("I have been selected")
    }

    def unselect() : Unit = {
        selected = false
        println("I have been unselected")
    }


}