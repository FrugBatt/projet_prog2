package graphics

import sfml.graphics.Texture
import sfml.graphics.Sprite

object ResourceManager:

  val resource_loc = "src/main/resources/"
  val resources = scala.collection.mutable.Map[String, Int]()
  val textures = scala.collection.mutable.Map[String, Texture]()
  val sprites = scala.collection.mutable.Map[String, Sprite]()

  def load_resource(loc: String) : Unit =
    if (resources contains loc) {
      resources(loc) += 1
    } else {
      val texture = Texture()
      texture.loadFromFile(resource_loc + loc)
      resources(loc) = 1
      textures(loc) = texture
      sprites(loc) = Sprite(texture)
    }

  def get_sprite(loc: String): Sprite = return sprites(loc)

  def close(loc : String) : Unit = {
    if (resources contains loc) {
      if (resources(loc) == 1) {
        textures(loc).close()
        sprites(loc).close()

        resources -= loc
        textures -= loc
        sprites -= loc
      } else {
        resources(loc) -= 1
      }
    }
  }

  def close_all() : Unit =
    sprites.foreach(_._2.close())
    textures.foreach(_._2.close())
