package fr.ribesg.n;
import com.badlogic.gdx.math.Vector2;
import fr.ribesg.n.game.Arrow;
import fr.ribesg.n.game.Background;
import fr.ribesg.n.game.Cannon;
import fr.ribesg.n.game.Renderable;

public class NWorld {

	private static final int MAX_ARROWS = 256;

	private final Background bg;
	private final Cannon     cannon;
	private final Arrow[]    arrows;

	private final Renderable[] objects;

	public NWorld() {
		this.bg = new Background();

		this.cannon = new Cannon(N.WIDTH / 2, N.HEIGHT / 2);

		this.arrows = new Arrow[MAX_ARROWS];
		for (int i = 0; i < MAX_ARROWS; i++) {
			this.arrows[i] = new Arrow();
		}

		this.objects = new Renderable[2 + MAX_ARROWS];
		this.objects[0] = this.bg;
		this.objects[MAX_ARROWS + 1] = this.cannon;
		System.arraycopy(this.arrows, 0, this.objects, 1, this.arrows.length);
	}

	public void update() {
		for (final Renderable object : this.objects) {
			object.update();
		}
	}

	public void fireArrow(final int amount) {
		for (int num = 0; num < amount; num++) {
			int i = 0;
			while (i < MAX_ARROWS && !this.arrows[i].isHidden()) {
				i++;
			}
			if (i < MAX_ARROWS) {
				final Arrow a = this.arrows[i];
				final Vector2 av = new Vector2(this.cannon.vx, this.cannon.vy);
				av.rotate((num - amount / 2f + 0.5f) * ((N.RAND.nextFloat() - 0.5f) * 10f + 10f) / amount);
				a.reset(this.cannon.x + this.cannon.vx, this.cannon.y - 16 + this.cannon.vy, av.x, av.y);
			}
		}
	}

	public Renderable[] getObjects() {
		return this.objects;
	}
}
