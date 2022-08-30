package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;
import java.util.Random;

public class ComicEnemy  {
    // //ʹ��MyPanel��ԭ���ǣ�Ҫ����MyPanel�е�timer
    public MyPanel myPanel;
    public BaseFrame baseFrame;
    //comicBoss�Ŀ�͸�
    public int width;
    public int height;
    //comicBoss������
    public int x;
    public int y;

    //comicBoss���ƶ��ٶ�
    public int comicSpeed;
    //comicBoss������ֵ
    public int hp;
    //comicBoss����Ч��������������
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-4.png"),
            Toolkit.getDefaultToolkit().getImage("images\\ComicBoss-5.png")
    };
    //comicBoss�ƶ�Ч��
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

    //��ŵ�ǰͼƬ�±�
    public int imageIndex = 0;
    //��comicBoss
    public void drawSelf(Graphics g) {

        if (hp > 0) {
            //comicBoss�ƶ�
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
            //������
//            g.drawImage(this.dieImages[imageIndex], x, y, width, height,null);
            //ÿ50�����л�һ��ͼƬ
            if (this.myPanel.timer % 600 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //�л�����
                    killed();
                }
            }
        }
    }
    //�Ƴ�����comicBoss
    private void killed() {
        this.myPanel.Boss.remove(this);
    }
    //if comicBoss���ڱ�����״̬��������ֵ����
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}
