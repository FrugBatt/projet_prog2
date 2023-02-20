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

class Enemy() extends AnimatedGameObject("game/enemy.png", 18, 22, Array(2), animationTime = 800L) {

    override def collision_box = Some(Rect[Float](position.x + 4, position.y, 6, sprite.textureRect.height))
}