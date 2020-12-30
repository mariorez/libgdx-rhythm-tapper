package org.seariver;

import org.seariver.screen.RhythmScreen;

public class RhythmGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen(new RhythmScreen());
    }
}
