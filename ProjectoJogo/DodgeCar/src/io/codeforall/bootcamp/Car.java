package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Car {


    private int speed = -10;

    private Rectangle carRectangle;

    private final int y1 = 65;

    private final int y2 = 115;

    private int previousY = -1;

    public Car(){
        //y=65 ou y=115
        int chosenY;
        if(previousY == -1){
            chosenY = (int) Math.round(Math.random());
        } else {
            if (previousY == 0)
                chosenY = 1;
            else
                chosenY = 0;
        }
        previousY = chosenY;

        if(chosenY == 0) {
            carRectangle = new Rectangle(450, y1, 80, 40);
        }
        else {
            carRectangle = new Rectangle(450, y2, 80, 40);
        }

    }

    public void fill(){
        carRectangle.fill();
    }

    public void move(){
        carRectangle.translate(speed, 0);
    }

    public Rectangle getCarRectangle(){
        return carRectangle;
    }


}
