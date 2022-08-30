package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Enemy   {
    // //使用MyPanel的原因是：要调用MyPanel中的timer
    public MyPanel myPanel;
    public BaseFrame baseFrame;
    //敌机的宽和高
    public int width;
    public int height;
    //敌机的坐标
    public int x;
    public int y;
    public Image image;
    //敌机的生命值
    public int hp;
    //敌机爆炸效果，放在数组中
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down4.png")
    };


    public Enemy(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    //存放当前图片下标
    public int imageIndex = 0;
    //画敌机
    public void drawSelf(Graphics g) {
        //判断敌机是否被打中
        if (hp > 0) {
            g.drawImage(this.image, x, y, width, height, null);
        } else {
            //被打中
            g.drawImage(this.dieImages[imageIndex], x, y, width, height,null);
            //每50毫秒切换一张图片
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //爆炸音效
//                    new AudioPlayer(AudioPlayer.BOOM).start();
                    //敌机死亡
                    killed();

                }
            }
        }

        if (this.myPanel.timer % 35 == 0) {
            y = y + 10;
            if (y >= BaseFrame.frameHeight) {
                killed();
            }
        }

    }
    Item[] images = new Item[]{};
    //移除死掉或废除界面的敌机
    public void killed() {
        /*Item001 i1=new Item001(myPanel);
        Item[] image = new Item[]{};
        if (image != null && image.length > 0) {
//            敌机死后,释放奖品
            for (int i = 0; i < image.length; i++) {
                Item item =image[i];
//                奖品出现的位置就是敌机死掉的位置
//                25*id的目的是将奖品隔开
                i1.x = this.x + this.width/2-i1.width/2;
                i1.y = this.y;
                this.myPanel.items.add(item);
            }
        }*/

        if (images != null && images.length > 0) {
//            敌机死后,释放奖品
            for (int i = 0; i < images.length; i++) {
                Item item =images[i];
//                奖品出现的位置就是敌机死掉的位置
//                25*id的目的是将奖品隔开

                item.x = this.x + 30*i;
                item.y = this.y;
                this.myPanel.items.add(item);
            }
        }
        this.myPanel.enemys.remove(this);
    }
    //if敌机处在被攻击状态，其生命值减少
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}
