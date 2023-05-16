package prog2
package objects

import scala.collection.mutable.Map

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

object PersonalInventory {
  
  val inventoryP1 = new Inventory()
  val inventoryP2 = new Inventory()

  var healthP1 = 10
  var healthP2 = 10

}



