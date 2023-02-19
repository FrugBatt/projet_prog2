package prog2
package events

import objects.ResourceType

sealed trait InteractionAction
class NoAction extends InteractionAction
class ResourceCollectAction(val resourceType : ResourceType) extends InteractionAction
