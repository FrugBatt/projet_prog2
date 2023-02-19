package prog2
package scene

import sfml.graphics.View
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.system.Vector2

import objects.King
import objects.CenteredCamera
import objects.World
import objects.Healthbar
import objects.*
import scala.compiletime.ops.float

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val king = new King()
    val camera = new CenteredCamera(window, width, height, king)
    val hp = new Healthbar()
    val inv = new Inventory()
    val hudcamera = new Camera(window,width,height)
    hp.scale = Vector2(5f,5f)
    inv.position = Vector2(0,height/10)
    inv.scale = Vector2(5f,5f)
    
    val w = world.sprite.textureRect.width
    val h = world.sprite.textureRect.height

    val rand = new scala.util.Random

    def random() : Float = {
      (rand.between(15,85).toFloat)/100
    }

    val enemy1 = new Enemy()
    enemy1.position = Vector2(w*random(),h*random())
    val hpe1 = new EntityHP(enemy1,10,2)

    val enemy2 = new Enemy()
    enemy2.position = Vector2(w*random(),h*random())
    val hpe2 = new EntityHP(enemy2,10,2)

    val enemy3 = new Enemy()
    enemy3.position = Vector2(w*random(),h*random())
    val hpe3 = new EntityHP(enemy3,10,2)

    val chicken1 = new Pouleto()
    chicken1.position = Vector2(w*random(),h*random())
    val hpc1 = new EntityHP(chicken1,5,3)

    val chicken2 = new Pouleto()
    chicken2.position = Vector2(w*random(),h*random())
    val hpc2 = new EntityHP(chicken2,5,3)

    val rock1 = new Resource("game/rock.png",0,"")
    rock1.position = Vector2(w*random(),h*random())

    val rock2 = new Resource("game/rock.png",0,"")
    rock2.position = Vector2(w*random(),h*random())

    val wood1 = new Resource("game/wood.png",0,"")
    wood1.position = Vector2(w*random(),h*random())


    objects = Vector(camera, world, rock1, rock2, wood1, enemy1, hpe1, enemy2, hpe2, enemy3, hpe3, chicken1, hpc1, chicken2, hpc2, king, hudcamera, inv, hp)
  }

}
