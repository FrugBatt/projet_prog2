package prog2
package events

import sfml.window.Event
import sfml.window.Keyboard

enum Control(val keys : Vector[Keyboard.Key]) {
  var listeners : Vector[Boolean => Unit] = Vector()

  case moveForward extends Control(Vector(Keyboard.Key.KeyZ))
  case moveBackward extends Control(Vector(Keyboard.Key.KeyS))
  case moveLeft extends Control(Vector(Keyboard.Key.KeyQ))
  case moveRight extends Control(Vector(Keyboard.Key.KeyD))

  case castleInventory extends Control(Vector(Keyboard.Key.KeyE))
  
  case attack extends Control(Vector(Keyboard.Key.KeyF))
  case harvest extends Control(Vector(Keyboard.Key.KeySpace))
  case collect extends Control(Vector(Keyboard.Key.KeyA))
  case build extends Control(Vector(Keyboard.Key.KeyC))

  case castleUp extends Control(Vector(Keyboard.Key.KeyUp))
  case castleDown extends Control(Vector(Keyboard.Key.KeyDown))
  case castleLeft extends Control(Vector(Keyboard.Key.KeyLeft))
  case castleRight extends Control(Vector(Keyboard.Key.KeyRight))
  
  case pause extends Control(Vector(Keyboard.Key.KeyEscape))

  def addListener(listener : Boolean => Unit) : Unit = {
    listeners = listeners :+ listener
  }

  def removeListener(listener : Boolean => Unit) : Unit = {
    listeners = listeners.filterNot(listener.==)
  } 
}

object ControlManager {
  
  def onKeyPressed(e : Event.KeyPressed) : Unit = Control.values.filter(c => c.keys.contains(e.code)).foreach(c => c.listeners.foreach(f => f(true)))

  def onKeyReleased(e : Event.KeyReleased) : Unit = Control.values.filter(c => c.keys.contains(e.code)).foreach(c => c.listeners.foreach(f => f(false)))

}
