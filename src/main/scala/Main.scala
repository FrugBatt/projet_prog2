package prog2

import sfml.graphics.*
import sfml.window.*
import graphics.ResourceManager
import game.Game

@main def main =
  ResourceManager.load_resource("cat", "cat.png")  

  Game.init()

