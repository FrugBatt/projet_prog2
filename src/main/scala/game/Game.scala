package prog2
package game

import sfml.graphics._
import sfml.window._
import graphics.ResourceManager
import scene.Scene
import scene.GameScene

object Game {

  var window : RenderWindow = _
  var scene : Scene = _
  var width : Int = 1024
  var height : Int = 768
  def init() : Unit = {
    window = RenderWindow(VideoMode(width, height), "Le RTS de Hugo et Simon les bews")
    scene = new GameScene(window, width, height)
  }

  def game_loop() : Unit = {
    while window.isOpen() do
      // === EVENTS === 
      for event <- window.pollEvent() do
        scene.call_event(event)
        event match {
          case _ : Event.Closed => window.closeWindow()
          case _ => ()
        }
      
      // === UPDATE ===
      scene.update()

      // === RENDER ===
      window.clear(Color.Black())
      window.draw(scene)
      window.display()
  }

  def end() : Unit = {
    scene.close()
  }

}
