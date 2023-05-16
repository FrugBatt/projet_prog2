package prog2
package scene

import sfml.system.Vector2
import objects.TextGameObject
import objects.RectangleShapeGameObject
import sfml.graphics.Color

import objects.Camera
import objects.Side

import game.Game

import sfml.window._

object PauseScene extends Scene {

  def init() = {
    val title = new TextGameObject("Pause (Press ESCAPE to quit)", characterSize = 50)
    title.position = Vector2(200,250)

    cameras = Vector(new Camera(Game.window, Game.width, Game.height, 1f, Side.Full))

    val background = new RectangleShapeGameObject(Color(0, 0, 0, 100), 0, 0, Game.width, Game.height)

    objects = Vector(background, title)
  }
}
