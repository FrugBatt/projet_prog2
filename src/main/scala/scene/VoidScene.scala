package prog2
package scene

import objects.Image
import sfml.system.Vector2

class VoidScene extends Scene {

  def init() : Unit = {
    objects = Vector(new Image("cat.png"))
  }

}
