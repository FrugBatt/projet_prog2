package prog2
package scene

import sfml.system.Vector2
import objects.TextGameObject

import game.Game

import sfml.window._

object PauseScene extends Scene {

  def init() = {
    val title = new TextGameObject("Pause (Press ESCAPE to quit)")
    title.scale = Vector2(1.5f,1.5f)
    title.position = Vector2(300,150)

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
