package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private Rectangle playerRectangle;

    private Picture picture;



    public Player(Picture picture) {
        this.picture = picture;
        playerRectangle = new Rectangle(15, 60, 80, 40);
    }


    public void fill() {
        playerRectangle.fill();
    }

    public void moveRight() {
        if((playerRectangle.getX() + playerRectangle.getWidth() + 10) <= (picture.getX() + picture.getWidth()))
            playerRectangle.translate(10,0);
    }


    public void moveLeft() {
        if((playerRectangle.getX() - 10) >= picture.getX())
            playerRectangle.translate(-10, 0);
    }
    public void moveUp() {
        if(playerRectangle.getY() - 10 >= picture.getY())
            playerRectangle.translate(0, -10);
    }

    public void moveDown() {
        if((playerRectangle.getY() + playerRectangle.getHeight() + 10) <= (picture.getY()) + picture.getHeight())
            playerRectangle.translate(0, 10);
    }


}
