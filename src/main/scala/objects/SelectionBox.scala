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
    val zoom = GameScene.camera.zoom
    var window_height : Int = 0
    var window_width : Int = 0

    override def init(): Unit = {

        super.init()

        Control.leftClick.addListener(click)

    }

    override def close(): Unit = {

        super.close()
        
        Control.leftClick.removeListener(click)

    }

    override def update(): Unit = {
        super.update()
        window_width = Game.window.size.x
        window_height = Game.window.size.y



        width = GameScene.mouse_position().x.toInt - x 
        height = GameScene.mouse_position().y.toInt - y
        shape = { 
            val r = RectangleShape((width,height))
            r.position = Vector2(x,y)
            r.fillColor = Color(255, 0, 40, 100)
            r.outlineColor = Color(255, 0, 40, 255)
            r.outlineThickness = 0.5f
            r
        }
    }

    override def draw(target : RenderTarget, states : RenderStates) : Unit = {
        if(clicking) shape.draw(target, states)
    }

    def click(press: Boolean) = {
        if(press){

            clicking = true
            x = GameScene.mouse_position().x.toInt
            y = GameScene.mouse_position().y.toInt
            GameScene.objects.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].unselect())
        }
        else{

            clicking = false
            GameScene.trigger_all(Some(Rect[Float](x,y,width,height)), objs => objs.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].select()))
        }
    }

    }

