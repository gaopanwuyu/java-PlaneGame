package com.Game.Game;

import com.Game.BaseFrame;


import javax.swing.*;

import java.awt.*;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;

public class MyPanel extends JPanel {
    public BaseFrame baseFrame;
    //��ŵ��Ǳ���ͼƬ
    public Image bgImage;
    public int timer = 0;
    public int top = 0;
    //�ɻ��÷�
    public DrawableThread drawableTherad;
    //����һ���ɻ����Ƕ���
    public Plane player;
    //����comicBoss����
    public ComicEnemy comicEnemy;
    //��������ӵ�
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //���������ͨ�л�
    public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
    //���comicBoss
    public ArrayList<ComicEnemy> Boss = new ArrayList<ComicEnemy>();
    //�����ŷɻ�
    public ArrayList<Plane> pigFeet = new ArrayList<Plane>();
    //������ел�����
    public ArrayList<Class> enemyType = new ArrayList<Class>();
    //������н�Ʒ
    public ArrayList<Item> items = new ArrayList<Item>();

    public MyPanel() {
        this.bgImage = Toolkit.getDefaultToolkit().getImage("images\\background.png");
        //�����ڴ������������Ƿɻ�Ҳ������
        this.player = new Plane(this);
        //����Boss
        this.comicEnemy = new ComicEnemy(this);
        //�����߳� �ػ�Panel
        this.drawableTherad = new DrawableThread(this);
        //�����߳�
        this.drawableTherad.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //���Ʊ���ͼ
        g.drawImage(this.bgImage, 0, top - this.bgImage.getHeight(this),
                this.bgImage.getWidth(this), this.bgImage.getHeight(this), null);
        g.drawImage(this.bgImage, 0, top,
                this.bgImage.getWidth(this), this.bgImage.getHeight(this), null);
        timer++;
        if (timer == 10000)
            timer = 0;
        if (timer % 10 == 0) {
            top++;
            if (top > this.bgImage.getHeight(this)) top = 0;
        }
        if (BaseFrame.state == 0) {
            g.drawImage(player.images[player.imageIndex], 155, 300, player.width, player.height, null);
            g.setFont(new Font("f����", Font.BOLD, 40));
            g.drawString("�����ʼ��Ϸ", 80, 350);
            g.setColor(Color.PINK);
        }
        if (BaseFrame.state == 1) {
            //��������
        /*    AudioPlayer bgMusic = new AudioPlayer(AudioPlayer.BG_MUSIC);
            bgMusic.start();
            AudioPlayer shootMusic = new AudioPlayer(AudioPlayer.SHOOT);
            shootMusic.start();*/

/*            URL url = this.getClass().getResource("images\\bgm.wav");
            AudioClip ac = Applet.newAudioClip(url);
            ac.play();*/

//            new AudioPlayer("images\\bgm.mp3").start();
            //�����ӵ�
            if (timer % 100 == 0) {
                //�����ӵ�
                Bullet bullet = new Bullet(this);
                //��������
                bullet.x = this.player.x + this.player.width / 2 - bullet.width / 2;
                bullet.y = this.player.y;
                bullet.I = this.player.x + this.player.width / 2 - bullet.width / 2 - 40;
                bullet.L = this.player.y + 30;
                bullet.a = this.player.x + this.player.width / 2 - bullet.width / 2 + 40;
                bullet.b = this.player.y + 30;
                bullet.comicX = this.comicEnemy.x + this.comicEnemy.width / 2 - bullet.comicWidth / 2;
                bullet.comicY = this.comicEnemy.y + this.comicEnemy.height;
                //����arrayList��
                this.bullets.add(bullet);
            }
            //�������е��ӵ�
            for (int i = 0; i < bullets.size(); i++) {
                this.bullets.get(i).drawSelf(g);
            }
            //���Ƶл�
            if (timer % 1100 == 0) {
                if (this.enemyType.size() > 0) {
                    //�������һ�����͵ĵл�
                    int index = (int) (Math.random() * this.enemyType.size());
                    Enemy enemy = null;
                    try {
                        enemy = (Enemy) (this.enemyType.get(index).getConstructors()[0]
                                .newInstance(new Object[]{this}));
                    } catch (InstantiationException | IllegalAccessException
                            | IllegalArgumentException | InvocationTargetException
                            | SecurityException e) {
                        //TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //�������õĵл�����������
                    this.enemys.add(enemy);
                }
            }
            //�������Ƿɻ�
            this.player.drawSelf(g);
            //����comicBoss
            this.comicEnemy.drawSelf(g);
            //���Ʒ���
            paintScore(g);
            //�����ел�������
            for (int i = 0; i < this.enemys.size(); i++) {
                this.enemys.get(i).drawSelf(g);
            }
            //�����н�Ʒ������
            for (int i = 0; i < this.items.size(); i++) {
                this.items.get(i).drawSelf(g);
            }
        }
        if (BaseFrame.state == 2) {
            g.setFont(new Font("����", Font.BOLD, 40));
            g.drawString("GAME OVER", 110, 305);
//            g.drawString("������¿�ʼ", 80, 355);
            g.setColor(Color.RED);
            if (BaseFrame.state == 3) {
                BaseFrame.state = 1;
            }
        }
    }

    //���Ʒ����ķ���
    int score = 0;
    int star = 0;

    public void paintScore(Graphics g) {
        //��������
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        //��ɫ
        g.setColor(Color.RED);
        g.setFont(font);
        if (star >= 100) {
            player.hp++;
            star = 0;
        }
        //����
        g.drawString("SCORE:" + score, 0, BaseFrame.frameHeight - 45);
        g.drawString("STAR:" + star, 0, BaseFrame.frameHeight - 90);
    }
}
