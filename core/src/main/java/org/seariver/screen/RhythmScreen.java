package org.seariver.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.seariver.BaseActor;
import org.seariver.BaseScreen;
import org.seariver.actor.FallingBox;
import org.seariver.actor.TargetBox;

import java.util.ArrayList;
import java.util.Collections;

public class RhythmScreen extends BaseScreen {

    private ArrayList<String> keyList;
    private ArrayList<Color> colorList;
    private ArrayList<TargetBox> targetList;
    private ArrayList<ArrayList<FallingBox>> fallingLists;

    public void initialize() {

        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("space.png");
        background.setSize(800, 600);
        BaseActor.setWorldBounds(background);

        keyList = new ArrayList<>();
        String[] keyArray = {"F", "G", "H", "J"};
        Collections.addAll(keyList, keyArray);

        colorList = new ArrayList<>();
        Color[] colorArray = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
        Collections.addAll(colorList, colorArray);

        Table targetTable = new Table();
        targetTable.setFillParent(true);
        targetTable.add().colspan(4).expandY();
        targetTable.row();
        mainStage.addActor(targetTable);

        targetList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TargetBox tb = new TargetBox(0, 0, mainStage, keyList.get(i), colorList.get(i));
            targetList.add(tb);
            targetTable.add(tb).pad(32);
        }

        fallingLists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fallingLists.add(new ArrayList<>());
        }
    }

    @Override
    public void update(float deltaTime) {
    }

    public boolean keyDown(int keycode) {
        return false;
    }
}
