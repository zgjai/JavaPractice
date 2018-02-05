package LintCode.SystemDesign.MessageSystem_RateLimiter;

/**
 * Factory is a design pattern in common usage. Please implement a ToyFactory which can generate proper toy based on the
 * given type. Your object will be instantiated and called as such: ToyFactory tf = new ToyFactory(); Toy toy =
 * tf.getToy(type); toy.talk();
 */
interface Toy {
    void talk();
}

class Dog implements Toy {
    // Write your code here

    @Override
    public void talk() {

    }
}

class Cat implements Toy {
    // Write your code here

    @Override
    public void talk() {

    }
}

public class ToyFactory {
    /**
     * @param type a string
     * @return Get object of the type
     */
    public Toy getToy(String type) {
        // Write your code here
        return null;
    }
}