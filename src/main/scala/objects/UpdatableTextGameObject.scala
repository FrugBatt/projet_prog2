package prog2
package objects

class UpdatableTextGameObject(val value : () => Int) extends TextGameObject(value() + "") {

  override def update() : Unit = {
    text.string = value() + ""
  }

}
