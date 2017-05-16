import curses
from curses.textpad import Textbox, rectangle

# Initialisierung von myscreen
myscreen = curses.initscr()
curses.start_color()
curses.use_default_colors()

# Colors
curses.init_pair(1, 1, 7) # rot/weiß
curses.init_pair(2, 2, -1) # grün/schwarz
curses.init_pair(3, 3, 4) # gelb/blau
curses.init_pair(4, 5, 6) # pink/türkis

# Eine editierbare Box
def testbox():
    myscreen.clear()
    myscreen.border(0)
    editwin = curses.newwin(5,30, 2,1)
    rectangle(myscreen, 1,0,1+5+1, 1+30+1)
    myscreen.refresh()
    box = Textbox(editwin)
    box.edit()

# Farbwechsel auf ganzem Bildschirm
def farbwechsel():
    myscreen.bkgd(" ", curses.color_pair(2))
    myscreen.clear()
    myscreen.border(0)
    myscreen.addstr(4,4,"RED ALERT!", curses.color_pair(2))
    myscreen.refresh()
    screen = chr(myscreen.getch())

def fenster_im_fenster():
    begin_x = 20; begin_y = 7
    height = 20; width = 20
    win = curses

# Kleines Fenster mit Hilfe von newwin()
def fenster():
    begin_x = 20; begin_y = 7
    height = 20; width = 20
    win = curses.newwin(height, width, begin_y, begin_x)
    win.bkgd(" ", curses.color_pair(3))
    win.border(0)
    win.addstr(2,2, "Look at this ...")
    win.addstr(3,2, "Mehr Fenster ^^")
    screen = chr(win.getch())

screen = 0
while screen != 9:

    myscreen.bkgd(" ", curses.color_pair(1))
    myscreen.clear()
    myscreen.border(0)
    myscreen.addstr(2,2, "Eine Eingabe pls ...")
    myscreen.addstr(4,4, "1 - Eine Textbox")
    myscreen.addstr(5,4, "2 - Farbwechsel")
    myscreen.addstr(6,4, "3 - Fenster")
    myscreen.addstr(7,4, "9 - EXIT!")

    myscreen.refresh()

    screen = chr(myscreen.getch())

    if screen == "1":
        testbox()

    if screen == "2":
        farbwechsel()

    if screen == "3":
        fenster()

    if screen == "9":
        break

curses.endwin()
