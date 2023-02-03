package prog2

import sfml.graphics._

trait GameObject extends Transformable with Drawable {
  
  def init() : Unit
  def update() : Unit

}
