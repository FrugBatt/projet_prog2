package prog2
package scene

import sfml.system.Vector2
import sfml.graphics.Color

import objects.RectangleShapeGameObject
import objects.TextGameObject
import game.Game

import objects.Camera
import objects.Side

object GameOverScene extends Scene {

  def init() : Unit = {
    cameras = Vector(new Camera(Game.window, Game.width, Game.height, 1f, Side.Full))

    val gameover_msg = new TextGameObject("GAME OVER", characterSize = 50)
    gameover_msg.position = Vector2(300,150)

    val background = new RectangleShapeGameObject(Color(0, 0, 0, 10), 0, 0, Game.width, Game.height)

    objects = Vector(background, gameover_msg)
  }

}
