# Jack Erlandson
# October 2021

# Maze BFS project from CMSCI 453 Algorithms

"""
Create a maze with BFS algorithm and 
"""

from tkinter import * #Package to create display
from random import choice, randint

NUM_ROWS, NUM_COLS = 40, 40 #40x40 grid 
SCALE = 20

north = [[1 for _ in range(NUM_COLS)] for _ in range(NUM_ROWS)]
east  = [[1 for _ in range(NUM_COLS)] for _ in range(NUM_ROWS)] #All directions
south = [[1 for _ in range(NUM_COLS)] for _ in range(NUM_ROWS)]
west  = [[1 for _ in range(NUM_COLS)] for _ in range(NUM_ROWS)]


def generate_maze():
    waiting = [(1, 1)]
    found = {(i, j) for i in range(NUM_ROWS) for j in range(NUM_COLS) if i == 0 or j == 0 or i == NUM_ROWS - 1 or j == NUM_COLS - 1} 
    found.add((1,1)) #Add the first coordinate

    while waiting: 
        w = waiting[len(waiting) - 1]
        r, c = w
        N = [item for item in [("n", (r + 1, c)), ("e", (r, c + 1)), ("s", (r - 1, c)), ("w", (r, c - 1))] if item[1] not in found]

        if len(N) is 0:
            w = waiting.pop() #pop item in waiting queue
        else:
            d, coord = choice(N) #Using choice from random module, It will create a random pattern every time
            nr, nc = coord
            #print(coord)
            
            o = 'enws'
            
            if d == o[0]:
                east[r][c] = 0
                west[nr][nc] = 0
            if d == o[1]:
                north[nr][nc] = 0
                south[r][c] = 0
            if d == o[2]:
                east[nr][nc] = 0
                west[r][c] = 0
            if d == o[3]:
                north[r][c] = 0
                south[nr][nc] = 0

            found.add((nr, nc)) 
            waiting.append((nr, nc)) 
    west[1][1] = 0 #start from the west
    east[NUM_ROWS - 2][NUM_COLS - 2] = 0 #End at the east

def display(): #Display the maze with the canvas object in maze module
    s = SCALE
    canvas.delete("all")
    for r in range(1, NUM_ROWS-1):
        for c in range(1, NUM_COLS-1): #Loop to create maze lines
            if west[r][c] == 1:
                canvas.create_line(s*c, s*r, s*c, s*r+s)
            if east[r][c] == 1:
                canvas.create_line(s*c+s, s*r, s*c+s, s*r+s)
            if south[r][c] == 1:
                canvas.create_line(s*c, s*r+s, s*c+s, s*r+s)
            if north[r][c] == 1:
                canvas.create_line(s*c, s*r, s*c+s, s*r)
    canvas.update()

canvas = Canvas(width=600, height=600, bg='white')
canvas.grid()

generate_maze()
display()

canvas.mainloop()
