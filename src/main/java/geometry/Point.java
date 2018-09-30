package geometry;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        double eps = 0.000001;
        boolean resX = false;
        boolean resY = false;
        if (Math.abs(point.x - this.x) < eps)
            resX = true;
        if (Math.abs(point.y - this.y) < eps)
            resY = true;
        return resX && resY;

    }
    public void add(Point point){
        this.x+=point.getX();
        this.y+=point.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
