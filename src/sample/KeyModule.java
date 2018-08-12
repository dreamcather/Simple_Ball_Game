package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

import java.awt.event.KeyEvent;

import static javafx.scene.input.KeyCode.*;

public class KeyModule {
    private boolean[] presed = new boolean[4];
    private int presed_form;

    KeyModule()
    {
        for (boolean i:presed
             ) {i =false;

        }
        presed_form =0;
    }

    public void Presed(KeyCode _key)
    {
        switch (_key) {
            case LEFT:
                presed[0] =true;
                break;
            case UP:
                presed[1] = true;
                break;
            case RIGHT:
                presed[2] =true;
                break;
            case DOWN:
                presed[3] = true;
                break;
        }
    }

    public void Realesed(KeyCode _key)
    {
        switch (_key) {
            case LEFT:
                presed[0] =false;
                break;
            case UP:
                presed[1] = false;
                break;
            case RIGHT:
                presed[2] = false;
                break;
            case DOWN:
                presed[3] = false;
                break;
        }
    }

    public void Move(Circle ths)
    {
        if(presed[0]==true)
            presed_form+=1;
        if(presed[1]==true)
            presed_form+=2;
        if(presed[2]==true)
            presed_form+=4;
        if(presed[3]==true)
            presed_form+=8;

        switch (presed_form){
            case 0:
                break;
            case 1:
                ths.setCenterX(ths.getCenterX() - 5f);
                break;
            case 2:
                ths.setCenterY(ths.getCenterY() - 5f);
                break;
            case 3:
                ths.setCenterX(ths.getCenterX() - 5f);
                ths.setCenterY(ths.getCenterY() - 5f);
                break;
            case 4:
                ths.setCenterX(ths.getCenterX() + 5f);
                break;
            case 5:
                break;
            case 6:
                ths.setCenterX(ths.getCenterX() + 5f);
                ths.setCenterY(ths.getCenterY() - 5f);
                break;
            case 8:
                ths.setCenterY(ths.getCenterY() + 5f);
                break;
            case 9:
                ths.setCenterX(ths.getCenterX() - 5f);
                ths.setCenterY(ths.getCenterY() + 5f);
                break;
            case 12:
                ths.setCenterX(ths.getCenterX() + 5f);
                ths.setCenterY(ths.getCenterY() + 5f);
                break;
        }
        presed_form =0;
    }

    public void Show()
    {
        System.out.println();
    }
}
