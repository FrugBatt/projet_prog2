package prog2
package game

import sfml.graphics._
import sfml.window._
import graphics.ResourceManager

object Game {

  var window : RenderWindow = _

  def init() : Unit = {
    window = RenderWindow(VideoMode(1024, 768), "Le RTS de Hugo et Simon les bews")

    game_loop()
  }

  def game_loop() : Unit = {
    while window.isOpen() do
      for event <- window.pollEvent() do
        event match {
          case Event.Closed() => window.closeWindow()
          case _ => ()
        }
      window.clear(Color.Black())
      window.draw(ResourceManager.get_sprite("cat"))
      window.display()

    end()
  }

  def end() : Unit = {
    ResourceManager.close()
  }

}
