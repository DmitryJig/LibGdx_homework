package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class MyInputProcessor implements InputProcessor {

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean spacePressed;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) leftPressed = true;
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) rightPressed = true;
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) upPressed = true;
        if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) downPressed = true;
        if (keycode == Input.Keys.SPACE) spacePressed = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) leftPressed = false;
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) rightPressed = false;
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) upPressed = false;
        if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) downPressed = false;
        if (keycode == Input.Keys.SPACE) spacePressed = false;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }
}
