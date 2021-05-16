package org.ClassicGames.games.snake;

import java.io.IOException;
import java.util.ListIterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.ClassicGames.controllers.*;
import org.ClassicGames.model.User;
import org.ClassicGames.services.FileSystemService;

import static org.ClassicGames.services.RecordService.addRecord;

public class snake {
    private Stage stage;
    private Scene scene;

    public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction direction = Direction.RIGHT; // default direction that the
													// snake goes
	private boolean moved = false;
	private static boolean running = false;

    private static Timeline timeline = new Timeline();

    private int score = 0; // starting score

    private ObservableList<Node> snake; // snake list

	private Label scoreLab; // label that indicates score
	private Label gameOverScoreLab;

	private Pane rootGameOver;
	private static Pane rootPause;

    public void GameStarting(MouseEvent event) throws IOException{
		FileSystemService.loadSettingsFromFile();

        scene = new Scene(create());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        recursKey(scene);
        stage.setScene(scene);
        stage.show();
        startGame();
    }

    private Parent create() throws IOException { // main function that creates
		Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("SnakeScreen.fxml"));

		Pane root2 = (Pane) root1.lookup("#paneOut");

		Pane root = (Pane) root2.lookup("#panePlay"); // pane on which the snake
														// is going

        Canvas canvas = (Canvas) root2.lookup("#canvas"); // canvas for the net

		rootGameOver = (Pane) root2.lookup("#gameOver");

		rootGameOver.setVisible(false);
		gameOverScoreLab = (Label) root2.lookup("#gameOverScoreLabel"); // score label

		rootPause = (Pane) root2.lookup("#pause");
		rootPause.setVisible(false);

		gameOverMenuController.createGameOverMenu(rootGameOver);
		pauseMenuController.createPauseMenu(rootPause);

        // setting the net
        GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, SnakeConfiguration.getBlockSize(), SnakeConfiguration.getBlockSize());
        for (int x = 0; x <= SnakeConfiguration.getWidth(); x += SnakeConfiguration.getBlockSize()) {
			for (int y = 0; y <= SnakeConfiguration.getHeight(); y += SnakeConfiguration.getBlockSize())
				gc.strokeRect(x, y, SnakeConfiguration.getBlockSize(), SnakeConfiguration.getBlockSize());
		}

        timeline = new Timeline(); // starting animation
        root.setPrefSize(SnakeConfiguration.getWidth(), SnakeConfiguration.getHeight());
		score = 0; // setting score for 0

        Group snakeBody = new Group();
		snake = snakeBody.getChildren();

		Rectangle food = Game.newFood();

		scoreLab = (Label) root1.lookup("#score"); // score label
		scoreLab.setText("" + score);

		KeyFrame frame = new KeyFrame(Duration.seconds(1 / SnakeConfiguration.getSpeed()), event -> {
			if (!running)
				return;

			Node tail = snake.get(snake.size() - 1);
			Node head = snake.get(0);

			for(int i = snake.size() - 1; i > 0; i--){
				snake.get(i).setTranslateX(snake.get(i-1).getTranslateX());
				snake.get(i).setTranslateY(snake.get(i-1).getTranslateY());
			}

			switch (direction) {
			case UP:
				head.setTranslateX(head.getTranslateX());
				head.setTranslateY(head.getTranslateY() - SnakeConfiguration.getBlockSize());
				break;
			case DOWN:
				head.setTranslateX(head.getTranslateX());
				head.setTranslateY(head.getTranslateY() + SnakeConfiguration.getBlockSize());
				break;
			case RIGHT:
				head.setTranslateX(head.getTranslateX() + SnakeConfiguration.getBlockSize());
				head.setTranslateY(head.getTranslateY());
				break;
			case LEFT:
				head.setTranslateX(head.getTranslateX() - SnakeConfiguration.getBlockSize());
				head.setTranslateY(head.getTranslateY());
				break;
			}
			moved = true;

			for (Node rect : snake) {
				if (rect != head && head.getTranslateX() == rect.getTranslateX()
						&& head.getTranslateY() == rect.getTranslateY()) {
					try {
						stoppingGame();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			Game.tailConditions(head);

			if (Game.checkConditions(head, food)) {
				// this checks if the food is under the snake
				ListIterator<Node> it = snake.listIterator();

				while (it.hasNext()) {
					Node x = it.next();
					boolean match = x.getTranslateX() == food.getTranslateX()
							&& x.getTranslateY() == food.getTranslateY();
					if (match) {
						food.setTranslateX(Game.randXY(SnakeConfiguration.getWidth()));
						food.setTranslateY(Game.randXY(SnakeConfiguration.getHeight()));
						while (it.hasPrevious()) {
							it.previous();
						}
					}
				}

				Rectangle rect = Game.grow(tail.getTranslateX(), tail.getTranslateY());

				score += 10;
				scoreLab.setText("" + score);



				snake.add(rect);
			}

		});

        timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		root.getChildren().addAll(food, snakeBody);

        return root2;
    }

    private void stoppingGame() throws IOException { // function stopping the
														// game
		stopGame();

		scoreLab.setVisible(false);
		gameOverScoreLab.setText("Score: " + score);
		rootGameOver.setVisible(true);
	}

	private void stopGame() { // 2nd function stopping

		addRecord(User.getLoggedUserName(),score);

		running = false;
		timeline.stop();
	}

	public void pauseGame() {
		if(!running)
			return;

		rootPause.setVisible(true);
		stopGame();
	}

	public static void continueGame() {
		rootPause.setVisible(false);
		running = true;
		timeline.play();
	}

	private void startGame() { // starting the game

		direction = Direction.RIGHT;

		Rectangle head = new Rectangle(SnakeConfiguration.getBlockSize(), SnakeConfiguration.getBlockSize());

		head.setTranslateY(Game.randXY(SnakeConfiguration.getHeight())); // the random position for height

		head.setFill(Color.GREENYELLOW); // head color
		//head.getStyleClass().add("snake");

		snake.add(head);

		Rectangle rect;

		for(int i = 1; i < SnakeConfiguration.getInitialLength(); i++){
			rect = Game.grow(snake.get(0).getTranslateX(), snake.get(0).getTranslateY());
			snake.add(rect);
		}

		timeline.play();
		running = true;
	}

	public void recursKey(Scene scene) {

		scene.setOnKeyPressed(event -> {
			if (!moved)
				return;

			switch (event.getCode()) {
			case UP:
				if (direction != Direction.DOWN)
					direction = Direction.UP;

				break;
			case DOWN:
				if (direction != Direction.UP)
					direction = Direction.DOWN;
				break;
			case RIGHT:
				if (direction != Direction.LEFT)
					direction = Direction.RIGHT;
				break;
			case LEFT:
				if (direction != Direction.RIGHT)
					direction = Direction.LEFT;
				break;
			case ESCAPE:
				pauseGame();
				break;
			}

			moved = false;
		});

	}

    public void MainStartSnake(MouseEvent event) throws IOException{
        GameStarting(event);
    }
    
}
