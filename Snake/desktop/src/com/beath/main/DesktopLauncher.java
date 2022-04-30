package com.beath.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.beath.main.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Snake");
		//config.useVsync(true);
		config.setWindowedMode(600, 600);
		config.setForegroundFPS(15);
		new Lwjgl3Application(new Game(), config);
	}
}
