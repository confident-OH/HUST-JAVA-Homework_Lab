package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;

import java.io.BufferedReader;

public class TermTupleScanner extends AbstractTermTupleScanner {
    /**
     * 缺省构造函数
     */
    public  TermTupleScanner(){ }

    /**
     * 构造函数
     * @param input：指定输入流对象，应该关联到一个文本文件
     */
    public  TermTupleScanner(BufferedReader input){
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        return null;
    }
}
