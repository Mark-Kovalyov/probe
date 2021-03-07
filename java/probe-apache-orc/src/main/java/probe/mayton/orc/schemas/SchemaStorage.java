package probe.mayton.orc.schemas;

import org.apache.orc.TypeDescription;

public class SchemaStorage {

    public static TypeDescription maxMindSchema = TypeDescription.createStruct()
            .addField("startIpNum", TypeDescription.createLong())
            .addField("endIpNum",   TypeDescription.createLong())
            .addField("country",    TypeDescription.createString())
            .addField("region",     TypeDescription.createString())
            .addField("city",       TypeDescription.createString())
            .addField("postalCode", TypeDescription.createString())
            .addField("latitude",   TypeDescription.createDouble())
            .addField("longitude",  TypeDescription.createDouble())
            .addField("dmaCode",    TypeDescription.createString())
            .addField("areaCode",   TypeDescription.createString());


}
