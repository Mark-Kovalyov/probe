CREATE TABLE Prefixes(
    prefix VARCHAR2(500) NOT NULL ,
    uri    VARCHAR2(500) NOT NULL ,
    CONSTRAINT Prefixes_PK PRIMARY KEY (prefix)
);

CREATE TABLE Triples(
    s number NOT NULL,
    p number NOT NULL,
    o number NOT NULL,
    PRIMARY KEY (s, p, o)
);
    
CREATE TABLE Nodes (
   hash number   NOT NULL,
   lex  CLOB     NOT NULL,
   lang varchar2 NOT NULL default E'',
   datatype varchar2(500) NOT NULL default '',
   type integer NOT NULL default E'0',
   PRIMARY KEY (hash)
);

