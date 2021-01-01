package org.seariver.actor;

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

    public Message(float x, float y, Stage stage) {
        super(x, y, stage);

        perfect = loadTexture("perfect.png");
        great = loadTexture("great.png");
        good = loadTexture("good.png");
        almost = loadTexture("almost.png");
        miss = loadTexture("miss.png");
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
}
