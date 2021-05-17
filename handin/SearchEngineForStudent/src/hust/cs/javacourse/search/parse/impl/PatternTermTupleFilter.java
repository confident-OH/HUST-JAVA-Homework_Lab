package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     * @param input 输入流对象
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    /**
     * 返回一个英文单词的termTuple
     * @return 下一个符合正则表达式的三元组
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple termTuple = input.next();
        if(termTuple==null)  return null;
        String pattern = Config.TERM_FILTER_PATTERN;
        String content = termTuple.term.getContent();
        while(!content.matches(pattern)){//检测是否符合正则表达式
            termTuple = input.next();
            if(termTuple==null)  return null;
            content = termTuple.term.getContent();
        }
        return termTuple;
    }
}
