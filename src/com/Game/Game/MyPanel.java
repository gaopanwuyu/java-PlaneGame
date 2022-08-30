package com.Game.Game;

import com.Game.BaseFrame;


import javax.swing.*;

import java.awt.*;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;

public class MyPanel extends JPanel {
    public BaseFrame baseFrame;
    //存放的是背景图片
    public Image bgImage;
    public int timer = 0;
    public int top = 0;
    //飞机得分
    public DrawableThread drawableTherad;
    //创建一个飞机主角对象
    public Plane player;
    //创建comicBoss对象
    public ComicEnemy comicEnemy;
    //存放所有子弹
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //存放所有普通敌机
    public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
    //存放comicBoss
    public ArrayList<ComicEnemy> Boss = new ArrayList<ComicEnemy>();
    //存放猪脚飞机
    public ArrayList<Plane> pigFeet = new ArrayList<Plane>();
    //存放所有敌机类型
    public ArrayList<Class> enemyType = new ArrayList<Class>();
    //存放所有奖品
    public ArrayList<Item> items = new ArrayList<Item>();

    public MyPanel() {
        this.bgImage = Toolkit.getDefaultToolkit().getImage("images\\background.png");
        //当窗口创建出来后，主角飞机也被创建
        this.player = new Plane(this);
        //创建Boss
        this.comicEnemy = new ComicEnemy(this);
        //创建线程 重绘Panel
        this.drawableTherad = new DrawableThread(this);
        //启动线程
        this.drawableTherad.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景图
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
            g.setFont(new Font("f仿宋", Font.BOLD, 40));
            g.drawString("点击开始游戏", 80, 350);
            g.setColor(Color.PINK);
        }
        if (BaseFrame.state == 1) {
            //背景音乐
        /*    AudioPlayer bgMusic = new AudioPlayer(AudioPlayer.BG_MUSIC);
            bgMusic.start();
            AudioPlayer shootMusic = new AudioPlayer(AudioPlayer.SHOOT);
            shootMusic.start();*/

/*            URL url = this.getClass().getResource("images\\bgm.wav");
            AudioClip ac = Applet.newAudioClip(url);
            ac.play();*/

//            new AudioPlayer("images\\bgm.mp3").start();
            //绘制子弹
            if (timer % 100 == 0) {
                //创建子弹
                Bullet bullet = new Bullet(this);
                //设置坐标
                bullet.x = this.player.x + this.player.width / 2 - bullet.width / 2;
                bullet.y = this.player.y;
                bullet.I = this.player.x + this.player.width / 2 - bullet.width / 2 - 40;
                bullet.L = this.player.y + 30;
                bullet.a = this.player.x + this.player.width / 2 - bullet.width / 2 + 40;
                bullet.b = this.player.y + 30;
                bullet.comicX = this.comicEnemy.x + this.comicEnemy.width / 2 - bullet.comicWidth / 2;
                bullet.comicY = this.comicEnemy.y + this.comicEnemy.height;
                //放入arrayList中
                this.bullets.add(bullet);
            }
            //画出所有的子弹
            for (int i = 0; i < bullets.size(); i++) {
                this.bullets.get(i).drawSelf(g);
            }
            //绘制敌机
            if (timer % 1100 == 0) {
                if (this.enemyType.size() > 0) {
                    //随机产生一种类型的敌机
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
                    //将创建好的敌机放在数组里
                    this.enemys.add(enemy);
                }
            }
            //绘制主角飞机
            this.player.drawSelf(g);
            //绘制comicBoss
            this.comicEnemy.drawSelf(g);
            //绘制分数
            paintScore(g);
            //将所有敌机画出来
            for (int i = 0; i < this.enemys.size(); i++) {
                this.enemys.get(i).drawSelf(g);
            }
            //将所有奖品画出来
            for (int i = 0; i < this.items.size(); i++) {
                this.items.get(i).drawSelf(g);
            }
        }
        if (BaseFrame.state == 2) {
            g.setFont(new Font("仿宋", Font.BOLD, 40));
            g.drawString("GAME OVER", 110, 305);
//            g.drawString("点击重新开始", 80, 355);
            g.setColor(Color.RED);
            if (BaseFrame.state == 3) {
                BaseFrame.state = 1;
            }
        }
    }

    //绘制分数的方法
    int score = 0;
    int star = 0;

    public void paintScore(Graphics g) {
        //设置字体
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        //颜色
        g.setColor(Color.RED);
        g.setFont(font);
        if (star >= 100) {
            player.hp++;
            star = 0;
        }
        //文字
        g.drawString("SCORE:" + score, 0, BaseFrame.frameHeight - 45);
        g.drawString("STAR:" + star, 0, BaseFrame.frameHeight - 90);
    }
}
