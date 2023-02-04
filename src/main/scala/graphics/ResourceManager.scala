package graphics

import sfml.graphics.Texture
import sfml.graphics.Sprite

object ResourceManager:

  val resource_loc = "src/main/resources/"
  val resources = scala.collection.mutable.Map[String, Texture]()
  val sprites = scala.collection.mutable.Map[String, Sprite]()

  def load_resource(loc: String) =
    val texture = Texture()
    texture.loadFromFile(resource_loc + loc)
    resources(loc) = texture
    sprites(loc) = Sprite(texture)

  def get_sprite(loc: String): Sprite = return sprites(loc)

  def close() =
    sprites.foreach(_._2.close())
    resources.foreach(_._2.close())
