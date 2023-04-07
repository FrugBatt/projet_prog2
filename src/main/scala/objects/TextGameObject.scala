package prog2
package objects

import sfml.graphics.RenderTarget
import sfml.graphics.RenderStates
import sfml.graphics.Text
import sfml.graphics.Font
import sfml.graphics.Color
import sfml.system.Vector2

class TextGameObject(label : String, characterSize : Int = 65) extends GameObject {

  override def trigger_box = None
  override def collision_box = None

  val f = {
    val f = Font()
    f.loadFromFile("src/main/resources/Minecraft-Regular.otf")
    f
  }

  val text : Text = {
    val t = Text()
    t.font = f

    t.string = label
    t.characterSize = characterSize
    t
  }

  def init() : Unit = {
  }

  def update() : Unit = {}
  def close() : Unit = {}

  def draw(target : RenderTarget, states : RenderStates) : Unit = {
    text.draw(target, states)
  }

  final def origin: Vector2[Float] = text.origin

  final def origin_=(x: Float, y: Float) = text.origin = (x, y)

  final def origin_=(origin: Vector2[Float]) = text.origin = origin

  override final def position: Vector2[Float] = text.position

  final def position_=(x: Float, y: Float) = text.position = (x, y)

  final def position_=(position: Vector2[Float]) = text.position = position

  final def move(x : Float, y : Float) : Unit = text.move(x, y)

  final def rotation: Float = text.rotation

  final def rotation_=(angle: Float) = text.rotation = angle

  final def scale: Vector2[Float] = text.scale

  final def scale_=(x: Float, y: Float) = text.scale = (x, y)

  final def scale_=(factors: Vector2[Float]) = text.scale = factors

  final def characterSize_=(size: Int) = text.characterSize = size

}
