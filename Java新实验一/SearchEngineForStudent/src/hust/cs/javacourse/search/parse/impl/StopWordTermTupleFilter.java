package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;

import java.io.IOException;
import java.util.Arrays;

public class StopWordTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     * @param input
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    /**
     * 从输入流中读取出一个个非停用词的TermTuple
     * @return
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple termTuple = input.next();
        if(termTuple==null)  return null; //到达输入尾
        String[] stopWords = StopWords.STOP_WORDS;
        while(Arrays.binarySearch(stopWords,termTuple.term.getContent())>=0){
            termTuple = input.next();
            if(termTuple==null)  return null;
        }
        return termTuple;
    }
}
