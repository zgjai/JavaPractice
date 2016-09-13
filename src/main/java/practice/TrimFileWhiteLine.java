package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by zhangguijiang on 16/7/5.
 */
public class TrimFileWhiteLine {
    public static void main(String[] args) {
        final String FILE_TO_READ = "/Users/zhangguijiang/tmpDoc/testTrim";
        final String FILE_TO_WRITE = "/Users/zhangguijiang/tmpDoc/newFile";
        TrimFileWhiteLine test = new TrimFileWhiteLine();
        test.trimFile(FILE_TO_READ, FILE_TO_WRITE);
    }

    private void trimFile(String fileToRead, String fileToWrite) {
        File file = new File(fileToRead);
        BufferedReader reader = null;
        try {
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                } else if (line.isEmpty()) {
                    continue;
                }
                buffer.append(line).append("\n");
            }
            writeFile(fileToWrite, buffer.toString());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String fileToWrite, String buffer) {
        File file = new File(fileToWrite);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(buffer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
