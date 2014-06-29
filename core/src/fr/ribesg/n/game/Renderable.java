package fr.ribesg.n.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {

	public boolean isHidden();

	public void update();
	public void render(final SpriteBatch batch);
}
