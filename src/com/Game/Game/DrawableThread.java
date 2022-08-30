package com.Game.Game;

import com.Game.Game.MyPanel;

public class DrawableThread extends Thread {
    public MyPanel myPanel;

    public DrawableThread(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    public void run() {
        while (true) {
            //重绘，调用paintComponent
            this.myPanel.repaint();
            try {
                this.currentThread().sleep(1);
            } catch (InterruptedException e) {
                //TODO 自动生成的catch块
                e.printStackTrace();
            }
        }
    }
}
