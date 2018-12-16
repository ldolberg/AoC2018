from collections import deque, defaultdict
import operator

l = deque([0])
points = defaultdict(int)
P = 458
x = 1
while x < 7201900:
    p = x % P
    if x % 23 ==0 :
        l.rotate(7)
        t = l.pop()
        points[p] += t+x 
        l.rotate(-1)
    else:
        l.rotate(-1)
        l.append(x)
    # print(l)
    x += 1
print (max(points.iteritems(), key=operator.itemgetter(1)))