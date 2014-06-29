package fr.ribesg.n.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ribesg.n.N;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) N.WIDTH;
		config.height = (int) N.HEIGHT;
		new LwjglApplication(new N(), config);
	}
}
