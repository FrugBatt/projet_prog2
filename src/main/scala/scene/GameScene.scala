package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2
import sfml.graphics.Rect

import objects.*
import scala.compiletime.ops.float
import scala.collection.mutable.ListBuffer
import javax.swing.Box

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val king = new King(this)
    val camera = new CenteredCamera(window, width, height, king)
   
    val w = world.sprite.textureRect.width
    val h = world.sprite.textureRect.height
    val x = 0f
    val y = 0f

    val north_wall = new BoxGameObject(Rect[Float](x,y-1, w,1))
    val west_wall = new BoxGameObject(Rect[Float](x-1,y,1,h))
    val south_wall = new BoxGameObject(Rect[Float](x,y+h+1,w,1))
    val east_wall = new BoxGameObject(Rect[Float](x+w+1,y,1,h))

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

    val rock1 = new Resource("game/rock.png",0,ResourceType.STONE)
    rock1.position = Vector2(w*random(),h*random())

    val rock2 = new Resource("game/rock.png",0,ResourceType.STONE)
    rock2.position = Vector2(w*random(),h*random())

    val wood1 = new Resource("game/wood.png",0,ResourceType.WOOD)
    wood1.position = Vector2(w*random(),h*random())


    objects = Vector(camera, world, rock1, rock2, wood1, enemy1, hpe1, enemy2, hpe2, enemy3, hpe3, chicken1, hpc1, chicken2, hpc2, north_wall, west_wall, south_wall, east_wall, king)
  }

}
