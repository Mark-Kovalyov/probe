# Jena, SparQL, Fuseki

## Configure

```
$ export JENA_HOME=/jena/jena-x-xx
$ export PATH=$JENA_HOME/bin
```

## Convert from one semantic web format (SWF) to another

```
```

## Execute SparQL query on random SWF-file online

### Header
```
PREFIX :          <http://ex.org/> 
PREFIX dc:        <http://purl.org/dc/elements/1.1/> 
PREFIX foaf:      <http://xmlns.com/foaf/0.1/> 
PREFIX rdf:       <http://www.w3.org/1999/02/22-rdf-syntax-ns#> 
PREFIX rdfs:      <http://www.w3.org/2000/01/rdf-schema#> 
PREFIX xsd:       <http://www.w3.org/2001/XMLSchema#> 
PREFIX owl:       <http://www.w3.org/2002/07/owl#> 
PREFIX dcterms:   <http://purl.org/dc/terms/> 
```

### Filter with locale
```
SELECT ?name 
WHERE {
 ?subj foaf:name ?name .
 FILTER (lang(?name) = 'en')
}
```

## Start Fuseki

```
$ /fuseki/
```


