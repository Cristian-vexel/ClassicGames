package org.ClassicGames.games.snake;

import javafx.scene.shape.Rectangle;

public class SnakeConfiguration {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private static int initialLength = 3;
	private static double speed = 10;
	private static int greedSize = 20;
	private static int BLOCK_SIZE = WIDTH / greedSize;

	static Rectangle food;

	private static double tailX;
	private static double tailY;


	public static void setTail(double x, double y) {
		tailX = x;
		tailY = y;
	}

	public static int getInitialLength() {
		return initialLength;
	}

	public static void setInitialLength(int initialLength) {
		SnakeConfiguration.initialLength = initialLength;
	}

	public static int getGreedSize() {
		return greedSize;
	}

	public static void setGreedSize(int greedSize) {
		SnakeConfiguration.greedSize = greedSize;
		SnakeConfiguration.BLOCK_SIZE = WIDTH / greedSize;
	}

	public static void setSpeed(double speed) {
		SnakeConfiguration.speed = speed;
	}

	public static double getSpeed() {
		return  SnakeConfiguration.speed;
	}

	public static double getTailX() {
		return tailX;
	}

	public static double getTailY() {
		return tailY;
	}

	public static Rectangle getFood() {
		return food;
	}

	public static int getBlockSize() {
		return BLOCK_SIZE;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

}
