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
     * @param input 输入流对象
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    /**
     * 从输入流中读取出一个个非停用词的TermTuple
     * @return 下一个非停用词三元组
     */
    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple termTuple = input.next();
        if(termTuple==null)  return null; //到达输入尾
        String[] stopWords = StopWords.STOP_WORDS;
        while(Arrays.binarySearch(stopWords,termTuple.term.getContent())>=0){
            //为停用词则跳过
            termTuple = input.next();
            if(termTuple==null)  return null;
        }
        return termTuple;
    }
}
