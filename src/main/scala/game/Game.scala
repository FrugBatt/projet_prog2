package prog2
package game

import sfml.graphics._
import sfml.window._
import graphics.ResourceManager
import scene.Scene
import scene.GameScene
import scene.HudScene
import scene.PauseScene

object Game {

  var window : RenderWindow = _
  var scenes : Vector[Scene] = _
  var width : Int = 1024
  var height : Int = 768

  var pause = false

  def init() : Unit = {
    window = RenderWindow(VideoMode(width, height), "Le RTS de Hugo et Simon les bews")
    
    val hud = new HudScene(window,width,height)
    val game_scene = new GameScene(window, hud, width, height)

    scenes = Vector(game_scene, hud)
  }

  def game_loop() : Unit = {
    while window.isOpen() do
      // === EVENTS === 
      for event <- window.pollEvent() do
        if (pause) PauseScene.call_event(event)
        else scenes.foreach(_.call_event(event))

        event match {
          case _ : Event.Closed => window.close()
          case _ => ()
        }
      
      // === UPDATE ===
      if (!pause) scenes.foreach(_.update())

      // === RENDER ===
      window.clear(Color.Black())
      scenes.foreach(window.draw(_))
      window.display()
  }

  def end() : Unit = {
    scenes.foreach(_.close())
  }

}
