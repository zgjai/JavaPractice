package LintCode.SystemDesign.MessageSystem_RateLimiter;

/**
 * Factory is a design pattern in common usage. Implement a ShapeFactory that can generate correct shape.
 * 
 * You can assume that we have only tree different shapes: Triangle, Square and Rectangle.
 *
 * Your object will be instantiated and called as such: ShapeFactory sf = new ShapeFactory(); Shape shape =
 * sf.getShape(shapeType); shape.draw();
 */
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    // Write your code here

    @Override
    public void draw() {

    }
}

class Square implements Shape {
    // Write your code here

    @Override
    public void draw() {

    }
}

class Triangle implements Shape {
    // Write your code here

    @Override
    public void draw() {

    }
}

public class ShapeFactory {
    /**
     * @param shapeType a string
     * @return Get object of type Shape
     */
    public Shape getShape(String shapeType) {
        // Write your code here
        return null;
    }
}