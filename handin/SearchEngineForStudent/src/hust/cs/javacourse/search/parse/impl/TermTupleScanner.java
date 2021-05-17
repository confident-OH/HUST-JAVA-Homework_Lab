package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.util.StringSplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static hust.cs.javacourse.search.util.Config.STRING_SPLITTER_REGEX;

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
    protected Queue<AbstractTermTuple> termTuples = new LinkedList<>();

    /**
     * 扫描
     * @return 返回分词得到的三元组对象
     */
    @Override
    public AbstractTermTuple next() {
        try{
            String Line = input.readLine();
            int pos = 0;
            while(Line!=null){
                Line = Line.trim();        //删除首尾字符
                Line = Line.toLowerCase(); //转小写
                StringSplitter splitter = new StringSplitter();
                splitter.setSplitRegex(STRING_SPLITTER_REGEX);
                List<String> words = splitter.splitByRegex(Line); //完成划分后的多个单词
                for(String word:words){
                    AbstractTermTuple termTuple = new TermTuple();
                    AbstractTerm term = new Term();
                    term.setContent(word);
                    termTuple.term = term;
                    termTuple.curPos = pos++;
                    termTuples.add(termTuple);
                }
                Line = input.readLine();
            }
            if(termTuples.isEmpty()) return null;
            else return termTuples.poll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
