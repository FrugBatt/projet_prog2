package prog2
package scene

import sfml.system.Vector2

import objects.TextGameObject

object GameOverScene extends Scene {

  def init() : Unit = {
    val gameover_msg = new TextGameObject("GAME OVER")
    gameover_msg.scale = Vector2(1.5f,1.5f)
    gameover_msg.position = Vector2(300,150)

    objects = Vector(gameover_msg)
  }

}
