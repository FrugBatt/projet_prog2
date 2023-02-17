package prog2
package scene

import sfml.graphics.View
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.system.Vector2

import objects.King
import objects.Camera
import objects.World

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val kingCamera = new Camera(window, width, height)

    val king = new King(kingCamera)

    objects = Vector(world, king)
  }

}
