/*

1/2
 - 1
 - 2

123
 - 12
 - 13
 - 23

1234   C(3,4) = 4! / (4-3)! 3! = 4! / 3! = 4
 - 123
 - 124
 - 134
 - 234

C(4,6) = 6! / (6-4)! * 4! = 6! / 2! * 4! = 5 * 6 / 2 = 15

123456       x
 1,23456     1
  12,3456    2
   123,456   3
    1234,56  4 > 1234 : 1
    1235,6   4 > 1235 : 2
    1236,()  4 > 1236 : 3
   124,56    3
    1245,6   4 > 1245 : 4
    1246     4 > 1246 : 5
   125,6     3
    1256     4 > 1256 : 6
  13,456     2
   134,56    3 
    1345,6   4 > 1345 : 7
     1346    4 > 1346 : 8
   135,6     3 
    1356     4 > 1356 : 9

*/

def printRest(a : List[Int], b : List[Int]) : Unit = {
    if (!b.isEmpty) {
       print a ++ b.head
       printRest(a,b.tail)
    }  
}

def perm(a : List[Int], b : List[Int]) : Unit = {
 if (a.length < 4)
    perm(a ++ b.head, b.tail)
 else
    print a
    printRest(a.slice(1,a.length),b)
}

def perm(x : List[Int]) : Unit = {
 prm(List[Int](), List(1,2,3,4,5,6)
}



