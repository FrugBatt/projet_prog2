package prog2
package event

import sfml.window.Event

trait Listener {

  def onClosed(e : Event.Closed) : Unit = {}
  def onResized(e : Event.Resized) : Unit = {}
  def onLostFocus(e : Event.LostFocus) : Unit = {}
  def onGainedFocus(e : Event.GainedFocus) : Unit = {}
  def onTextEntered(e : Event.TextEntered) : Unit = {}
  def onKeyPressed(e : Event.KeyPressed) : Unit = {}
  def onKeyReleased(e : Event.KeyReleased) : Unit = {}
  def onMouseWheelScrolled(e : Event.MouseWheelScrolled) : Unit = {}
  def onMouseButtonPressed(e : Event.MouseButtonPressed) : Unit = {}
  def onMouseButtonReleased(e : Event.MouseButtonReleased) : Unit = {}
  def onMouseMoved(e : Event.MouseMoved) : Unit = {}
  def onMouseEntered(e : Event.MouseEntered) : Unit = {}
  def onMouseLeft(e : Event.MouseLeft) : Unit = {}
  def onJoystickButtonPressed(e : Event.JoystickButtonPressed) : Unit = {}
  def onJoystickButtonReleased(e : Event.JoystickButtonReleased) : Unit = {}
  def onJoystickMoved(e : Event.JoystickMoved) : Unit = {}
  def onJoystickConnected(e : Event.JoystickConnected) : Unit = {}
  def onJoystickDisconnected(e : Event.JoystickDisconnected) : Unit = {}
  def onTouchBegan(e : Event.TouchBegan) : Unit = {}
  def onTouchMoved(e : Event.TouchMoved) : Unit = {}
  def onTouchEnded(e : Event.TouchEnded) : Unit = {}
  def onSensorChanged(e : Event.SensorChanged) : Unit = {}

}
