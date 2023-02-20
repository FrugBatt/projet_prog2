package prog2
package objects

import sfml.graphics._
import events.Listener
import events.InteractionAction
import events.NoAction
import sfml.system.Vector2

abstract class GameObject(refreshTime : Long = 10L) extends Drawable with Listener {
  
  var lastUpdate : Long = System.currentTimeMillis()
  var hpbar : Option[EntityHP] = None

  def init() : Unit

  def update_head() : Unit = {
    if (System.currentTimeMillis() - lastUpdate < refreshTime) return
    lastUpdate = System.currentTimeMillis()
    
    update()
  }
  def update() : Unit

  def close() : Unit

  def collision_box : Option[Rect[Float]]
  def trigger_box : Option[Rect[Float]]

  def interact() : InteractionAction = return NoAction()

  init()
  
}
