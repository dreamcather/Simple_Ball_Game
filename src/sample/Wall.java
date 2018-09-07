package sample;

public class Wall {
    private double xCoordinateStart;

    private double yCoordinateStart;

    private double xCoordinateEnd;

    private double yCoordinateEnd;

    private double xNormal;

    private double yNormal;

    private double xLine;

    private double yLine;

    private void CalculateLine(){

    }

    private void CalculateNormal() {

    }

    Wall(double xStart, double yStart, double xEnd, double yEnd) {
        xCoordinateStart = xStart;

        yCoordinateStart = yStart;

        xCoordinateEnd = xEnd;

        yCoordinateEnd = yEnd;

        CalculateLine();

        CalculateNormal();

    }
}
