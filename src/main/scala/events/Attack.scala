

package prog2
package events


sealed trait AttackAction
class NoAttack extends AttackAction
class AttackSuccess(val target: GameObject) extends AttackAction
