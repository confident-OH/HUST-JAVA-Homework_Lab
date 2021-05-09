package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Posting extends AbstractPosting {
    /**
     * 子类实现的无参数构造函数
     */
    public Posting(){
        super();
    }

    /**
     * 子类实现的有参数构造函数
     * @param docId
     * @param freq
     * @param positions
     */
    public Posting(int docId, int freq, List<Integer> positions){
        super(docId, freq, positions);
    }

    /**
     * 覆盖equals方法，检测是否相等
     * @param obj ：要比较的另外一个Posting
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass()!=obj.getClass()) return false;
        Posting p = (Posting) obj;
        return p.docId == this.docId && p.freq == this.freq &&
                p.positions.containsAll(this.positions) &&
                this.positions.containsAll(p.positions);
    }

    /**
     * 覆盖toString内容
     * @return
     */
    @Override
    public String toString() {
        return "docId: " + this.docId + "freq: " + this.freq +
                "\nPositions: " + this.positions.toString();
    }

    /**
     * 得到Posting的docId
     * @return
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 设置Posting的docId
     * @param docId：包含单词的文档id
     */
    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * 得到Posting的freq
     * @return
     */
    @Override
    public int getFreq() {
        return this.freq;
    }

    /**
     * 设置Posting的freq
     * @param freq:单词在文档里出现的次数
     */
    @Override
    public void setFreq(int freq) {
        this.freq = freq;
    }

    /**
     * 得到Posting的Positions
     * @return
     */
    @Override
    public List<Integer> getPositions() {
        return this.positions;
    }

    /**
     * 设置Posting的Positions
     * @param positions：单词在文档里出现的位置列表
     */
    @Override
    public void setPositions(List<Integer> positions) {
        this.positions = new ArrayList<Integer>();
        this.positions.addAll(positions);
    }

    /**
     *
     * @param o： 另一个Posting对象
     * @return
     */
    @Override
    public int compareTo(AbstractPosting o) {
        return this.docId - o.getDocId();
    }

    /**
     * 进行排序
     */
    @Override
    public void sort() {
        Collections.sort(this.positions);
    }

    /**
     *
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.docId);
            out.writeObject(this.freq);
            out.writeObject(this.positions.size());
            for (Integer i : this.positions)
                out.writeObject(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.docId = (Integer) in.readObject();
            this.freq = (Integer) in.readObject();
            Integer size = (Integer) in.readObject();
            for (int i = 0; i < size; i++)
                this.positions.add((Integer) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
