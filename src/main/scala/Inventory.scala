package prog2
package objects

import objects.Resource

class Inventory {

  val content = scala.collection.mutable.Map[ResourceType, Int]()

  def amount(res : ResourceType) : Int = {
    return content.getOrElse(res, 0)
  }

  def remove(res : ResourceType, amount : Int) : Unit = {
    if (this.amount(res) - amount <= 0) {
      content -= res
    } else {
      content(res) = content(res) - amount
    }
  }

  def add(res : ResourceType, amount : Int) : Unit = {
    if (this.amount(res) == 0) {
      content(res) = amount
    } else {
      content(res) = content(res) + amount
    }
  }

}
