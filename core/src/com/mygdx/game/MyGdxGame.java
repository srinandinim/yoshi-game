package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Rectangle;


/*
Srinandini Marpaka
 */

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Yoshi yoshi;
    Berries berriesOne;
    Berries berriesTwo;
    Berries berriesThree;
    Rectangle rectangleYoshi;
    Rectangle rectangleBerriesOne;
    Rectangle rectangleBerriesTwo;
    Rectangle rectangleBerriesThree;
    Boolean grab;
    Boolean one = false;
    Boolean two = false;
    Boolean three = false;
    Texture texture;
    TextureRegion backgroundTexture;
    int score;

    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bgshrub.png"));
        backgroundTexture = new TextureRegion(texture, 0, 0, 800, 480);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        yoshi = new Yoshi();
        berriesOne = new Berries(1);
        berriesTwo = new Berries(2);
        berriesThree = new Berries(3);

        stage.addActor(berriesOne);
        stage.addActor(berriesTwo);
        stage.addActor(berriesThree);
        stage.addActor(yoshi);
    }

    public void render() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        grab = yoshi.getMove();
        if (grab == true) {
            rectangleYoshi = yoshi.getPlayerRectangle();
            rectangleBerriesOne = berriesOne.getPlayerRectangle();
            rectangleBerriesTwo = berriesTwo.getPlayerRectangle();
            rectangleBerriesThree = berriesThree.getPlayerRectangle();
            if (one == false && yoshi.getX() < 125 && rectangleYoshi.overlaps(rectangleBerriesOne)) {
                berriesOne.remove();
                one = true;
                score += 2;
            }
            if (two == false && yoshi.getX() < 505 && rectangleYoshi.overlaps(rectangleBerriesTwo)) {
                berriesTwo.remove();
                two = true;
                score += 2;
            }
            if (three == false && yoshi.getX() < 745 && rectangleYoshi.overlaps(rectangleBerriesThree)) {
                berriesThree.remove();
                three = true;
                score += 2;
            }
        }

        yoshi.setScore(score);
        stage.act(Gdx.graphics.getDeltaTime());

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0);
        stage.getBatch().end();

        stage.draw();
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

}

