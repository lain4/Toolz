import time
from math import sin,cos,pi
import opc
import sys
import random

ip = "10.23.42.139:7890"

client = opc.Client(ip)
if client.can_connect():
    print("connected")
else:
    print("can't connect")
x = random.randint(0, 7)
while True:

    for i in range(255):
        r = 1*i
        g = 1*i/2
        b = 1*i
        j = 0
        k = 0
        array = [(r,g,b)]*512
        random.shuffle(array)
        client.put_pixels(array)
			#r = (0.5*sin(i/255*2*pi)+0.5)*255
			#g = (0.5*sin(i/255*2*pi)+0.5)*255
			#b = (0.5*cos(i/255*2*pi)+0.5)*255
    #for j in range(512):
    #    array = [(r,g,b)]*j
    #    client.put_pixels(array, channel=0)
    #    time.sleep(0.01)

    #for k in range(256):
    #    array2 = [(255,255,255)]*k
    #    time.sleep(1)
    #    client.put_pixels(array2, channel=2)
