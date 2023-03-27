package prog2
package objects

import scala.collection.mutable.Map

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

object PersonalInventory extends SpriteGameObject("game/inventory.png"){
  
  val inventory = new Inventory()
  var health = 10

  def update() : Unit = {}

}



