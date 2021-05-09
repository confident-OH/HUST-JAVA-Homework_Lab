package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.util.List;

public class Document extends AbstractDocument {
    /**
     * 缺省构造函数
     */
    public Document(){}

    /**
     * 构造函数1
     * @param docId
     * @param docPath
     */
    public Document(int docId, String docPath){
        super(docId, docPath);
    }
    /**
     * 构造函数2
     * @param docId
     * @param docPath
     */
    public Document(int docId, String docPath,List<AbstractTermTuple> tuples){
        super(docId, docPath, tuples);
    }

    /**
     * 得到DocId
     * @return
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 设置Docid
     * @param docId：文档id
     */
    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * 得到文档路径
     * @return
     */
    @Override
    public String getDocPath() {
        return this.docPath;
    }

    /**
     * 设置文档绝对路径
     * @param docPath：文档绝对路径
     */
    @Override
    public void setDocPath(String docPath) {
        this.docPath = new String(docPath);
    }

    /**
     * 得到三元组
     * @return
     */
    @Override
    public List<AbstractTermTuple> getTuples() {
        return this.tuples;
    }

    /**
     * 添加三元组
     * @param tuple ：要添加的三元组
     */
    @Override
    public void addTuple(AbstractTermTuple tuple) {
        this.tuples.add(tuple);
    }

    /**
     * 判断是否存在指定的三元组
     * @param tuple ： 指定的三元组
     * @return
     */
    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return this.tuples.contains(tuple);
    }

    /**
     * 得到指定的三元组
     * @param index：指定下标位置
     * @return
     */
    @Override
    public AbstractTermTuple getTuple(int index) {
        return this.tuples.get(index);
    }

    /**
     * 得到三元组的大小
     * @return
     */
    @Override
    public int getTupleSize() {
        return this.tuples.size();
    }

    /**
     * 转换为字符串
     * @return
     */
    @Override
    public String toString() {
        return "docId: " + this.docId + "docPath: " + this.docPath + "tuples: " + this.tuples.toString();
    }
}
