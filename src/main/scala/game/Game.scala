package prog2
package game

import sfml.graphics._
import sfml.window._
import graphics.ResourceManager
import scene.Scene
import scene.VoidScene

object Game {

  var window : RenderWindow = _
  var scene : Scene = _

  def init() : Unit = {
    window = RenderWindow(VideoMode(1024, 768), "Le RTS de Hugo et Simon les bews")
    scene = VoidScene()
  }

  def game_loop() : Unit = {
    while window.isOpen() do
      for event <- window.pollEvent() do
        scene.call_event(event)
        event match {
          case _ : Event.Closed => window.closeWindow()
          case _ => ()
        }
      window.clear(Color.Black())
      window.draw(scene)
      window.display()
  }

  def end() : Unit = {
    scene.close()
  }

}
