package com.Game.Game;

import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.*;

/**
 * ���ֲ��ŵ��߳���
 * -public void start()
 * ���´��߳̿�ʼִ�У�Java��������ô��̵߳�run����������
 * ��Ҫ���������߳�Ҫִ�в�������������
 *
 * @ClassName:AudioPlayer
 * @Description:TODO(���ֲ�����)
 */
/*public class AudioPlayer extends Thread {
    Player player;
    File music;

    *//**
     * ���췽����������һ��MP3��Ƶ�ļ�
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
     * ���ŷ���
     *//*
    public void play() throws Exception {
        BufferedInputStream buffer = new BufferedInputStream(
                new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }
    *//**
     * �߳�ʹ��һ�ο���ʹ����������ķ�ʽ��������
     * new Thread(){}.start();
     * ��дrun()����Ҫsuper.run()�̳и���Thread����
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

    //��Ƶ�ļ���
    private String videos;
    private AudioFormat audioFormat;
    private byte[] samples;

    //ֹͣ���ű�־
    private boolean isStop = false;
    //ѭ�����ű�־
    private boolean inLoop = false;
    //���޲��ű�־
    private boolean toEnd = false;


    public AudioPlayer(String filename) {
        //��ʼ��filename
        this.videos = filename;
//        this.isStop = isStop;
//        this.inLoop = inLoop;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            //����һ��AudioInputStream���ڽ����������Ƶ���ݣ�ʹ��AudioSystem����ȡ��Ƶ����Ƶ������
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(videos));
            //��AudioFormat����ȡAudioInputStream�ĸ�ʽ
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
        //Դ������SoureDataLine�ǿ���д�����ݵ�������
        SourceDataLine dataLine = null;
        //��ȡ��������֧�ֵ���Ƶ��ʽDataLine.info
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
                //����
                if(toEnd){
                    break;
                }
                //����Ƶ����ȡָ������������������ֽڣ���������뻺������
                numBytesRead =
                        source.read(buffer, 0, buffer.length);
                //ͨ����Դ�����н�����д���Ƶ��
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
     * ����ѭ����ͣ
     */
    public void setToEnd(boolean toEnd){this.toEnd = toEnd;}
    /**
     * ����ѭ������
     */
    public void setInLoop(boolean inLoop){this.inLoop = inLoop;}
    /**
     * ������Ƶѭ������
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

