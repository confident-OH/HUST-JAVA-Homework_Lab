package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.*;

import java.io.*;
import java.util.*;

/**
 * AbstractIndex的具体实现类
 */
public class Index extends AbstractIndex {

    /**
     * 返回termToPostingListMapping
     *
     * @return 索引的字符串表示
     */
    @Override
    public String toString() {
        StringBuffer curString = new StringBuffer();
        curString.append("Indexes: \n{\n");
        for(int i = 0; i<docIdToDocPathMapping.size(); i++){
            curString.append(String.valueOf(i) + ": " + docIdToDocPathMapping.get(i) + "\n");
        }
        curString.append("}\n\nFollowing are Inverted Indexes: \n");
        for (AbstractTerm termList : termToPostingListMapping.keySet()) {
            curString.append(termList.toString()+": {\n");
            curString.append(termToPostingListMapping.get(termList).toString());
            curString.append("\n}\n");
        }
        return curString.toString();
    }

    /**
     * 添加文档到索引，更新索引内部的HashMap
     *
     * @param document ：文档的AbstractDocument子类型表示
     */
    @Override
    public void addDocument(AbstractDocument document) {
        docIdToDocPathMapping.put(document.getDocId(), document.getDocPath()); //增加docId-Path对
        List<AbstractTermTuple> curTermTuple = document.getTuples();
        for (AbstractTermTuple a : curTermTuple) {
            Term curTerm = (Term) a.term;
            PostingList curPList = new PostingList();
            if (termToPostingListMapping.containsKey(curTerm)) {
                //判断在倒挂索引中是否已存在该term
                curPList = (PostingList) termToPostingListMapping.get(curTerm);
            }
            Posting curPosting = new Posting();
            if (curPList.indexOf(document.getDocId()) != -1) {
                curPosting = (Posting) curPList.get(curPList.indexOf(document.getDocId()));
                curPosting.setFreq(curPosting.getFreq() + 1);
                List<Integer> positions = curPosting.getPositions();
                positions.add(a.curPos);
            } else {
                curPosting.setDocId(document.getDocId());
                curPosting.setFreq(1);
                List<Integer> positions = new ArrayList<>();
                positions.add(a.curPos);
                curPosting.setPositions(positions);
            }
            curPList.add(curPosting);
            termToPostingListMapping.put(curTerm, curPList);
        }
    }

    /**
     * <pre>
     * 从索引文件里加载已经构建好的索引.内部调用FileSerializable接口方法readObject即可
     * @param file ：索引文件
     * </pre>
     */
    @Override
    public void load(File file) {
        try {
            readObject(new ObjectInputStream(new FileInputStream(file)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * <pre>
     * 将在内存里构建好的索引写入到文件. 内部调用FileSerializable接口方法writeObject即可
     * @param file ：写入的目标索引文件
     * </pre>
     */
    @Override
    public void save(File file) {
        try {
            writeObject(new ObjectOutputStream(new FileOutputStream(file)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 返回指定单词的PostingList
     *
     * @param term : 指定的单词
     * @return ：指定单词的PostingList;如果索引字典没有该单词，则返回null
     */
    @Override
    public AbstractPostingList search(AbstractTerm term) {
        return this.termToPostingListMapping.get(term);
    }

    /**
     * 返回索引的字典.字典为索引里所有单词的并集
     *
     * @return ：索引中Term列表
     */
    @Override
    public Set<AbstractTerm> getDictionary() {
        return termToPostingListMapping.keySet();
    }

    /**
     * <pre>
     * 对索引进行优化，包括：
     *      对索引里每个单词的PostingList按docId从小到大排序
     *      同时对每个Posting里的positions从小到大排序
     * 在内存中把索引构建完后执行该方法
     * </pre>
     */
    @Override
    public void optimize() {
        for (AbstractTerm abstractTerm : this.getDictionary()) {
            PostingList curPostingList = (PostingList) termToPostingListMapping.get(abstractTerm);
            curPostingList.sort();
        }
    }

    /**
     * 根据docId获得对应文档的完全路径名
     *
     * @param docId ：文档id
     * @return : 对应文档的完全路径名
     */
    @Override
    public String getDocName(int docId) {
        return this.docIdToDocPathMapping.get(docId);
    }

    /**
     * 写到二进制文件
     *
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            int size = termToPostingListMapping.size();
            out.writeObject(size);
            size = docIdToDocPathMapping.size();
            out.writeObject(size);
            for (AbstractTerm term : termToPostingListMapping.keySet()) {
                out.writeObject(term);
                out.writeObject(termToPostingListMapping.get(term));
            }
            for (Integer docId : docIdToDocPathMapping.keySet()) {
                out.writeObject(docId);
                out.writeObject(docIdToDocPathMapping.get(docId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从二进制文件读
     *
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.docIdToDocPathMapping = new TreeMap<>();
            this.termToPostingListMapping = new TreeMap<AbstractTerm, AbstractPostingList>();
            Integer size1 = (Integer) in.readObject();
            Integer size2 = (Integer) in.readObject();
            for (int i = 0; i < size1; i++) {
                AbstractTerm curTerm = (Term) in.readObject();
                AbstractPostingList curPostingList = (PostingList) in.readObject();
                this.termToPostingListMapping.put(curTerm, curPostingList);
            }
            for (int i = 0; i < size2; i++) {
                Integer curDocId = (Integer) in.readObject();
                String curDocPath = (String) in.readObject();
                this.docIdToDocPathMapping.put(curDocId, curDocPath);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
