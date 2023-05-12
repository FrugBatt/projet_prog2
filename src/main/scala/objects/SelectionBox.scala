package prog2
package objects

import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Mouse
import sfml.window.Event
import sfml.graphics._
import graphics.ResourceManager
import sfml.system.Vector2
import game.Game
import scene._
import objects.PlayerControlledEntity
import objects.RectangleShapeGameObject
import events._
import scala.compiletime.ops.long

object SelectionBox extends RectangleShapeGameObject(Color(0, 80, 255, 100),0,0,0,0) {
    var x = 0
    var y = 0
    var width = 0
    var height = 0
    var clicking = false

    override def init(): Unit = {

        super.init()

        Control.mouseClick.addListener(click)

    }

    override def close(): Unit = {

        super.close()
        
        Control.mouseClick.removeListener(click)

    }

    override def update(): Unit = {
        super.update()
        width = Mouse.position.x - x
        height = Mouse.position.y - y
        shape = { 
            val r = RectangleShape((width,height))
            r. position = Vector2(x,y)
            r.fillColor = Color(0, 80, 255, 100)
            r
        }

    }

    override def draw(target : RenderTarget, states : RenderStates) : Unit = {
        if(clicking) shape.draw(target, states)
    }

    def click(press: Boolean) = {
        if(press){

            clicking = true
            x = Mouse.position.x
            y = Mouse.position.y
            println(x) 
            println(y)
            GameScene.objects.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].unselect())
        }
        else{

            clicking = false
            println(width)
            println(height)
            GameScene.trigger_all(Some(Rect[Float](x,y,width,height)), objs => objs.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].select()))
        }
    }

    }

