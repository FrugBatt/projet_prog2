package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2
import sfml.graphics.Rect
import sfml.window.Event

import game.Game
import sfml.window.Keyboard

import objects.*
import scala.compiletime.ops.float
import scala.collection.mutable.ListBuffer
import javax.swing.Box

class GameScene(window : RenderTarget, width : Int, height : Int) extends Scene {
  
  def init() : Unit = {
    val world = new World()   

    val king = new King(this)
    val camera = new CenteredCamera(window, width, height, 0.2f, king)
   
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

    val enemy1 = new Enemy(this)
    enemy1.position = Vector2(w*random(),h*random())
    // val hpe1 = new EntityHP(enemy1,10,2)

    val enemy2 = new Enemy(this)
    enemy2.position = Vector2(w*random(),h*random())
    // val hpe2 = new EntityHP(enemy2,10,2)

    val enemy3 = new Enemy(this)
    enemy3.position = Vector2(w*random(),h*random())
    // val hpe3 = new EntityHP(enemy3,10,2)

    val chicken1 = new Pouleto(this)
    chicken1.position = Vector2(w*random(),h*random())
    // val hpc1 = new EntityHP(chicken1,5,3)

    val chicken2 = new Pouleto(this)
    chicken2.position = Vector2(w*random(),h*random())
    // val hpc2 = new EntityHP(chicken2,5,3)

    val mountain = new ResourceGenerator(ResourceType.STONE,"game/mountain.png",Vector2(w*random(),h*random()))
    mountain.scale = Vector2(1.5f,1.5f)
    val forest = new ResourceGenerator(ResourceType.WOOD,"game/forest.png",Vector2(w*random(),h*random()))

    val castle = new Base()
    castle.position = Vector2(w*random(),h*random())
    objects = Vector(camera, world, mountain, forest, enemy1, enemy2, enemy3, chicken1, chicken2, north_wall, west_wall, south_wall, east_wall, king, castle)

    add(mountain.deposit)
    add(forest.deposit)
  }

  override def call_event(e : Event) : Unit = {
    e match{
      case e : Event.KeyPressed =>
        if (e.code == Keyboard.Key.KeyEscape) {
          Game.pause = true
          Game.scenes = Game.scenes :+ PauseScene
        }
      case _ => ()
    }
    super.call_event(e)
  }

}
