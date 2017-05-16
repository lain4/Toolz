filee = open("mqtt.txt")

for line in filee:
    print(line.rstrip())
filee.close()

