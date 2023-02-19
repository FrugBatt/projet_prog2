package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Rect

class BoxGameObject(box : Rect[Float]) extends GameObject {

  def collision_box = Some(box)
  def trigger_box = None

  def init() : Unit = {}
  def update() : Unit = {}
  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {}

}
