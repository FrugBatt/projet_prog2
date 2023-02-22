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
import objects.Resource
import events._

class Pouleto extends EntityGameObject(5, () => new Resource("game/meat.png", 3, ResourceType.MEAT), "game/chicken.png", 16, 17, Array(12)) {
    
    override def collision_box = Some(Rect[Float](position.x + 2, position.y + 7, 12, 7))
    override def trigger_box = Some(Rect[Float](position.x - 4, position.y - 4, sprite.textureRect.width + 8, sprite.textureRect.height + 8))

}
