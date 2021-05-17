package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.*;
import hust.cs.javacourse.search.index.impl.*;
import hust.cs.javacourse.search.query.*;
import hust.cs.javacourse.search.query.impl.*;
import hust.cs.javacourse.search.parse.*;
import hust.cs.javacourse.search.parse.impl.*;
import hust.cs.javacourse.search.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MySearchEgine {
    /**
     * 显示菜单目录
     */
    public static void printMyOrder(){
        System.out.println(" _________________________________________ ");
        System.out.println("|     Welcome To OH Search Engine         |");
        System.out.println("|  1. Build Index                         |");
        System.out.println("|  2. Load Index From index.bat           |");
        System.out.println("|  3. Search Specific Articles            |");
        System.out.println("|  0. Exit                                |");
        System.out.println(" _________________________________________ ");
        System.out.println("Please Input Your Operation: ");
    }

    /**
     * 函数主体，进行单词搜索
     * @param args 命令行参数
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        AbstractIndexBuilder indexBuilder = null;
        AbstractIndexSearcher indexSearcher = null;
        int opt = 1;        //  用户选项
        Scanner scanner = new Scanner(System.in);
        while (opt != 0) {
            printMyOrder();
            opt = scanner.nextInt();
            String cc = scanner.nextLine();
            switch (opt) {
                case 1:
                    if (indexBuilder == null) {
                        AbstractDocumentBuilder documentBuilder = new DocumentBuilder();
                        indexBuilder = new IndexBuilder(documentBuilder);
                        // 指定文件根目录
                        String rootDir = Config.DOC_DIR;
                        System.out.println("Start build index ...");
                        AbstractIndex index = indexBuilder.buildIndex(rootDir);
                        index.optimize();
                        System.out.println(index.toString());  //控制台打印index的内容
                        //测试保存到文件
                        String indexFile = Config.INDEX_DIR + "index.dat";
                        index.save(new File(indexFile));    //索引保存到文件
                        indexFile = Config.INDEX_DIR + "index.dat";
                        if (indexSearcher == null) {
                            indexSearcher = new IndexSearcher();
                        }
                        indexSearcher.open(indexFile);
                    } else {
                        System.out.println("Index already exists!");
                    }
                    break;
                case 2:
                    if (indexSearcher == null) {
                        System.out.println("Start load index...");
                        String indexFile = Config.INDEX_DIR + "index.dat";
                        indexSearcher = new IndexSearcher();
                        indexSearcher.open(indexFile);
                        System.out.println("Index load finished!");
                    } else {
                        System.out.println("Index already exists!");
                    }
                    break;
                case 3:
                    if (indexSearcher == null) {
                        System.out.println("Please Build Index First");
                    } else {
                        System.out.println("Please follow the search rules: \"word\" \"word && word\" \"word || word\"");
                        SimpleSorter sorter = new SimpleSorter();
                        Scanner scanner1 = new Scanner(System.in);

                        AbstractHit[] resHits = null;
                        AbstractTerm queryTerm1 = null;
                        AbstractTerm queryTerm2 = null;
                        Scanner scanner2 = new Scanner(System.in);

                        String searchLine = scanner.nextLine();
                        String[] searchP = searchLine.trim().split(" ");
                        if(searchP.length == 1){
                            queryTerm1 = new Term(searchP[0].trim());
                            if (isIllegalWord(queryTerm1)) {
                                System.out.println("Illegal Word");
                            } else {
                                resHits = indexSearcher.search(queryTerm1, sorter);
                                showRes(resHits);
                            }
                        }
                        else if (searchP.length == 2){
                            queryTerm1 = new Term(searchP[0].trim());
                            queryTerm2 = new Term(searchP[1].trim());
                            if (isIllegalWord(queryTerm1) || isIllegalWord(queryTerm2)) {
                                System.out.println("Illegal Word");
                            } else {
                                resHits = indexSearcher.search(queryTerm1, queryTerm2, sorter, AbstractIndexSearcher.LogicalCombination.LINK);
                                showRes(resHits);
                            }
                        }
                        else if(searchP.length == 3){
                            queryTerm1 = new Term(searchP[0].trim());
                            queryTerm2 = new Term(searchP[2].trim());
                            if(searchP[1].trim().equals("&&")){
                                if (isIllegalWord(queryTerm1) || isIllegalWord(queryTerm2)) {
                                    System.out.println("Illegal Word");
                                } else {
                                    resHits = indexSearcher.search(queryTerm1, queryTerm2, sorter, AbstractIndexSearcher.LogicalCombination.AND);
                                    showRes(resHits);
                                }
                            }
                            else if(searchP[1].trim().equals("||")){
                                if (isIllegalWord(queryTerm1) || isIllegalWord(queryTerm2)) {
                                    System.out.println("Words Illegal");
                                } else {
                                    resHits = indexSearcher.search(queryTerm1, queryTerm2, sorter, AbstractIndexSearcher.LogicalCombination.OR);
                                    showRes(resHits);
                                }
                            }
                            else{
                                System.out.println("Error input! \n" +
                                        "Please check the number of words you input and between word only a blank!");
                            }
                        }
                        else{
                            System.out.println("Error input! \n" +
                                    "Please check the number of words you input and between word only a blank!");
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Input Error!");
                    break;
            }
        }
        scanner.close();
    }

    /**
     *
     * @param resHits 搜索返回的Hit数组
     */
    public static void showRes(AbstractHit[] resHits) {
        int i = 1;
        if (resHits != null) {
            for (AbstractHit hit : resHits) {
                System.out.print("*************************** Document "+i);
                i++;
                System.out.println(" ******************************************");
                System.out.println(hit.toString());
            }
            System.out.println("*********************** Search END ************************\n");
        } else {
            System.out.println("Not Found!");
        }
    }

    /**
     *
     * @param term 待检测单词
     * @return 是否符合规则
     */
    public static boolean isIllegalWord(AbstractTerm term) {

        ArrayList<String> stopWords = new ArrayList<>(Arrays.asList(StopWords.STOP_WORDS));
        return stopWords.contains(term.getContent())
                && term.getContent().length() > Config.TERM_FILTER_MAXLENGTH
                && term.getContent().length() < Config.TERM_FILTER_MINLENGTH
                && !term.getContent().matches(Config.TERM_FILTER_PATTERN);
    }
}
