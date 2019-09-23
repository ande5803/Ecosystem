package dk.bundgaard.anders.ecosystem;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.bundgaard.anders.ecosystem.animal.Animal;
import dk.bundgaard.anders.ecosystem.processor.CollisionProcessor;
import dk.bundgaard.anders.ecosystem.processor.LifecycleProcessor;
import dk.bundgaard.anders.ecosystem.processor.EngagementProcessor;
import dk.bundgaard.anders.ecosystem.processor.Processor;

import java.util.LinkedList;
import java.util.List;

public class MainScreen implements Screen {

    private SpriteBatch batch;
    private List<Processor> processors;

    @Override
    public void show() {
        batch = new SpriteBatch();
        processors = new LinkedList<>();
        processors.add(new LifecycleProcessor());
        processors.add(new EngagementProcessor());
        processors.add(new CollisionProcessor());
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Processor processor : processors) {
            processor.process(deltaTime);
        }

        batch.begin();
        for (Animal animal : Ecosystem.getInstance().animals) {
            animal.draw(batch);
        }
        batch.end();
    }

    @Override public void resize(int width, int height) { }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() { }

    @Override public void dispose() { }
}
