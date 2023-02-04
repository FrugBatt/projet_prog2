package prog2
package scene

import objects.Image

class VoidScene extends Scene {

  def init() : Unit = {
    val img = new Image("cat.png")
    img.init()

    objects = Vector(img)
  }

}
