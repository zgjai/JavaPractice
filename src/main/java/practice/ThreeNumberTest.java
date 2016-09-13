package practice;

/**
 * Created by zhangguijiang on 16/5/30.
 */
public class ThreeNumberTest {

    public static void main(String[] args){
        ThreeNumberTest threeNumberTest = new ThreeNumberTest();
        threeNumberTest.test();
    }

    private void test(){
        int n1 = 10;
        int n2 = 2;
        int n3 = 3;
        ThreeNumber number = new ThreeNumber(n1, n2, n3);
        System.out.println( n1 + " + " + n2 + " + " + n3 + " = " + number.addition());
        System.out.println( n1 + " - " + n2 + " - " + n3 + " = " + number.subtraction());
        System.out.println( n1 + " * " + n2 + " * " + n3 + " = " + number.multiplication());
        System.out.println( n1 + " / " + n2 + " / " + n3 + " = " + number.division());
    }

    private class ThreeNumber implements NumberOperational {

        private int n1;
        private int n2;
        private int n3;

        public ThreeNumber() {
        }

        public ThreeNumber(int n1, int n2, int n3) {
            this.n1 = n1;
            this.n2 = n2;
            this.n3 = n3;
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

        public int getN3() {
            return n3;
        }

        public void setN3(int n3) {
            this.n3 = n3;
        }

        public int addition() {
            return this.n1 + this.n2 + this.n3;
        }

        public int subtraction() {
            return this.n1 - this.n2 - this.n3;
        }

        public long multiplication() {
            return this.n1 * this.n2 * this.n3;
        }

        public float division() {
            float f1 = (float) this.n1;
            float f2 = (float) this.n2;
            float f3 = (float) this.n3;
            return f1/f2/f3;
        }
    }
}
