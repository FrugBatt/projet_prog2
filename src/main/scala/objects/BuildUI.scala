package prog2
package objects

import events._
import scene._
import game.Game
import objects.TextGameObject
import objects.PersonalInventory
import objects.King
import sfml.system.Vector2

object BuildUI extends StatedGameObject("game/buildUI.png",110,62) {
    var active = false

    scale = Vector2(3f,3f)
    position = Vector2(20, Game.height - 300)


    val castle_build = new TextGameObject("Castle       20    4     2", 25)
    castle_build.position = Vector2(40, Game.height - 240)
    val barracks_build = new TextGameObject("Barracks   10    10   4", 25)
    barracks_build.position = Vector2(40, Game.height - 203)
    val mine_build = new TextGameObject("Mine          15    8    2", 25)
    mine_build.position = Vector2(40, Game.height - 166)


    override def init() : Unit = {
        super.init()

        Control.uiUp.addListener(up)
        Control.uiDown.addListener(down)
        Control.uiConfirm.addListener(build)
    }

    override def close() : Unit = {
        super.close()

        Control.uiUp.removeListener(up)
        Control.uiDown.removeListener(down)
        Control.uiConfirm.removeListener(build)
    }

    def uidisplay() : Unit = {
        HudScene.add(this)
        HudScene.add(castle_build)
        HudScene.add(barracks_build)
        HudScene.add(mine_build)
        active = true
    }

    def uiclose() : Unit = {
        HudScene.del(this)
        HudScene.del(castle_build)
        HudScene.del(barracks_build)
        HudScene.del(mine_build)
        active = false
    }

    def up(start: Boolean) : Unit = {
        state = (state-1).max(0)
    }
    def down(start: Boolean) : Unit = {
        state = (state+1).min(2)
    }
    def build(start: Boolean) : Unit = {
        if (start && active) {
            if(state == 0){
                if (!GameScene.king.has_castle){
                    if (PersonalInventory.inventory.amount(ResourceType.STONE) >= 20 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 4 && PersonalInventory.inventory.amount(ResourceType.COIN) >= 2) {
                        val castle = new Castle(GameScene.king)
                        castle.position = (GameScene.king.position.x, GameScene.king.position.y + GameScene.king.sprite.textureRect.height)
                        castle.update()
                        GameScene.add(castle)
                        PersonalInventory.inventory.remove(ResourceType.STONE,20)
                        PersonalInventory.inventory.remove(ResourceType.WOOD,4)
                        PersonalInventory.inventory.remove(ResourceType.COIN,2)
                        GameScene.king.has_castle = true
                    }
                    else println("not enough resources")
                }
                else println("you can only have one castle")
            }

            if (state == 1) {
                if (PersonalInventory.inventory.amount(ResourceType.STONE) >= 10 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 10 && PersonalInventory.inventory.amount(ResourceType.COIN) >= 4) {
                        val barracks = new Barracks(GameScene.king)
                        barracks.position = (GameScene.king.position.x, GameScene.king.position.y + GameScene.king.sprite.textureRect.height)
                        GameScene.add(barracks)
                        PersonalInventory.inventory.remove(ResourceType.STONE,10)
                        PersonalInventory.inventory.remove(ResourceType.WOOD,10)
                        PersonalInventory.inventory.remove(ResourceType.COIN,4)
                }
                else println("not enough resources")
            }


            if (state == 2) {
                if (PersonalInventory.inventory.amount(ResourceType.STONE) >= 15 && PersonalInventory.inventory.amount(ResourceType.WOOD) >= 4 && PersonalInventory.inventory.amount(ResourceType.COIN) >= 0) {
                        val mine = new Mine(GameScene.king)
                        mine.position = (GameScene.king.position.x, GameScene.king.position.y + GameScene.king.sprite.textureRect.height)
                        GameScene.add(mine)
                        PersonalInventory.inventory.remove(ResourceType.STONE,15)
                        PersonalInventory.inventory.remove(ResourceType.WOOD,8)
                        PersonalInventory.inventory.remove(ResourceType.COIN,2)
                }
                else println("not enough resources")
            }
        }
    }

}