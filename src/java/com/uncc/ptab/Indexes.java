/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uncc.ptab;

/**
 *
 * @author sgara
 */
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.LucenePackage;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldDocs;

public class Indexes {
        /**
     *
     * @param args
     */
    	//private Searcher indexSearcher;
 
    public static void main(String[] args) {
        indexDirectory();
        String ver= LucenePackage.get().getImplementationVersion();
                System.out.println("======================================================");
                System.out.println(ver);
                System.out.println("======================================================");
        //search("");
        
    }   
             Indexes methods = new Indexes();
             //methods.termQueryExample();
             
    private static void indexDirectory() {      
         //Apache Lucene Indexing Directory .txt files     
         try {  
         //indexing directory    
         Path path = Paths.get("C:\\Users\\sgara\\Desktop\\PTAB_New\\Week09\\PTAB_Indexes");
         Directory directory;
             directory = FSDirectory.open(path);
         IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());        
         IndexWriter indexWriter = new IndexWriter(directory, config);
         indexWriter.deleteAll();
         File f = new File("C:\\Users\\sgara\\Desktop\\PTAB_New\\Week08\\PTAB_TextConverted"); // current directory     
             for (File file : f.listFiles()) {
                    System.out.println("indexed " + file.getCanonicalPath());               
                    Document doc = new Document();
                    doc.add(new TextField("path", file.getName(), Store.YES));
                    FileInputStream is = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    while((line = reader.readLine())!=null){
                       stringBuffer.append(line).append("\n");
                    }
                    reader.close();
                    doc.add(new TextField("contents", stringBuffer.toString(), Store.YES));
                    indexWriter.addDocument(doc);           
             }                           
             indexWriter.close();           
             directory.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }                   
    }
         
//    private static void search(String text) {   
//        //Apache Lucene searching text inside .txt files
//        try {   
//            Path path = Paths.get("D:\\LuceneProjFiles\\Ptab_Indexes\\PTAB_20150221_20150227_indexes");
//            Directory directory = FSDirectory.open(path);       
//            IndexReader indexReader =  DirectoryReader.open(directory);
//            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//            QueryParser queryParser = new QueryParser("contents",  new StandardAnalyzer());  
//            Query query = queryParser.parse(text);
//            TopFieldDocs topDocs = (TopFieldDocs) indexSearcher.search(query,10);
//            System.out.println("totalHits " + topDocs.totalHits);
//            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {           
//                Document document = indexSearcher.doc(scoreDoc.doc);
//                System.out.println("path " + document.get("path"));
//                System.out.println("content " + document.get("contents"));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }               
//    }
    
//    private void termQueryExample(){
//		System.out.println("TermQuery example: Search mails having the word \"java\"" +
//				" in the subject field");
//		Term term = new Term("subject","java");
//		Query query = new TermQuery(term);	    
//	    showSearchResults(query);
//	}
//    
//    private void showSearchResults(Query query ){
//		
//		try{
//			/* First parameter is the query to be executed and 
//			   second parameter indicates the no of search results to fetch
//			 */
//			TopDocs topDocs = indexSearcher.search(query,20);	
//			System.out.println("Total hits "+topDocs.totalHits);
//			
//			// Get an array of references to matched documents
//			ScoreDoc[] scoreDosArray = topDocs.scoreDocs;	
//			for(ScoreDoc scoredoc: scoreDosArray){
//				//Retrieve the matched document and show relevant details
//				Document doc = indexSearcher.doc(scoredoc.doc);
//				System.out.println("\nSender: "+doc.getField("sender").stringValue());
//				System.out.println("Subject: "+doc.getField("subject").stringValue());
//				System.out.println("Email file location: "+
//								doc.getField("emailDoc").stringValue());	
//			}
//			System.out.println("---------------------------------------------");
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		
//	}
  }