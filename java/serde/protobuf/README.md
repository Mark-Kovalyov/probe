# GeoIpCity protobuf

https://developers.google.com/protocol-buffers/docs/proto3

## Features

* Binary
* Language-tolerant
* Integer == varint
* Types: varing, sint32, sint64, fixed32, fixed64, sfixed32, sfixed64
* Collections: map 
* Enumerations
* Complex types: messages
* Splittable : no

## Benchmark

|Source(CSV)|Dest(Protobuf)|Ratio|Encode Time|Rows   |Speed          |
|-----------|--------------|-----|-----------|-------|---------------|
|431909944  | 342181608    |0.79 | 36 s      |5748952|160 000 rows/s |

## Sample of binary file

```
00000000  28 08 80 80 80 08 10 ff  ff 9f 08 1a 00 2a 02 41  |(............*.A|
00000010  55 32 00 39 00 00 00 00  00 00 3b c0 41 00 00 00  |U2.9......;.A...|
00000020  00 00 a0 60 40 4a 00 52  00 28 08 80 80 a4 08 10  |...`@J.R.(......|
00000030  ff ff a7 08 1a 00 2a 02  4d 59 32 00 39 00 00 00  |......*.MY2.9...|
00000040  00 00 00 04 40 41 00 00  00 00 00 20 5c 40 4a 00  |....@A..... \@J.|
00000050  52 00 28 08 80 94 a8 08  10 ff 95 a8 08 1a 00 2a  |R.(............*|
00000060  02 41 55 32 00 39 00 00  00 00 00 00 3b c0 41 00  |.AU2.9......;.A.|
00000070  00 00 00 00 a0 60 40 4a  00 52 00 28 08 80 80 ac  |.....`@J.R.(....|
00000080  08 10 ff ff af 08 1a 00  2a 02 4b 52 32 00 39 00  |........*.KR2.9.|
00000090  00 00 00 00 80 42 40 41  00 00 00 00 00 e0 5f 40  |.....B@A......_@|
000000a0  4a 00 52 00 28 08 80 80  b0 08 10 ff ff bf 08 1a  |J.R.(...........|
000000b0  00 2a 02 43 4e 32 00 39  00 00 00 00 00 80 41 40  |.*.CN2.9......A@|
000000c0  41 00 00 00 00 00 40 5a  40 4a 00 52 00 28 08 80  |A.....@Z@J.R.(..|
000000d0  80 c0 08 10 ff ff cf 08  1a 00 2a 02 4b 52 32 00  |..........*.KR2.|
000000e0  39 00 00 00 00 00 80 42  40 41 00 00 00 00 00 e0  |9......B@A......|
000000f0  5f 40 4a 00 52 00 28 08  80 80 d4 08 10 ff ff d7  |_@J.R.(.........|
00000100  08 1a 00 2a 02 4a 50 32  00 39 00 00 00 00 00 00  |...*.JP2.9......|
00000110  42 40 41 00 00 00 00 00  40 61 40 4a 00 52 00 28  |B@A.....@a@J.R.(|
00000120  08 80 80 d8 08 10 ff ff  db 08 1a 00 2a 02 49 4e  |............*.IN|
00000130  32 00 39 00 00 00 00 00  00 34 40 41 00 00 00 00  |2.9......4@A....|
00000140  00 40 53 40 4a 00 52 00  33 08 80 80 dc 08 10 ff  |.@S@J.R.3.......|
00000150  9f dc 08 1a 02 30 32 2a  02 49 4e 32 00 39 55 30  |.....02*.IN2.9U0|
00000160  2a a9 13 60 31 40 41 3c  4e d1 91 5c 9e 53 40 4a  |*..`1@A<N..\.S@J|
00000170  00 52 09 48 79 64 65 72  61 62 61 64 28 08 80 a0  |.R.Hyderabad(...|
00000180  dc 08 10 ff bf dc 08 1a  00 2a 02 49 4e 32 00 39  |.........*.IN2.9|
00000190  00 00 00 00 00 00 34 40  41 00 00 00 00 00 40 53  |......4@A.....@S|
000001a0  40 4a 00 52 00 33 08 80  c0 dc 08 10 ff ff dc 08  |@J.R.3..........|
000001b0  1a 02 30 32 2a 02 49 4e  32 00 39 55 30 2a a9 13  |..02*.IN2.9U0*..|
```

## Protoc tool 3.6.1

```
protoc --version
libprotoc 3.6.1
```

```
Usage: protoc [OPTION] PROTO_FILES
Parse PROTO_FILES and generate output based on the options given:
  -IPATH, --proto_path=PATH   Specify the directory in which to search for
                              imports.  May be specified multiple times;
                              directories will be searched in order.  If not
                              given, the current working directory is used.
  --version                   Show version info and exit.
  -h, --help                  Show this text and exit.
  --encode=MESSAGE_TYPE       Read a text-format message of the given type
                              from standard input and write it in binary
                              to standard output.  The message type must
                              be defined in PROTO_FILES or their imports.
  --decode=MESSAGE_TYPE       Read a binary message of the given type from
                              standard input and write it in text format
                              to standard output.  The message type must
                              be defined in PROTO_FILES or their imports.
  --decode_raw                Read an arbitrary protocol message from
                              standard input and write the raw tag/value
                              pairs in text format to standard output.  No
                              PROTO_FILES should be given when using this
                              flag.
  --descriptor_set_in=FILES   Specifies a delimited list of FILES
                              each containing a FileDescriptorSet (a
                              protocol buffer defined in descriptor.proto).
                              The FileDescriptor for each of the PROTO_FILES
                              provided will be loaded from these
                              FileDescriptorSets. If a FileDescriptor
                              appears multiple times, the first occurrence
                              will be used.
  -oFILE,                     Writes a FileDescriptorSet (a protocol buffer,
    --descriptor_set_out=FILE defined in descriptor.proto) containing all of
                              the input files to FILE.
  --include_imports           When using --descriptor_set_out, also include
                              all dependencies of the input files in the
                              set, so that the set is self-contained.
  --include_source_info       When using --descriptor_set_out, do not strip
                              SourceCodeInfo from the FileDescriptorProto.
                              This results in vastly larger descriptors that
                              include information about the original
                              location of each decl in the source file as
                              well as surrounding comments.
  --dependency_out=FILE       Write a dependency output file in the format
                              expected by make. This writes the transitive
                              set of input file paths to FILE
  --error_format=FORMAT       Set the format in which to print errors.
                              FORMAT may be 'gcc' (the default) or 'msvs'
                              (Microsoft Visual Studio format).
  --print_free_field_numbers  Print the free field numbers of the messages
                              defined in the given proto files. Groups share
                              the same field number space with the parent 
                              message. Extension ranges are counted as 
                              occupied fields numbers.

  --plugin=EXECUTABLE         Specifies a plugin executable to use.
                              Normally, protoc searches the PATH for
                              plugins, but you may specify additional
                              executables not in the path using this flag.
                              Additionally, EXECUTABLE may be of the form
                              NAME=PATH, in which case the given plugin name
                              is mapped to the given executable even if
                              the executable's own name differs.
  --cpp_out=OUT_DIR           Generate C++ header and source.
  --csharp_out=OUT_DIR        Generate C# source file.
  --java_out=OUT_DIR          Generate Java source file.
  --js_out=OUT_DIR            Generate JavaScript source.
  --objc_out=OUT_DIR          Generate Objective C header and source.
  --php_out=OUT_DIR           Generate PHP source file.
  --python_out=OUT_DIR        Generate Python source file.
  --ruby_out=OUT_DIR          Generate Ruby source file.
  @<filename>                 Read options and filenames from file. If a
                              relative file path is specified, the file
                              will be searched in the working directory.
                              The --proto_path option will not affect how
                              this argument file is searched. Content of
                              the file will be expanded in the position of
                              @<filename> as in the argument list. Note
                              that shell expansion is not applied to the
                              content of the file (i.e., you cannot use
                              quotes, wildcards, escapes, commands, etc.).
                              Each line corresponds to a single argument,
                              even if it contains spaces.

```

## Citatas

https://www.linkedin.com/pulse/many-hadoop-file-formats-which-one-choose-santosh-panda/

Protobuf is designed by google and satisfies all the above properties 
except splittability, that is why its not used in hadoop world.This is 
because it does not have a container format and its just series of protobuf 
objects in the file format.So the overhead of defining your own container 
to make this format splittable needs a lot of work.This is also not recommend 
format for Hadoop.