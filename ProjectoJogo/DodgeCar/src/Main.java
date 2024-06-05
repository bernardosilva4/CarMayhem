import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        Picture picture = new Picture(10, 10, "resources/empty-road.jpeg");
        picture.draw();

        game.init();
    }
}