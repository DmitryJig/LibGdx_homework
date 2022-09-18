package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *  Класс анимации
 */
public class MyAnim {

    private Texture img;

    private Animation<TextureRegion> anm;

    private float time;

    public MyAnim(String name, int row, int col, float fps, Animation.PlayMode playMode) {

        time = 0;
        img = new Texture(name);
        TextureRegion reg1 = new TextureRegion(img);
        TextureRegion[][] regions = reg1.split(img.getWidth() / col, img.getHeight() / row);
        TextureRegion[] lineRegions = new TextureRegion[col * row];
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                lineRegions[cnt++] = regions[i][j];
            }
        }
        anm = new Animation<>(1 / fps, lineRegions);
        anm.setPlayMode(playMode);
    }

    public TextureRegion draw() {
        return anm.getKeyFrame(time);
    }

    public void setTime(float dt) {
        time += dt;
    }

    public void dispose() {
        this.img.dispose();
    }
}
