package prog2
package objects

class UpdatableTextGameObject(val value : () => Int, val prefix : String = "", val suffix : String = "") extends TextGameObject(prefix + value() + suffix + "") {

  override def update() : Unit = {
    text.string = prefix + value() + suffix + ""
  }
}
