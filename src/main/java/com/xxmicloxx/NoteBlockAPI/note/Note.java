package com.xxmicloxx.NoteBlockAPI.note;

import cn.nukkit.level.Sound;

public class Note {

    private byte instrument;
    private byte key;

    public Note(byte instrument, byte key) {
        switch (instrument) {
            case 1:
                instrument = 4;
                break;
            case 2:
                instrument = 1;
                break;
            case 3:
                instrument = 2;
                break;
            case 4:
                instrument = 3;
                break;
            case 5:
                instrument = 8;
                break;
            case 7:
                instrument = 5;
                break;
            case 8:
                instrument = 7;
                break;
        }
        this.instrument = instrument;
        this.key = key;
    }

    public byte getInstrument(boolean limit) {
        if (limit && instrument > 4) return 0;
        return instrument;
    }

    public Sound getSoundEnum(boolean limit) {
        switch (getInstrument(limit)) {
            case 0:
                return Sound.NOTE_HARP;
            case 1:
                return Sound.NOTE_BD;
            case 2:
                return Sound.NOTE_SNARE;
            case 3:
                return Sound.NOTE_HAT;
            case 4:
                return Sound.NOTE_BASSATTACK;
            case 5:
                return Sound.NOTE_BELL;
            case 6:
                return Sound.NOTE_FLUTE;
            case 7:
                return Sound.NOTE_CHIME;
            case 8:
                return Sound.NOTE_GUITAR;
            case 9:
                return Sound.NOTE_XYLOPHONE;
            case 10:
                return Sound.NOTE_IRON_XYLOPHONE;
            case 11:
                return Sound.NOTE_COW_BELL;
            case 12:
                return Sound.NOTE_DIDGERIDOO;
            case 13:
                return Sound.NOTE_BIT;
            case 14:
                return Sound.NOTE_BANJO;
            case 15:
                return Sound.NOTE_PLING;
            default:
                return Sound.NOTE_HARP;
        }
    }

    public void setInstrument(byte instrument) {
        this.instrument = instrument;
    }

    public byte getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }

    public float getNoteSoundPitch() {
        return (float) Math.pow(2d, ((double) key - 33d - 12d) / 12d);
    }

}
