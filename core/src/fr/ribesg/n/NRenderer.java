package fr.ribesg.n;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import fr.ribesg.n.game.Renderable;

public class NRenderer {

	private final OrthographicCamera camera;
	private final FillViewport       viewport;

	private final NWorld world;

	private final SpriteBatch batch;
	private final BitmapFont  font;

	public NRenderer(final NWorld world) {
		// Initialize camera & viewport
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, N.WIDTH, N.HEIGHT);
		this.viewport = new FillViewport(N.WIDTH, N.HEIGHT, this.camera);
		this.viewport.update();

		// Attach world
		this.world = world;

		// Create SpriteBatch
		this.batch = new SpriteBatch();

		// Create a font
		this.font = new BitmapFont();
		this.font.setColor(Color.RED);
	}

	public void render() {
		// Make sure the camera is updated
		this.camera.update();

		// Clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin batch drawing
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		{
			// Draw World
			int nb = 0;
			for (final Renderable object : this.world.getObjects()) {
				if (!object.isHidden()) {
					object.render(this.batch);
					nb++;
				}
			}

			// Draw FPS (and some other data eventually)
			this.font.draw(this.batch, "FPS=" + Gdx.graphics.getFramesPerSecond(), N.WIDTH / 2 - 50, N.HEIGHT - 20);
			this.font.draw(this.batch, "RES=" + N.WIDTH + "x" + N.HEIGHT, N.WIDTH / 2 - 50, N.HEIGHT - 40);
			this.font.draw(this.batch, "ARES=" + N.ACTUAL_WIDTH + "x" + N.ACTUAL_HEIGHT, N.WIDTH / 2 - 50, N.HEIGHT - 60);
			this.font.draw(this.batch, "MAPOS=" + Gdx.input.getX() + " / " + Gdx.input.getY(), N.WIDTH / 2 - 50, N.HEIGHT - 80);
			this.font.draw(this.batch, "MPOS=" + N.inputX() + "/" + N.inputY(), N.WIDTH / 2 - 50, N.HEIGHT - 100);
			this.font.draw(this.batch, "Arrows=" + (nb - 2) + "/" + (this.world.getObjects().length - 2), N.WIDTH / 2 - 50, N.HEIGHT - 120);
		}
		this.batch.end();
		// End batch drawing
	}

	public void resize(final int width, final int height) {
		this.viewport.update(width, height);
	}

	public void dispose() {
		this.font.dispose();
		this.batch.dispose();
	}
}
