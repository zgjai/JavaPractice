package LintCode.SystemDesign;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangguijiang on 2018/1/15.
 */
public class GFSClient extends BaseGFSClient {

    private final int chunkSize;
    private Map<String, Integer> fileChunkNum = new HashMap<>();

    /*
     * @param chunkSize: An integer
     */public GFSClient(int chunkSize) {
        // do intialization if necessary
        this.chunkSize = chunkSize;
    }

    /*
     * @param filename: a file name
     * @return: conetent of the file given from GFS
     */
    public String read(String filename) {
        // write your code here
        Integer chunkSize = fileChunkNum.get(filename);
        if (chunkSize == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= chunkSize; i++) {
            sb.append(readChunk(filename, i));
        }
        return sb.toString();
    }

    /*
     * @param filename: a file name
     * @param content: a string
     * @return: nothing
     */
    public void write(String filename, String content) {
        // write your code here
        int len = content.length();
        int chunkLen = len / chunkSize - 1;
        if (len % chunkSize > 0) {
            chunkLen++;
        }
        fileChunkNum.put(filename, chunkLen);
        for (int i = 0; i <= chunkLen; i++) {
            int start = i * chunkSize;
            int end = i == chunkLen ? len : (i + 1) * chunkSize;
            writeChunk(filename, i, content.substring(start, end));
        }
    }
}

class BaseGFSClient {
    private Map<String, String> chunk_list;

    public BaseGFSClient() {
    }

    public String readChunk(String filename, int chunkIndex) {
        // Read a chunk from GFS
        return "";
    }

    public void writeChunk(String filename, int chunkIndex, String content) {
        // Write a chunk to GFS
    }
}