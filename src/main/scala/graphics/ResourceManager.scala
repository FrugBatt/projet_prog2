package graphics

import sfml.graphics.Texture
import sfml.graphics.Sprite

object ResourceManager:

  val resource_loc = "src/main/resources/"
  val resources = scala.collection.mutable.Map[String, Int]()
  val textures = scala.collection.mutable.Map[String, Texture]()

  def load_resource(loc: String) : Unit =
    if (resources contains loc) {
      resources(loc) += 1
    } else {
      val texture = Texture()
      texture.loadFromFile(resource_loc + loc)
      resources(loc) = 1
      textures(loc) = texture
    }

  def get_sprite(loc: String): Sprite = return Sprite(textures(loc))

  def close(loc : String) : Unit = {
    if (resources contains loc) {
      if (resources(loc) == 1) {
        textures(loc).close()

        resources -= loc
        textures -= loc
      } else {
        resources(loc) -= 1
      }
    }
  }

  def close_all() : Unit =
    textures.foreach(_._2.close())
