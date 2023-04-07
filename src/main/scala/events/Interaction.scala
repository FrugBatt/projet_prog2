package prog2
package events

import objects.ResourceType

// == ACTION == 
sealed trait InteractionAction
class ResourceHarvestAction extends InteractionAction
class ResourceCollectAction extends InteractionAction

// == RESPONSE == 
sealed trait InteractionResponse
class NoAction extends InteractionResponse
class ResourceCollectResponse(val resourceType : ResourceType) extends InteractionResponse
class ResourceHarvestResponse(val resourceType: ResourceType) extends InteractionResponse
// class ResourceRetrievalReponse(val resourceType: ResourceType) extends InteractionResponse
