import sfml.graphics.*
import sfml.window.*
@main def main =
  scala.util.Using.Manager { use =>
    val window = use(RenderWindow(VideoMode(1024, 768), "Hello world"))
    val texture = use(Texture())
    texture.loadFromFile("cat.png")
    val sprite = use(Sprite(texture))
    while window.isOpen() do
      for event <- window.pollEvent() do
        event match {
          case _: Event.Closed => window.closeWindow()
          case _ => ()
        }
      window.clear(Color.Black())
      window.draw(sprite)
      window.display()
  }
