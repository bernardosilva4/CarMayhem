package io.codeforall.bootcamp;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Car {
    private final Picture picture;
    private int speed = -10;

    public Car() {

        int chosenY = (int) Math.round(Math.random() * 4);

        //Different conditions to spawn cars randomly on the 4 road rows
        if (chosenY == 0) {
            int y1 = 113;
            picture = new Picture(450, y1, "resources/images/blue race car copy.png");
        } else if (chosenY == 1) {
            int y2 = 150;
            picture = new Picture(450, y2, "resources/images/blue race car copy.png");
        } else if (chosenY == 2) {
            int y3 = 187;
            picture = new Picture(450, y3, "resources/images/blue race car copy.png");
        } else if (chosenY == 3){
            int y4 = 224;
            picture = new Picture(450, y4, "resources/images/blue race car copy.png");
        }else{
            int y5 = 264;
            picture = new Picture(450, y5,"resources/images/blue race car copy.png");
        }
    }

    public void fill() {
        picture.draw();
    }

    public void move() {
        picture.translate(speed, 0);
    }

    public Picture getCarPicture() {
        return picture;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

