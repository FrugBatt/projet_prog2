package prog2
package objects

import scala.collection.mutable.Map

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates

import graphics.ResourceManager

trait AnimatedGameObject(val resourceLocPrefix : String, val resourceLocExtension : String, val anims : Array[(String, Int)], var animation : String) extends GameObject {

  val animations = Map[String, Array[String]]()
  var animIter = 0

  def init() : Unit = {
    for (i <- 0 to (anims.length-1)) {
      val (animName, animNum) = anims(i)
      val animTab = new Array[String](animNum)

      for (j <- 0 to (animNum - 1)) {
        val animLoc = resourceLocPrefix + "_" + animName + "_" + j + "." + resourceLocExtension
        ResourceManager.load_resource(animLoc)
        animTab(j) = animLoc
      }

      animations(animName) = animTab
    }
  }

  def update() : Unit = {
    animIter += 1
    if (animIter == animations(animation).length) animIter = 0
  }

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    states.transform *= transform
    ResourceManager.get_sprite(animations(animation)(animIter)).draw(target, states)
  }

  override def close() : Unit = {
    animations.foreach(_._2.foreach(ResourceManager.close(_)))
  }

}
