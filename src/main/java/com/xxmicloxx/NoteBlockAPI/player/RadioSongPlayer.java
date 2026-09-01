package com.xxmicloxx.NoteBlockAPI.player;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.network.protocol.PlaySoundPacket;
import com.xxmicloxx.NoteBlockAPI.*;
import com.xxmicloxx.NoteBlockAPI.note.Layer;
import com.xxmicloxx.NoteBlockAPI.note.Note;

import java.util.*;

public class RadioSongPlayer extends SongPlayer {

    public RadioSongPlayer(Song song) {
        super(song);
    }

    @Override
    public void playTick(Player p, int tick) {
        List<DataPacket> batchedPackets = new ArrayList<>();
        for (Layer l : song.getLayerHashMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }

            boolean limit = p.protocol < 388;

            int pitch = note.getKey() - 33;
            if (note.getInstrument(false) >= song.getFirstCustomInstrumentIndex()) {
                PlaySoundPacket psk = new PlaySoundPacket();
                psk.name = song.getCustomInstruments()[note.getInstrument(false) - song.getFirstCustomInstrumentIndex()].getName();
                psk.x = (int) ((float) p.x);
                psk.y = (int) ((float) p.y + p.getEyeHeight());
                psk.z = (int) ((float) p.z);
                psk.pitch = note.getNoteSoundPitch();
                psk.volume = (float) l.getVolume() / 100 * ((float) this.getVolume() / 100);
                batchedPackets.add(psk);
            } else if (p.protocol >= 312 && pitch < 0) {
                PlaySoundPacket psk = new PlaySoundPacket();
                psk.name = note.getSoundEnum(limit).getSound();
                psk.x = (int) p.x;
                psk.y = (int) p.y;
                psk.z = (int) p.z;
                psk.pitch = note.getNoteSoundPitch();
                psk.volume = (float) l.getVolume() / 100 * ((float) this.getVolume() / 100);
                batchedPackets.add(psk);
            } else {
                LevelSoundEventPacket pk = new LevelSoundEventPacket();
                pk.x = (float) p.x;
                pk.y = (float) p.y;
                pk.z = (float) p.z;
                pk.sound = LevelSoundEventPacket.SOUND_NOTE;
                pk.entityIdentifier = ":";
                pk.extraData = note.getInstrument(limit) << 8 | (pitch & 0xFF);
                batchedPackets.add(pk);
            }

        }

        for (DataPacket pk: batchedPackets) {
            p.dataPacket(pk);
        }
        //Server.getInstance().batchPackets(new Player[]{p}, batchedPackets.stream().toArray(DataPacket[]::new), true);
    }

}
