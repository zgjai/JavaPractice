package practice.ReflectPractice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhangguijiang on 16/6/29.
 */
public class ClassPractice {
    public static void main(String[] args) throws Exception{
        String flag = "test";
        Class<?>  classType = flag.getClass();
        System.out.println(classType);
        Class<?> classType2 = Boolean.TYPE;
        System.out.println(classType2);

        Field[] fields = classType.getFields();
        for (Field item : fields){
            System.out.println(item);
        }
        System.out.println();

        Field[] fields1 = classType.getDeclaredFields();
        for (Field item : fields1){
            System.out.println(item);
        }
        System.out.println();

        Method[] methods = classType.getMethods();
        for (Method item : methods){
            System.out.println(item);
        }
        System.out.println();

        Constructor[] constructors = classType.getConstructors();
        for (Constructor item : constructors){
            System.out.println(item);
        }
        System.out.println();

    }
}
