package prog2
package game

import sfml.graphics._
import sfml.window._
import graphics.ResourceManager
import scene.Scene
import scene.GameScene
import scene.HudScene
import scene.PauseScene

import sfml.system.Vector2

import objects._
import events._

object Game {

  var window : RenderWindow = _
  var scenes : Vector[Scene] = _
  var width : Int = 1024
  var height : Int = 768

  var pause = false
  
  var single = true

  def init() : Unit = {

    window = RenderWindow(VideoMode(width, height), "Toujours pas de nom pour ce jeu")
    
    val hud = HudScene
    val game_scene = GameScene

    Control.switchSingleMulti.addListener(switchSingleMulti)

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
          case e : Event.KeyPressed => ControlManager.onKeyPressed(e)
          case e : Event.KeyReleased => ControlManager.onKeyReleased(e)
          case e : Event.MouseButtonPressed => ControlManager.onMouseButtonPressed(e)
          case e : Event.MouseButtonReleased => ControlManager.onMouseButtonReleased(e)
          case e : Event.MouseWheelScrolled => ControlManager.onMouseWheelScrolled(e)
          case _ => ()
        }
      
      // === UPDATE ===
      if (!pause) scenes.foreach(_.update())

      // === RENDER ===
      window.clear(Color.Black())
      scenes.foreach(window.draw(_))
      window.display()
      width = window.size.x
      height = window.size.y
  }

  def end() : Unit = {
    scenes.foreach(_.close())
  }

  def switchSingleMulti(start : Boolean) : Unit = {
    if (start) {
      single = !single
      if (single) {
        GameScene.cameras = Vector(new CenteredCamera(window, width, height, 0.2f, GameScene.king1, Side.Full))

        GameScene.del(GameScene.king2)
        GameScene.king2.close()
        GameScene.king2 = null

        HudScene.del(HudScene.hpP2)
        HudScene.del(HudScene.invhudP2)
        HudScene.del(HudScene.stone_amountP2)
        HudScene.del(HudScene.wood_amountP2)
        HudScene.del(HudScene.coin_amountP2)
      } else {
        GameScene.king2 = new King(GameScene, PlayerState.P2)
        GameScene.king2.position = Vector2(GameScene.king1.position.x,GameScene.king1.position.y + 50)
        GameScene.add(GameScene.king2)

        // GameScene.addCamera(new CenteredCamera(Game.window, Game.width, Game.height, 0.2f, GameScene.king2, Side.Right)
        GameScene.cameras = Vector(new CenteredCamera(window, width, height, 0.2f, GameScene.king1, Side.Left), new CenteredCamera(window, width, height, 0.2f, GameScene.king2, Side.Right))

        HudScene.add(HudScene.hpP2)
        HudScene.add(HudScene.invhudP2)
        HudScene.add(HudScene.stone_amountP2)
        HudScene.add(HudScene.wood_amountP2)
        HudScene.add(HudScene.coin_amountP2)
      }
    }
  }

}
