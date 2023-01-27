package graphics

import sfml.graphics.Texture
import sfml.graphics.Sprite

object ResourceManager:

  val resource_loc = "src/main/resources/"
  val resources = scala.collection.mutable.Map[String, Texture]()
  val sprites = scala.collection.mutable.Map[String, Sprite]()

  def load_resource(id: String, file: String) =
    val texture = Texture()
    texture.loadFromFile(resource_loc + file)
    resources(id) = texture
    sprites(id) = Sprite(texture)


  def get_sprite(id: String): Sprite = return sprites(id)

  def close() =
    sprites.foreach(_._2.close())
    resources.foreach(_._2.close())
