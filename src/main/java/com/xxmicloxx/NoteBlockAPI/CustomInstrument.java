package com.xxmicloxx.NoteBlockAPI;

import cn.nukkit.level.Sound;

public class CustomInstrument {

    private final byte index;
    private final String name;
    private final String soundFileName;
    private final Sound sound;

    /**
     * Creates a CustomInstrument
     * @param index
     * @param name
     * @param soundFileName
     */
    public CustomInstrument(byte index, String name, String soundFileName) {
        this.index = index;
        this.name = name;
        this.soundFileName = soundFileName.replaceAll(".ogg", "");
        if (this.soundFileName.equalsIgnoreCase("pling")){
            this.sound = Sound.NOTE_PLING;
        } else {
            this.sound = Sound.fromName(name);
        }
    }

    /**
     * Gets index of CustomInstrument
     * @return index
     */
    public byte getIndex() {
        return index;
    }

    /**
     * Gets name of CustomInstrument
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets file name of the sound
     * @return file name
     */
    public String getSoundFileName() {
        return soundFileName;
    }

    /**
     * Gets the Sound for this CustomInstrument
     * @return Sound enum
     */
    public Sound getSound() {
        return sound;
    }
}
