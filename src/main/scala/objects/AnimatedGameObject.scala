package prog2
package objects

import scala.collection.mutable.Map

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite
import sfml.graphics.Rect

import graphics.ResourceManager

trait AnimatedGameObject(resource : String, width : Int, height : Int, animationNum : Array[Int]) extends GameObject {

  var animationIteration = 0
  var animationState = 0

  var sprite : Sprite = _

  def init() : Unit = {
    ResourceManager.load_resource(resource)

    sprite = ResourceManager.get_sprite(resource)
  }

  def animationRect : Rect[Int] = {
    val x = animationIteration * width
    val y = animationState * height
    return Rect[Int](x, y, width, height)
  }

  def update() : Unit = {
    animationIteration += 1
    if (animationIteration >= animationNum(animationState)) animationIteration = 0

    sprite.textureRect = animationRect
  }

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    states.transform *= transform
    sprite.draw(target, states)
  }

  override def close() : Unit = {
    sprite.close()
    ResourceManager.close(resource)
  }

}
