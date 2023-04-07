package prog2
package objects

import sfml.graphics._
import events._
import sfml.system.Vector2

abstract class GameObject(refreshTime : Long = 10L) extends Drawable with Listener {

  def id : Int = 0

  def collision_box : Option[Rect[Float]]
  def trigger_box : Option[Rect[Float]]

  def position : Vector2[Float] = Vector2(0,0)
  
  var lastUpdate : Long = System.currentTimeMillis()

  def init() : Unit

  def update_head() : Unit = {
    if (System.currentTimeMillis() - lastUpdate < refreshTime) return
    lastUpdate = System.currentTimeMillis()
    
    update()
  }
  def update() : Unit

  def close() : Unit

  def interact() : InteractionResponse = return NoAction()
  def damage(dmg: Int, attacker: SpriteGameObject) : AttackResponse = return NoAttack()

  init()
  
}
