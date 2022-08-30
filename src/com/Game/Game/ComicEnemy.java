package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;
import java.util.Random;

public class ComicEnemy  {
    // //使用MyPanel的原因是：要调用MyPanel中的timer
    public MyPanel myPanel;
    public BaseFrame baseFrame;
    //comicBoss的宽和高
    public int width;
    public int height;
    //comicBoss的坐标
    public int x;
    public int y;

    //comicBoss的移动速度
    public int comicSpeed;
    //comicBoss的生命值
    public int hp;
    //comicBoss死亡效果，放在数组中
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-4.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-5.png")
    };
    //comicBoss移动效果
    public Image[] Image = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-2.png"),
    };


    public ComicEnemy(MyPanel myPanel) {
        this.myPanel = myPanel;
        this.width = 142;
        this.height = 125;
        this.hp=20;
        this.x = 0 ;
        this.y = 0;
        this.comicSpeed = 1;
    }

    //存放当前图片下标
    public int imageIndex = 0;
    //画comicBoss
    public void drawSelf(Graphics g) {

        if (hp > 0) {
            //comicBoss移动
            if (this.x > (BaseFrame.frameWidth - this.width) || this.x < 0)
            comicSpeed =-comicSpeed;
            x += comicSpeed;
//            g.drawImage(this.Image[imageIndex], x, y, width, height, null);
            if (this.myPanel.timer % 100 == 0) {
                imageIndex++;
                if (imageIndex == this.Image.length) {
                    imageIndex = 0;
                }
            }
        } else {
            //被打中
//            g.drawImage(this.dieImages[imageIndex], x, y, width, height,null);
            //每50毫秒切换一张图片
            if (this.myPanel.timer % 600 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //敌机死亡
                    killed();
                }
            }
        }
    }
    //移除死掉comicBoss
    private void killed() {
        this.myPanel.Boss.remove(this);
    }
    //if comicBoss处在被攻击状态，其生命值减少
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}
