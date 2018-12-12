#!/bin/bash -v

dot -Tpng ../input/graphviz-sample-01.dot > ../out/graphviz-sample-01-dot.png

fdp -Tpng ../input/graphviz-sample-01.dot > ../out/graphviz-sample-01-fdp.png
