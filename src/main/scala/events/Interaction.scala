package prog2
package events

import objects.ResourceType

sealed trait InteractionResponse
class NoAction extends InteractionResponse
class ResourceCollectAction(val resourceType : ResourceType) extends InteractionResponse
