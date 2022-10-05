package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Класс игры
 */
public class GDX_Game_homework extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private MyAtlasAnim walk, stand, tmpA;
    private Music music;
    private Sound sound;
    private MyInputProcessor myInputProcessor;
    private float x, y;
    int dir = 0, step = 1;
    private Rectangle rectangle, window;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();
        rectangle = new Rectangle();
        window = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        myInputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(myInputProcessor);

        music = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
        music.setVolume(0.05f);
        music.setLooping(true);
        music.play();

        sound = Gdx.audio.newSound(Gdx.files.internal("sound_1.mp3"));

        batch = new SpriteBatch();
        walk = new MyAtlasAnim("atlas/maAtlas", "walk", 7, Animation.PlayMode.LOOP);
        stand = new MyAtlasAnim("atlas/maAtlas", "wide", 7, Animation.PlayMode.LOOP);

    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        tmpA = stand;
        dir = 0;

//        float x = Gdx.input.getX() - catAnim.draw().getRegionWidth() / 2;
//        float y = Gdx.graphics.getHeight() - (Gdx.input.getY() + catAnim.draw().getRegionHeight() / 2);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            sound.stop();
            sound.play();
        }
        if (myInputProcessor.isLeftPressed()) {
            dir = -1;
            tmpA = walk;
        }
        if (myInputProcessor.isRightPressed()) {
            dir = 1;
            tmpA = walk;
        }
        if (myInputProcessor.isUpPressed()) y++;
        if (myInputProcessor.isDownPressed()) y--;
        if (myInputProcessor.isSpacePressed()) {
            x = Gdx.graphics.getWidth() / 2;
            y = Gdx.graphics.getHeight() / 2;
            sound.stop();
            sound.play();
        }

        if (dir == -1) x -= step;
        if (dir == 1) x += step;

        TextureRegion tmp = tmpA.draw();
        if (!tmpA.draw().isFlipX() && dir == -1) tmpA.draw().flip(true, false);
        if (tmpA.draw().isFlipX() && dir == 1) tmpA.draw().flip(true, false);

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = tmp.getRegionWidth();
        rectangle.height = tmp.getRegionHeight();

        tmpA.setTime(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(tmpA.draw(), x, y);
        batch.end();

        // отрисовываем квадратик вокруг персонажа
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
        if (!window.overlaps(rectangle)) { // если все тело вышло за пределы границы окна
            Gdx.graphics.setTitle("Out");
        }
//        if (!window.contains(rectangle)) Gdx.graphics.setTitle("Out"); // если выступили за границу окна
        else Gdx.graphics.setTitle("In");
    }

    @Override
    public void dispose() {
        batch.dispose();
        walk.dispose();
        stand.dispose();
        tmpA.dispose();
        music.dispose();
        shapeRenderer.dispose();
    }
}
