# LZO 

## LZO-indexer

```
$ lzo-indexer 1.2billion.txt.lzo
  File "/home/mayton/.local/bin/lzo-indexer", line 39
    print "Skipping indexing of %s" % (lzo_path)
          ^
SyntaxError: invalid syntax

$ cat -n /home/username/.local/bin/lzo-indexer

     1	#!/usr/bin/python3
     2	
     3	import sys
     4	import os
     5	import struct
     6	import argparse
     7	
     8	# Add the project directory to the python path
     9	sys.path.append(os.path.join(os.path.dirname(__file__), "../"))
    10	
    11	import lzo_indexer
    12	
    13	
    14	def parse_args(argv):
    15	
    16	    parser = argparse.ArgumentParser()
    17	    parser.add_argument("--verbose", "-v", default=False, action="store_true",
    18	                        help="Enable verbose logging")
    19	    parser.add_argument("--force", "-f", default=False, action="store_true",
    20	                        help="Force re-creation of an index even if it exists")
    21	    parser.add_argument("lzo_files", type=str, nargs="+",
    22	                        help="List of LZO files to index")
    23	
    24	    # Parse the arguments
    25	    return parser.parse_args(argv)
    26	
    27	
    28	def main():
    29	    args = parse_args(sys.argv[1:])
    30	
    31	    for lzo_path in args.lzo_files:
    32	        if not lzo_path.endswith(".lzo"):
    33	            raise Exception("Invalid LZO file given")
    34	        index_path = "%s.index" % (lzo_path)
    35	
    36	        with open(lzo_path, "r") as lzo_file:
    37	            if not args.force:
    38	                if os.path.isfile(index_path):
    39	                    print "Skipping indexing of %s" % (lzo_path)

```