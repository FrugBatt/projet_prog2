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

class Soldier() extends PlayerControlledEntity(10,() => new Resource("game/coin.png", 2, ResourceType.COIN),"game/soldier.png",32,32,Array(1,1)){

    override def select(): Unit = {
        super.select()
        state = 1
    }

    override def unselect(): Unit = {
        super.unselect()
        state = 0
    }

}