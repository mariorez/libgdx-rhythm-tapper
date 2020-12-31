package org.seariver.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.seariver.BaseActor;
import org.seariver.BaseGame;
import org.seariver.BaseScreen;
import org.seariver.SongData;
import org.seariver.actor.FallingBox;
import org.seariver.actor.TargetBox;

import java.util.ArrayList;
import java.util.Collections;

public class RhythmScreen extends BaseScreen {

    private ArrayList<String> keyList;
    private ArrayList<Color> colorList;
    private ArrayList<TargetBox> targetList;
    private ArrayList<ArrayList<FallingBox>> fallingLists;

    private Music gameMusic;
    private SongData songData;

    // how many seconds until NoteBox reaches TargetBox
    private final float leadTime = 3;
    // advanceTimer is set to be leadTime seconds ahead of music time position
    private float advanceTimer;
    private float spawnHeight;
    private float noteSpeed;

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

        advanceTimer = 0;
        spawnHeight = 650;
        noteSpeed = (spawnHeight - targetList.get(0).getY()) / leadTime;

        TextButton startButton = new TextButton("Start", BaseGame.textButtonStyle);
        startButton.addListener((Event e) -> {
            if (!isTouchDownEvent(e)) return false;

            FileHandle dataFileHandle = Gdx.files.internal("FunkyJunky.key");
            songData = new SongData();
            songData.readFromFile(dataFileHandle);
            songData.resetIndex();

            FileHandle songFileHandle = Gdx.files.internal(songData.getSongName());
            gameMusic = Gdx.audio.newMusic(songFileHandle);
            startButton.setVisible(false);

            return true;
        });

        uiTable.add(startButton);
    }

    @Override
    public void update(float deltaTime) {

        if (songData == null)
            return;

        if (advanceTimer < leadTime && advanceTimer + deltaTime > leadTime)
            gameMusic.play();

        if (advanceTimer < leadTime)
            advanceTimer += deltaTime;
        else
            advanceTimer = leadTime + gameMusic.getPosition();

        while (!songData.isFinished() && advanceTimer >= songData.getCurrentKeyTime().getTime()) {
            String key = songData.getCurrentKeyTime().getKey();
            int i = keyList.indexOf(key);

            FallingBox fallingBox = new FallingBox(targetList.get(i).getX(), spawnHeight, mainStage);
            fallingBox.setSpeed(noteSpeed);
            fallingBox.setMotionAngle(270);
            fallingBox.setColor(colorList.get(i));

            fallingLists.get(i).add(fallingBox);

            songData.advanceIndex();
        }
    }

    public boolean keyDown(int keycode) {
        return false;
    }
}
