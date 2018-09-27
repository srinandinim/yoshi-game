package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Berries extends Actor {

    //Srinandini Marpaka
    
    TextureAtlas textureAtlas;
    Animation<TextureRegion> textureRegionAnimation;
    TextureRegion textureRegion;
    Sprite sprite;
    float timeForStill = 0.0f;
    int counter = 0;

    public Berries(int num) {
        textureAtlas = new TextureAtlas(Gdx.files.internal("BerriesSpriteSheet/BerriesAtlas.atlas"));
        textureRegionAnimation = new Animation<TextureRegion>(1 / 10f, textureAtlas.getRegions());
        textureRegion = textureRegionAnimation.getKeyFrame(0, false);
        sprite = new Sprite(new Texture("berries.png"));
        sprite.setSize(40, 40);
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        counter = num;
    }

    public void draw(Batch batch, float parentAlpha) {
        timeForStill += Gdx.graphics.getDeltaTime();
        if (timeForStill > 0) {
            textureRegion = textureRegionAnimation.getKeyFrame(timeForStill, false);
            if (counter > 0) {
                setPosition();
                sprite.setRegion(textureRegion);
                sprite.draw(batch);
            }
        }

    }

    public void setPosition(){
        if (counter == 1){
            sprite.setPosition(120, 122);
        }
        if (counter == 2){
            sprite.setPosition(500, 122);
        }
        if (counter == 3){
            sprite.setPosition(740,122);
        }
    }

    public Rectangle getPlayerRectangle() {
        return sprite.getBoundingRectangle();
    }

}
