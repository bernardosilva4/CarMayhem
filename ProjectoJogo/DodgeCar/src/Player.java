import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private Rectangle playerRectangle;



    public Player() {
        playerRectangle = new Rectangle(15, 60, 80, 40);
    }


    public void fill() {
        playerRectangle.fill();
    }

    public void moveRight() {
        if(playerRectangle.getX() == .getMaxX()) {
            playerRectangle.translate(0,0);

        }
        playerRectangle.translate(10, 0);
    }


    public void moveLeft() {
        playerRectangle.translate(-10, 0);
    }
    public void moveUp() {
        playerRectangle.translate(0, -10);
    }

    public void moveDown() {
        playerRectangle.translate(0, 10);
    }


}
