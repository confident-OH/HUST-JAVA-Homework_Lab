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
     * @param docId 文档ID
     * @param docPath 文档路径
     */
    public Document(int docId, String docPath){
        super(docId, docPath);
    }
    /**
     * 构造函数2
     * @param docId 文档ID
     * @param docPath 文档路径
     * @param tuples 三元组列表
     */
    public Document(int docId, String docPath,List<AbstractTermTuple> tuples){
        super(docId, docPath, tuples);
    }

    /**
     * 得到DocId
     * @return 文档对应的DocId
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 设置Docid
     * @param docId 文档id
     */
    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * 得到文档路径
     * @return 字符串：文件路径
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
     * 得到Document生成的三元组列表
     * @return 三元组列表
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
     * @return true: 包含该三元组
     */
    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return this.tuples.contains(tuple);
    }

    /**
     * 得到指定的三元组
     * @param index：指定下标位置
     * @return 返回对应的下标的三元组
     */
    @Override
    public AbstractTermTuple getTuple(int index) {
        return this.tuples.get(index);
    }

    /**
     * 得到三元组list的大小
     * @return 返回三元组列表的大小
     */
    @Override
    public int getTupleSize() {
        return this.tuples.size();
    }

    /**
     * 转换为字符串
     * @return 将该Document转为字符串
     */
    @Override
    public String toString() {
        return "docId: " + this.docId + "docPath: " + this.docPath + "tuples: " + this.tuples.toString();
    }
}
