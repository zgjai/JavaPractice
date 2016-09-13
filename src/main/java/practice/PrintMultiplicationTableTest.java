package practice;

/**
 * Created by zhangguijiang on 16/5/24.
 */
public class PrintMultiplicationTableTest {

    public static void main(String[] args){
        PrintMultiplicationTableTest test = new PrintMultiplicationTableTest();
        test.printMultiplicationTable(9);
    }

    private void printMultiplicationTable(int maxNum){
        for (int i=1; i<=maxNum; i++){
            printOneLine(i);
            if (i != maxNum){
                System.out.print("\n");
            }
        }
    }

    private void printOneLine(int lineNum){
        for (int i=1; i<=lineNum; i++){
            System.out.print(lineNum + "*" + i + "=" + lineNum*i);
            if (i != lineNum){
                System.out.print("  ");
            }
        }
    }
}
