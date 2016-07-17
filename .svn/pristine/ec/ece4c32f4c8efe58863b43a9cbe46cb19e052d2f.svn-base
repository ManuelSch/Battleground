package ims.tuwien.ac.at.battleground.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;
import java.util.Map;

import ims.tuwien.ac.at.battleground.R;

/**
 * This class handles all sounds that occur in the application. It is also
 * used to control the volume of the application.
 *
 * @author hoch
 * @author schueller
 */
public class SoundManager extends ContextWrapper {

    private Map<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
    private Map<Integer, Double> lastTimeSoundPlayed = new HashMap<Integer, Double>();

    private static SoundManager INSTANCE = null;
    private SoundPool mySoundPool;
    private boolean soundOn = true;

    /**
     * Private Constructor (singleton)
     * @param context the context of the application
     */
    private SoundManager(Context context) {

        super(context);
        /*
        //FUER API-VERSION 21 und hoeher:
        AudioAttributes attributes = new AudioAttributes.Builder()
                                         .setUsage(AudioAttributes.USAGE_GAME)
                                         .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                         .build();
        mySoundPool = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(5).build();*/

        this.mySoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        this.loadSounds();
    }

    /**
     * Returns the instance of the SoundManager
     * @param context The context of the application
     * @return The instance of the SoundManager
     */
    public static SoundManager CREATE_INSTANCE(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new SoundManager(context);
        }
        return INSTANCE;
    }

    /**
     * Returns the instance of the SoundManager
     * @return The instance of the SoundManager
     */
    public static SoundManager GET_INSTANCE() {
        return INSTANCE;
    }

    /**
     * All sounds that are used in the application are handled here.
     */
    private void loadSounds()
    {
        int laser = mySoundPool.load(this.getBaseContext(), R.raw.laser1_1, 1);
        int rocket = mySoundPool.load(this.getBaseContext(), R.raw.rocket, 1);
        int flakbullet = mySoundPool.load(this.getBaseContext(), R.raw.flakbullet, 1);

        soundMap.put(R.raw.laser1_1, laser);
        soundMap.put(R.raw.rocket, rocket);
        soundMap.put(R.raw.flakbullet, flakbullet);

        lastTimeSoundPlayed.put(R.raw.laser1_1, (double)0);
        lastTimeSoundPlayed.put(R.raw.rocket, (double)0);
        lastTimeSoundPlayed.put(R.raw.flakbullet, (double)0);
    }

    /**
     * Plays the corresponding sound
     * @param soundID The ID of the sound to be played
     * @param rate The rate in which the sound should be played
     */
    public void playSound(int soundID, float rate)
    {
        //Verhindern, dass zu viele Sounds auf einmal abgespielt werden (mind. 100 ms Pause zwischen den verschiedenen Sounds):
        double currentTime = System.currentTimeMillis();
        if(lastTimeSoundPlayed.get(soundID) + 100 < currentTime) {
            lastTimeSoundPlayed.put(soundID,currentTime);
            mySoundPool.play(soundMap.get(soundID), 1, 1, 1, 0, rate);
        }
    }

    /**
     * Turns the sound on or off (depending on the current state)
     */
    public void toggleSound() {
        soundOn = !soundOn;
        if(soundOn) {
            this.unmute();
        }
        else {
            this.mute();
        }

    }

    /**
     * Mutes the application
     */
    public void mute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ((AudioManager)getSystemService(Context.AUDIO_SERVICE)).adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
        } else {
            ((AudioManager)getSystemService(Context.AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_MUSIC, true);
        }
    }

    /**
     * Unmutes the application
     */
    public void unmute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ((AudioManager)getSystemService(Context.AUDIO_SERVICE)).adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
        } else {
            ((AudioManager)getSystemService(Context.AUDIO_SERVICE)).setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
    }

}
