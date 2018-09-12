package sample;

public class Vector {
    private double xCoefficient;
    private double yCoefficient;


    public double getxCoefficient() {
        return xCoefficient;
    }

    public double getyCoefficient() {
        return yCoefficient;
    }

    public Vector(double xCoefficient, double yCoefficient) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
    }

    public Vector(Point start, Point end){
        xCoefficient = end.getX() - start.getX();
        yCoefficient = end.getY() - start.getY();
    }

    public Point addition(Point point) {
        return new Point(point.getX() + xCoefficient, point.getY() + yCoefficient);
    }

    public Vector addition(Vector vector){
        return new Vector(this.xCoefficient + vector.xCoefficient,this.yCoefficient + vector.yCoefficient);
    }

    public boolean equals(Vector vector){
        boolean xRes = false;
        boolean yRes = false;
        double eps = 0.00001;
        if(Math.abs(vector.xCoefficient-this.xCoefficient)<eps)
            xRes=true;
        if(Math.abs(vector.yCoefficient-this.yCoefficient)<eps)
            yRes=true;
        return  xRes&&yRes;
    }

}
