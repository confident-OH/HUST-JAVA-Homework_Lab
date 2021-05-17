package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     * @param input 输入流对象
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    /**
     * 过滤掉过长或过短的单词
     * @return 下一个未因长度被过滤的三元组
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple curTermTuple= input.next();
        if(curTermTuple==null)  return null;
        int length = curTermTuple.term.getContent().length();
        while(length<Config.TERM_FILTER_MINLENGTH||length>Config.TERM_FILTER_MAXLENGTH){
            //长度不满足的词跳过
            curTermTuple = input.next();
            if(curTermTuple==null)  return null;
            length = curTermTuple.term.getContent().length();
        }
        return curTermTuple;
    }
}
