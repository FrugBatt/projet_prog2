package prog2

import sfml.graphics._
import event.Listener
import graphics.ResourceManager
import sfml.system.Vector2

abstract class GameObject(resource : String) extends Drawable with Listener {
  
  var sprite : Sprite = _

  def init() : Unit = {
    ResourceManager.load_resource(resource)
    sprite = ResourceManager.get_sprite(resource)
  }

  def update() : Unit

  def close() : Unit = {
    sprite.close()
    ResourceManager.close(resource)
  }

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    sprite.draw(target, states)
  }

  def bounds : Rect[Float] = {
    return Rect[Float](position.x, position.y, sprite.textureRect.width, sprite.textureRect.height)
  }

  final def origin: Vector2[Float] = sprite.origin

  final def origin_=(x: Float, y: Float) = sprite.origin = (x, y)

  final def origin_=(origin: Vector2[Float]) = sprite.origin = origin

  final def position: Vector2[Float] = sprite.position

  final def position_=(x: Float, y: Float) = sprite.position = (x, y)

  final def position_=(position: Vector2[Float]) = sprite.position = position

  final def rotation: Float = sprite.rotation

  final def rotation_=(angle: Float) = sprite.rotation = angle

  final def scale: Vector2[Float] = sprite.scale

  final def scale_=(x: Float, y: Float) = sprite.scale = (x, y)

  final def scale_=(factors: Vector2[Float]) = sprite.scale = factors

  init()
  
}
