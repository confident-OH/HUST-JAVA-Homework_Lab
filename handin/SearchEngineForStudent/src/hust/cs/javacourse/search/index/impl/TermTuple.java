package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;

public class TermTuple extends AbstractTermTuple {
    /**
     * 缺省构造函数
     */
    public TermTuple(){super();}

    /**
     * 覆盖equal方法
     * @param obj ：要比较的另外一个三元组
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass()!=obj.getClass()) return false;
        TermTuple p = (TermTuple) obj;
        return this.term.equals(p.term) && this.curPos == p.curPos;
    }

    /**
     * 覆盖toString方法
     * @return TermTuple转换为的字符串
     */
    @Override
    public String toString() {
        return this.term.toString() + "frec: " + this.freq + "curPos: " + this.curPos;
    }

}
