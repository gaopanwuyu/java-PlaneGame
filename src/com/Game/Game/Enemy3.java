package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Enemy3 extends Enemy {
    public Enemy3(MyPanel myPanel) {
        super(myPanel);
        //TODO Auto-generated constructor stub
        this.width = 169;
        this.height = 258;
        this.hp = 9;
        this.x = (int) (Math.random() * (BaseFrame.frameWidth - this.width));
        this.y = -this.height;
        this.image = Toolkit.getDefaultToolkit().getImage("images\\enemy3_n1.png");
        Item001 i1 = new Item001(myPanel);
        Item001 i2 = new Item001(myPanel);
        Item001 i3 = new Item001(myPanel);
        this.images = new Item[]{i1,i2,i3};

    }
    //敌机爆炸效果，放在数组中
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down4.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down5.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down6.png")
    };
    //存放当前图片下标
    public int imageIndex = 0;
    public int m = 6;
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
                    this.myPanel.score+=3;
                    killed();

                    //
         /*           Item001 i1=new Item001(myPanel);
                    Item[] image = new Item[]{i1};
                    if (image != null && image.length > 0) {
//            敌机死后,释放奖品
                        for (int i = 0; i < image.length; i++) {
                            Item item =image[i];
//                奖品出现的位置就是敌机死掉的位置
//                25*id的目的是将奖品隔开
                            i1.x = this.x + this.width/4-i1.width/2;
                            i1.y = this.y+i1.height/2;
                            i1.m = this.x + this.width*2/4-i1.width/2;
                            i1.n= this.y+i1.height/2;
                            i1.o = this.x + this.width*3/4-i1.width/2;
                            i1.p = this.y+i1.height/2;
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
