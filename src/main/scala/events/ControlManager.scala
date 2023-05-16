package prog2
package events

import sfml.window._

enum Keys(val code : Int) {
  case Unknown extends Keys(-1)
  case A extends Keys(0)
  case B extends Keys(1)
  case C extends Keys(2)
  case D extends Keys(3)
  case E extends Keys(4)
  case F extends Keys(5)
  case G extends Keys(6)
  case H extends Keys(7)
  case I extends Keys(8)
  case J extends Keys(9)
  case K extends Keys(10)
  case L extends Keys(11)
  case M extends Keys(12)
  case N extends Keys(13)
  case O extends Keys(14)
  case P extends Keys(15)
  case Q extends Keys(16)
  case R extends Keys(17)
  case S extends Keys(18)
  case T extends Keys(19)
  case U extends Keys(20)
  case V extends Keys(21)
  case W extends Keys(22)
  case X extends Keys(23)
  case Y extends Keys(24)
  case Z extends Keys(25)
  case Num0 extends Keys(26)
  case Num1 extends Keys(27)
  case Num2 extends Keys(28)
  case Num3 extends Keys(29)
  case Num4 extends Keys(30)
  case Num5 extends Keys(31)
  case Num6 extends Keys(32)
  case Num7 extends Keys(33)
  case Num8 extends Keys(34)
  case Num9 extends Keys(35)
  case Escape extends Keys(36)
  case LControl extends Keys(37)
  case LShift extends Keys(38)
  case LAlt extends Keys(39)
  case LSystem extends Keys(40)
  case RControl extends Keys(41)
  case RShift extends Keys(42)
  case RAlt extends Keys(43)
  case RSystem extends Keys(44)
  case Menu extends Keys(45)
  case LBracket extends Keys(46)
  case RBracket extends Keys(47)
  case Semicolon extends Keys(48)
  case Comma extends Keys(49)
  case Period extends Keys(50)
  case Quote extends Keys(51)
  case Slash extends Keys(52)
  case Backslash extends Keys(53)
  case Tilde extends Keys(54)
  case Equal extends Keys(55)
  case Hyphen extends Keys(56)
  case Space extends Keys(57)
  case Enter extends Keys(58)
  case Backspace extends Keys(59)
  case Tab extends Keys(60)
  case PageUp extends Keys(61)
  case PageDown extends Keys(62)
  case End extends Keys(63)
  case Home extends Keys(64)
  case Insert extends Keys(65)
  case Delete extends Keys(66)
  case Add extends Keys(67)
  case Subtract extends Keys(68)
  case Multiply extends Keys(69)
  case Divide extends Keys(70)
  case Left extends Keys(71)
  case Right extends Keys(72)
  case Up extends Keys(73)
  case Down extends Keys(74)
  case Numpad0 extends Keys(75)
  case Numpad1 extends Keys(76)
  case Numpad2 extends Keys(77)
  case Numpad3 extends Keys(78)
  case Numpad4 extends Keys(79)
  case Numpad5 extends Keys(80)
  case Numpad6 extends Keys(81)
  case Numpad7 extends Keys(82)
  case Numpad8 extends Keys(83)
  case Numpad9 extends Keys(84)
  case F1 extends Keys(85)
  case F2 extends Keys(86)
  case F3 extends Keys(87)
  case F4 extends Keys(88)
  case F5 extends Keys(89)
  case F6 extends Keys(90)
  case F7 extends Keys(91)
  case F8 extends Keys(92)
  case F9 extends Keys(93)
  case F10 extends Keys(94)
  case F11 extends Keys(95)
  case F12 extends Keys(96)
  case F13 extends Keys(97)
  case F14 extends Keys(98)
  case F15 extends Keys(99)
  case Pause extends Keys(100)

  case MouseLeft extends Keys(101)
  case MouseRight extends Keys(102)
  case MouseMiddle extends Keys(103)
  case MouseButton1 extends Keys(104)
  case MouseButton2 extends Keys(105)
  case MouseWheel1Up extends Keys(106)
  case MouseWheel1Down extends Keys(107)
  case MouseWheel2Up extends Keys(108)
  case MouseWheel2Down extends Keys(109)
}

