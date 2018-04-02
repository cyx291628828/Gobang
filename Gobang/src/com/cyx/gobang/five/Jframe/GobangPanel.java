package com.cyx.gobang.five.Jframe;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GobangPanel extends JPanel {
    
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        GobangPanel chesspanel = new GobangPanel();  
        chesspanel.gobang();  
    } 
    
    public void gobang()  
    {  
        //设置版面  
        JFrame frame = new JFrame();  
        frame.setTitle("五子棋");  
        frame.setResizable(false);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(3);  
        frame.setSize(850,700);  
        //设置布局  
        frame.setLayout(null);  
          
        frame.add(this,BorderLayout.CENTER);  
        frame.add(new Button("hah "), BorderLayout.EAST);
        //在jpanel组件上画棋子  
        JPanel panel1 = new JPanel();  
        panel1.setBackground(Color.DARK_GRAY);  
        panel1.setPreferredSize(new Dimension(150,0));  
        frame.add(panel1,BorderLayout.EAST);  
          
        frame.setVisible(true);  
          
    }
    
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
    }
}
