package LintCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangguijiang on 2017/3/25.
 */

class Document {
    public int id;
    public String content;
}

public class InvertedIndex {

    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // Write your code here
        Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
        if (docs == null || docs.size() == 0) {
            return result;
        }
        for (Document item : docs) {
            String[] docArray = item.content.split("\\s+");
            for (String doc : docArray) {
                List<Integer> idList = result.get(doc);
                if (idList == null) {
                    idList = new ArrayList<Integer>();
                    result.put(doc, idList);
                }
                if (!idList.contains(item.id)) {
                    idList.add(item.id);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "This is the content of    document 1 it is very short";
        String[] a = test.split("\\s+");
        System.out.println(a);
    }

}
