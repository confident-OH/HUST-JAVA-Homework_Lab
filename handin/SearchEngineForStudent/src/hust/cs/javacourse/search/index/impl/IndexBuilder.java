package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class IndexBuilder extends AbstractIndexBuilder {
    public IndexBuilder(AbstractDocumentBuilder docBuilder) {
        super(docBuilder);
    }

    /**
     * 构造Index
     * @param rootDirectory ：指定目录
     * @return 生成的倒排索引index
     */
    @Override
    public AbstractIndex buildIndex(String rootDirectory){
        Index index = new Index();
        DocumentBuilder docBuilder = new DocumentBuilder();
        List<String> files = FileUtil.list(rootDirectory); //从文件夹构建
        for (String string : files) {
            index.addDocument(docBuilder.build(docId, string, new File(string)));
            docId++;
        }
        index.optimize();
        return index;
    }
}
