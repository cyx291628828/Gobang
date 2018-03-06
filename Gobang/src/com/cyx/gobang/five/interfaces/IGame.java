package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;
import com.cyx.gobang.five.enums.ChessPlayer;
import com.cyx.gobang.five.enums.GameState;

public class IGame{

    private OnGobangGameBefore gobangGameBefore = new OnGobangGameBefore() {
        
        @Override
        public void onGameBefore(ChessBoard cBoard) {
    	
        }
    };
    
    private OnGobangGameStart gobangGameStart = new OnGobangGameStart() {
        
        @Override
        public void onGameStart(ChessBoard cBoard) {
    	
        }
    };
    
    private OnGobangGamePause gobangGamePause = new OnGobangGamePause() {
        
        @Override
        public void onGamePause(ChessBoard cBoard) {
    	
        }
    };
    
    private OnGobangGameSave gobangGameSave = new OnGobangGameSave() {
        
        @Override
        public void onGameSave(ChessBoard cBoard) {
    	
        }
    };
    
    private OnGobangGameOver gobangGameOver = new OnGobangGameOver() {
        
        @Override
        public void onGameWin(ChessBoard cBoard, ChessPlayer winPlayer) {
            cBoard.upState(GameState.OVER);
        }
        
    };

    public OnGobangGameBefore getGobangGameBefore() {
        return gobangGameBefore;
    }

    public void setGobangGameBefore(OnGobangGameBefore gobangGameBefore) {
        this.gobangGameBefore = gobangGameBefore;
    }

    public OnGobangGameStart getGobangGameStart() {
        return gobangGameStart;
    }

    public void setGobangGameStart(OnGobangGameStart gobangGameStart) {
        this.gobangGameStart = gobangGameStart;
    }

    public OnGobangGamePause getGobangGamePause() {
        return gobangGamePause;
    }

    public void setGobangGamePause(OnGobangGamePause gobangGamePause) {
        this.gobangGamePause = gobangGamePause;
    }

    public OnGobangGameSave getGobangGameSave() {
        return gobangGameSave;
    }

    public void setGobangGameSave(OnGobangGameSave gobangGameSave) {
        this.gobangGameSave = gobangGameSave;
    }

    public OnGobangGameOver getGobangGameOver() {
        return gobangGameOver;
    }

    public void setGobangGameOver(OnGobangGameOver gobangGameOver) {
        this.gobangGameOver = gobangGameOver;
    }


}
