/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uncc.ptab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.facet.index.FacetFields;
import org.apache.lucene.facet.params.FacetSearchParams;
import org.apache.lucene.facet.search.FacetResult;
import org.apache.lucene.facet.search.FacetResultNode;
import org.apache.lucene.facet.search.FacetsCollector;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyWriter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
//import static sun.rmi.transport.TransportConstants.Version;

/**
 *
 * @author sgara
 */
public class FacetIndexing {
    
   Directory indexDir = new RAMDirectory();
Directory taxoDir = new RAMDirectory();
IndexWriter indexWriter;
DirectoryTaxonomyWriter taxoWriter;
FacetFields facetFields = new FacetFields(taxoWriter);

DirectoryReader indexr;
IndexSearcher searcher = new IndexSearcher(indexr);
DirectoryTaxonomyReader taxor;
    
FacetSearchParams fsp = new FacetSearchParams();
    private Directory taxDir;

    public FacetIndexing() throws IOException {
        this.taxor = new DirectoryTaxonomyReader(taxoWriter);
        this.indexr = DirectoryReader.open(indexWriter, false);
        this.taxoWriter = new DirectoryTaxonomyWriter(taxDir);
        this.indexWriter = new IndexWriter(indexDir,
                new IndexWriterConfig(Version.LUCENE_50, new KeywordAnalyzer()));
    }
fsp.addFacetRequest(new CountFacetRequest(new CategoryPath("ApplicationNumber"), 10));
fsp.addFacetRequest(new CountFacetRequest(new CategoryPath("PatentNumber"), 10));
FacetsCollector facetsCollector = FacetsCollector.create(fsp, indexr, taxor);
searcher.search(new MatchAllDocsQuery(), facetsCollector);

for (FacetResult fres : facetsCollector.getFacetResults()) {
  FacetResultNode root = fres.getFacetResultNode();
  System.out.println(String.format("%s (%d)", root.label, root.value));
  for (FacetResultNode cat : root.getSubResults()) {
    System.out.println(String.format("  %s (%d)", cat.label.components[1], 
                       cat.value));
  }
}
List<CategoryPath> AppNum = new ArrayList<CategoryPath>();
AppNum.add(new CategoryPath("ApplicationNumber", "10/644,468"));
AppNum.add(new CategoryPath("ApplicationNumber", "11/119,379"));
AppNum.add(new CategoryPath("ApplicationNumber", "11/152,544"));
AppNum.add(new CategoryPath("ApplicationNumber", "12/786,037"));
AppNum.add(new CategoryPath("ApplicationNumber", "12/629,786"));
AppNum.add(new CategoryPath("ApplicationNumber", "11/701,330"));
AppNum.add(new CategoryPath("ApplicationNumber", "11/456,793"));
AppNum.add(new CategoryPath("ApplicationNumber", "11/137,207"));

List<CategoryPath> PatentNum = new ArrayList<CategoryPath>();
PatentNum.add(new CategoryPath("PatentNumber", "US20050044494 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20050253172 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20060031818 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20100235508 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20100145883 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20080183558 A1"));
PatentNum.add(new CategoryPath("PatentNumber", "US20080016008 A1"));



