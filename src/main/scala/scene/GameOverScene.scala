package prog2
package scene

import sfml.system.Vector2

import objects.TextGameObject

object GameOverScene extends Scene {

  def init() : Unit = {
    val gameover_msg = new TextGameObject("GAME OVER", characterSize = 50)
    gameover_msg.position = Vector2(300,150)

    objects = Vector(gameover_msg)
  }

}
