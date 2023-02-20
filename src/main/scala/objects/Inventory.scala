package prog2
package objects

import scala.collection.mutable.Map

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

object Inventory extends SpriteGameObject("game/inventory.png"){
  
  var wood = 0
  var stone = 0
  var coin = 0

  def update() : Unit = {}

}



