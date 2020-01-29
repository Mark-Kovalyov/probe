package mayton.probe.facet;


import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.facet.FacetResult;
import org.apache.lucene.facet.Facets;
import org.apache.lucene.facet.FacetsCollector;
import org.apache.lucene.facet.FacetsConfig;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.TaxonomyWriter;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyWriter;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class ProbeFacet {

    public static void main(String[] args) throws IOException {

        Directory directory = FSDirectory.open(Paths.get("/tmp/.probeFaced"));

        TaxonomyWriter taxonomyWriter = new DirectoryTaxonomyWriter(directory, IndexWriterConfig.OpenMode.CREATE);

        Document doc = new Document();

        doc.add(new StringField("title", "The War", Field.Store.YES));
//    ...
        //List<CategoryPath> categories = new ArrayList<CategoryPath>();
        //categories.add(new CategoryPath("author", "Mark Twain"));
        //categories.add(new CategoryPath("year", "2010"));
  //  ...
        //DocumentBuilder categoryDocBuilder = new CategoryDocumentBuilder(taxo);
        //categoryDocBuilder.setCategoryPaths(categories);
        //categoryDocBuilder.build(doc);
        //writer.addDocument(doc);

        directory.close();

        /////////////////////////////////

        directory = FSDirectory.open(Paths.get("/tmp/.probeFaced"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        TaxonomyReader taxo = new DirectoryTaxonomyReader(directory);
    //...
        //Query q = new TermQuery(new Term(SimpleUtils.TEXT, "white"));
        TopScoreDocCollector tdc = TopScoreDocCollector.create(10, 5);
    //...
        //FacetSearchParams facetSearchParams = new FacetSearchParams();
        //facetSearchParams.addFacetRequest(new CountFacetRequest( new CategoryPath("author"), 10));
    //...
        //FacetsCollector facetsCollector = new FacetsCollector(facetSearchParams, indexReader, taxo);
        //searcher.search(q, MultiCollector.wrap(topDocsCollector, facetsCollector));
        //List<FacetResult> res = facetsCollector.getFacetResults();

        ///////////////////////////////// Multiple Category Lists

        
    }

}
