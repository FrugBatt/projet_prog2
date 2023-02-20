

package prog2
package events


sealed trait AttackResponse
class NoAttack extends AttackResponse
class AttackSuccess extends AttackResponse
class AttackKilled extends AttackResponse
