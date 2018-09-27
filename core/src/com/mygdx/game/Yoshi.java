package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Yoshi extends Actor implements GestureDetector.GestureListener{

    //Srinandini Marpaka

    TextureAtlas textureAtlasMove;
    TextureAtlas textureAtlasGrab;
    Animation<TextureRegion> runAnimation;
    Animation<TextureRegion> grabAnimation;
    TextureRegion textureRegionRun;
    TextureRegion textureRegionGrab;
    float timeforRun = 0.0f;
    float timeforGrab = 0.0f;
    Sprite sprite;
    boolean move = false;
    BitmapFont font = new BitmapFont();

    int yPosition = 100;
    int score;

    public Yoshi() {
        textureAtlasMove = new TextureAtlas(Gdx.files.internal("YoshiSpriteSheet/YoshiLargeAtlas.atlas"));
        textureAtlasGrab = new TextureAtlas(Gdx.files.internal("TongueSpriteSheet/YoshiTongueAtlas.atlas"));
        runAnimation = new Animation(1 / 15f, textureAtlasMove.getRegions()); //1/20 will display 20 frames in one second
        grabAnimation = new Animation(1 / 8f, textureAtlasGrab.getRegions());
        textureRegionRun = runAnimation.getKeyFrame(0, true);
        textureRegionGrab = grabAnimation.getKeyFrame(0, false);
        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setSize(100, 100);
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        GestureDetector gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    public void act (float delta){
        super.act(delta);
        if (move == false && getX()<700){
            setX(getX()+1);
        }

    }

    public void draw (Batch batch, float parentAlpha){
        timeforRun += Gdx.graphics.getDeltaTime(); //Keeps track of the amount of total time that has passed
        timeforGrab += Gdx.graphics.getDeltaTime();
        if (move){
            textureRegionGrab = grabAnimation.getKeyFrame(timeforGrab, true);
            sprite.setRegion(textureRegionGrab);
            sprite.draw(batch);
            timeforRun = 0;
        } else {
            textureRegionRun = runAnimation.getKeyFrame(timeforRun, true);
            sprite.setRegion(textureRegionRun);
            sprite.draw(batch);
            timeforGrab = 0;
        }
        font.getData().setScale(3,3);
        font.draw(batch, "Score: " + getScore(), 80, 430);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public boolean getMove(){
        return move;
    }

    public void positionChanged(){
        sprite.setPosition(getX(), yPosition);
        super.positionChanged();
    }

    public Rectangle getPlayerRectangle(){
        return sprite.getBoundingRectangle();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        move = !move;
        return true;

    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
