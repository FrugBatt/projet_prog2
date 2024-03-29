package prog2
package scene

import sfml.graphics.RenderTarget
import sfml.system.Vector2
import sfml.graphics.Rect
import sfml.window.Event
import sfml.window.Mouse

import game.Game
import events.Control
import sfml.window.Keyboard

import objects.*
import scala.compiletime.ops.float
import scala.collection.mutable.ListBuffer
import javax.swing.Box

object GameScene extends Scene {
  
  var king1 : King = _
  var king2 : King = _
  var camera : CenteredCamera = _
  var world : World = _
  var north_wall : BoxGameObject = _
  var west_wall : BoxGameObject = _
  var south_wall : BoxGameObject = _
  var spawner : Spawner = _

  def mouse_position() = {
    Game.window.mapPixelToCoords(Mouse.position,camera.v) - Vector2(24,22)
  } 

  def init() : Unit = {
    king1 = new King(this, PlayerState.P1)
    // king2 = new King(this, PlayerState.P2)
    // king2.position = Vector2(100,100)
    
    camera = new CenteredCamera(Game.window, Game.width, Game.height, 0.2f, king1, Side.Full)
    // cameras = Vector(camera, new CenteredCamera(Game.window, Game.width, Game.height, 0.2f, king2, Side.Right))
    cameras = Vector(camera)
    world = new World()  
    val w = world.map_rect().width
    val h = world.map_rect().height

    val selectionbox = SelectionBox

    north_wall = new BoxGameObject(Rect[Float](0,-1,w,1))
    west_wall = new BoxGameObject(Rect[Float](-1,0,1,h))
    south_wall = new BoxGameObject(Rect[Float](0,h + 1,w,1))

    val x = 0f
    val y = 0f

    val rand = new scala.util.Random

    def random() : Float = {
      (rand.between(15,85).toFloat)/100
    }

    spawner = new Spawner(this,6000L,12000L)

    val ogre = new Ogre(this)
    ogre.position = Vector2(w*random(),h*random())
    // val hpe1 = new EntityHP(enemy1,10,2)

    val goblin = new Goblin(this)
    goblin.position = Vector2(w*random(),h*random())

    val chicken1 = new Pouleto(this)
    chicken1.position = Vector2(w*random(),h*random())
    // val hpc1 = new EntityHP(chicken1,5,3)

    val chicken2 = new Pouleto(this)
    chicken2.position = Vector2(w*random(),h*random())
    // val hpc2 = new EntityHP(chicken2,5,3)

    val mountain = new ResourceGenerator(ResourceType.STONE,"game/mountain.png",Vector2(w*random()*0.85f,h*random()*0.85f))
    mountain.scale = Vector2(1.5f,1.5f)
    val forest = new ResourceGenerator(ResourceType.WOOD,"game/forest.png",Vector2(w*random()*0.85f,h*random()*0.85f))

    val soldier1 = new Soldier()
    soldier1.position = Vector2(w*random(),h*random())

    val soldier2 = new Soldier()
    soldier2.position = Vector2(w*random(),h*random())

    val soldier3 = new Soldier()
    soldier3.position = Vector2(w*random(),h*random())

    val soldier4 = new Soldier()
    soldier4.position = Vector2(w*random(),h*random())


    objects = Vector(spawner, world, Lava, mountain, forest, ogre, goblin, chicken1, chicken2, north_wall, west_wall, south_wall, king1, soldier1, soldier2, soldier3, soldier4, selectionbox)

    add(mountain.deposit)
    add(forest.deposit)

    Control.pause.addListener(pause)
  }

  def pause(start : Boolean) : Unit = {
    if (start) {
      if (Game.pause) {
        Game.pause = false
        Game.scenes = Game.scenes.filterNot(PauseScene.==)
      } else {
        Game.pause = true
        Game.scenes = Game.scenes :+ PauseScene
      }
    }
  }

}
