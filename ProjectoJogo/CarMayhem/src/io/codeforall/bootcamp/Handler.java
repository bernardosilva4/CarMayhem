package io.codeforall.bootcamp;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Handler implements KeyboardHandler {

    private final Keyboard keyboard;

    private Game game;

    private final Player player;


    public Handler(Player player, Game game) {
        this.player = player;
        this.game = game;
        keyboard = new Keyboard(this);

        createKeyboardEvents();
    }

    public void createKeyboardEvents() {
        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventSpace = new KeyboardEvent();
        keyboardEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(keyboardEventSpace);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboard.addEventListener(keyboardEventDown);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.moveRight();
                break;

            case KeyboardEvent.KEY_LEFT:
                player.moveLeft();
                break;

            case KeyboardEvent.KEY_SPACE:

                game.setMainMenu(false);
                game.deleteMainMenu();

                break;

            case KeyboardEvent.KEY_UP:
                player.moveUp();
                break;

            case KeyboardEvent.KEY_DOWN:
                player.moveDown();
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}


