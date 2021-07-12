# LZ4 file format example

## Links

* Spec: https://docs.fileformat.com/compression/lz4/
* Sources: https://github.com/lz4/lz4

## Example

Params

|Name|Value|
|----|-----|
|compression|SequenceFile.CompressionType.BLOCK|
|keyClass|Text
|valueClass|NullWritable.class
|blockSize|4096
|appendIfExists|false
|syncInterval|4096

Constants
* Magic number: 0x184D2204

```bash
hexdump -n 200 -C 1.2billion.txt.lz4
00000000  04 22 4d 18 64 70 b9 f3  ba 3a 00 f1 ff ff ff ff  |."M.dp...:......|
00000010  ff ff ff ff ff ff ff ff  ff ff ff ff ff ff ff ff  |................|
00000020  ff ff ff ff ff ff ff 3e  68 38 6d 71 6f 35 74 6f  |.......>h8mqo5to|
00000030  66 37 7a 6d 34 6d 74 0a  46 6f 72 6d 69 64 61 62  |f7zm4mt.Formidab|
00000040  6c 65 6d 65 6e 74 31 0a  68 32 38 34 65 76 33 36  |lement1.h284ev36|
00000050  39 38 31 69 63 0a 7a 65  64 6f 71 6f 72 6f 0a 6b  |981ic.zedoqoro.k|
00000060  30 35 78 32 34 35 72 0a  68 69 62 61 67 65 6a 65  |05x245r.hibageje|
00000070  0a 68 68 31 62 61 32 76  30 36 0a 42 65 6c 6c 74  |.hh1ba2v06.Bellt|
00000080  6f 70 70 65 72 21 0a 44  65 77 65 79 2d 64 65 63  |opper!.Dewey-dec|
00000090  69 6d 61 6c 0a 68 79 34  67 75 39 6d 36 38 35 30  |imal.hy4gu9m6850|
000000a0  63 62 36 69 75 0a 67 63  65 71 34 35 30 6d 67 64  |cb6iu.gceq450mgd|
000000b0  6c 74 76 34 0a 4f 6e 69  72 74 61 70 6d 69 72 0a  |ltv4.Onirtapmir.|
000000c0  6b 61 65 76 73 30 7a 67                           |kaevs0zg|
```

