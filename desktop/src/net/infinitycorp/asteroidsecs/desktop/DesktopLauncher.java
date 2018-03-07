package net.infinitycorp.asteroidsecs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.infinitycorp.asteroidsecs.AsteroidsECS;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 1024;
		cfg.height = 768;
		cfg.foregroundFPS = 60;
		new LwjglApplication(new AsteroidsECS(), cfg);
	}
}
