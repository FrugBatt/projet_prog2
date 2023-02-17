package prog2
package objects

import sfml.graphics.Rect

abstract class StatedGameObject(resource : String, width : Int, height : Int) extends SpriteGameObject(resource) {
  
  var state = 0

  def stateRect : Rect[Int] = {
    val x = sprite.textureRect.left
    val y = state * height
    return Rect[Int](x, y, width, height)
  }

  def update() : Unit = {
    sprite.textureRect = stateRect
  }

}
