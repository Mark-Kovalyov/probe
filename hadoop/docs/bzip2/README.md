# Bzip2

## Links

## Utils

* bzcat
* bzmore, bzless
* bzcmp
* bzdiff
* bzgrep, bzegrep, bzfgrep
* bzip2recover

## Test data
```
-rw-rw-r-- 1 mayton mayton  8129486942 Jul 10 14:11 1.2billion.txt.bz2
-rw-rw-r-- 1 mayton mayton 13556247325 Jul 10 14:34 1.2billion.txt.lz4
-rw-r--r-- 1 mayton mayton 13300260941 Jul 10 23:51 1.2billion.txt.lzo
```

## Magic
```
Start block : 31 41 59 26 53 59 (Pi)
End of file : 17 72 45 38 50 90 (sqrt(Pi))
```

## Sample of Bzip2
```
hexdump -n 400 -C 1.2billion.txt.bz2
00000000  42 5a 68 31 31 41 59 26  53 59 04 23 bf 5d 00 6a  |BZh11AY&SY.#.].j|
00000010  fb df fb f8 30 7f ef ff  ff ff ff ff ff ff ff ff  |....0...........|
00000020  fe 64 00 04 60 40 00 00  21 00 00 bd f2 29 42 60  |.d..`@..!....)B`|
00000030  eb 0d a5 54 d5 9b 5a 01  40 aa 69 2a 0a 0b 61 a3  |...T..Z.@.i*..a.|
00000040  7d 8e 9c d8 b6 02 af 63  8b a0 ea 9b ac ec e7 6c  |}......c.......l|
00000050  cb 60 24 b6 2c c3 5b b3  ef 4f b7 b8 ef b6 ee ef  |.`$.,.[..O......|
00000060  7b cd 3b bb a5 e7 d7 0c  d0 24 69 db d7 2f 6b 6f  |{.;......$i../ko|
00000070  43 03 d6 51 ea b0 84 f6  35 46 8c 76 1e 83 40 09  |C..Q....5F.v..@.|
00000080  07 a6 7b 34 14 19 29 44  81 a1 a6 83 79 67 2a 13  |..{4..)D....yg*.|
00000090  65 1d 6f 60 00 00 1e 89  15 6b 51 23 46 85 00 0a  |e.o`.....kQ#F...|
000000a0  74 ad 35 a5 0d b1 b6 11  05 c6 39 01 d0 00 05 d3  |t.5.......9.....|
000000b0  68 68 28 aa 00 97 7d eb  e7 40 00 0e 80 6f 8f b1  |hh(...}..@...o..|
000000c0  35 af 62 9d ee 38 fa fb  79 df 7c ae de 79 ef 65  |5.b..8..y.|..y.e|
000000d0  d9 ee ed c7 b6 36 de bb  d9 dc 76 e6 4e ed f5 06  |.....6....v.N...|
000000e0  ee 7b b5 d3 69 7a be e6  51 de fb 7a ee cd a5 bb  |.{..iz..Q..z....|
000000f0  76 5b 73 ba fa 0f be db  af b9 ed be b4 7d cd ef  |v[s..........}..|
00000100  3b bd c7 be ea 95 df 4f  7c 5f 55 9d 74 ee bb 9b  |;......O|_U.t...|
00000110  9e dd 71 b1 ee fb de e8  72 34 fb 79 6a 95 cf bc  |..q.....r4.yj...|
00000120  77 3b 6b d1 4e af ad d3  d7 7d dd e6 de f5 38 73  |w;k.N....}....8s|
00000130  d8 d0 e9 6d ee 3a ee de  4b 7b d5 f7 76 f8 3d 00  |...m.:..K{..v.=.|
00000140  77 cc 87 d8 70 82 93 7b  80 db b9 13 dd 83 9c c3  |w...p..{........|
00000150  4b 9e 57 36 be f7 9e fb  dd df 7b b6 f9 d7 b8 db  |K.W6......{.....|
00000160  bc bd e5 c5 64 d0 9d ce  7b 86 15 ed 37 bd 77 bd  |....d...{...7.w.|
00000170  b6 84 f6 f7 30 3a 75 e8  03 5f 7b ee a0 74 3e 87  |....0:u.._{..t>.|
00000180  d3 67 dc d3 41 af a1 97  af 76 e9 2d d3 bd 9b c7  |.g..A....v.-....|
```

## Blocks

