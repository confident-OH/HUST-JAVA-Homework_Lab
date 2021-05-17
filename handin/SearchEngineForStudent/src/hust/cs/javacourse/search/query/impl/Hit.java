package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;

import java.util.Map;

public class Hit extends AbstractHit {
    /**
     * 缺省构造函数
     */
    public Hit(){
        super();
    }

    /**
     *
     * @param docId 文档ID
     * @param docPath 文档绝对路径
     */
    public Hit(int docId, String docPath){
        super(docId,docPath);
    }

    /**
     *
     * @param docId 文档ID
     * @param docPath 文档绝对路径
     * @param termPostingMapping 命中的单词和对应的Posting键值对
     */
    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> termPostingMapping){
        super(docId,docPath,termPostingMapping);
    }

    /**
     *
     * @return 返回DocID
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     *
     * @return 返回绝对路径
     */
    @Override
    public String getDocPath() {
        return this.docPath;
    }

    /**
     *
     * @return 返回文档内容
     */
    @Override
    public String getContent() {
        return this.content;
    }

    /**
     *
     * @param content ：文档内容
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return 返回文档得分
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     *
     * @param score ：文档得分
     */
    @Override
    public void setScore(double score) {
        this.score = score;
    }

    /**
     *
     * @return 返回单词Map对
     */
    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return this.termPostingMapping;
    }

    /**
     *
     * @return 返回Hit对象转换的字符串
     */
    @Override
    public String toString() {
        return "Hit!\n"+"DocId:"+this.docId+" DocPath:"+this.docPath+"\nContent:"+
                this.content+"\nScore:"+(-this.score)+"\n"+this.termPostingMapping.toString();
    }

    /**
     *
     * @param o     ：要比较的名字结果
     * @return 返回大小比对结果
     */
    @Override
    public int compareTo(AbstractHit o) {
        return (int)(this.score - o.getScore());
    }
}
