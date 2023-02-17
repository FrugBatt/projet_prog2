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

    val king = new King()
    val camera = new Camera(window, width, height, king)

    objects = Vector(world, king, camera)
  }

}
