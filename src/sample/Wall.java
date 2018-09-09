package sample;

public class Wall {
    private double xCoordinateStart;

    private double yCoordinateStart;

    private double xCoordinateEnd;

    private double yCoordinateEnd;

    public double xNormal;

    public double yNormal;

    public double xLineCoefficient;

    public double yLineCoefficient;

    public double freeLineCoefficient;


    Wall(double xStart, double yStart, double xEnd, double yEnd) {
        xCoordinateStart = xStart;

        yCoordinateStart = yStart;

        xCoordinateEnd = xEnd;

        yCoordinateEnd = yEnd;

        calculateLine();

        calculateNormal();

    }

    private void calculateLine(){

        xLineCoefficient = yCoordinateStart - yCoordinateEnd;
        yLineCoefficient = xCoordinateEnd - xCoordinateStart;
        freeLineCoefficient = xCoordinateStart*yCoordinateEnd - xCoordinateEnd*yCoordinateStart;
    }

    private void calculateNormal() {
        double lenght = Math.sqrt(Math.pow(xLineCoefficient,2) + Math.pow(yLineCoefficient,2));
        xNormal = xLineCoefficient/lenght;
        yNormal = yLineCoefficient/lenght;

    }

    public double calculateDistanceToPoint(double xPoint, double yPoint){

        return Math.abs(xLineCoefficient*xPoint + yLineCoefficient*yPoint + freeLineCoefficient)
                /Math.sqrt(Math.pow(xLineCoefficient,2)+Math.pow(yLineCoefficient,2));
    }
}
