package prog2
package scene

import objects.Image
import objects.Button

import sfml.system.Vector2

class VoidScene extends Scene {

  def test() : Unit = {
    println("hello world")
  }

  def init() : Unit = {
    val img = new Image("cat.png")

    img.position = Vector2(100f, 0f)

    val button = new Button("button.png", 82, 41, test)
    button.position = Vector2(200f, 200f)

    objects = Vector(img, button)
  }

}
