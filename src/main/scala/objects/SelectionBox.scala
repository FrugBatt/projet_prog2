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
    
  var mouse_position = Vector2[Float](0,0)

  var selection_origin = Vector2[Float](0,0)

  var clicking = false

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

    shape = { 
      val r = RectangleShape((mouse_position.x - selection_origin.x, mouse_position.y - selection_origin.y))
      r.position = selection_origin
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
      selection_origin = mouse_position
      GameScene.objects.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].unselect())
    } else {
      clicking = false
      GameScene.trigger_all(Some(Rect[Float](selection_origin.x,selection_origin.y,shape.size.x,shape.size.y)), objs => objs.foreach(o => if(o.isInstanceOf[PlayerControlledEntity]) o.asInstanceOf[PlayerControlledEntity].select()))
    }
  }

  def getView(x : Int) : View = {
    if (Game.single) GameScene.cameras(0).v
    else if (x < Game.window.size.x/2) GameScene.cameras(0).v
    else GameScene.cameras(1).v
  }

  override def onMouseMoved(e : Event.MouseMoved) = {
    mouse_position = Game.window.mapPixelToCoords(Vector2(e.x, e.y), getView(e.x))
  }

}

