package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Posting;
import hust.cs.javacourse.search.index.impl.PostingList;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexSearcher extends AbstractIndexSearcher {
    /**
     * 打开文件
     * @param indexFile ：指定索引文件
     */
    @Override
    public void open(String indexFile) {
        this.index.load(new File(indexFile));
    }

    /**
     * 单词搜索
     * @param queryTerm ：检索词
     * @param sorter ：排序器
     * @return 查找到的Hit[]数组
     */
    @Override
    public AbstractHit[] search(AbstractTerm queryTerm, Sort sorter) {
        PostingList postingList = (PostingList) index.search(queryTerm);
        if(postingList==null)   return new Hit[0];
        AbstractHit[] hits = new Hit[postingList.size()];
        for(int i=0;i<postingList.size();i++){
            Posting curPosting = (Posting) postingList.get(i);
            hits[i] = new Hit(curPosting.getDocId(),index.getDocName(curPosting.getDocId()));
            hits[i].getTermPostingMapping().put(queryTerm,curPosting);
            hits[i].setScore(sorter.score(hits[i]));
        }
        sorter.sort(Arrays.asList(hits));
        return hits;
    }
    /**
     * 逻辑搜索
     * @param queryTerm1 ：第1个检索词
     * @param queryTerm2 ：第2个检索词
     * @param sorter ：    排序器
     * @param combine ：   多个检索词的逻辑组合方式
     * @return 查找到的Hit[]数组
     */
    @Override
    public AbstractHit[] search(AbstractTerm queryTerm1, AbstractTerm queryTerm2, Sort sorter, LogicalCombination combine) {
        PostingList postingList1 = (PostingList) index.search(queryTerm1);
        PostingList postingList2 = (PostingList) index.search(queryTerm2);
        List<AbstractHit> hitList = new ArrayList<>();
        if(combine == LogicalCombination.AND){
            //AND逻辑
            if(postingList1==null||postingList2==null)  return new Hit[0]; //只要一个为空，那么就返回空了
            for(int i=0;i<postingList1.size();i++){
                Posting curPosting1 = (Posting) postingList1.get(i);
                int curDocId = curPosting1.getDocId();
                int index2 = postingList2.indexOf(curDocId);
                if(index2!=-1) {
                    // 1， 2中都有，符合，添加至hitList中
                    Posting curPosting2 = (Posting) postingList2.get(index2);
                    Hit curHit = new Hit(curDocId,index.getDocName(curDocId));
                    curHit.getTermPostingMapping().put(queryTerm1,curPosting1);
                    curHit.getTermPostingMapping().put(queryTerm2,curPosting2);
                    curHit.setScore(sorter.score(curHit));
                    hitList.add(curHit);
                }
            }
        }
        else if(combine == LogicalCombination.OR){
            //OR逻辑
            //只要有一个为空，那么就选另外一个直接调用单个词搜索的就行了
            if(postingList1==null)  return search(queryTerm2,sorter);
            if(postingList2==null)  return search(queryTerm1,sorter);
            for(int i=0;i<postingList1.size();i++){
                Posting curPosting1 = (Posting) postingList1.get(i);
                int curDocId = curPosting1.getDocId();
                int index2 = postingList2.indexOf(curDocId);
                Hit curHit = new Hit(curDocId,index.getDocName(curDocId));
                if(index2==-1) {
                    //注意要防止重复
                    curHit.getTermPostingMapping().put(queryTerm1,curPosting1);
                }
                else{
                    Posting curPosting2 = (Posting) postingList2.get(index2);
                    curHit.getTermPostingMapping().put(queryTerm1,curPosting1);
                    curHit.getTermPostingMapping().put(queryTerm2,curPosting2);
                }
                curHit.setScore(sorter.score(curHit));
                hitList.add(curHit);
            }
            for(int i=0;i<postingList2.size();i++){
                Posting curPosting2 = (Posting) postingList2.get(i);
                int curDocId = curPosting2.getDocId();
                Hit curHit = new Hit(curDocId,index.getDocName(curDocId));
                curHit.getTermPostingMapping().put(queryTerm2,curPosting2);
                curHit.setScore(sorter.score(curHit));
                hitList.add(curHit);
            }
        }
        else if(combine == LogicalCombination.LINK) {
            // 连词查询
            boolean lable = false;
            //若有一个词为空（可能为停用词之类的），那么就当单词查询处理
            if(postingList1==null && postingList2 == null){
                System.out.println("Warning: The two word is does not meet the query rules");
                return new Hit[0];
            }
            else if (postingList1 == null) {
                System.out.println("Warning: The first word is does not meet the query rules");
                return search(queryTerm2, sorter);
            } else if (postingList2 == null) {
                System.out.println("Warning: The second word is does not meet the query rules");
                return search(queryTerm1, sorter);
            }
            for (int i = 0; i < postingList1.size(); i++) {
                Posting curPosting1 = (Posting) postingList1.get(i);
                int curDocId = curPosting1.getDocId();
                int index2 = postingList2.indexOf(curDocId);
                if (index2 != -1) {
                    // 1， 2中都有，下面查看两者的position
                    Posting curPosting2 = (Posting) postingList2.get(index2);
                    for (Integer po : curPosting1.getPositions()) {
                        if (curPosting2.getPositions().contains(po + 1) || curPosting2.getPositions().contains(po - 1)) {
                            lable = true;
                            break;
                        }
                    }
                    // 如果有相邻的词，则lable为true
                    if (lable) {
                        Hit curHit = new Hit(curDocId, index.getDocName(curDocId));
                        curHit.getTermPostingMapping().put(queryTerm1, curPosting1);
                        curHit.getTermPostingMapping().put(queryTerm2, curPosting2);
                        curHit.setScore(sorter.score(curHit));
                        hitList.add(curHit);
                    }
                }
            }
        }
        sorter.sort(hitList);
        AbstractHit[] res = new AbstractHit[hitList.size()];
        return hitList.toArray(res);
    }
}