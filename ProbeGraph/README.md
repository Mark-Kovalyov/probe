# Graph library comparison

## Content

* JUNG (edu.uci.ics.jung.graph)
* JGgraphT (JGT)
* Apache Commons Graph (ACG)
* Guava commons.graph (GCG)
* GRPH (http://www.i3s.unice.fr/~hogie/maven_repository/)

## Features comparison (Genrics)

Feature         | JUNG  | JGT  | ACG  | GCG | GRPH
--------------- | ----- | ---- | ---- | --- | -----
Generics        |   +   |  ?   |  -   |  ?  |   ?
Generic vertex  |   +   |  ?   |  -   |  +  |   ?
Generic edge    |   +   |  ?   |  -   |  -  |   ?

(todo: complete filling)

## Graph types
 Type              | JUNG  | JGT  | ACG  | GCG | GRPH  |
-------------------|-------|------|------|-----|-------|
 Directed          |   +   |      |      |   + |       |
 Directed Sparce   |   +   |      |      |   ? |       |
 Sparce            |   +   |      |      |   ? |       |
 Sparce Multigraph |   +   |      |      |   ? |       |
 Multigraph/Network|   ?   |      |      |   + |       |

(todo: complete filling)

## Export/Import features comparison 
 Format            | JUNG  | JGT  | ACG  | GCG | GRPH  |
-------------------|-------|------|------|-----|-------|
 GraphML(XML)      |       |      |      |     |   +   |
 GML               |       |      |      |     |   +   |
 DOT/GraphViz      |       |      |      |     |   +   |
 DGS/Dynamic Graphs|       |      |      |     |   +   |
 Inet/Caida maps   |       |      |      |     |   +   |
 JUNG              |       |      |      |     |   +   |
 Mascopt           |       |      |      |     |   +   |

(todo: complete filling)

## Algorithms availability 
 Algorithm                   | JUNG  | JGT  | ACG  | GCG | GRPH  |
 ----------------------------|-------|------|------|-----|-------|
 eccentricity                |       |      |      |  ?  |   +   |
 radius                      |       |      |      |  ?  |   +   |
 diameter                    |       |      |      |  ?  |   +   |
 in/out vertex/edge degrees  |       |      |      |  ?  |   +   |
 clustering coefficient      |       |      |      |  -  |   +   |
 cluster                     |  +    |      |      |  ?  |   ?   |
 density                     |       |      |      |  -  |   +   |
 connected components        |       |      |      |  -  |   +   | 
 minimal spanning tree       |       |      |      |  -  |   +   | 
 spanning                    |  +    |      |      |  -  |   ?   |
 shortest paths              |  +    |      |      |  -  |   +   |    
 BFS/RS                      |       |      |      |  -  |   +   | 
 DFS                         |       |      |   +  |     |   +   |
 distributions               |       |      |      |  -  |   +   | 
 maximum clique              |       |      |      |  -  |   +   | 
 minimum vertex cover        |       |      |      |  -  |   +   | 
 maximum independent set     |       |      |      |  -  |   +   |  
 maximum flow                |       |      |      |  -  |   +   | 
 flow                        |  +    |      |      |  -  |   ?   |
 (sub)graph isomorphism      |  +    |      |      |  -  |   +   | 
 clique                      |  +    |      |      |  -  |   ?   |
 color                       |  +    |      |      |  -  |   ?   |
 scoring                     |  +    |      |      |  -  |   ?   |
 tour                        |  +    |      |      |  -  |   ?   |

(todo: complete filling)

## Conclusions

### JUNG

Nice.

### Guava common.graph

Today, you should use Guava commons.graph (GCG) if you need
only basic datastructures and primitive lookup for vertices/edges.

Good news for JUNG. Referring to GCG documentation, new version
of JUNG is going to be migrated under GCG interfaces. Nice!

### GRPH

Oldest library! Since 1998. Huh.. Impressive amount of algorithms.

Some points:
- Bad jar design. Mix of class and java files. Not a good for IDE navigation.
- Trash in JAR

### Apache common graph

No generics! Looks like Java 1.4 designed.
