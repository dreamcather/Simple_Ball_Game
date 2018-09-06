package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

import java.awt.event.KeyEvent;

import static javafx.scene.input.KeyCode.*;

public class KeyModule {
    double R=1;
    double RR = 150;
    double x=1;
    double y =0;
    double curentx;
    double curenty;
    double arc=5;
    Circle ln;
    private boolean[] presed = new boolean[4];
    private int presed_form;

    KeyModule(Circle ths)
    {
        curentx = ths.getCenterX();
        curenty = ths.getCenterY();
        ln=ths;
    }

    void Collision_Wall(double _x,double _y)
    {
        double xk=0;
        double yk=0;
        if(_x<20){
            xk=-1;
            yk=0;
        }
        if(_y>380){
            xk=0;
            yk=1;
        }
        if(_x>380){
            xk=1;
            yk=0;
        }
        if(_y<20){
            xk=0;
            yk=-1;
        }
        x=x-2*xk;
        y=y-2*yk;
        double len = Math.sqrt(x*x +y*y);
        x=x/len;
        y=y/len;
    }

    public void Move(Circle ths)
    {
        double tmpx = curentx+x*R;
        double tmpy = curenty +y*R;
        ln.setCenterX(tmpx);
        ln.setCenterY(tmpy);
        curentx = tmpx;
        curenty = tmpy;
        if((curentx>400)||(curentx<50)||(curenty>390)||(curenty<50))
            Collision_Wall(curentx,curenty);
    }

    public void Show()
    {
        System.out.println();
    }
}
