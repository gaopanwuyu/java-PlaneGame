package com.Game;

import com.Game.Game.*;

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;


public class BaseFrame extends JFrame {
    public static int state = 0;
    //窗口的宽高
    public static int frameWidth = 420;
    public static int frameHeight = 700;
    public MyPanel myPanel;
     //鼠标监听事件
     public FrameMouseListener frameMouseListener;
     public void setTouchListener() {
         this.frameMouseListener = new FrameMouseListener();
         this.frameMouseListener.baseFrame = this;
         this.addMouseListener(this.frameMouseListener);
     }
    //键盘监听
    public FrameKeyListener frameKeyListener;
    public void setFrameKeyListener(){
        this.frameKeyListener = new FrameKeyListener();
        this.frameKeyListener.baseFrame = this;
        this.addKeyListener(this.frameKeyListener);
    }

    public void addEnemyType(Class c) {
        this.myPanel.enemyType.add(c);
    }
    public BaseFrame() {
        //设置标题
        this.setTitle("飞机大战");
//        super("飞机大战");
        //获取屏幕分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int) ((screenSize.getWidth() - frameWidth) / 2), 30, frameWidth, frameHeight);
//      setBounds((int) (screenSize.getWidth() - frameWidth / 2), 30, frameWidth, frameHeight);
        //设置布局方式
        this.setLayout(null);
        //创建一个MyPanel对象
        this.myPanel = new MyPanel();
        //设置MyPanel对象的位置和大小
        this.myPanel.setBounds(0,0,frameWidth,frameHeight);
//      setLayout(null);
        //将组件添加到窗口中
        this.add(this.myPanel);
        //设置监听器
        setTouchListener();
        setFrameKeyListener();
        //添加敌机类型
        addEnemyType(Enemy1.class);
        addEnemyType(Enemy2.class);
        addEnemyType(Enemy3.class);
//        addEnemyType(Enemy4.class);
        //窗口的可视化，显示窗口，JFrame默认窗口不显示
        this.setVisible(true);
        //不允许玩家修改界面大小
        setResizable(false);
//        setResizable(true);
        //设置窗口关闭，窗口关闭后，程序停止
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
