package com.Game.Game;

import java.awt.*;

import com.Game.BaseFrame;

public class Plane {
    //使用MyPanel的原因是，要调用MyPanel中的timer
    public MyPanel myPanel;
    //width，height是主角飞机宽度与高度
    public int width = 102;
    public int height = 126;
    //x，y是主角飞机的坐标（左上角）
    public int x;
    public int y;
    //主角飞机血量
    public int hp=2;
    //将所有的主角飞机 图片对象放到数组中
    public Image[] images = new Image[] {
            Toolkit.getDefaultToolkit().getImage("images\\me1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me2.png")
    };
    public Image[] dieImages = new Image[] {
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_1.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_2.png"),
            Toolkit.getDefaultToolkit().getImage("images\\me_destroy_3.png")
    };
    //存放当前图片的下标
    public int imageIndex = 0;
    public Plane(MyPanel myPanel) {
        this.myPanel = myPanel;
        this.x = (BaseFrame.frameWidth - this.width)/2;
        this.y = BaseFrame.frameHeight - this.height*2;
    }

    public void drawSelf(Graphics g) {
        if (hp > 0) {
            g.drawImage(this.images[imageIndex], x, y, width, height, null);
            //每隔50毫秒切换一张图片
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                //图片遍历到最后一个后，图片下标再次重0开始
                if (this.imageIndex == this.images.length) {
                    this.imageIndex = 0;
                }
            }
        } else {
            g.drawImage(this.dieImages[imageIndex], x, y, width, height, null);
            if (this.myPanel.timer % 50 == 0) {
                imageIndex++;
                if (imageIndex >= this.dieImages.length) {
                    //敌机死亡
                    killed();
                    this.myPanel.baseFrame.state = 2;
                }
            }
        }
        //判断主角飞机是否与敌机碰撞
        for (int i = 0; i < this.myPanel.enemys.size(); i++) {
            Enemy enemy = this.myPanel.enemys.get(i);
            if ((enemy.x < this.myPanel.player.x + this.myPanel.player.width && enemy.x + enemy.width > this.myPanel.player.x)
                    && (enemy.y < this.myPanel.player.y + this.myPanel.player.height && enemy.y + enemy.height > this.myPanel.player.y)) {
                this.myPanel.player.underAttack();
                this.myPanel.enemys.remove(enemy);
            }
        }
        //判断主角飞机是否吃到奖品
        for (int i = 0; i < this.myPanel.items.size(); i++) {
            Item item = this.myPanel.items.get(i);
            if ((this.x >= item.x - this.width && this.x <= item.x + item.width)
                    && (this.y >= item.y - this.height && this.y <= item.y + item.height)) {
                //星星数增加
                this.myPanel.star++;
                item.eated();
            }
        }
    }

    private void killed() {
        this.myPanel.pigFeet.remove(this);
    }
    //if猪脚飞机处在被攻击状态，其生命值减少
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}
