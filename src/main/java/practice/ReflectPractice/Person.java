package practice.ReflectPractice;

/**
 * Created by zhangguijiang on 16/6/29.
 */
public class Person {

    private String name;
    private Integer age;
    private String sex;

    public Person() {
    }

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAll(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void sayHello() {
        System.out.println("hello, world!");
    }

    public void saySomething(String something) {
        System.out.println(something);
    }

    public void sayNameAndAge(String name, Integer age){
        System.out.println(name);
        System.out.println(age);
    }

    public void printInfo() {
        System.out.println("name:" + this.name + "\n" + "age:" + this.age + "\n" + "sex:" + this.sex + "\n");
    }
}
