fobj_in = open("/home/snisstls/mqtt.txt")
fobj_out = open("yellow_snow2.txt","w")
i = 1
for line in fobj_in:
    print(line.rstrip())
    fobj_out.write(str(i) + ": " + line)
    i = i + 1
fobj_in.close()
fobj_out.close()
