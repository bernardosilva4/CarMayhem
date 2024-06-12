package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private final Picture playerPicture;

    private final Picture backgroundPicture;


    public Player(Picture playerPicture, Picture backgroundPicture) {
        this.playerPicture = playerPicture;
        this.backgroundPicture = backgroundPicture;

    }

    public void fill() {
        playerPicture.draw();
    }

    //Move methods with conditions to limit their movement in relation to the road image
    public void moveRight() {
        if ((playerPicture.getX() + playerPicture.getWidth() + 15) <= (backgroundPicture.getX() + backgroundPicture.getWidth())) {
            playerPicture.translate(15, 0);
        }
    }

    public void moveLeft() {
        if ((playerPicture.getX() - 10) >= backgroundPicture.getX()) {
            playerPicture.translate(-15, 0);
        }
    }

    public void moveUp() {
        if (playerPicture.getY() - 10 >= backgroundPicture.getY()) {
            playerPicture.translate(0, -15);
        }
    }

    public void moveDown() {
        if ((playerPicture.getY() + playerPicture.getHeight() + 10) <= (backgroundPicture.getY()) + backgroundPicture.getHeight()) {
            playerPicture.translate(0, 15);
        }
    }

    public Picture getPlayerPicture() {
        return playerPicture;
    }
}
