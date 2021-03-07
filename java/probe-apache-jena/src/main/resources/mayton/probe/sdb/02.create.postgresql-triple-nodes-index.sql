CREATE TABLE Prefixes (
    prefix VARCHAR(50) NOT NULL ,
    uri VARCHAR(500) NOT NULL ,
    CONSTRAINT Prefixes_PK PRIMARY KEY  (prefix)
)

connection().execUpdate("INSERT INTO Prefixes VALUES ('x',       'http://example/')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('ex',      'http://example.org/')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('rdf',     'http://www.w3.org/1999/02/22-rdf-syntax-ns#')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('rdfs',    'http://www.w3.org/2000/01/rdf-schema#')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('xsd',     'http://www.w3.org/2001/XMLSchema#')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('owl' ,    'http://www.w3.org/2002/07/owl#')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('foaf',    'http://xmlns.com/foaf/0.1/')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('dc',      'http://purl.org/dc/elements/1.1/')") ;
                connection().execUpdate("INSERT INTO Prefixes VALUES ('dcterms', 'http://purl.org/dc/terms/')") ;

CREATE TABLE Nodes (
   id SERIAL,
   hash BIGINT NOT NULL,
   lex TEXT NOT NULL,
   lang varchar NOT NULL default '',
   datatype varchar(200) NOT NULL default '',
   type integer NOT NULL default '0',
   PRIMARY KEY (id)
)

CREATE UNIQUE INDEX Hash ON Nodes(hash)

CREATE TABLE Triples (
    s integer NOT NULL,
    p integer NOT NULL,
    o integer NOT NULL,
    PRIMARY KEY (s, p, o)
)

CREATE TABLE Quads (
    g integer NOT NULL,
    s integer NOT NULL,
    p integer NOT NULL,
    o integer NOT NULL,
    PRIMARY KEY (g, s, p, o)
)

CREATE INDEX ObjSubj ON Triples (o, s)
CREATE INDEX PredObj ON Triples (p, o)

CREATE INDEX SubjPredObj ON Quads (s, p, o)
CREATE INDEX PredObjSubj ON Quads (p, o, s)
CREATE INDEX ObjSubjPred ON Quads (o, s, p)
CREATE INDEX GraPredObj ON Quads (g, p, o)
CREATE INDEX GraObjSubj ON Quads (g, o, s)

CREATE INDEX ObjSubj ON Triples (o, s)
CREATE INDEX ObjSubj ON Triples (o, s)
