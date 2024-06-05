import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game
{
    Rectangle road = new Rectangle(10, 10, 533, 200);

    Player player = new Player();

    //Car car = new Car();


    public static final int NUM_CARS = 4;

    /**
     * Container of Cars
     */
    private Car[] cars;

    private int carCreationCounter = 0;
    private int carCreationInterval =50;
    private int currentCarIndex = 0;

    public void init() {
        cars = new Car[NUM_CARS];

        new Handler(player);
        road.draw();
        player.fill();
        startGameLoop();
    }

    private void startGameLoop() {
        while (true) {
            if(carCreationCounter % carCreationInterval == 0 && currentCarIndex < NUM_CARS) {
                cars[currentCarIndex] = CarFactory.getNewCar();
                cars[currentCarIndex].fill();
                currentCarIndex++;
            }

            for (int i = 0; i < currentCarIndex; i++) {
                if(cars[i].getCarRectangle().getX() == 0){
                    cars[i].getCarRectangle().delete();
                }
                cars[i].move();
            }
            carCreationCounter++;

            // Add a delay to control the speed of the game loop
            try {
                Thread.sleep(100); // Adjust the delay as needed (in milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
