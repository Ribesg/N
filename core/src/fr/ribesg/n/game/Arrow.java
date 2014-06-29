package fr.ribesg.n.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import fr.ribesg.n.N;

public class Arrow implements Renderable {

	private static final float ARROW_SPEED   = 0.35f;
	private static final float ARROW_GRAVITY = -0.5f;

	private static Texture  texture;
	private static Sprite[] sprites;
	private static float    size;

	/**
	 * Creates necessary resources.
	 */
	public static void create() {
		Arrow.texture = new Texture("sprites/arrow.png");
		Arrow.sprites = new Sprite[5];
		for (int i = 0; i < Arrow.sprites.length; i++) {
			Arrow.sprites[i] = new Sprite(Arrow.texture, i * 15, 0, 15, 32);
			Arrow.sprites[i].setOrigin(7, 16);
		}
		Arrow.size = Math.max(Arrow.sprites[0].getWidth(), Arrow.sprites[0].getHeight()) + 10;
	}

	/**
	 * Frees allocated resources.
	 */
	public static void dispose() {
		Arrow.texture.dispose();
	}

	private float x, y, vx, vy, angle;
	private boolean hidden, frozen;
	private long freezeTime;
	private int  currentSprite;

	public Arrow() {
		this.hidden = true;
	}

	public void reset(final float x, final float y, final float vx, final float vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.angle = MathUtils.radiansToDegrees * (float) Math.atan2(this.vy, this.vx) - 90;
		this.hidden = false;
		this.frozen = false;
		this.freezeTime = 0;
		this.currentSprite = 0;
	}

	@Override
	public void update() {
		if (!this.hidden) {
			if (this.frozen) {
				if (this.currentSprite > 0 && this.currentSprite < Arrow.sprites.length - 1) {
					this.currentSprite++;
					this.vy += ARROW_GRAVITY;
					this.x += ARROW_SPEED * vx;
					this.y += ARROW_SPEED * vy;
				}
				if (this.freezeTime + 2500 < System.currentTimeMillis()) {
					this.hidden = true;
				}
			} else {
				if (this.x > N.WIDTH + Arrow.size || this.x < 0 - Arrow.size) {
					this.hidden = true;
				} else if (this.y < 3 * Arrow.size / (N.RAND.nextFloat() * 2 + 1)) {
					this.frozen = true;
					this.currentSprite = 1;
					this.freezeTime = System.currentTimeMillis();
				} else {
					this.vy += ARROW_GRAVITY;
					this.x += ARROW_SPEED * vx;
					this.y += ARROW_SPEED * vy;
					this.angle = MathUtils.radiansToDegrees * (float) Math.atan2(this.vy, this.vx) - 90;
				}
			}
		}
	}

	@Override
	public void render(final SpriteBatch batch) {
		if (!this.hidden) {
			Arrow.sprites[this.currentSprite].setCenter(x, y);
			Arrow.sprites[this.currentSprite].setRotation(angle);
			Arrow.sprites[this.currentSprite].draw(batch);
		}
	}

	@Override
	public boolean isHidden() {
		return this.hidden;
	}
}
