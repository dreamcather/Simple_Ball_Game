package detection;

import object.Wall;
import object.Ball;

public interface ObjectDetectVisitor<T> {

    T visit(Ball ball);

    T visit(Wall wall);
}
