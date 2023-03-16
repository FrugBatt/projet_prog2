package prog2
package objects

import graphics.ResourceManager

import sfml.window.Keyboard
import sfml.window.Event
import sfml.graphics.Rect
import sfml.graphics.Sprite
import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.system.Vector2
import game.Game
import scene._
import objects.Resource
import objects.ResourceType
import objects.King
import events._
import scala.compiletime.ops.long

class EntityGameObject(hp : Int, drop : () => Resource, resource : String, width : Int, height : Int, animationLen : Array[Int], animationTime : Long = 800L, val hp_x_offset: Int = 2) extends AnimatedGameObject(resource, width, height, animationLen, animationTime) {

  val health = {
    val h = new EntityHP(hp)
    h
  }

  override def update(): Unit = {
    super.update()
    health.update()
    health.position = (position.x + hp_x_offset, position.y - 2)
  }

  override def attack(dmg : Int, attacker : SpriteGameObject) = {
    health.hp = (health.hp - dmg).max(0)
    if (health.hp == 0) {
      val d = drop()
      d.position = position
      return AttackKilled(Some(d))
    }
    return AttackSuccess()
  }

  override def draw(target : RenderTarget, states : RenderStates) : Unit = {
    super.draw(target, states)
    health.draw(target, states)
  }

  override def position_=(x: Float, y: Float) = {
    sprite.position = (x, y)
    health.position = (x + 2, y - 2)
  }

  override def position_=(position: Vector2[Float]) = {
    sprite.position = position
    health.position = (position.x + 2, position.y - 2)
  }
}
