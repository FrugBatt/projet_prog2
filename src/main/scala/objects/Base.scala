package prog2
package objects

import sfml.graphics.Rect


class Base() extends AnimatedGameObject("game/castle.png", 51, 48,Array(8)){
    
    override def collision_box = Some(Rect[Float](position.x + 1, position.y + 24, 47, 23))

}