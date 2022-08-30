package com.Game.Game;

import java.awt.*;

public class Bullet {
    //ʹ��MyPanel��ԭ���ǣ�Ҫ����MyPanel�е�timer
    public MyPanel myPanel;
    //width��height���ӵ��Ŀ�͸�
    public int width = 5;
    public int height = 11;
    //comicBoss�ӵ��Ŀ�͸�
    public int comicWidth=38;
    public int comicHeight=30;
    //���Ƿɻ��ӵ�������
    public int x,y;
    public int I,L;
    public int a,b;
    public int c,d;
    public int e,f;
    //comicBoss���ӵ�����
    public int comicX,comicY;

    //�����ǵ��ӵ���ͼƬ����ŵ�������
    public Image[] images = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\bullet1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\bullet2.png")
    };
    //��comicBoss���ӵ���ͼƬ����ŵ�������
    public Image[] comicImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\comicEye-3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\comicEye-4.png")
    };
    //��ŵ�ǰͼƬ���±�
    public int imageIndex = 0;
    public Bullet(MyPanel myPanel) {
        this.myPanel = myPanel;
    }
    //���ӵ�
    public void drawSelf(Graphics g) {
        g.drawImage(this.images[imageIndex], x-40, y+30, width, height, null);
        g.drawImage(this.images[imageIndex], x+40, y+30, width, height, null);
        g.drawImage(this.images[imageIndex], x, y, width, height, null);
//        g.drawImage(this.comicImages[imageIndex], comicX, comicY,null);
        //ÿ����������ӵ��л�һ��ͼƬ
        if (this.myPanel.timer % 50 == 0) {
            imageIndex++;
            //ͼƬ���������һ����ͼƬ�±��ٴ���0��ʼ
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
            //�ӵ������������ƶ�
            L -= 60;
            b -= 60;
            d -= 60;
            f -= 60;
            y = y - 60;
            //����ӵ��ϳ����ڣ����Ƴ��ӵ�
            if (y < 0) {
                //�Ƴ��ӵ�
                this.myPanel.bullets.remove(this);
            }
            if (L < 0) {
                //�Ƴ��ӵ�
                this.myPanel.bullets.remove(this);
            }
            if (b < 0) {
                //�Ƴ��ӵ�
                this.myPanel.bullets.remove(this);
            }
        }
        //comicBoss�ӵ����л��ٶ�
        if (this.myPanel.timer % 80 == 0) {
            imageIndex++;
            //ͼƬ���������һ����ͼƬ�±��ٴ���0��ʼ
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
            //�ӵ������������ƶ�
            comicY += 80;
            //����ӵ��ϳ����ڣ����Ƴ��ӵ�
            if (comicY < 0) {
                this.myPanel.bullets.remove(this);
            }

        }
        //�ж��ӵ��Ƿ���ел�

        for (int i = 0; i < this.myPanel.enemys.size(); i++) {
            Enemy e = this.myPanel.enemys.get(i);
            //�ж�����ӵ��Ƿ���ел�
            if ((this.x >= e.x && this.x <= e.x + e.width) &&
                    (this.y >= e.y  && this.y <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);

                //��ȥ�л���hpֵ
                e.underAttack();

            }
            if ((this.I >= e.x  && this.I <= e.x + e.width) &&
                    (this.L >= e.y  && this.L <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥ�л���hpֵ
                e.underAttack();
            }
            if ((this.a >= e.x  && this.a <= e.x + e.width) &&
                    (this.b >= e.y  && this.b <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥ�л���hpֵ
                e.underAttack();
            }
            if ((this.c >= e.x  && this.c <= e.x + e.width) &&
                    (this.d >= e.y  && this.d <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥ�л���hpֵ
                e.underAttack();
            }
            if ((this.e >= e.x  && this.e <= e.x + e.width) &&
                    (this.f >= e.y  && this.f <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥ�л���hpֵ
                e.underAttack();
            }
        }
        //�ж��Ƿ����comicBoss
        for (int i = 0; i < this.myPanel.Boss.size(); i++) {
            ComicEnemy e = this.myPanel.Boss.get(i);
            if ((this.x >= e.x && this.x <= e.x + e.width) &&
                    (this.y >= e.y && this.y <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥcomicBoss��hpֵ
                e.underAttack();
            }
            if ((this.I >= e.x && this.I <= e.x + e.width) &&
                    (this.L >= e.y && this.L <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥcomicBoss��hpֵ
                e.underAttack();
            }
            if ((this.a >= e.x && this.a <= e.x + e.width) &&
                    (this.b >= e.y && this.b <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥcomicBoss��hpֵ
                e.underAttack();
            }
        }
        //�ж��Ƿ�������
        for (int i = 0; i < this.myPanel.pigFeet.size(); i++) {
            Plane e = this.myPanel.pigFeet.get(i);
            if ((this.comicX >= e.x && (this.comicX + comicWidth) <= e.x + e.width) &&
                    (this.comicY >= e.y && (this.comicY + comicHeight) <= e.y + e.height)) {
                //�Ƴ����ӵ�
                this.myPanel.bullets.remove(this);
                //��ȥ��ŵ�hpֵ
                e.underAttack();
            }
        }
    }
}
