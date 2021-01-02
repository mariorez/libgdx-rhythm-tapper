package org.seariver.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.seariver.BaseActor;

public class Message extends BaseActor {

    public Animation perfect;
    public Animation great;
    public Animation good;
    public Animation almost;
    public Animation miss;
    private Animation countdown3;
    private Animation countdown2;
    private Animation countdown1;
    private Animation countdownGo;
    private Animation congratulations;

    private Sound blip;
    private Sound tone;

    public Message(float x, float y, Stage stage) {

        super(x, y, stage);

        perfect = loadTexture("perfect.png");
        great = loadTexture("great.png");
        good = loadTexture("good.png");
        almost = loadTexture("almost.png");
        miss = loadTexture("miss.png");
        countdown3 = loadTexture("countdown-3.png");
        countdown2 = loadTexture("countdown-2.png");
        countdown1 = loadTexture("countdown-1.png");
        countdownGo = loadTexture("countdown-go.png");
        congratulations = loadTexture("congratulations.png");

        blip = Gdx.audio.newSound(Gdx.files.internal("blip.wav"));
        tone = Gdx.audio.newSound(Gdx.files.internal("tone.wav"));
    }

    public void pulseFade() {
        setOpacity(1);
        clearActions();
        Action pulseFade = Actions.sequence(
                Actions.scaleTo(1.1f, 1.1f, 0.05f),
                Actions.scaleTo(1.0f, 1.0f, 0.05f),
                Actions.delay(1),
                Actions.fadeOut(0.5f));
        addAction(pulseFade);
    }

    public void displayCountdown() {
        Action countdown = Actions.sequence(
                Actions.run(() -> setAnimation(countdown3)),
                Actions.run(() -> blip.play()),
                Actions.alpha(1),
                Actions.scaleTo(1.1f, 1.1f, 0.05f), Actions.scaleTo(1.0f, 1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run(() -> setAnimation(countdown2)),
                Actions.run(() -> blip.play()),
                Actions.alpha(1),
                Actions.scaleTo(1.1f, 1.1f, 0.05f), Actions.scaleTo(1.0f, 1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run(() -> setAnimation(countdown1)),
                Actions.run(() -> blip.play()),
                Actions.alpha(1),
                Actions.scaleTo(1.1f, 1.1f, 0.05f), Actions.scaleTo(1.0f, 1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run(() -> setAnimation(countdownGo)),
                Actions.run(() -> tone.play()),
                Actions.alpha(1),
                Actions.fadeOut(1));

        addAction(countdown);
    }

    public void displayCongratulations() {
        setOpacity(0);
        setAnimation(congratulations);
        setScale(2);
        addAction(Actions.fadeIn(4));
    }
}
