package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.*;

public class Item {
    //ʹ��MyPanel��ԭ���ǣ�Ҫ����MyPanel�е�timer
    public MyPanel myPanel;
    //��Ʒ�Ŀ�͸�
    public int width;
    public int height;
    //��Ʒ������
    public int x,y;
    //��Ʒ�������ٶ�(��л��ٶ�һ��)
    public int speed ;
    //��Ʒ��������ת�ٶ�(ͼƬ�л�ʱ��)
    public int imageSpeed;
    public Item(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    public Image[] images;

    //��ŵ�ǰͼƬ���±�
    public int imageIndex = 0;


    //����Ʒ
    public void drawSelf(Graphics g) {
        g.drawImage(this.images[imageIndex], x, y, width, height, null);

        //ÿ��imageSpeed�����л�һ��ͼƬ
        if (this.myPanel.timer % imageSpeed == 0) {
            imageIndex++;
            //ͼƬ���������һ��,ͼƬ�±���0��ʼ
            if (this.imageIndex == this.images.length) {
                this.imageIndex = 0;
            }
        }

        //��Ʒ�ƶ���������,������
        if (this.myPanel.timer % speed == 0) {
            y++ ;
            if (y >= BaseFrame.frameHeight) {
                this.myPanel.items.remove(this);

            }
        }
    }
    //��Ʒ���Ե�
    public void eated(){
        this.myPanel.items.remove(this);
    }
}
