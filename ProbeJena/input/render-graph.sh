#!/bin/bash -v

dot -Tpng graphviz-sample-01.dot > ../out/graphviz-sample-01-dot.png

fdp -Tpng graphviz-sample-01.dot > ../out/graphviz-sample-01-fdp.png
