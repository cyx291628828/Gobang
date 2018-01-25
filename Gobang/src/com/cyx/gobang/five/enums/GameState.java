package src.com.cyx.gobang.five.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameState {
    /**
     * 游戏开始前
     */
    START_BEFORE(0x0000,1),
    /**
     * 游戏开始
     */
    START(0x0001,1),
    /**
     * 游戏暂停
     */
    PAUSE(0x0002,2),
    /**
     * 游戏存盘
     */
    SAVE(0x0004,3),
    /**
     * 游戏结束前
     */
    OVER_BEGIN(0x0008,1),
    /**
     * 游戏结束
     */
    OVER(0x0010,1),
    /**
     * 游戏结束后
     */
    OVER_AFTER(0x0020,1),
    ;
    private int state;
    private int group;
    private int mark;
    
    GameState(int state, int group) {
        this.state = state;
        this.group = group;
        this.mark = 0;
    }

    GameState(int state, int group, int mark) {
        this.state = state;
        this.group = group;
        this.mark = mark;
    }
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGroup() {
        return group;
    }

    public int getMark() {
        return mark;
    }

    public boolean compare(int state) {
        return ((this.state & state) != 0);
    }
    
    public static int clearGroupAndSet(int statevalue, GameState state) {
        statevalue = (statevalue & state.getMark());
        statevalue = (statevalue | state.getState());
        return statevalue;
    }
    
    public static void initMarks() {
        //
	GameState[] values = GameState.values();
        Map<Integer, List<GameState>> vmaps = new HashMap<>();
        for (GameState value : values) {
            List<GameState> vlist = vmaps.get(value.group);
            if (vlist == null) {
                vlist = new ArrayList<>();
                vmaps.put(value.group, vlist);
            }
            vlist.add(value);
        }

        for (Map.Entry<Integer, List<GameState>> entrySet : vmaps.entrySet()) {
            List<GameState> value = entrySet.getValue();
            if (value != null) {
                int nmark = 0;
                for (GameState v1 : value) {
                    if (v1 != null) {
                        nmark = nmark | v1.state;
                    }
                }
                nmark = ~nmark;
                for (GameState v1 : value) {
                    if (v1.mark == 0) {
                        v1.mark = nmark;
                    } else {
                        v1.mark = (v1.mark & nmark);
                    }
                }
            }
        }
    }
    static {
        initMarks();
    }
}
