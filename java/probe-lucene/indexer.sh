#!/bin/bash

java -jar indexer.jar -g -a core.SimpleAnalyzer             -i .index_SimpleAnalyzer              -s /documents
java -jar indexer.jar -g -a core.WhitespaceAnalyzer         -i .index_WhitespaceAnalyzer          -s /documents
java -jar indexer.jar -g -a core.KeywordAnalyzer            -i .index_KeywordAnalyzer             -s /documents
java -jar indexer.jar -g -a standard.StandardAnalyzer       -i .index_StandardAnalyzer            -s /documents

java -jar indexer.jar -g -a en.EnglishAnalyzer              -i .index_EnglishAnalyzer             -s /documents
java -jar indexer.jar -g -a ru.RussianAnalyzer              -i .index_RussianAnalyzer             -s /documents
java -jar indexer.jar -g -a uk.UkrainianMorfologikAnalyzer  -i .index_UkrainianMorfologikAnalyzer -s /documents

