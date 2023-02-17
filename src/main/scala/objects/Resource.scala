package prog2
package objects

import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Sprite

import graphics.ResourceManager

class Resource(resource : String, val value: Int, val kind: String) extends SpriteGameObject(resource){
  
  def update() : Unit = {}

}



