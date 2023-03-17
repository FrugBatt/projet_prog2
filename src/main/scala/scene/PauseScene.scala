package prog2
package scene

import sfml.system.Vector2
import objects.TextGameObject
import objects.RectangleShapeGameObject
import sfml.graphics.Color

import game.Game

import sfml.window._

object PauseScene extends Scene {

  def init() = {
    val title = new TextGameObject("Pause (Press ESCAPE to quit)", characterSize = 50)
    title.position = Vector2(200,250)

    val background = new RectangleShapeGameObject(Color(0, 0, 0, 100), 0, 0, Game.width, Game.height)

    objects = Vector(background, title)
  }

  override def call_event(e : Event) : Unit = {
    e match {
      case e : Event.KeyPressed =>
        if (e.code == Keyboard.Key.KeyEscape) {
          Game.pause = false
          Game.scenes = Game.scenes.filterNot(this.==)
        }
      case _ => ()
    }
  }

}
