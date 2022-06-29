package org.seariver;

import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public class SongData {

    private String songName;
    private float songDuration;
    private ArrayList<KeyTimePair> keyTimeList;
    private int keyTimeIndex;

    public class KeyTimePair {

        private String key;
        private Float time;

        public KeyTimePair(String key, Float time) {
            this.key = key;
            this.time = time;
        }

        public String getKey() {
            return key;
        }

        public Float getTime() {
            return time;
        }
    }

    public SongData() {
        keyTimeList = new ArrayList<>();
    }

    public void setSongName(String name) {
        songName = name;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongDuration(float duration) {
        songDuration = duration;
    }

    public float getSongDuration() {
        return songDuration;
    }

    public void addKeyTime(String key, Float time) {
        keyTimeList.add(new KeyTimePair(key, time));
    }

    public void resetIndex() {
        keyTimeIndex = 0;
    }

    public void advanceIndex() {
        keyTimeIndex++;
    }

    public KeyTimePair getCurrentKeyTime() {
        return keyTimeList.get(keyTimeIndex);
    }

    public int keyTimeCount() {
        return keyTimeList.size();
    }

    public boolean isFinished() {
        return keyTimeIndex >= keyTimeList.size();
    }

    public void writeToFile(FileHandle file) {
        // boolean: true=append, false=overwrite.
        file.writeString(getSongName() + "\n", false);
        file.writeString(getSongDuration() + "\n", true);
        for (KeyTimePair ktp : keyTimeList) {
            String data = ktp.getKey() + "," + ktp.getTime() + "\n";
            file.writeString(data, true);
        }
    }

    public void readFromFile(FileHandle file) {
        String rawData = file.readString();
        String[] dataArray = rawData.split(System.lineSeparator());
        setSongName(dataArray[0]);
        setSongDuration(Float.parseFloat(dataArray[1]));
        keyTimeList.clear();
        for (int i = 2; i < dataArray.length; i++) {
            String[] keyTimeData = dataArray[i].split(",");
            String key = keyTimeData[0];
            Float time = Float.parseFloat(keyTimeData[1]);
            keyTimeList.add(new KeyTimePair(key, time));
        }
    }
}
