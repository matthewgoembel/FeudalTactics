package com.sesu8642.feudaltactics.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sesu8642.feudaltactics.ui.stages.ResizableResettableStage;

public class GameScreen implements Screen {

	private OrthographicCamera camera;
	private Viewport viewport;
	private ResizableResettableStage activeStage;

	public GameScreen(OrthographicCamera camera, Viewport viewport, ResizableResettableStage stage) {
		this.camera = camera;
		this.viewport = viewport;
		this.activeStage = stage;
	}

	@Override
	public void show() {
		activeStage.reset();
		Gdx.input.setInputProcessor(activeStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		viewport.apply();
		activeStage.draw();
		activeStage.act();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		viewport.apply();
		activeStage.updateOnResize(width, height);
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
//		dispose();
	}

	@Override
	public void dispose() {
		// note: slides can contain other stuff that should be disposed as well
		activeStage.dispose();
	}

	protected void setActiveStage(ResizableResettableStage activeStage) {
		this.activeStage = activeStage;
	}

	public ResizableResettableStage getActiveStage() {
		return activeStage;
	}

	public Viewport getViewport() {
		return viewport;
	}

}
