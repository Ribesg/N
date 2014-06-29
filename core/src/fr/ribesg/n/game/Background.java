package fr.ribesg.n.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background implements Renderable {

	private final Texture texture;

	public Background() {
		this.texture = new Texture("bg.png");
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public void update() {
		// NOP
	}

	@Override
	public void render(final SpriteBatch batch) {
		batch.draw(this.texture, 0, 0);
	}
}
