package fr.ribesg.n;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import fr.ribesg.n.game.Arrow;
import fr.ribesg.n.game.Cannon;

import java.util.Random;

public class N extends ApplicationAdapter {

	public static Random RAND = new Random();

	public static float WIDTH  = 1280;
	public static float HEIGHT = 720;

	public static float ACTUAL_WIDTH  = 1280;
	public static float ACTUAL_HEIGHT = 720;

	public static float inputX() {
		final float x = Gdx.input.getX();
		if (WIDTH > ACTUAL_WIDTH) {
			return (WIDTH - ACTUAL_WIDTH) / 2 + x;
		} else {
			return x * WIDTH / ACTUAL_WIDTH;
		}
	}

	public static float inputY() {
		final float y = Gdx.input.getY();
		if (HEIGHT > ACTUAL_HEIGHT) {
			return (HEIGHT - ACTUAL_HEIGHT) / 2 + y;
		} else {
			return y * HEIGHT / ACTUAL_HEIGHT;
		}
	}

	private NWorld    world;
	private NRenderer renderer;

	private boolean justFired;

	/**
	 * Creates our app.
	 */
	@Override
	public void create() {
		// Load entities resources
		Cannon.create();
		Arrow.create();

		this.world = new NWorld();
		this.renderer = new NRenderer(this.world);

		this.justFired = false;
	}

	/**
	 * Renders our world to the screen.
	 */
	@Override
	public void render() {
		if (!this.justFired) {
			if (Gdx.input.isTouched()) {
				this.justFired = true;
				this.world.fireArrow(N.RAND.nextInt(4) + 1);
			}
		} else if (!Gdx.input.isTouched()) {
			this.justFired = false;
		}
		this.renderer.render();
		this.world.update();
	}

	/**
	 * Handles screen resize.
	 *
	 * @param width  new screen width
	 * @param height new screen height
	 */
	@Override
	public void resize(final int width, final int height) {
		N.ACTUAL_WIDTH = width;
		N.ACTUAL_HEIGHT = height;
		this.renderer.resize(width, height);
	}

	/**
	 * Frees allocated resources.
	 */
	@Override
	public void dispose() {
		this.renderer.dispose();
		Arrow.dispose();
		Cannon.dispose();
	}
}
