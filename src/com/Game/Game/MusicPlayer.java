/*
package com.Game.Game;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicPlayer {
    public MusicPlayer(){
    }

    static Player player;

    */
/**
     * 播放 50 秒并结束播放
     *//*

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File("images\\bgm.mp3");
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream stream = new BufferedInputStream(fis);
                    player = new Player(stream);
                    player.play();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }).start();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.close();
    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        File file = new File("images\\bgm.mp3");
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        Player player = new Player(stream);
        player.play();
          File file = new File("images\\bgm.mp3");
        new Thread(()->{
            try{
                new AudioPlayer(file).play();
            }catch(Exception e){
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }
}
*/
