package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2

import objects.*
import scala.compiletime.ops.float

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val king = new King()
    val camera = new CenteredCamera(window, width, height, king)
   
    val w = world.sprite.textureRect.width
    val h = world.sprite.textureRect.height

    val rand = new scala.util.Random

    def random() : Float = {
      (rand.between(15,85).toFloat)/100
    }

    val enemy1 = new Enemy()
    enemy1.position = Vector2(w*random(),h*random())

    val enemy2 = new Enemy()
    enemy2.position = Vector2(w*random(),h*random())

    val enemy3 = new Enemy()
    enemy3.position = Vector2(w*random(),h*random())

    val chicken1 = new Pouleto()
    chicken1.position = Vector2(w*random(),h*random())

    val chicken2 = new Pouleto()
    chicken2.position = Vector2(w*random(),h*random())

    val rock1 = new Resource("game/rock.png",0,"")
    rock1.position = Vector2(w*random(),h*random())

    val rock2 = new Resource("game/rock.png",0,"")
    rock2.position = Vector2(w*random(),h*random())

    val wood1 = new Resource("game/wood.png",0,"")
    wood1.position = Vector2(w*random(),h*random())


    objects = Vector(camera, world, rock1, rock2, wood1, enemy1, enemy2, enemy3, chicken1, chicken2, king)
  }

}
