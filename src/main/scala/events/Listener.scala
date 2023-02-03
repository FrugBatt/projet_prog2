package prog2
package event

import sfml.window.Event

trait Listener {

  def event(e : Event) : Unit

}
