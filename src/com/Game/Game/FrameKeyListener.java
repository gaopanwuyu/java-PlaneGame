package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameKeyListener implements KeyListener {
    public BaseFrame baseFrame;

    public void keyTyped(KeyEvent e) {
        //TODO Auto-generated method stub

    }
    @Override
    public void keyPressed(KeyEvent e) {
        //TODO Auto-generated method stub
        //按下对应按钮，获取按钮的值
        int F = 10;

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT :
                this.baseFrame.myPanel.player.x -= F;
                if (BaseFrame.state==0)BaseFrame.state = 1;
                if (BaseFrame.state==2)BaseFrame.state = 3;
                break;
            case KeyEvent.VK_RIGHT:
                this.baseFrame.myPanel.player.x +=F;
                if (BaseFrame.state==0)BaseFrame.state = 1;
                if (BaseFrame.state==2)BaseFrame.state = 3;
                break;
            case KeyEvent.VK_UP:
                this.baseFrame.myPanel.player.y -= F;
                if (BaseFrame.state==0)BaseFrame.state = 1;
                if (BaseFrame.state==2)BaseFrame.state = 3;
                break;
            case KeyEvent.VK_DOWN:
                this.baseFrame.myPanel.player.y += F;
                if (BaseFrame.state==0)BaseFrame.state = 1;
                if (BaseFrame.state==2)BaseFrame.state = 3;
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        //TODO Auto-generated method stub
    }
}
