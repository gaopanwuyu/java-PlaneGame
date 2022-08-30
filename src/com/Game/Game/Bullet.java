package com.Game.Game;

import java.awt.*;

public class Bullet {
    //使用MyPanel的原因是：要调用MyPanel中的timer
    public MyPanel myPanel;
    //width，height是子弹的宽和高
    public int width = 5;
    public int height = 11;
    //comicBoss子弹的宽和高
    public int comicWidth=38;
    public int comicHeight=30;
    //主角飞机子弹的坐标
    public int x,y;
    public int I,L;
    public int a,b;
    public int c,d;
    public int e,f;
    //comicBoss的子弹坐标
    public int comicX,comicY;

    //将主角的子弹的图片对象放到数组中
    public Image[] images = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\bullet1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\bullet2.png")
    };
    //将comicBoss的子弹的图片对象放到数组中
    public Image[] comicImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\comicEye-3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\comicEye-4.png")
    };
    //存放当前图片的下标
    public int imageIndex = 0;
    public Bullet(MyPanel myPanel) {
        this.myPanel = myPanel;
    }
    //画子弹
    public void drawSelf(Graphics g) {
        g.drawImage(this.images[imageIndex], x-40, y+30, width, height, null);
        g.drawImage(this.images[imageIndex], x+40, y+30, width, height, null);
        g.drawImage(this.images[imageIndex], x, y, width, height, null);
//        g.drawImage(this.comicImages[imageIndex], comicX, comicY,null);
        //每隔毫秒猪脚子弹切换一张图片
        if (this.myPanel.timer % 50 == 0) {
            imageIndex++;
            //图片遍历到最后一个，图片下标再次重0开始
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
            //子弹的坐标向上移动
            L -= 60;
            b -= 60;
            d -= 60;
            f -= 60;
            y = y - 60;
            //如果子弹废除窗口，则移除子弹
            if (y < 0) {
                //移除子弹
                this.myPanel.bullets.remove(this);
            }
            if (L < 0) {
                //移除子弹
                this.myPanel.bullets.remove(this);
            }
            if (b < 0) {
                //移除子弹
                this.myPanel.bullets.remove(this);
            }
        }
        //comicBoss子弹的切换速度
        if (this.myPanel.timer % 80 == 0) {
            imageIndex++;
            //图片遍历到最后一个，图片下标再次重0开始
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
            //子弹的坐标向上移动
            comicY += 80;
            //如果子弹废除窗口，则移除子弹
            if (comicY < 0) {
                this.myPanel.bullets.remove(this);
            }

        }
        //判断子弹是否打中敌机

        for (int i = 0; i < this.myPanel.enemys.size(); i++) {
            Enemy e = this.myPanel.enemys.get(i);
            //判断猪脚子弹是否打中敌机
            if ((this.x >= e.x && this.x <= e.x + e.width) &&
                    (this.y >= e.y  && this.y <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);

                //减去敌机的hp值
                e.underAttack();

            }
            if ((this.I >= e.x  && this.I <= e.x + e.width) &&
                    (this.L >= e.y  && this.L <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去敌机的hp值
                e.underAttack();
            }
            if ((this.a >= e.x  && this.a <= e.x + e.width) &&
                    (this.b >= e.y  && this.b <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去敌机的hp值
                e.underAttack();
            }
            if ((this.c >= e.x  && this.c <= e.x + e.width) &&
                    (this.d >= e.y  && this.d <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去敌机的hp值
                e.underAttack();
            }
            if ((this.e >= e.x  && this.e <= e.x + e.width) &&
                    (this.f >= e.y  && this.f <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去敌机的hp值
                e.underAttack();
            }
        }
        //判断是否打中comicBoss
        for (int i = 0; i < this.myPanel.Boss.size(); i++) {
            ComicEnemy e = this.myPanel.Boss.get(i);
            if ((this.x >= e.x && this.x <= e.x + e.width) &&
                    (this.y >= e.y && this.y <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去comicBoss的hp值
                e.underAttack();
            }
            if ((this.I >= e.x && this.I <= e.x + e.width) &&
                    (this.L >= e.y && this.L <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去comicBoss的hp值
                e.underAttack();
            }
            if ((this.a >= e.x && this.a <= e.x + e.width) &&
                    (this.b >= e.y && this.b <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去comicBoss的hp值
                e.underAttack();
            }
        }
        //判断是否打中猪脚
        for (int i = 0; i < this.myPanel.pigFeet.size(); i++) {
            Plane e = this.myPanel.pigFeet.get(i);
            if ((this.comicX >= e.x && (this.comicX + comicWidth) <= e.x + e.width) &&
                    (this.comicY >= e.y && (this.comicY + comicHeight) <= e.y + e.height)) {
                //移除该子弹
                this.myPanel.bullets.remove(this);
                //减去猪脚的hp值
                e.underAttack();
            }
        }
    }
}
