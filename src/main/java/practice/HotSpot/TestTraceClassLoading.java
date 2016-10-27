package practice.HotSpot;


import java.lang.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.Hashtable;

/**
 * Created by zhangguijiang on 2016/10/8.
 */
public class TestTraceClassLoading {
    public static void main(String[] args){
        System.out.println("test");
        Test test = new Test();
        TestVMOptions testVMOptions = new TestVMOptions();
    }

    private static class Test implements AnnotatedArrayType{
        public Test() {
        }

        public AnnotatedType getAnnotatedGenericComponentType() {
            return null;
        }

        public Type getType() {
            return null;
        }

        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return null;
        }

        public Annotation[] getAnnotations() {
            return new Annotation[0];
        }

        public Annotation[] getDeclaredAnnotations() {
            return new Annotation[0];
        }
    }
}
