package com.Game.Game;

import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.*;

/**
 * 音乐播放的线程类
 * -public void start()
 * 导致此线程开始执行；Java虚拟机调用此线程的run（）方法：
 * 需要将创建的线程要执行操作声明在其中
 *
 * @ClassName:AudioPlayer
 * @Description:TODO(音乐播放器)
 */
/*public class AudioPlayer extends Thread {
    Player player;
    File music;

    *//**
     * 构造方法，参数是一个MP3音频文件
     *//*
    public AudioPlayer(File file) {
        this.music = file;
    }


    @Override
    public void run() {
        super.run();
        try {
            play();
        } catch (Exception e) {
            //TODO   Auto-generated catch block
            e.printStackTrace();
        }
    }

    *//**
     * 播放方法
     *//*
    public void play() throws Exception {
        BufferedInputStream buffer = new BufferedInputStream(
                new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }
    *//**
     * 线程使用一次可以使用匿名子类的方式进行启动
     * new Thread(){}.start();
     * 重写run()不需要super.run()继承父类Thread方法
     *//*
    public static void main(String[] args) {
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
}*/
public class AudioPlayer extends Thread {

    //音频文件名
    private String videos;
    private AudioFormat audioFormat;
    private byte[] samples;

    //停止播放标志
    private boolean isStop = false;
    //循环播放标志
    private boolean inLoop = false;
    //借宿播放标志
    private boolean toEnd = false;


    public AudioPlayer(String filename) {
        //初始化filename
        this.videos = filename;
//        this.isStop = isStop;
//        this.inLoop = inLoop;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            //定义一个AudioInputStream用于接收输入的音频数据，使用AudioSystem来获取音频的音频输入流
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(videos));
            //用AudioFormat来获取AudioInputStream的格式
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        DataInputStream dataInputStream = new DataInputStream(stream);
        try {
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return samples;
    }

    public void play(InputStream source) {
        int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
        byte[] buffer = new byte[size];
        //源数据行SoureDataLine是可以写入数据的数据行
        SourceDataLine dataLine = null;
        //获取受数据行支持的音频格式DataLine.info
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataLine.start();
        try {
            int numBytesRead = 0;
            while (numBytesRead != -1) {
                //结束
                if(toEnd){
                    break;
                }
                //从音频流读取指定的最大数量的数据字节，并将其放入缓冲区中
                numBytesRead =
                        source.read(buffer, 0, buffer.length);
                //通过此源数据行将数据写入混频器
                if (numBytesRead != -1) {
                    dataLine.write(buffer, 0, numBytesRead);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        dataLine.drain();
        dataLine.close();

    }



    /**
     * 设置循环暂停
     */
    public void setToEnd(boolean toEnd){this.toEnd = toEnd;}
    /**
     * 设置循环播放
     */
    public void setInLoop(boolean inLoop){this.inLoop = inLoop;}
    /**
     * 控制音频循环播放
     */
    @Override
    public void run() {
        if(inLoop){
            while (inLoop){
                InputStream stream = new ByteArrayInputStream(samples);
                play(stream);
            }
        }
        InputStream stream = new ByteArrayInputStream(samples);
        play(stream);
    }
}

