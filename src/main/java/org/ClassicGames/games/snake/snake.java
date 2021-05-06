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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class snake {
    private Stage stage;
    private Scene scene;

    public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction direction = Direction.RIGHT; // default direction that the
													// snake goes
	private boolean moved = false;
	private boolean running = false;

    private Timeline timeline = new Timeline();

    private int score = 0; // starting score
    private static double speed = 0.1; // default speed

    private ObservableList<Node> snake; // snake list

    public void GameStarting(MouseEvent event) throws IOException{
        scene = new Scene(create());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        recursKey(scene);
        stage.setScene(scene);
        stage.show();
        startGame();
    }

    private Parent create() throws IOException { // main function that creates
		Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("SnakeScreen.fxml"));

		Pane root = (Pane) root1.lookup("#panePlay"); // pane on which the snake
														// is going

        Canvas canvas = (Canvas) root.lookup("#canvas"); // canvas for the net

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

		KeyFrame frame = new KeyFrame(Duration.seconds(speed), event -> {
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
				//rect.getStyleClass().add("snake"); // setting the score if the
													//// food was eaten
				score += 10;
				//scoreLab.setText("" + score);

				snake.add(rect);
			}

		});

        timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		root.getChildren().addAll(food, snakeBody);

        return root1;
    }

    private void stoppingGame() throws IOException { // function stopping the
														// game
		stopGame();
		//theStage.setScene(menu);
		//stage = new Stage(); // new stage for pop-up window
		//stage.setResizable(false);
		//stage.setTitle("Koniec gry");

		//Parent root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Scores.fxml"));

		//Pane root1 = (Pane) root.lookup("#scores");
		//buttonOk = (Button) root.lookup("#enterButton");
		//field = (TextField) root.lookup("#inputField");

		//buttonOk.setOnAction(e -> ButtonClicked(e));

		//Scene scene = new Scene(root1);
		//scene.getStylesheets().add(css);
		//stage.setScene(scene);
		
			//stage.show();
		
	}

	private void stopGame() { // 2nd function stopping
		running = false;
		timeline.stop();
	}

	private void startGame() { // starting the game

		direction = Direction.RIGHT;

		Rectangle head = new Rectangle(SnakeConfiguration.getBlockSize(), SnakeConfiguration.getBlockSize());

		head.setTranslateY(Game.randXY(SnakeConfiguration.getHeight())); // the random position for height

		head.setFill(Color.GREENYELLOW); // head color
		//head.getStyleClass().add("snake");

		snake.add(head);

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
				stopGame();
				//theStage.setScene(menu);
				break;
			}

			moved = false;
		});

	}

    public void MainStartSnake(MouseEvent event) throws IOException{
        GameStarting(event);
    }
    
}
