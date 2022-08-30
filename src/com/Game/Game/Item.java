package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Item {
    //使用MyPanel的原因是，要调用MyPanel中的timer
    public MyPanel myPanel;
    //奖品的宽和高
    public int width;
    public int height;
    //奖品的坐标
    public int x,y;
    //奖品的下移速度(与敌机速度一致)
    public int speed ;
    //奖品的自身旋转速度(图片切换时间)
    public int imageSpeed;
    public Item(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    public Image[] images;

    //存放当前图片的下标
    public int imageIndex = 0;


    //画奖品
    public void drawSelf(Graphics g) {
        g.drawImage(this.images[imageIndex], x, y, width, height, null);

        //每隔imageSpeed毫秒切换一张图片
        if (this.myPanel.timer % imageSpeed == 0) {
            imageIndex++;
            //图片遍历到最后一个,图片下标重0开始
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
        }

        //奖品移动超出窗口,则销毁
        if (this.myPanel.timer % speed == 0) {
            y++ ;
            if (y >= BaseFrame.frameHeight) {
                this.myPanel.items.remove(this);

            }
        }
    }
    //奖品被吃掉
    public void eated(){
        this.myPanel.items.remove(this);
    }
}
