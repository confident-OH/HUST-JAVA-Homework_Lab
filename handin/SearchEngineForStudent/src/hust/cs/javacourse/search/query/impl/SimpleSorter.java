package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SimpleSorter implements Sort {
    /**
     * 对命中的结果进行排序
     * @param hits ：命中结果集合
     */
    @Override
    public void sort(List<AbstractHit> hits) {
        Collections.sort(hits);
    }

    /**
     *
     * @param hit ：命中文档
     * @return 返回文档的分值
     */
    @Override
    public double score(AbstractHit hit) {
        double score = 0;
        for (Map.Entry<AbstractTerm, AbstractPosting> entry : hit.getTermPostingMapping().entrySet()) {
            if (entry.getValue() != null) {
                score += entry.getValue().getFreq();
            }
        }
        //一定要取反，不然倒排会出错
        return -score;
    }
}
