package de.sesu8642.feudaltactics.ingame.ui;

import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.common.eventbus.EventBus;

import de.sesu8642.feudaltactics.menu.common.dagger.MenuBackgroundCamera;
import de.sesu8642.feudaltactics.menu.common.dagger.MenuBackgroundRenderer;
import de.sesu8642.feudaltactics.menu.common.dagger.MenuViewport;
import de.sesu8642.feudaltactics.menu.common.ui.ResizableResettableStage;
import de.sesu8642.feudaltactics.renderer.MapRenderer;

/**
 * {@link Stage} that displays the in-game combat log.
 */
@Singleton
public class CombatLogStage extends ResizableResettableStage {

    private Table rootTable;
    private Label combatLog;
    private MapRenderer mapRenderer;
    private Skin skin;
    private OrthographicCamera camera;

    @Inject
    public CombatLogStage(EventBus eventBus, @MenuViewport Viewport viewport, @MenuBackgroundCamera OrthographicCamera camera, 
            @MenuBackgroundRenderer MapRenderer mapRenderer, Skin skin) {
        super(viewport);
        this.camera = camera;
        this.mapRenderer = mapRenderer;
        this.skin = skin;
        initUI();
    }

    private void initUI() {
        combatLog = new Label("", skin);

        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.add(combatLog).left().bottom().pad(10).expand();

        this.addActor(rootTable);
    }

    public void setCombatLogText(Queue<String> log) {
        String newText = "";
        Queue<String> copyLog = new LinkedList<>(log);

        for (String text : copyLog) {
            newText += ("\n" + text + "\n");
        }

        combatLog.setFontScale(0.5f);
        combatLog.setText(newText);
    }


    @Override
    public void updateOnResize(int width, int height) {
        rootTable.pack();
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void draw() {
        mapRenderer.render();
        super.draw();
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
