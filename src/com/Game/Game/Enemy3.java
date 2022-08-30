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
    //�л���ըЧ��������������
    public Image[] dieImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down4.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down5.png"),
            Toolkit.getDefaultToolkit().getImage("images\\enemy3_down6.png")
    };
    //��ŵ�ǰͼƬ�±�
    public int imageIndex = 0;
    public int m = 6;
    //���л�
    public void drawSelf(Graphics g) {
        //�ж��Ƿ񱻻���
        if (hp > 0) {
            g.drawImage(this.image, x, y, width, height, null);
        } else {
            //������
            g.drawImage(this.dieImages[imageIndex], x, y, width, height, null);
            //ÿ10�����л�һ��ͼƬ
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //�л�����
                    //�Ʒ�������
                    this.myPanel.score+=3;
                    killed();

                    //
         /*           Item001 i1=new Item001(myPanel);
                    Item[] image = new Item[]{i1};
                    if (image != null && image.length > 0) {
//            �л�����,�ͷŽ�Ʒ
                        for (int i = 0; i < image.length; i++) {
                            Item item =image[i];
//                ��Ʒ���ֵ�λ�þ��ǵл�������λ��
//                25*id��Ŀ���ǽ���Ʒ����
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
        //�л������ƶ�������л����������
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
