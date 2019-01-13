package mayton.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.shared.impl.PrefixMappingImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.replace;

public class RdfToGraphviz {

    static Logger logger = LoggerFactory.getLogger(RdfToGraphviz.class);

    public static String trimNs(String arg) {
        return arg;
        /*if (arg.startsWith("https://permid.org/")) {
            return replace(arg, "https://permid.org/", "perm:");
        } else if(arg.startsWith("http://www.w3.org/2004/02/skos/core#")) {
            return replace(arg, "http://www.w3.org/2004/02/skos/core#", "skos:");
        } else if(arg.startsWith("http://permid.org/ontology/financial/")) {
            return replace(arg, "http://permid.org/ontology/financial/", "ont:");
        } else if(arg.startsWith("http://www.w3.org/1999/02/22-rdf-syntax-ns#")) {
            return replace(arg, "http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf:");
        } else  {
            return arg;
        }*/
    }

    public static PrefixMapping createMapping() {
        PrefixMapping mapping = new PrefixMappingImpl();
        Map<String,String> map = new HashMap<>();
        map.put("rambo","http://john.rambo/movies");
        mapping.setNsPrefixes(map);
        return mapping;
    }

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Usage: java -jar rdf-to-graphviz.jar [inputfile] [outputfile] [[]]");
        }

        logger.info(":: Start");

        Model model = ModelFactory.createDefaultModel();


        logger.info(":: [2]");

        model.setNsPrefixes(createMapping());

        String inputFileName = args[0];
        String outputFileName = args[1];

        logger.info(":: [3]");

        // [Loaded java.lang.Throwable$WrappedPrintStream from /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar]
        //[Loaded java.util.IdentityHashMap$KeySet from /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar]
        //java.lang.ExceptionInInitializerError
        //        at org.apache.jena.riot.RDFParserBuilder.buildFactoryRDF(RDFParserBuilder.java:597)
        //        at org.apache.jena.riot.RDFParserBuilder.build(RDFParserBuilder.java:569)
        //        at org.apache.jena.riot.RDFParserBuilder.parse(RDFParserBuilder.java:500)
        //        at org.apache.jena.riot.RDFDataMgr.parseFromInputStream(RDFDataMgr.java:870)
        //        at org.apache.jena.riot.RDFDataMgr.read(RDFDataMgr.java:268)
        //        at org.apache.jena.riot.RDFDataMgr.read(RDFDataMgr.java:242)
        //        at org.apache.jena.riot.RDFDataMgr.read(RDFDataMgr.java:232)
        //        at mayton.rdf.RdfToGraphviz.main(RdfToGraphviz.java:65)
        //Caused by: java.lang.NullPointerException
        //        at org.apache.jena.query.ARQ.isTrue(ARQ.java:650)
        //        at org.apache.jena.riot.system.RiotLib.<clinit>(RiotLib.java:61)
        //        ... 8 more
        RDFDataMgr.read(model, new FileInputStream(inputFileName), decodeLang(inputFileName));

        logger.info(":: [4]");

        PrintWriter pw = new PrintWriter(new FileOutputStream(outputFileName));

        pw.println("digraph assetClass {");

        pw.println("node [shape=oval]; ");

        ResIterator subjs = model.listSubjects();

        while (subjs.hasNext()) {
            Resource subj = subjs.nextResource();
            pw.printf("  \"%s\" ; \n", trimNs(subj.toString()));
        }

        pw.println("node [shape=box]; ");

        StmtIterator sts = model.listStatements();

        while (sts.hasNext()) {
            Statement st = sts.next();

            Resource  subj   = st.getSubject();
            Property  pred   = st.getPredicate();
            RDFNode   object = st.getObject();

            if (object instanceof ResourceImpl){
                pw.printf("\"%s\" -> \"%s\" [ label = \"%s\" ]; \n",
                        trimNs(subj.toString()),
                        trimNs(object.toString()),
                        trimNs(pred.toString()));
            }
        }

        pw.println("}");
        pw.close();
    }


    private static Lang decodeLang(String arg) {
        logger.info(":: decodeLang {}", arg);
        if (arg.toLowerCase().endsWith(".ttl")) {
            return Lang.TTL;
        } else if (arg.toLowerCase().endsWith(".trig")) {
            return Lang.TRIG;
        } else if (arg.toLowerCase().endsWith(".nq")) {
            return Lang.NTRIPLES;
        } else if (arg.toLowerCase().endsWith(".nt")) {
            return Lang.NQ;
        } else if (arg.toLowerCase().endsWith(".xml")) {
            return Lang.RDFXML;
        } else {
            throw new IllegalArgumentException("Illegal file type " + arg);
        }
    }

}
