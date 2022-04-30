package com.beath.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;



public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle head;
	private ShapeRenderer sr;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 600;
	private final int MOVEMENT_SPEED = 20;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		sr = new ShapeRenderer();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
		head = new Rectangle();
		head.width = head.height = 20;
		head.x = WINDOW_WIDTH / 2 - 20;
		head.y = WINDOW_HEIGHT / 2 - 20;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.end();

		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(head.x, head.y, head.width, head.height);
		sr.setColor(Color.DARK_GRAY);
		for (int i = 1; i < WINDOW_WIDTH / 20; i++)
			sr.rect(i * 20, 0, 1, WINDOW_HEIGHT);

		for (int i = 1; i < WINDOW_HEIGHT / 20; i++)
			sr.rect(0, i * 20, WINDOW_WIDTH, 1);
		sr.end();

		setDirection();
		updateDirection();
		detectCollision();





	}

	public void setDirection() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (right) return;
			left = true;
			right = false;
			up = false;
			down = false;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if  (left) return;
			left = false;
			right = true;
			up = false;
			down = false;
		} else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (down) return;
			left = false;
			right = false;
			up = true;
			down = false;
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (up) return;
			left = false;
			right = false;
			up = false;
			down = true;
		}
	}

	public void updateDirection() {
		if (left) head.x -= MOVEMENT_SPEED;
		else if (right) head.x += MOVEMENT_SPEED;
		else if (up) head.y += MOVEMENT_SPEED;
		else if (down) head.y -= MOVEMENT_SPEED;
	}

	public void detectCollision() {
		if (head.x < 0) head.x = 0;
		else if (head.x > WINDOW_WIDTH - 20) head.x = WINDOW_WIDTH - 20;
		else if (head.y < 0) head.y = 0;
		else if (head.y > WINDOW_HEIGHT - 20) head.y = WINDOW_HEIGHT - 20;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		sr.dispose();
	}
}