object Keys {
  def fromKey(k : Keyboard.Key) : Keys = {
    k match {
      case Keyboard.Key.KeyUnknown => Unknown
      case Keyboard.Key.KeyA => A
      case Keyboard.Key.KeyB => B
      case Keyboard.Key.KeyC => C
      case Keyboard.Key.KeyD => D
      case Keyboard.Key.KeyE => E
      case Keyboard.Key.KeyF => F
      case Keyboard.Key.KeyG => G
      case Keyboard.Key.KeyH => H
      case Keyboard.Key.KeyI => I
      case Keyboard.Key.KeyJ => J
      case Keyboard.Key.KeyK => K
      case Keyboard.Key.KeyL => L
      case Keyboard.Key.KeyM => M
      case Keyboard.Key.KeyN => N
      case Keyboard.Key.KeyO => O
      case Keyboard.Key.KeyP => P
      case Keyboard.Key.KeyQ => Q
      case Keyboard.Key.KeyR => R
      case Keyboard.Key.KeyS => S
      case Keyboard.Key.KeyT => T
      case Keyboard.Key.KeyU => U
      case Keyboard.Key.KeyV => V
      case Keyboard.Key.KeyW => W
      case Keyboard.Key.KeyX => X
      case Keyboard.Key.KeyY => Y
      case Keyboard.Key.KeyZ => Z
      case Keyboard.Key.KeyNum0 => Num0
      case Keyboard.Key.KeyNum1 => Num1
      case Keyboard.Key.KeyNum2 => Num2
      case Keyboard.Key.KeyNum3 => Num3
      case Keyboard.Key.KeyNum4 => Num4
      case Keyboard.Key.KeyNum5 => Num5
      case Keyboard.Key.KeyNum6 => Num6
      case Keyboard.Key.KeyNum7 => Num7
      case Keyboard.Key.KeyNum8 => Num8
      case Keyboard.Key.KeyNum9 => Num9
      case Keyboard.Key.KeyEscape => Escape
      case Keyboard.Key.KeyLControl => LControl
      case Keyboard.Key.KeyLShift => LShift
      case Keyboard.Key.KeyLAlt => LAlt
      case Keyboard.Key.KeyLSystem => LSystem
      case Keyboard.Key.KeyRControl => RControl
      case Keyboard.Key.KeyRShift => RShift
      case Keyboard.Key.KeyRAlt => RAlt
      case Keyboard.Key.KeyRSystem => RSystem
      case Keyboard.Key.KeyMenu => Menu
      case Keyboard.Key.KeyLBracket => LBracket
      case Keyboard.Key.KeyRBracket => RBracket
      case Keyboard.Key.KeySemicolon => Semicolon
      case Keyboard.Key.KeyComma => Comma
      case Keyboard.Key.KeyPeriod => Period
      case Keyboard.Key.KeyQuote => Quote
      case Keyboard.Key.KeySlash => Slash
      case Keyboard.Key.KeyBackslash => Backslash
      case Keyboard.Key.KeyTilde => Tilde
      case Keyboard.Key.KeyEqual => Equal
      case Keyboard.Key.KeyHyphen => Hyphen
      case Keyboard.Key.KeySpace => Space
      case Keyboard.Key.KeyEnter => Enter
      case Keyboard.Key.KeyBackspace => Backspace
      case Keyboard.Key.KeyTab => Tab
      case Keyboard.Key.KeyPageUp => PageUp
      case Keyboard.Key.KeyPageDown => PageDown
      case Keyboard.Key.KeyEnd => End
      case Keyboard.Key.KeyHome => Home
      case Keyboard.Key.KeyInsert => Insert
      case Keyboard.Key.KeyDelete => Delete
      case Keyboard.Key.KeyAdd => Add
      case Keyboard.Key.KeySubtract => Subtract
      case Keyboard.Key.KeyMultiply => Multiply
      case Keyboard.Key.KeyDivide => Divide
      case Keyboard.Key.KeyLeft => Left
      case Keyboard.Key.KeyRight => Right
      case Keyboard.Key.KeyUp => Up
      case Keyboard.Key.KeyDown => Down
      case Keyboard.Key.KeyNumpad0 => Numpad0
      case Keyboard.Key.KeyNumpad1 => Numpad1
      case Keyboard.Key.KeyNumpad2 => Numpad2
      case Keyboard.Key.KeyNumpad3 => Numpad3
      case Keyboard.Key.KeyNumpad4 => Numpad4
      case Keyboard.Key.KeyNumpad5 => Numpad5
      case Keyboard.Key.KeyNumpad6 => Numpad6
      case Keyboard.Key.KeyNumpad7 => Numpad7
      case Keyboard.Key.KeyNumpad8 => Numpad8
      case Keyboard.Key.KeyNumpad9 => Numpad9
      case Keyboard.Key.KeyF1 => F1
      case Keyboard.Key.KeyF2 => F2
      case Keyboard.Key.KeyF3 => F3
      case Keyboard.Key.KeyF4 => F4
      case Keyboard.Key.KeyF5 => F5
      case Keyboard.Key.KeyF6 => F6
      case Keyboard.Key.KeyF7 => F7
      case Keyboard.Key.KeyF8 => F8
      case Keyboard.Key.KeyF9 => F9
      case Keyboard.Key.KeyF10 => F10
      case Keyboard.Key.KeyF11 => F11
      case Keyboard.Key.KeyF12 => F12
      case Keyboard.Key.KeyF13 => F13
      case Keyboard.Key.KeyF14 => F14
      case Keyboard.Key.KeyF15 => F15
      case Keyboard.Key.KeyPause => Pause
    }
  }

