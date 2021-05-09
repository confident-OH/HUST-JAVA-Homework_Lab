package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     * @param input
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    /**
     * 过滤掉过长或过短的单次
     * @return
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple curTermTuple= input.next();
        if(curTermTuple==null)  return null;
        int length = curTermTuple.term.getContent().length();
        while(length< Config.TERM_FILTER_MINLENGTH||length>Config.TERM_FILTER_MAXLENGTH){
            curTermTuple = input.next();
            if(curTermTuple==null)  return null;
            length = curTermTuple.term.getContent().length();
        }
        return curTermTuple;
    }
}
