package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Enemy4 extends Enemy {
    public Enemy4(MyPanel myPanel) {
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
    //�л���ըЧ��������������
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy1_down4.png")
    };
    //��ŵ�ǰͼƬ�±�
    public int imageIndex = 0;
    //���л�
    //��Ӻ����ƶ��ٶ�
    int m = 10;

    public void drawSelf(Graphics g) {
        //�ж��Ƿ񱻻���
        if (hp > 0) {
            g.drawImage(this.image, x, y, width, height, null);

        } else {
            //������
            g.drawImage(this.dieImages[imageIndex], x, y, width, height, null);
            //ÿ10�����л�һ��ͼƬ
            if (this.myPanel.timer % 10 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //�л�����
                    killed();
                    this.myPanel.score++;
                }
            }
        }
        //�л������ƶ�������л����������
        if (this.myPanel.timer % 35 == 0) {
            y = y + 10;
            if (this.x > (BaseFrame.frameWidth * 4 / 5 - this.width / 2) || this.x < (BaseFrame.frameWidth / 5 - this.width / 2)) {
                m = -m;
            } else if (this.x > (BaseFrame.frameWidth - this.width) || this.x < 0) {
                m = -m;
            }
            x += m;
            if (y >= BaseFrame.frameHeight) {
                killed();
            }
        }
    }
}
