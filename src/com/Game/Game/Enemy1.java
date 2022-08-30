package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Enemy1 extends Enemy{
    public Enemy1(MyPanel myPanel) {
        super(myPanel);
        //TODO Auto-generated constructor stub
        this.width = 57;
        this.height = 43;
        this.hp = 3;
        this.x = (int) (Math.random() * (BaseFrame.frameWidth - this.width));
        this.y = -this.height;
        this.image = Toolkit.getDefaultToolkit().getImage("images\\enemy1.png");
        Item001 i1=new Item001(myPanel);
        this.images = new Item[]{i1};
    }
    //敌机爆炸效果，放在数组中
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down4.png")
    };
    //存放当前图片下标
    public int imageIndex = 0;
    public int m = 8;
    //画敌机
    public void drawSelf(Graphics g) {
        //判断是否被击中
        if (hp > 0) {
            g.drawImage(this.image, x, y, width, height, null);
        } else {
            //被击中
            g.drawImage(this.dieImages[imageIndex], x, y, width, height, null);
            //每10毫秒切换一张图片
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //敌机死亡
                    //计分器增加
                    this.myPanel.score++;
                    killed();

 /*                   Item001 i1=new Item001(myPanel);
                    Item[] image = new Item[]{i1};
                    if (image != null && image.length > 0) {
//            敌机死后,释放奖品
                        for (int i = 0; i < image.length; i++) {
                            Item item =image[i*2];
//                奖品出现的位置就是敌机死掉的位置
//                25*id的目的是将奖品隔开
                            i1.x = this.x + this.width/2-i1.width/2;
                            i1.y = this.y+i1.height/2;
                            this.myPanel.items.add(item);
                        }
                    }*/
                }
            }
        }
        //敌机向下移动，如果敌机出界就销毁
        if (this.myPanel.timer % 35 == 0) {
            y = y + 10;
            if (this.x > (BaseFrame.frameWidth - this.width) || this.x < 0)
                m =-m;
            x += m;
            if (y >= BaseFrame.frameHeight) {
                killed();
            }
        }
    }
}
