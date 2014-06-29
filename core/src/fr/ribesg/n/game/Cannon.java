package fr.ribesg.n.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ribesg.n.N;

public class Cannon implements Renderable {

	private static Texture texture;

	/**
	 * Creates necessary resources.
	 */
	public static void create() {
		Cannon.texture = new Texture("sprites/cannon.png");
	}

	/**
	 * Frees allocated resources.
	 */
	public static void dispose() {
		Cannon.texture.dispose();
	}

	public float x, y, vx, vy, angle;
	private Sprite sprite;

	public Cannon(final float x, final float y) {
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.angle = 0;

		this.sprite = new Sprite(Cannon.texture);
		this.sprite.setOrigin(15, 15);
		this.sprite.setCenter(this.x, this.y);
	}

	/**
	 * Updates the cannon angle.
	 */
	public void update() {
		final Vector2 v = new Vector2(N.inputX() - this.x, (this.y + 16) - N.inputY());
		this.angle = (float) Math.toDegrees(Math.atan2(v.y, v.x)) - 90;
		this.sprite.setRotation(this.angle);

		v.nor().scl(32);
		this.vx = v.x;
		this.vy = v.y;
	}

	/**
	 * Renders the cannon on the provided SpriteBatch.
	 *
	 * @param batch the SpriteBatch on which our cannon will be rendered
	 */
	public void render(final SpriteBatch batch) {
		this.sprite.draw(batch);
	}

	@Override
	public boolean isHidden() {
		return false;
	}
} 
