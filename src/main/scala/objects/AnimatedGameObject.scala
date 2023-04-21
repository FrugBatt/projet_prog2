package prog2
package objects

import scala.collection.mutable.Map

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite
import sfml.graphics.Rect

import graphics.ResourceManager

abstract class AnimatedGameObject(resource : String, width : Int, height : Int, animationNum : Array[Int], val animationTime : Long = 200L) extends StatedGameObject(resource, width, height) {

  var animationIteration = 0

  var lastAnimationUpdate = System.currentTimeMillis()
  var lastAnimationState = 0

  def animationRect : Rect[Int] = {
    val x = animationIteration * width
    val y = stateRect.top
    return Rect[Int](x, y, width, height)
  }

  override def init() : Unit = {
    super.init()
    sprite.textureRect = animationRect
  }

  override def update() : Unit = {
    if (System.currentTimeMillis() - lastAnimationUpdate < animationTime && state == lastAnimationState) return
    super.update()
    lastAnimationUpdate = System.currentTimeMillis()
    lastAnimationState = state 

    animationIteration += 1
    if (animationIteration >= animationNum(state)) animationIteration = 0

    sprite.textureRect = animationRect
  }

}
