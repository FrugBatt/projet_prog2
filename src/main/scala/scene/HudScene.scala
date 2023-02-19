package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2

import objects.*

class HudScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val hp = new Healthbar()
    val inv = new Inventory()
    val hudcamera = new Camera(window,width,height)

    hp.scale = Vector2(5f,5f)
    inv.position = Vector2(0,height/10)
    inv.scale = Vector2(5f,5f)
    
    objects = Vector(hudcamera, inv, hp)
  }

}
