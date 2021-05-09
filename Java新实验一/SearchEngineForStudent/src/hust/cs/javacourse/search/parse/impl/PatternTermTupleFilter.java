package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     * @param input
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    /**
     * 返回一个英文单词的termTuple
     * @return
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple termTuple = input.next();
        if(termTuple==null)  return null;
        String pattern = Config.TERM_FILTER_PATTERN;
        String content = termTuple.term.getContent();
        while(!content.matches(pattern)){
            termTuple = input.next();
            if(termTuple==null)  return null;
            content = termTuple.term.getContent();
        }
        return termTuple;
    }
}
