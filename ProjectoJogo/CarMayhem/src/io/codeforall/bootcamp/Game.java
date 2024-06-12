package io.codeforall.bootcamp;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.io.IOException;

public class Game {


    private final Picture mainMenuImg = new Picture(10,10,"resources/images/MainMenuResize.png");

    private final Picture gameOverImg = new Picture(10,10,"resources/images/GameOverResize2.png");

    private final Picture life1 = new Picture(20,20,"resources/images/life.png");
    private final Picture life2 = new Picture(60,20,"resources/images/life.png");
    private final Picture life3 = new Picture(100,20,"resources/images/life.png");
    private final Picture life4 = new Picture(140,20,"resources/images/life.png");
    private final Picture life5 = new Picture(180,20,"resources/images/life.png");

    private final Picture pictureGarden = new Picture(10,10,"resources/images/background1.jpg");

    private final Picture picture = new Picture(10, pictureGarden.getHeight() + pictureGarden.getY(), "resources/images/big_road.png");

    private final Picture playerPicture = new Picture(10, (picture.getY() + picture.getHeight() + 10)/2, "resources/images/race car red copy.png");

    private final Picture pictureGarden2 = new Picture(10, picture.getHeight()+ picture.getY(),"resources/images/background2.jpg");

    private final Picture lifeRoad = new Picture(pictureGarden.getX() + pictureGarden.getWidth()-125,113,"resources/images/life.png");




    private final Player player = new Player(playerPicture, picture);

    public static final int NUM_CARS = 500;

    private Car[] cars;

    private Sound sound;

    private Sound soundMainMenu;

    private Sound soundBackgroundMusic;
    private boolean mainMenu = true;
    private int carCreationCounter = 0;
    private int carCreationInterval = 20;
    private int currentCarIndex = 0;


    private int lives = 4;
    private int score = 0;

    Text text = new Text((pictureGarden.getX()+ pictureGarden.getWidth())-80, 40, Integer.toString(score));
    private final Picture scoreFont = new Picture((text.getX()+text.getWidth()-170),30,"resources/images/score1.png");


    //Game start
    public void init() throws InterruptedException, IOException {
        cars = new Car[NUM_CARS];
        sound = new Sound();
        soundMainMenu = new Sound();
        soundBackgroundMusic = new Sound();

        //sound.playSound();
        new Handler(player,this);
        mainMenuImg.draw();
        soundMainMenu.playSound("resources/sounds/MainMenuSong.wav");
        soundMainMenu.loopSound(3);


        while(mainMenu){


            Thread.sleep(200);

        }
        if(!getMainMenu()) {

            soundMainMenu.stopSound();
            soundBackgroundMusic.playSound("resources/sounds/background_music.wav");
            Thread.sleep(200);
            drawField();
            scoreFont.draw();
            text.grow(20, 40);
            text.draw();
            player.fill();
            startGameLoop();

        }
    }

