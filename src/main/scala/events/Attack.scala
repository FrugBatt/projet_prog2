

package prog2
package events

import objects.Resource


sealed trait AttackResponse
class NoAttack extends AttackResponse
class AttackSuccess extends AttackResponse
class AttackKilled(val drop: Resource) extends AttackResponse
