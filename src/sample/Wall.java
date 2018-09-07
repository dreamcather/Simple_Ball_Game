package sample;

public class Wall {
    private double xCoordinateStart;

    private double yCoordinateStart;

    private double xCoordinateEnd;

    private double yCoordinateEnd;

    private double xNormal;

    private double yNormal;

    private double xLineCoefficient;

    private double yLineCoefficient;

    private double freeLineCoefficient;


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

    }

    public double calculateDistanceToPoint(double xPoint, double yPoint){
        return 0;
    }
}
