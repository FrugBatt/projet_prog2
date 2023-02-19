package prog2
package scene

import sfml.graphics.View
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.system.Vector2

import objects.King
import objects.CenteredCamera
import objects.World
import objects.Healthbar
import objects.*

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val king = new King()
    val camera = new CenteredCamera(window, width, height, king)
    val hp = new Healthbar()
    val inv = new Inventory()
    val hudcamera = new Camera(window,width,height)

    objects = Vector(world, king, camera, inv, hp, hudcamera)
  }

}
