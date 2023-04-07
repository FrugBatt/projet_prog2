package prog2
package events

import sfml.window.Event
import sfml.window.Keyboard

enum Control(val keys : Vector[Keyboard.Key]) {
  var listeners : Vector[Boolean => Unit] = Vector()

  case MoveForward extends Control(Vector(Keyboard.Key.KeyZ))

  def addListener(listener : Boolean => Unit) : Unit = {
    listeners = listeners :+ listener
  }
}

object ControlManager {
  
  def onKeyPressed(e : Event.KeyPressed) : Unit = Control.values.filter(c => c.keys.contains(e.code)).foreach(c => c.listeners.foreach(f => f(true)))

  def onKeyReleased(e : Event.KeyReleased) : Unit = Control.values.filter(c => c.keys.contains(e.code)).foreach(c => c.listeners.foreach(f => f(false)))

}
