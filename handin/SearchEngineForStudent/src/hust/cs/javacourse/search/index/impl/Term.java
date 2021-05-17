package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Term extends AbstractTerm {
    /**
     * 缺省构造函数
     */
    public Term(){super();}

    public Term(String content){
        super(content);
    }

    /**
     * 覆盖equal方法
     * @param obj ：要比较的另外一个Term
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass()!=obj.getClass()) return false;
        Term p = (Term) obj;
        return this.content.equals(p.getContent());
    }

    /**
     * 覆盖toString方法
     * @return Term转换为的字符串
     */
    @Override
    public String toString() {
        return this.content;
    }

    /**
     * 得到Content
     * @return 返回content
     */
    @Override
    public String getContent() {
        return this.content;
    }

    /**
     * 设置content
     * @param content：Term的内容
     */
    @Override
    public void setContent(String content) {
        this.content = new String(content);
    }

    /**
     * 为排序时设置比较方案
     * @param o： 要比较的Term对象
     * @return 若小于输入的Term，返回值小于0，若大于则返回值大于0，若等于则返回值等于0
     */
    @Override
    public int compareTo(AbstractTerm o) {
        return this.content.compareTo(o.getContent());
    }

    /**
     * 设置写入文件
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置读取方案
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.setContent((String) (in.readObject()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
