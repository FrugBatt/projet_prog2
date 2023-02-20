

package prog2
package events


sealed trait AttackAction
class NoAttack extends AttackAction
class AttackSuccess extends AttackAction
class AttackKilled extends AttackAction
