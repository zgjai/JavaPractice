package practice;

/**
 * Created by zhangguijiang on 16/5/30.
 */
public class NumberTest {


    public static void main(String[] args) {
        NumberTest numberTest = new NumberTest();
        numberTest.test();
    }


    private void test(){
        int n1 = 10;
        int n2 = 3;
        Number number = new Number(n1, n2);
        System.out.println( n1 + " + " + n2 + " = " + number.addition());
        System.out.println( n1 + " - " + n2 + " = " + number.subtraction());
        System.out.println( n1 + " * " + n2 + " = " + number.multiplication());
        System.out.println( n1 + " / " + n2 + " = " + number.division());
    }

    private class Number {
        private int n1;
        private int n2;

        public Number() {
        }

        public Number(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        public int getN1() {
            return n1;
        }

        public void setN1(int n1) {
            this.n1 = n1;
        }

        public int getN2() {
            return n2;
        }

        public void setN2(int n2) {
            this.n2 = n2;
        }

        public int addition(){
            return this.getN1() + this.getN2();
        }

        public int subtraction(){
            return this.getN1() - this.getN2();
        }

        public long multiplication(){
            return this.getN1() * this.getN2();
        }

        public float division(){
            float f1 = (float) this.getN1();
            float f2 = (float) this.getN2();
            return f1 / f2;
        }
    }
}
