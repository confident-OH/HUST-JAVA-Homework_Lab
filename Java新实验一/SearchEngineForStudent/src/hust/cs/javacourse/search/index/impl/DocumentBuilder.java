package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PatternTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.*;

public class DocumentBuilder extends AbstractDocumentBuilder {
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) throws IOException {
        Document item = new Document(docId, docPath);
        AbstractTermTuple tuple = (AbstractTermTuple) termTupleStream.next();
        while(tuple!=null){
            item.addTuple(tuple);
            tuple = termTupleStream.next();
        }
        return item;
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file){
        AbstractTermTupleStream abstractTermTupleStream = null;
        AbstractDocument abstractDocument = null;
        try {
            abstractTermTupleStream = new TermTupleScanner(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
            abstractTermTupleStream = new PatternTermTupleFilter(abstractTermTupleStream);
            abstractTermTupleStream = new LengthTermTupleFilter(abstractTermTupleStream);
            abstractTermTupleStream = new StopWordTermTupleFilter(abstractTermTupleStream);
            abstractDocument = this.build(docId, docPath, abstractTermTupleStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (abstractTermTupleStream != null) abstractTermTupleStream.close();
        }
        return abstractDocument;
    }
}
