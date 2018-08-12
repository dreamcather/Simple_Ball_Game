package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.ArrayList;

public class EquationOfMotion {

    private Circle ball;
    private double a =-2;
    private double b =8;
    private double speed = 3;
    private Circle player;
    AnchorPane st;
    private ArrayList<Circle> model_list;
    Label lb;

    EquationOfMotion(Circle tmp, ArrayList<Circle> models, Circle hero, AnchorPane _st, Label _lb){
        ball = tmp;
        player=hero;
        st = _st;
        double len = Math.sqrt(a*a +b*b);
        a=a/len;
        b=b/len;
        model_list = models;
        lb = _lb;
    }

    private double R(Circle one, Circle two) {
        return Math.sqrt(Math.pow(one.getCenterX()-two.getCenterX(),2)+Math.pow(one.getCenterY()-two.getCenterY(),2));
    }

    private void Collision_test() {double x = ball.getCenterX() + speed*a;
        double y = ball.getCenterY() + speed*b;
        if((x>400)||(x<50)||(y>390)||(y<50))
            Collision_Wall(x,y);
        for (int i =0;i < model_list.size();i++)
        {
            if(!model_list.get(i).equals(ball))
            {
                if(R(ball,model_list.get(i))<20)
                {
                    Collisionn_Sphere(model_list.get(i));
                }
            }
            if(R(ball, player)<20){
                Collision_Hero();
            }
        }
    }

    private void Collision_Hero() {
        st.getChildren().remove(ball);
        lb.setText(lb.getText()+ " *");
        a=0;
        b =0;
        ball.setCenterY(-20);
        ball.setCenterX(-20);
        model_list.remove(ball);
    }

    private void Collisionn_Sphere(Circle tmp) {
        double xk = -ball.getCenterX()+tmp.getCenterX();
        double yk = -ball.getCenterY() + tmp.getCenterY();
        double len = Math.sqrt(xk*xk + yk*yk);
        xk/=len;
        yk/=len;
        a=a-2*xk;
        b=b-2*yk;
        len = Math.sqrt(a*a +b*b);
        a=a/len;
        b=b/len;

    }

    public void Move(){
        Collision_test();
        ball.setCenterX(ball.getCenterX()+speed*a);
        ball.setCenterY(ball.getCenterY()+speed*b);
    };

    private void Collision_Wall(double x,double y) {
        double xk=0;
        double yk=0;
        if(x<20){
            xk=-1;
            yk=0;
        }
        if(y>380){
            xk=0;
            yk=1;
        }
        if(x>380){
            xk=1;
            yk=0;
        }
        if(y<20){
            xk=0;
            yk=-1;
        }
        a=a-2*xk;
        b=b-2*yk;
        double len = Math.sqrt(a*a +b*b);
        a=a/len;
        b=b/len;

    }

    public void SetPar(double _a,double _b) {
        a= _a;
        b= _b;
        double len = Math.sqrt(a*a +b*b);
        a=a/len;
        b=b/len;
    }
}
