package prog2

import sfml.graphics._
import event.Listener

trait GameObject extends Transformable with Drawable with Listener {
  
  def init() : Unit
  def update() : Unit

}
