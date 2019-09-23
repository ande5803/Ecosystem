package dk.bundgaard.anders.ecosystem;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import dk.bundgaard.anders.ecosystem.animal.Animal;
import dk.bundgaard.anders.ecosystem.animal.Eagle;
import dk.bundgaard.anders.ecosystem.animal.Fox;
import dk.bundgaard.anders.ecosystem.animal.Mouse;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ecosystem extends Game {
	
	private static final int INITIAL_MOUSE_COUNT = 100;
	private static final int INITIAL_FOX_COUNT = 50;
	private static final int INITIAL_EAGLE_COUNT = 15;

	public List<Animal> animals;
	private static Ecosystem instance;

	private Ecosystem() {}

	public static Ecosystem getInstance() {
		if (instance == null) instance = new Ecosystem();
		return instance;
	}

	@Override
	public void create () {
		animals = new LinkedList<>();
		setScreen(new MainScreen());
		setup();
	}

	private void setup() {
		Random random = new Random();
		for (int i = 0; i < INITIAL_MOUSE_COUNT; i++) {
			int x = random.nextInt(Gdx.graphics.getWidth());
			int y = random.nextInt(Gdx.graphics.getHeight());
			Mouse mouse = new Mouse(x, y);
			mouse.setFemale(random.nextBoolean());
		}
		for (int i = 0; i < INITIAL_FOX_COUNT; i++) {
			int x = random.nextInt(Gdx.graphics.getWidth());
			int y = random.nextInt(Gdx.graphics.getHeight());
			Fox fox = new Fox(x, y);
			fox.setFemale(random.nextBoolean());
		}
		for (int i = 0; i < INITIAL_EAGLE_COUNT; i++) {
			int x = random.nextInt(Gdx.graphics.getWidth());
			int y = random.nextInt(Gdx.graphics.getHeight());
			Eagle eagle = new Eagle(x, y);
			eagle.setFemale(random.nextBoolean());
		}
	}
}
