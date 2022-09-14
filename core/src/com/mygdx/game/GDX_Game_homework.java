package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GDX_Game_homework extends ApplicationAdapter {
    private SpriteBatch batch;
    private MyAnim coinAnim;
    private MyAnim catAnim;

    @Override
    public void create() {
        batch = new SpriteBatch();
        coinAnim = new MyAnim("coin.png", 1, 6, 15, Animation.PlayMode.LOOP);
        catAnim = new MyAnim("sprites-cat-running.png", 2, 4, 15, Animation.PlayMode.LOOP);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        coinAnim.setTime(Gdx.graphics.getDeltaTime());
        catAnim.setTime(Gdx.graphics.getDeltaTime());

        float x = Gdx.input.getX() - catAnim.draw().getRegionWidth() / 2;
        float y = Gdx.graphics.getHeight() - (Gdx.input.getY() + catAnim.draw().getRegionHeight()/2);

        batch.begin();
        batch.draw(coinAnim.draw(), 0, 0);
        batch.draw(catAnim.draw(), x, y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        coinAnim.dispose();
        catAnim.dispose();
    }
}
