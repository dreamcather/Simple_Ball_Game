package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import javafx.scene.control.Label;
import java.awt.*;
import  java.util.Random;

import java.util.ArrayList;

public class Enemy_List {
    private int kol;
    ArrayList<Circle> models = new ArrayList<Circle>();
    ArrayList<EquationOfMotion> enemy_list = new ArrayList<EquationOfMotion>();
    Circle player;
    Random rd =  new Random();
    Enemy_List(int k, AnchorPane st, Circle pl, Label _lb)
    {
        kol=k;
        player = pl;
        for(int i = 0;i<kol;i++) {
            double x =  rd.nextInt(360)+ 20;
            double y = rd.nextInt(360)+ 20;
            Circle cr = new Circle(x,y,10,Color.RED);
            models.add(cr);
            st.getChildren().add(cr);
            enemy_list.add(new EquationOfMotion(cr,models,player,st,_lb));
        }
    }
    public void Move()
    {
        for(int i = 0;i<enemy_list.size();i++) {
            enemy_list.get(i).Move();
        }
    }
}
