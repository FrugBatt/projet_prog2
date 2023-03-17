package prog2
package scene

import sfml.window.Event
import sfml.graphics.*
import sfml.system.Vector2
import scala.collection.immutable.Vector
import objects.GameObject
import objects.SpriteGameObject
import scala.collection.mutable.ListBuffer

trait Scene extends Transformable with Drawable {
  
  var objects : Vector[GameObject] = _
  
  def init() : Unit

  def safe_move(obj : SpriteGameObject, movX : Float, movY : Float) : Unit = {
    val oldPosX = obj.position
    obj.move(movX, 0)
    if (obj.collision_box.isDefined && objects.exists(o => o != obj && o.collision_box.isDefined && o.collision_box.get.intersects(obj.collision_box.get))) obj.position = oldPosX
    
    val oldPosY = obj.position
    obj.move(0, movY)
    if (obj.collision_box.isDefined && objects.exists(o => o != obj && o.collision_box.isDefined && o.collision_box.get.intersects(obj.collision_box.get))) obj.position = oldPosY
  }

  def trigger_all(trigbox : Option[Rect[Float]], action : (Vector[GameObject]) => Unit) : Unit = if(trigbox.isDefined) action(objects.filter(obj => obj.trigger_box.isDefined && obj.trigger_box.get.intersects(trigbox.get)))
  def trigger[A <: GameObject](trigbox : Option[Rect[Float]], action : (A) => Unit) : Unit = if(trigbox.isDefined) objects.filter(obj => obj.isInstanceOf[A] && obj.trigger_box.isDefined && obj.trigger_box.get.intersects(trigbox.get)).map(obj => obj.asInstanceOf[A]).foreach(action(_))

  def del(obj : GameObject) : Unit = {
    objects = objects.filterNot(obj.==)
  }

  def add(obj: GameObject): Unit = {
    objects = objects :+ obj
  }

  def call_event(e : Event) : Unit = {
    e match {
      case e : Event.Closed => objects.foreach(_.onClosed(e))
      case e : Event.Resized => objects.foreach(_.onResized(e))
      case e : Event.LostFocus => objects.foreach(_.onLostFocus(e))
      case e : Event.GainedFocus => objects.foreach(_.onGainedFocus(e))
      case e : Event.TextEntered => objects.foreach(_.onTextEntered(e))
      case e : Event.KeyPressed => objects.foreach(_.onKeyPressed(e))
      case e : Event.KeyReleased => objects.foreach(_.onKeyReleased(e))
      case e : Event.MouseWheelScrolled => objects.foreach(_.onMouseWheelScrolled(e))
      case e : Event.MouseButtonPressed => objects.foreach(_.onMouseButtonPressed(e))
      case e : Event.MouseButtonReleased => objects.foreach(_.onMouseButtonReleased(e))
      case e : Event.MouseMoved => objects.foreach(_.onMouseMoved(e))
      case e : Event.MouseEntered => objects.foreach(_.onMouseEntered(e))
      case e : Event.MouseLeft => objects.foreach(_.onMouseLeft(e))
      case e : Event.JoystickButtonPressed => objects.foreach(_.onJoystickButtonPressed(e))
      case e : Event.JoystickButtonReleased => objects.foreach(_.onJoystickButtonReleased(e))
      case e : Event.JoystickMoved => objects.foreach(_.onJoystickMoved(e))
      case e : Event.JoystickConnected => objects.foreach(_.onJoystickConnected(e))
      case e : Event.JoystickDisconnected => objects.foreach(_.onJoystickDisconnected(e))
      case e : Event.TouchBegan => objects.foreach(_.onTouchBegan(e))
      case e : Event.TouchMoved => objects.foreach(_.onTouchMoved(e))
      case e : Event.TouchEnded => objects.foreach(_.onTouchEnded(e))
      case e : Event.SensorChanged => objects.foreach(_.onSensorChanged(e))
    }
  }

  def update() : Unit = {
    objects.foreach(_.update_head())
  }

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    states.transform *= transform
    objects.foreach(_.draw(target, states))
  }

  def close() : Unit = {
    objects.foreach(_.close())
  }

  init()
  
}
