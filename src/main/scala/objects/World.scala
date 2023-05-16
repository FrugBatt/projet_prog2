package prog2
package objects

import graphics.ResourceManager

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

import sfml.system.Vector2
import scene.GameScene

class World() extends GameObject() {

  val rand = new scala.util.Random()

  def resource : String = {
    rand.nextInt(4) match {
      case 0 => "game/terrain_1.png"
      case 1 => "game/terrain_2.png"
      case 2 => "game/terrain_3.png"
      case 3 => "game/terrain_4.png"
    }
  }
  var sprites : Vector[Sprite] = Vector(load_sprite(resource))

  var width : Int = 1

  override def collision_box = None
  override def trigger_box = None
  
  def load_sprite(name : String) : Sprite = {
    ResourceManager.load_resource(name)
    return ResourceManager.get_sprite(name)
  }

  def expand_map() : Unit = {
    val new_sprite = load_sprite(resource)
    new_sprite.position = Vector2(width*new_sprite.textureRect.width,0)
    sprites = sprites :+ new_sprite
    width += 1
    GameScene.north_wall.box = Rect(0,-1,width*new_sprite.textureRect.width,1)
    GameScene.south_wall.box = Rect(0,map_rect().height + 1,width*new_sprite.textureRect.width,1)
  }

  def map_rect() : Rect[Float] = {
    Rect(0,0,width*sprites(0).textureRect.width,sprites(0).textureRect.height)
  }

  override def init() : Unit = {}

  def update() : Unit = {
    if (GameScene.cameras.exists(cam => map_rect().width < cam.v.center.x + sprites(0).textureRect.width/2)) {
      expand_map()
      println("map expanded")
    }
  }
    
  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    sprites.foreach(_.draw(target, states))
  }

  override def close() : Unit = {
  }

}
