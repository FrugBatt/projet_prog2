package prog2
package objects

import events._
import scene._
import game.Game
import objects.TextGameObject
import sfml.system.Vector2

object BuildUI extends StatedGameObject("game/buildUI.png",110,62) {
    var active = false

    scale = Vector2(3f,3f)
    position = Vector2(20, Game.height - 300)


    val castle_build = new TextGameObject("Castle       10    4     2", 25)
    castle_build.position = Vector2(40, Game.height - 240)
    val barracks_build = new TextGameObject("Barracks    3     6    4", 25)
    barracks_build.position = Vector2(40, Game.height - 203)
    val mine_build = new TextGameObject("Mine          15    4    0", 25)
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
    def build(start: Boolean) : Unit = {}

}