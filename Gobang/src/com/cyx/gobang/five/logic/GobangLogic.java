package src.com.cyx.gobang.five.logic;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import src.com.cyx.gobang.five.chessboard.ChessBoard;
import src.com.cyx.gobang.five.enums.ChessPlayer;
import src.com.cyx.gobang.five.structs.BestPoint;
import src.com.cyx.gobang.five.structs.ChessPoint;
import src.com.cyx.gobang.five.structs.ChessPointMsg;
import src.com.cyx.gobang.five.structs.ForecastPointMsg;

public class GobangLogic {

    private static Logger logger = LogManager.getLogger(GobangLogic.class.getName());

    private static volatile GobangLogic gobangLogic;
    private static final Object S_OBJECT = new Object();

    public static GobangLogic getMe() {
	if (gobangLogic == null) {
	    synchronized (S_OBJECT) {
		if (gobangLogic == null) {
		    gobangLogic = new GobangLogic();
		}
	    }
	}
	return gobangLogic;
    }

    private GobangLogic() {

    }

    /**
     * ���� ���� ���
     * 
     * @param cBoard
     * @param cPoint
     *            ���ӵ�
     * @param cPlayer
     *            ���ӷ�
     * @return
     */
    public boolean dropChess(ChessBoard cBoard, ChessPoint cPoint, ChessPlayer cPlayer) {
	if (!checkPointIsDrop(cBoard, cPoint, ChessPlayer.BLANK)) {
	    return false;
	}
	if(cBoard.getRecordList().size() == 0){
	    cBoard.setPlayer(cPlayer);
	}else {
	    if(cBoard.getPlayer().compare(cPlayer)){
		logger.error("���Ӵ���:��ǰ���ӷ���ǰһ����ͬ");
		return false;
	    }else {
		cBoard.setPlayer(cPlayer);
	    }
	}
	ForecastPointMsg forecastPointMsg = cBoard.getForecastPointMsg();
	forecastPointMsg.addDropChess(cPoint, cPlayer);
	// �������
	calculateScore(forecastPointMsg.getChessPointMsgs());
	cBoard.getRecordList().add(forecastPointMsg.getDropChessMsg(cPoint));
	return true;
    }

    private void calculateScore(ChessPointMsg[][] chessPointMsgs) {
	
    }

    public boolean dropChess(ChessBoard cBoard, ChessPoint cPoint, int cMark) {
	return dropChess(cBoard, cPoint, ChessPlayer.formMark(cMark));
    }

    /**
     * ����������ΪnullĬ�ϼ����Ƿ�Ϊ��λ��
     * 
     * @param cBoard
     * @param cPoint
     * @return
     */
    public boolean checkPointIsDrop(ChessBoard cBoard, ChessPoint cPoint) {
	return checkPointIsDrop(cBoard, cPoint, ChessPlayer.BLANK);
    }

    /**
     * ���cPoint�������ӷ��Ƿ���cPlayer�е�һ��
     * 
     * @param cBoard
     * @param cPoint
     * @param cPlayer
     * @return
     */
    public boolean checkPointIsDrop(ChessBoard cBoard, ChessPoint cPoint, ChessPlayer... cPlayer) {
	if (cPlayer == null) {
	    cPlayer = new ChessPlayer[] { ChessPlayer.BLANK };
	}
	if (!checkChessPointIndex(cBoard, cPoint)) {
	    return false;
	}
	ChessPointMsg[][] chessPointMsg = cBoard.getForecastPointMsg().getChessPointMsgs();
	ChessPlayer player = chessPointMsg[cPoint.getPoint_x()][cPoint.getPoint_y()].getPlayer();
	for (ChessPlayer cp : cPlayer) {
	    if (player.compare(cp)) {
		return true;
	    }
	}
	return false;
    }

    public boolean checkChessPointIndex(ChessBoard cBoard, ChessPoint cPoint) {
	if (cPoint.getPoint_x() < 0 || cPoint.getPoint_y() < 0) {
	    return false;
	}
	ChessPointMsg[][] chessPointMsg = cBoard.getForecastPointMsg().getChessPointMsgs();
	if (cPoint.getPoint_x() >= chessPointMsg.length) {
	    return false;
	}
	if (cPoint.getPoint_y() >= chessPointMsg.length) {
	    return false;
	}
	return true;
    }

    private String listToJson(ChessBoard cBoard) {
	String jsonString = JSON.toJSONString(cBoard.getRecordList());
	cBoard.getRecordList();
	return "";
    }

    public static void main(String[] args) {
	logger.error("����");
	ChessBoard cbBoard = new ChessBoard();
	BestPoint bPoint = new BestPoint();
	cbBoard.getForecastPointMsg().getBetterPoints().add(bPoint);
	bPoint.setPoint(new ChessPoint(2, 3));
	String jsonString = JSON.toJSONString(cbBoard.getForecastPointMsg().getBetterPoints());
	System.err.println((new Date()).getTime());
    }
}
