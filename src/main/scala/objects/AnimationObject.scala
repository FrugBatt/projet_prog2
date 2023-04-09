package prog2
package objects

import scene._
import sfml.system.Vector2


class AnimationObject(context : Scene, x: Float, y : Float, resource : String, width : Int, height : Int, animationNum : Array[Int], animationTime : Long = 200L) extends AnimatedGameObject(resource, width, height, animationNum, animationTime){
    var start : Long = 0
    var finished : Boolean = false
    position = Vector2(x,y)

    override def collision_box = None

    def animate(s : Int) = {
        start = System.currentTimeMillis()
        state = s
        animationIteration = 0
        context.add(this)
    }

    override def update(): Unit = {
        super.update()

        if(System.currentTimeMillis() - start > animationTime*animationNum(state)) {
            finished = true
            context.del(this)
        }
    }
}