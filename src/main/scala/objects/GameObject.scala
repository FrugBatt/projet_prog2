package prog2

import sfml.graphics._
import event.Listener
import graphics.ResourceManager
import sfml.system.Vector2

abstract class GameObject(refreshTime : Long = 10L) extends Drawable with Listener {
  
  var lastUpdate : Long = System.currentTimeMillis()

  def init() : Unit

  def update_head() : Unit = {
    if (System.currentTimeMillis() - lastUpdate < refreshTime) return
    lastUpdate = System.currentTimeMillis()
    
    update()
  }
  def update() : Unit

  def close() : Unit

  init()
  
}