  def fromMouseButton(button : Mouse.Button) : Keys = {
    button match {
      case Mouse.Button.Left => MouseLeft
      case Mouse.Button.Right => MouseRight
      case Mouse.Button.Middle => MouseMiddle
      case Mouse.Button.XButton1 => MouseButton1
      case Mouse.Button.XButton2 => MouseButton2
    }
  }

}

enum Control(val keys : Vector[Keys]) {
  var listeners : Vector[Boolean => Unit] = Vector()

  case moveForward extends Control(Vector(Keys.Z))
  case moveBackward extends Control(Vector(Keys.S))
  case moveLeft extends Control(Vector(Keys.Q))
  case moveRight extends Control(Vector(Keys.D))

  case structureInteract extends Control(Vector(Keys.E))
  
  case attack extends Control(Vector(Keys.F))
  case harvest extends Control(Vector(Keys.Space))
  case collect extends Control(Vector(Keys.A))
  case build extends Control(Vector(Keys.C))

  case leftClick extends Control(Vector(Keys.MouseLeft))
  case rightClick extends Control(Vector(Keys.MouseRight))

  case uiConfirm extends Control(Vector(Keys.Enter))
  case uiUp extends Control(Vector(Keys.Up, Keys.MouseWheel1Up))
  case uiDown extends Control(Vector(Keys.Down, Keys.MouseWheel1Down))
  case uiLeft extends Control(Vector(Keys.Left, Keys.MouseWheel2Up))
  case uiRight extends Control(Vector(Keys.Right, Keys.MouseWheel2Down))
  
  case pause extends Control(Vector(Keys.Escape))

  def addListener(listener : Boolean => Unit) : Unit = {
    listeners = listeners :+ listener
  }

  def removeListener(listener : Boolean => Unit) : Unit = {
    listeners = listeners.filterNot(listener.==)
  } 
}

object ControlManager {
  
  def onKeyPressed(e : Event.KeyPressed) : Unit = Control.values.filter(c => c.keys.contains(Keys.fromKey(e.code))).foreach(c => c.listeners.foreach(f => f(true)))

  def onKeyReleased(e : Event.KeyReleased) : Unit = Control.values.filter(c => c.keys.contains(Keys.fromKey(e.code))).foreach(c => c.listeners.foreach(f => f(false)))

  def onMouseButtonPressed(e : Event.MouseButtonPressed) : Unit = Control.values.filter(c => c.keys.contains(Keys.fromMouseButton(e.button))).foreach(c => c.listeners.foreach(f => f(true)))

  def onMouseButtonReleased(e : Event.MouseButtonReleased) : Unit = Control.values.filter(c => c.keys.contains(Keys.fromMouseButton(e.button))).foreach(c => c.listeners.foreach(f => f(false)))

  def onMouseWheelScrolled(e : Event.MouseWheelScrolled) : Unit = {
    val key = e.wheel match {
      case Mouse.Wheel.VerticalWheel => {
        if e.delta >= 0 then Keys.MouseWheel1Up
        else Keys.MouseWheel1Down
      }
      case Mouse.Wheel.HorizontalWheel => {
        if e.delta >= 0 then Keys.MouseWheel2Up
        else Keys.MouseWheel2Down
      }
    }

    Control.values.filter(c => c.keys.contains(key)).foreach(c => c.listeners.foreach(f => f(true)))
  }

}
