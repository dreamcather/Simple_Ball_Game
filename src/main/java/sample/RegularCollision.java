package sample;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void collide() {

//        first.gameModel.model.setCenterX(100);
//        first.gameModel.model.setCenterY(100);
//        second.gameModel.model.setCenterX(110);
//        second.gameModel.model.setCenterY(100);
//
//        first.xCoefficient=1;
//        first.yCoefficient =1;
//        second.xCoefficient =-1;
//        second.yCoefficient = -1;

        Line perpendicular = new Line(first.getPosition(),second.getPosition());
        Line firstParalel = new Line(first.getPosition(),perpendicular.getNormal());
        Line secondParalel = new Line(second.getPosition(),perpendicular.getNormal());

        Point firstEnd = first.getFuturePosition();
        Point secondEnd = second.getFuturePosition();

        Point firstPerpendicularPoint = firstParalel.getProectionPoint(firstEnd);
        Point firstParalelPoint = perpendicular.getProectionPoint(firstEnd);

        Point secondPerpendicularPoint = secondParalel.getProectionPoint(secondEnd);
        Point secondParalelPoint = perpendicular.getProectionPoint(secondEnd);

        Vector firstParalelVector = new Vector(first.getPosition(),firstParalelPoint);
        Vector firstPerpendicularVector = new Vector(first.getPosition(), firstPerpendicularPoint);

        Vector secondParalelVector = new Vector(second.getPosition(),secondParalelPoint);
        Vector secondPerpendicularVector = new Vector(second.getPosition(), secondPerpendicularPoint);

        first.changeVector(firstPerpendicularVector.addition(secondParalelVector));
        second.changeVector(secondPerpendicularVector.addition(firstParalelVector));




//
//        double xCoefficientParalelLine = -yCoefficientPerpendicularLine;
//        double yCoefficientParalelLine = xCoefficientPerpendicularLine;
//        double freeCoefficientParalelLine = first.getXCenter()*yCoefficientPerpendicularLine -
//                first.getYCenter()*xCoefficientPerpendicularLine;
//
//        double xFirstParalProection = (yCoefficientParalelLine*(yCoefficientParalelLine*
//                (first.getXCenter()+first.xCoefficient)+xCoefficientParalelLine*(first.getYCenter()+
//                first.yCoefficient))-xCoefficientParalelLine*freeCoefficientParalelLine)/(Math.pow((xCoefficientParalelLine),2)+
//                Math.pow((yCoefficientParalelLine),2));
//        double yFirstParalProection = (xCoefficientParalelLine*(-yCoefficientParalelLine*
//                (first.getXCenter()+first.xCoefficient)+xCoefficientParalelLine*(first.getYCenter()+
//                first.yCoefficient))-yCoefficientParalelLine*freeCoefficientParalelLine)/(Math.pow((xCoefficientParalelLine),2)+
//                Math.pow((yCoefficientParalelLine),2));
//        double firstParalProectionLenght = Math.sqrt(Math.pow((first.getXCenter() - xFirstParalProection),2) +
//                Math.pow((first.getYCenter() - yFirstParalProection),2));
//
//        double xFirstPerpendicularProection = (yCoefficientPerpendicularLine*(yCoefficientPerpendicularLine*
//                (first.getXCenter()+first.xCoefficient)+xCoefficientPerpendicularLine*(first.getYCenter()+
//                first.yCoefficient))-xCoefficientPerpendicularLine*freeCoefficientPerpendicularLine)/
//                (Math.pow((xCoefficientPerpendicularLine),2)+ Math.pow((yCoefficientPerpendicularLine),2));
//        double yFirstPerpendicularProection = (xCoefficientPerpendicularLine*(-yCoefficientPerpendicularLine*
//                (first.getXCenter()+first.xCoefficient)+xCoefficientPerpendicularLine*(first.getYCenter()+
//                first.yCoefficient))-yCoefficientPerpendicularLine*freeCoefficientPerpendicularLine)/
//                (Math.pow((xCoefficientPerpendicularLine),2)+ Math.pow((yCoefficientPerpendicularLine),2));
//
//        double firstPerpendicularProectionLenght = Math.sqrt(Math.pow((first.getXCenter() - xFirstPerpendicularProection),2) +
//                Math.pow((first.getYCenter() - yFirstPerpendicularProection),2));
//
//        freeCoefficientParalelLine = second.getXCenter()*yCoefficientPerpendicularLine -
//                second.getYCenter()*xCoefficientPerpendicularLine;
//
//        double xSecondParalProection = (yCoefficientParalelLine*(yCoefficientParalelLine*
//                (second.getXCenter()+second.xCoefficient)+xCoefficientParalelLine*(second.getYCenter()+
//                second.yCoefficient))-xCoefficientParalelLine*freeCoefficientParalelLine)/(Math.pow((xCoefficientParalelLine),2)+
//                Math.pow((yCoefficientParalelLine),2));
//        double ySecondParalProection = (xCoefficientParalelLine*(-yCoefficientParalelLine*
//                (second.getXCenter()+second.xCoefficient)+xCoefficientParalelLine*(second.getYCenter()+
//                second.yCoefficient))-yCoefficientParalelLine*freeCoefficientParalelLine)/(Math.pow((xCoefficientParalelLine),2)+
//                Math.pow((yCoefficientParalelLine),2));
//        double secondParalProectionLenght = Math.sqrt(Math.pow((second.getXCenter() - xSecondParalProection),2) +
//                Math.pow((second.getYCenter() - ySecondParalProection),2));
//
//        double xSecondPerpendicularProection = (yCoefficientPerpendicularLine*(yCoefficientPerpendicularLine*
//                (second.getXCenter()+second.xCoefficient)+xCoefficientPerpendicularLine*(second.getYCenter()+
//                second.yCoefficient))-xCoefficientPerpendicularLine*freeCoefficientPerpendicularLine)/
//                (Math.pow((xCoefficientPerpendicularLine),2)+ Math.pow((yCoefficientPerpendicularLine),2));
//        double ySecondPerpendicularProection = (xCoefficientPerpendicularLine*(-yCoefficientPerpendicularLine*
//                (second.getXCenter()+second.xCoefficient)+xCoefficientPerpendicularLine*(second.getYCenter()+
//                second.yCoefficient))-yCoefficientPerpendicularLine*freeCoefficientPerpendicularLine)/
//                (Math.pow((xCoefficientPerpendicularLine),2)+ Math.pow((yCoefficientPerpendicularLine),2));
//
//        double secondPerpendicularProectionLenght = Math.sqrt(Math.pow((second.getXCenter() - xSecondPerpendicularProection),2) +
//                Math.pow((second.getYCenter() - ySecondPerpendicularProection),2));
//
//        double colisionXFirstCoefficient = first.xCoefficient*firstParalProectionLenght -
//                second.xCoefficient*secondPerpendicularProectionLenght;


    }
}
