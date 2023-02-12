package prog2
package objects

import scala.collection.mutable.Map

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite
import sfml.graphics.Rect

import graphics.ResourceManager

abstract class AnimatedGameObject(resource : String, width : Int, height : Int, animationNum : Array[Int], val animationTime : Long = 200L) extends GameObject(resource) {

  var animationIteration = 0
  var animationState = 0

  var lastAnimationUpdate = System.currentTimeMillis()
  var lastAnimationState = 0

  def animationRect : Rect[Int] = {
    val x = animationIteration * width
    val y = animationState * height
    return Rect[Int](x, y, width, height)
  }

  def update() : Unit = {
    if (System.currentTimeMillis() - lastAnimationUpdate < animationTime && animationState == lastAnimationState) return
    lastAnimationUpdate = System.currentTimeMillis()
    lastAnimationState = animationState

    animationIteration += 1
    if (animationIteration >= animationNum(animationState)) animationIteration = 0

    sprite.textureRect = animationRect
  }

}
