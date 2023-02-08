package prog2
package scene

import objects.Image

class VoidScene extends Scene {

  def init() : Unit = {
    objects = Vector(new Image("cat.png"))
  }

}
