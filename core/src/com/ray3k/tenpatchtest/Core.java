package com.ray3k.tenpatchtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends ApplicationAdapter {
	private Stage stage;
	private Skin skin;
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("test-export.json"));
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		Table root = new Table();
		root.setFillParent(true);
		root.setBackground(skin.getDrawable("brick-wall-ten"));
		stage.addActor(root);
		
		root.defaults().growX().space(20);
		Button button = new Button(skin);
		root.add(button);
		
		root.row();
		final ProgressBar progressBar = new ProgressBar(0, 1, .01f, false, skin);
		root.add(progressBar);
		stage.addListener(new InputListener() {
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				progressBar.setValue(x / stage.getWidth());
				return false;
			}
		});
		
		root.row();
		Table table = new Table();
		table.add().height(500);
		ScrollPane scrollPane = new ScrollPane(table, skin);
		scrollPane.setFadeScrollBars(false);
		root.add(scrollPane).grow();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}
}
