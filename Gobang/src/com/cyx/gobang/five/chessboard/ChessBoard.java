package com.cyx.gobang.five.chessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.cyx.gobang.five.constant.GobangConstant;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.enums.GameState;
import com.cyx.gobang.five.structs.BestPoint;
import com.cyx.gobang.five.structs.ChessPoint;
import com.cyx.gobang.five.structs.ChessPointMsg;
import com.cyx.gobang.five.structs.ChessPointScore;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月15
 * @function 棋盘上的逻辑主要功能
 *
 */
public class ChessBoard {
    /**
     * 默认棋盘大小为GobangConstant.CHESS_SIZE = 15
     */
    private int CHESS_SIZE = GobangConstant.CHESS_SIZE;
    /**
     * 默认棋盘格子数为GobangConstant.CHESS_GRID = 14
     */
    private int CHESS_GRID = GobangConstant.CHESS_GRID;
    /**
     * 当前下子玩家，默认黑棋先手
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * 棋盘的坐标点信息
     */
    private ChessPointMsg[][] chessPointMsg = new ChessPointMsg[CHESS_SIZE][CHESS_SIZE];
    /**
     * 游戏状态  默认为游戏开始前
     */
    private GameState gameState = GameState.START_BEGIN;
    /**
     * 保存前十二个较好的落子点
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();//最好的十二个位置
    /**
     * 保存还没下且电脑已经预测分数的 150个点  队列里面只保存key，分数存在forecastMap中
     */
    private Queue<String> forecastPoint = new LinkedList<String>();
    /**
     * 保存还没下且电脑已经预测分数的 150个点
     */
    private Map<String, ChessPointMsg[][]> forecastMap = new HashMap<String, ChessPointMsg[][]>();
    
    
    public ChessBoard() { 
    }
    /**
     * 初始化棋盘信息
     */
    private void init() {
	for(int i = 0 ; i < CHESS_SIZE ; i++){
	    for(int j = 0 ; j < CHESS_SIZE ; j++){
		chessPointMsg[i][j] = new ChessPointMsg(new ChessPoint(i,j), ChessPlayer.BLANK);
	    }
	}
    }
    public static void main(String[] args) {
	ChessBoard chessBoard = new ChessBoard();
	chessBoard.init();
	ChessPointMsg chessPointMsg2 = chessBoard.chessPointMsg[5][5];
	ChessPoint point = chessPointMsg2.getPoint();
	System.out.println(point.getPoint_x());
    }
}
