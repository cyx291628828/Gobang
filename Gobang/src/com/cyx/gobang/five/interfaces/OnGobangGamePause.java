package src.com.cyx.gobang.five.interfaces;

import src.com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGamePause {

    /**
     * 在游戏暂停时调用
     * @param cBoard
     */
    public void onGamePause(ChessBoard cBoard);
}
