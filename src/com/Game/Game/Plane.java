package com.Game.Game;

import java.awt.*;

import com.Game.BaseFrame;

public class Plane {
    //ʹ��MyPanel��ԭ���ǣ�Ҫ����MyPanel�е�timer
    public MyPanel myPanel;
    //width��height�����Ƿɻ������߶�
    public int width = 102;
    public int height = 126;
    //x��y�����Ƿɻ������꣨���Ͻǣ�
    public int x;
    public int y;
    //���Ƿɻ�Ѫ��
    public int hp=2;
    //�����е����Ƿɻ� ͼƬ����ŵ�������
    public Image[] images = new Image[] {
            Toolkit.getDefaultToolkit().getImage("images\\me1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me2.png")
    };
    public Image[] dieImages = new Image[] {
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_3.png")
    };
    //��ŵ�ǰͼƬ���±�
    public int imageIndex = 0;
    public Plane(MyPanel myPanel) {
        this.myPanel = myPanel;
        this.x = (BaseFrame.frameWidth - this.width)/2;
        this.y = BaseFrame.frameHeight - this.height*2;
    }

    public void drawSelf(Graphics g) {
        if (hp > 0) {
            g.drawImage(this.images[imageIndex], x, y, width, height, null);
            //ÿ��50�����л�һ��ͼƬ
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                //ͼƬ���������һ����ͼƬ�±��ٴ���0��ʼ
                if (this.imageIndex == this.images.length) {
                    this.imageIndex = 0;
                }
            }
        } else {
            g.drawImage(this.dieImages[imageIndex], x, y, width, height, null);
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //�л�����
                    killed();
                    this.myPanel.baseFrame.state = 2;
                }
            }
        }
        //�ж����Ƿɻ��Ƿ���л���ײ
        for (int i = 0; i < this.myPanel.enemys.size(); i++) {
            Enemy enemy = this.myPanel.enemys.get(i);
            if ((enemy.x < this.myPanel.player.x + this.myPanel.player.width && enemy.x + enemy.width > this.myPanel.player.x)
                    && (enemy.y < this.myPanel.player.y + this.myPanel.player.height && enemy.y + enemy.height > this.myPanel.player.y)) {
                this.myPanel.player.underAttack();
                this.myPanel.enemys.remove(enemy);
            }
        }
        //�ж����Ƿɻ��Ƿ�Ե���Ʒ
        for (int i = 0; i < this.myPanel.items.size(); i++) {
            Item item = this.myPanel.items.get(i);
            if ((this.x >= item.x - this.width && this.x <= item.x + item.width)
                    && (this.y >= item.y - this.height && this.y <= item.y + item.height)) {
                //����������
                this.myPanel.star++;
                item.eated();
            }
        }
    }

    private void killed() {
        this.myPanel.pigFeet.remove(this);
    }
    //if��ŷɻ����ڱ�����״̬��������ֵ����
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}
