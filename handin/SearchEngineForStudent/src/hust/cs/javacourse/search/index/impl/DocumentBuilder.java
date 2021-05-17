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
    /**
     * 从Stream流导入Document
     * @param docId             : 文档id
     * @param docPath           : 文档绝对路径
     * @param termTupleStream   : 文档对应的TermTupleStream
     * @return 构建完成的Document对象
     * @throws IOException 文件路径异常
     */
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

    /**
     * 从文件路径导入Document，先过滤并转换至Stream流，再调用上一个build
     * @param docId     : 文档id
     * @param docPath   : 文档绝对路径
     * @param file      : 文档对应File对象
     * @return 构建完成的Document对象
     */
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
