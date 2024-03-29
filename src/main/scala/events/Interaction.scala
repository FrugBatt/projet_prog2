package prog2
package events

import objects.PlayerState
import objects.ResourceType

// == ACTION == 
sealed trait InteractionAction
class ResourceHarvestAction(val player : PlayerState) extends InteractionAction
class ResourceCollectAction(val player : PlayerState) extends InteractionAction

// == RESPONSE == 
sealed trait InteractionResponse
class NoAction extends InteractionResponse
class ResourceCollectResponse(val resourceType : ResourceType) extends InteractionResponse
class ResourceHarvestResponse(val resourceType: ResourceType) extends InteractionResponse
