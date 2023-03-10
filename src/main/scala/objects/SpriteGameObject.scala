package prog2
package objects

import sfml.graphics._
import graphics.ResourceManager
import sfml.system.Vector2

abstract class SpriteGameObject(resource : String) extends GameObject() {
  
  var sprite : Sprite = _

  def init() : Unit = {
    ResourceManager.load_resource(resource)
    sprite = ResourceManager.get_sprite(resource)
  }

  def close() : Unit = {
    // sprite.close()
    ResourceManager.close(resource)
  }

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    sprite.draw(target, states)
  }

  def bounds : Rect[Float] = {
    return Rect[Float](position.x, position.y, sprite.textureRect.width, sprite.textureRect.height)
  }

  def collision_box = Some(bounds)
  def trigger_box = Some(bounds)

  def origin: Vector2[Float] = sprite.origin

  def origin_=(x: Float, y: Float) = sprite.origin = (x, y)

  def origin_=(origin: Vector2[Float]) = sprite.origin = origin

  def position: Vector2[Float] = sprite.position

  def position_=(x: Float, y: Float) = sprite.position = (x, y)

  def position_=(position: Vector2[Float]) = sprite.position = position

  def center: Vector2[Float] = Vector2(position.x + sprite.textureRect.width / 2, position.y + sprite.textureRect.height / 2)

  def move(x : Float, y : Float) : Unit = sprite.move(x, y)

  def rotation: Float = sprite.rotation

  def rotation_=(angle: Float) = sprite.rotation = angle

  def scale: Vector2[Float] = sprite.scale

  def scale_=(x: Float, y: Float) = sprite.scale = (x, y)

  def scale_=(factors: Vector2[Float]) = sprite.scale = factors

}
