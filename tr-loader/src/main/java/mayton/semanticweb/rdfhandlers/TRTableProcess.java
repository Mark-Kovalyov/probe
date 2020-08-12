package mayton.semanticweb.rdfhandlers;

import mayton.semanticweb.FieldDescriptor;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;

import java.util.Map;

public class TRTableProcess {

    protected String tableName;

    protected Map<IRI, FieldDescriptor> fieldDescriptorMap;

    public TRTableProcess(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<IRI, FieldDescriptor> getFieldDescriptorMap() {
        return fieldDescriptorMap;
    }

    public void setFieldDescriptorMap(Map<IRI, FieldDescriptor> fieldDescriptorMap) {
        this.fieldDescriptorMap = fieldDescriptorMap;
    }
}
