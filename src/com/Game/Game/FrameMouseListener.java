package com.Game.Game;

import com.Game.BaseFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FrameMouseListener implements MouseListener {
    public BaseFrame baseFrame;

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO Auto-generated method stub
        this.baseFrame.myPanel.player.x = e.getX() - this.baseFrame.myPanel.player.width / 2;
        this.baseFrame.myPanel.player.y = e.getY() - this.baseFrame.myPanel.player.height / 2;
        if (BaseFrame.state==0)BaseFrame.state = 1;
        if (BaseFrame.state==2)BaseFrame.state = 3;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO Auto-generated method stub
    }
    public void mouseReleased(MouseEvent e) {
        //TODO Auto-generated method stub
    }
    public void mouseEntered(MouseEvent e) {
        //TODO Auto-generated method stub
    }
    public void mouseExited(MouseEvent e) {
        //TODO Auto-generated method stub
    }
}
