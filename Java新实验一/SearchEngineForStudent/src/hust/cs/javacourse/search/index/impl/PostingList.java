package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PostingList extends AbstractPostingList {
    /**
     * 构造函数
     */
    public PostingList(){
        super();
    }
    /**
     * 增加元素，同时保证没有重复元素
     * @param posting：Posting对象
     */
    @Override
    public void add(AbstractPosting posting) {
        if(!this.list.contains(posting)){
            this.list.add(posting);
        }
    }

    /**
     * 转换为字符串
     * @return
     */
    @Override
    public String toString() {
        String re = "";
        for(AbstractPosting a:this.list){
            re = re + a.toString() + " ";
        }
        return re;
    }

    /**
     * 将一个Posting列表添加进来
     * @param postings：Posting列表
     */
    @Override
    public void add(List<AbstractPosting> postings) {
        for (AbstractPosting a1 : postings) {
            boolean id = true;
            for(AbstractPosting a2:list){
                if(a2.equals(a1)){
                    id = false;
                }
            }
            if(id){
                list.add(a1);
            }
        }
    }

    /**
     * 获取第index个Posting
     * @param index ：下标
     * @return
     */
    @Override
    public AbstractPosting get(int index) {
        return this.list.get(index);
    }

    /**
     * 返回posting对应的index
     * @param posting：指定的Posting对象
     * @return
     */
    @Override
    public int indexOf(AbstractPosting posting) {
        return this.list.indexOf(posting);
    }

    /**
     * 若找到返回对应值，若未找到，返回-1
     * @param docId ：文档id
     * @return
     */
    @Override
    public int indexOf(int docId) {
        int i=0;
        for(AbstractPosting a:this.list){
            if(a.getDocId()==docId){
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * 检查是否存在对应的Posting对象
     * @param posting： 指定的Posting对象
     * @return
     */
    @Override
    public boolean contains(AbstractPosting posting) {
        return this.list.contains(posting);
    }

    /**
     * 删除对应下标的posting
     * @param index：指定的下标
     */
    @Override
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * 删除对应的Posting
     * @param posting ：定的Posting对象
     */
    @Override
    public void remove(AbstractPosting posting) {
        this.list.remove(posting);
    }

    /**
     * 返回list的大小
     * @return
     */
    @Override
    public int size() {
        return this.list.size();
    }

    /**
     * 清空list
     */
    @Override
    public void clear() {
        this.list.clear();
    }

    /**
     * 输出list是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * 对List进行排序
     */
    @Override
    public void sort() {
        this.list.sort(Comparator.naturalOrder());
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.list = new ArrayList<AbstractPosting>((List<AbstractPosting>) (in.readObject()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