    //GameLoop
    private void startGameLoop() throws InterruptedException, IOException {
        while (lives > 0) {
            //Condition to create cars with an Interval
            if (carCreationCounter % carCreationInterval == 0 && currentCarIndex < NUM_CARS) {
                cars[currentCarIndex] = CarFactory.getNewCar();
                cars[currentCarIndex].fill();
                currentCarIndex++;
            }

            for (int i = 0; i < currentCarIndex; i++) {

                if(cars[i] == null)
                    continue;

                //Condition if any cars get to the end of the road
                if (cars[i].getCarPicture().getX() <= 0) {
                    cars[i].getCarPicture().delete();
                    cars[i] = null;
                    lives--;
                    lifeImgCounter(lives);

                        //GameOver
                        if (lives == 0) {
                            soundBackgroundMusic.stopSound();
                            sound.playSound("resources/sounds/GameOver.wav");
                            gameOverImg.draw();
                            Thread.sleep(5500);
                            System.exit(0);
                        }
                    continue;

                }
                scoreEvents(score, i);
                cars[i].move();

                //Condition if playerCar collides with the star that spawns on the road incrementing the variable lives
                if(isCollisionLife(player,lifeRoad)){
                    sound.playSound("resources/sounds/life_sound.wav");
                    lifeRoad.delete();
                    lifeRoad.translate(0,-200);
                    lives++;
                    lifeImgVerifier(lives);
                }

                //Collision checker for playerCar and cars printing a new score everytime it happens
                if(isCollision(player, cars[i])) {
                    carExplosion(cars, i);
                    sound.playSound("resources/sounds/explosion_sound.wav");
                    cars[i].getCarPicture().delete();
                    cars[i] = null;
                    score += 50;
                    text.delete();
                    Text text = new Text((pictureGarden.getX() + pictureGarden.getWidth()) - 130, 40, Integer.toString(score));
                    this.text = text;
                    text.grow(20, 40);
                    text.draw();
                }


            }

            carCreationCounter++;
            //Delay to control the speed of the game loop
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Method to draw most of the game images, created to facilitate reading in the main game loop
    public void drawField(){
        pictureGarden.draw();
        picture.draw();
        pictureGarden2.draw();
        life1.draw();
        life2.draw();
        life3.draw();
        life4.draw();
    }

    public void setCarCreationInterval(int creationInterval){
        this.carCreationInterval = creationInterval;
    }

    //Collision method (crazy mental gymnastics)
    public boolean isCollision(Player p, Car c) {
        int playerX = p.getPlayerPicture().getX();
        int playerY = p.getPlayerPicture().getY();
        int playerWidth = p.getPlayerPicture().getWidth();
        int playerHeight = p.getPlayerPicture().getHeight();
        int carX = c.getCarPicture().getX();
        int carY = c.getCarPicture().getY();
        int carWidth = c.getCarPicture().getWidth();
        int carHeight = c.getCarPicture().getHeight();
        return playerX < carX + carWidth &&
                playerX + playerWidth > carX &&
                playerY < carY + carHeight &&
                playerY + playerHeight > carY;
    }

    //Same collision method but with Picture as an argument instead of Car
    public boolean isCollisionLife(Player p, Picture pic) {
        int playerX = p.getPlayerPicture().getX();
        int playerY = p.getPlayerPicture().getY();
        int playerWidth = p.getPlayerPicture().getWidth();
        int playerHeight = p.getPlayerPicture().getHeight();
        int picX = pic.getX();
        int picY = pic.getY();
        int picWidth = pic.getWidth();
        int picHeight = pic.getHeight();
        return playerX < picX + picWidth &&
                playerX + playerWidth > picX &&
                playerY < picY + picHeight &&
                playerY + playerHeight > picY;
    }

    //Method that deletes life images depending on the variable decremental
    public void lifeImgCounter(Integer lives){
        if (lives == 0){
            life1.delete();
        }
        if(lives == 1){
            life2.delete();
        }
        if(lives == 2){
            life3.delete();
        }
        if(lives == 3) {
            life4.delete();
        }
        if(lives == 4) {
            life5.delete();
        }
    }

    //Method that draws life images depending on the variable incrementation
    public void lifeImgVerifier(Integer lives){
        if (lives == 1){
            life1.draw();
        }
        if(lives == 2){
            life2.draw();
        }
        if(lives == 3){
            life3.draw();
        }
        if(lives == 4) {
            life4.draw();
        }
        if(lives == 5) {
            life5.draw();
        }
    }

    //Method for the image explosion, created to facilitate reading in the main game loop
    public void carExplosion(Car[] cars, Integer i) throws InterruptedException {
        Picture explosion = new Picture(cars[i].getCarPicture().getX(), cars[i].getCarPicture().getY() - 20, "resources/images/explosion.png");
        explosion.draw();
        Thread.sleep(150);
        explosion.delete();
    }

    //Method for the different changes in speed, car spawn timers and additional life spawn
    public void scoreEvents(int score, Integer i){
        if (score >= 500 && score < 2000){
            cars[i].setSpeed(-20);
        }
        if(score > 1500){
            setCarCreationInterval(10);
        }
        if (score >= 2000 && score < 4000){
            cars[i].setSpeed(-30);
        }
        if (score == 1750){
            lifeRoad.draw();
        }
        if(score == 2750){
            lifeRoad.delete();
            lifeRoad.translate(0,-200);
        }
        if (score >= 4000 && score <= 6000){
            cars[i].setSpeed(-40);
        }
        if (score >= 6000){
            cars[i].setSpeed(-60);
        }
    }
    public boolean getMainMenu(){
       return mainMenu;
    }

    public void setMainMenu(boolean menu){
        this.mainMenu = menu;
    }

    public void deleteMainMenu(){
        mainMenuImg.delete();
    }

    public void deleteGameOverMenu(){
        gameOverImg.delete();
    }
}

