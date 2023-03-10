package prog2
package scene

import sfml.system.Vector2
import objects.TextGameObject

import game.Game

import sfml.window._

object PauseScene extends Scene {

  def init() = {
    val title = new TextGameObject("Pause (Press ESCAPE to quit)", characterSize = 50)
    title.position = Vector2(200,250)

    objects = Vector(title)
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
