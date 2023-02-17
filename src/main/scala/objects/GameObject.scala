package prog2

import sfml.graphics._
import event.Listener
import graphics.ResourceManager
import sfml.system.Vector2

abstract class GameObject extends Drawable with Listener {
  
  def init() : Unit

  def update() : Unit

  def close() : Unit

  init()
  
}
