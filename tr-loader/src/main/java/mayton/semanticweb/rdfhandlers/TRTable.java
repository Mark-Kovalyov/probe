package mayton.semanticweb.rdfhandlers;

import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;

import java.util.Map;

public class TRTable {

    protected String tableName;

    protected Map<IRI, Pair<String, String>> predicates;

    public TRTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<IRI, Pair<String, String>> getPredicates() {
        return predicates;
    }

    public void setPredicates(Map<IRI, Pair<String, String>> predicates) {
        this.predicates = predicates;
    }
}
