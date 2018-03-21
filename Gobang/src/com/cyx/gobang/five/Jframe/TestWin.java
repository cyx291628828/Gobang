package com.cyx.gobang.five.Jframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.logic.GobangLogic;
import com.cyx.gobang.five.structs.ChessPoint;

public class TestWin extends JFrame implements MouseListener {

    ChessBoard cBoard = null;
    int stx = 28, sty = 51, endx = 770, endy = 770;
    int jg = 50;
     public List<MPoint> points = new ArrayList<MPoint>();
    

    public TestWin(String title) { // 定义一个CreateJFrame()方法

	this.setTitle(title);// 窗口标题
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭的操作
	this.setLocation(100, 200);// 窗口位置
	this.setSize(830, 850);// 窗口大小
	this.setVisible(true);// 窗口可见

    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
	int stx = 40, sty = 60, endx = 790, endy = 810;
	int jg = 50;
	// 绘制外层矩形框
	g.drawRect(stx, sty, endx - stx, endy - sty);
	/*
	 * 绘制水平10个，垂直10个方格。 即水平方向9条线，垂直方向9条线， 外围四周4条线已经画过了，不需要再画。 同时内部64个方格填写数字。
	 */
	for (int i = 0; i < 15; i++) {
	    // 绘制第i条水平线
	    g.drawLine(stx, sty + (i * jg), endx, sty + (i * jg));

	    // 绘制第i条竖直线
	    g.drawLine(stx + (i * jg), sty, stx + (i * jg), endy);

	    // 填写第i行从第1个方格到第8个方格里面的数字（方格序号从0开始）
	    g.drawString(i + "", stx - 15, sty + (i * jg) + 5);
	    g.drawString(i + "", stx + (i * jg) - 5, sty - 5);
	}
	
	for(MPoint mPoint : points){
	    if(mPoint.getType().compare(ChessPlayer.BLACK)){
		g.setColor(Color.BLACK);
		g.fillOval((mPoint.getY() * jg) + stx - 10, (mPoint.getX() * jg)+sty - 10, 20, 20);
	    }else{
		g.setColor(Color.BLACK);
		g.drawOval((mPoint.getY() * jg) + stx - 10, (mPoint.getX() * jg)+sty - 10, 20, 20);
	    }
	}
    }

    public static void main(String args[]) {// 在主方法中调用createJFrame()方法
	TestWin testWin = new TestWin("创建一个JFrame窗体");
	testWin.cBoard = new ChessBoard();
	testWin.addMouseListener(testWin);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	int x = ((arg0.getX() - stx) + jg / 2 ) / jg ;
	int y = ((arg0.getY() - sty) + jg / 2 ) / jg ;
	ChessPoint chessPoint = new ChessPoint(y,x);
	ChessPlayer player = cBoard.getPlayer();
	if(GobangLogic.getMe().dropChess(cBoard, chessPoint, player)){
	    MPoint mPoint = new MPoint(y,x,player);
	    points.add(mPoint);
	    repaint(arg0.getX()-25,arg0.getY()-25,50, 50);
	}
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	System.out.println("dd");

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}

class DrawPanel extends JPanel {// 画线的JPanel
    @Override
    public void paint(Graphics g) {
	super.paint(g);
	g.setColor(Color.BLACK);
	int stx = 20, sty = 20, endx = 770, endy = 770;
	int jg = 50;
	// 绘制外层矩形框
	g.drawRect(stx, sty, endx - stx, endy - sty);
	/*
	 * 绘制水平10个，垂直10个方格。 即水平方向9条线，垂直方向9条线， 外围四周4条线已经画过了，不需要再画。 同时内部64个方格填写数字。
	 */
	for (int i = 0; i < 15; i++) {
	    // 绘制第i条水平线
	    g.drawLine(stx, sty + (i * jg), endx, sty + (i * jg));

	    // 绘制第i条竖直线
	    g.drawLine(stx + (i * jg), sty, stx + (i * jg), endy);

	    // 填写第i行从第1个方格到第8个方格里面的数字（方格序号从0开始）
	    g.drawString(i + "", stx - 15, sty + (i * jg) + 5);
	    g.drawString(i + "", sty + (i * jg) - 5, stx - 5);
	}
	
    }
}