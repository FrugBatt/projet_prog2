import sfml.graphics.*
import sfml.window.*
import graphics.ResourceManager

@main def main =
  val window = RenderWindow(VideoMode(1024, 768), "Hello world")

  ResourceManager.load_resource("cat", "cat.png")  

  while window.isOpen() do
    for event <- window.pollEvent() do
      event match {
        case _: Event.Closed => window.closeWindow()
        case _ => ()
      }
    window.clear(Color.Black())
    window.draw(ResourceManager.get_sprite("cat"))
    window.display()

  ResourceManager.close()
