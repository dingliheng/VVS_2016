package svv;

/**
 * Created by sudan on 2016/11/25.
 */
import javax.swing.JButton;

public interface Configs {

    public final int EMPTY         = 0;
    public final int MINE          = 1;
    public final int CHECKED       = 2;
    public final int MINE_COUNT    = 10;   // 雷的个数
    public final int BUTTON_BORDER = 50;   // 每个点的尺寸
    public final int MINE_SIZE     = 10;   // 界面规格, 10x10
    public final int START_X       = 20;   // 起始位置x
    public final int START_Y       = 50;   // 起始位置y

    //	public boolean flag = false;
    public JButton[][] jb = new JButton[10][10];
    //	public JLabel jl;
//	public JLabel showTime;
    public int[][] map = new int[10][10];

}
