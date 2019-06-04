package word2vec.coms;

import java.io.File;
import java.io.IOException;

public class Word2VecTest {
    private static final File sportCorpusFile = new File("\\\\msra-irm104\\E$\\users\\v-lianji\\mlsdata\\Dianping\\raw\\target_shops\\word2vec\\Review1000ByShop.merge.txt");

    public static void main(String[] args) throws IOException {
        
        
        //进行分词训练

        Learn lean = new Learn() ;

        lean.learnFile(sportCorpusFile) ;

        lean.saveModel(new File("\\\\msra-irm104\\E$\\users\\v-lianji\\mlsdata\\Dianping\\raw\\target_shops\\word2vec\\Review1000ByShop.merge.model")) ;



        //加载测试

        Word2VEC w2v = new Word2VEC() ;

        w2v.loadJavaModel("\\\\msra-irm104\\E$\\users\\v-lianji\\mlsdata\\Dianping\\raw\\target_shops\\word2vec\\Review1000ByShop.merge.model") ;

        System.out.println(w2v.distance("喜欢")); ;
        System.out.println(w2v.distance("好吃")); ;
        System.out.println(w2v.distance("讨厌")); ;
        
        for(float i:w2v.getWordMap().get("喜欢")) 
        	System.out.print(i + " ");
        
        

    }

    
 
}