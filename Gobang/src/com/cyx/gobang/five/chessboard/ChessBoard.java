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
 * @author ������
 * @date 2017��11��15
 * @function �����ϵ��߼���Ҫ����
 *
 */
public class ChessBoard {
    /**
     * Ĭ�����̴�СΪGobangConstant.CHESS_SIZE = 15
     */
    private int CHESS_SIZE = GobangConstant.CHESS_SIZE;
    /**
     * Ĭ�����̸�����ΪGobangConstant.CHESS_GRID = 14
     */
    private int CHESS_GRID = GobangConstant.CHESS_GRID;
    /**
     * ��ǰ������ң�Ĭ�Ϻ�������
     */
    private ChessPlayer player = ChessPlayer.BLACK;
    /**
     * ���̵��������Ϣ
     */
    private ChessPointMsg[][] chessPointMsg = new ChessPointMsg[CHESS_SIZE][CHESS_SIZE];
    /**
     * ��Ϸ״̬  Ĭ��Ϊ��Ϸ��ʼǰ
     */
    private GameState gameState = GameState.START_BEGIN;
    /**
     * ����ǰʮ�����Ϻõ����ӵ�
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();//��õ�ʮ����λ��
    /**
     * ���滹û���ҵ����Ѿ�Ԥ������� 150����  ��������ֻ����key����������forecastMap��
     */
    private Queue<String> forecastPoint = new LinkedList<String>();
    /**
     * ���滹û���ҵ����Ѿ�Ԥ������� 150����
     */
    private Map<String, ChessPointMsg[][]> forecastMap = new HashMap<String, ChessPointMsg[][]>();
    
    
    public ChessBoard() { 
    }
    /**
     * ��ʼ��������Ϣ
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
