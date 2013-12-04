import sys

l = ["0", "1", "2", "3"]

buf = []

def printLine(token, count):
    global buf

    if len(buf) == count:
        print "    %s" % (", ".join(buf)+",",)
        buf = []

    buf.append("%s(\"%s\")" % (token.upper(), token))

    #print "    %s(\"%s\")," % (token.upper(), token)

def doRecursive(l, count, val, key, originalCount):
    if count == 0:
        for k in key:
            val = val.replace(k, key[k])
        printLine(val, originalCount)
        return
    for c in l:
        doRecursive(l, count-1, val + c, key, originalCount)

dimension = int(sys.argv[1])

coordsKey = dict(list((l[i], ["x","y","z","w"][i]) for i in range(len(l))))
colorsKey = dict(list((l[i], ["r","g","b","a"][i]) for i in range(len(l))))
texCoordsKey = dict(list((l[i], ["s","t","p","q"][i]) for i in range(len(l))))

for i in range(4):
    doRecursive(l[:dimension], i+1, "", coordsKey, dimension)
    doRecursive(l[:dimension], i+1, "", colorsKey, dimension)
    doRecursive(l[:dimension], i+1, "", texCoordsKey, dimension)

