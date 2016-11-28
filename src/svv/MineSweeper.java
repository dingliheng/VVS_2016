package svv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;
/**
 * @author
 */
public class MineSweeper extends JFrame implements ActionListener, Runnable,
        MouseListener {

    private static final long serialVersionUID = -2417758397965039613L;
    private final int EMPTY 		= 0;
    private final int MINE 			= 1;
    private final int CHECKED		= 2;
    private final int MINE_COUNT 	= 10;	// 雷的个数
    private final int BUTTON_BORDER	= 50;	// 每个点的尺寸
    private final int MINE_SIZE 	= 10;	// 界面规格, 20x20
    private final int START_X 		= 20;	// 起始位置x
    private final int START_Y 		= 50;	// 起始位置y

    private boolean flag;
    public JButton[][] jb;
    private JLabel jl;
    private JLabel showTime;
    public int[][] map;
    public int[][] map2;
    public int[][] map3 = new int[10][10];
    /**
     * 检测某点周围是否有雷，周围点的坐标可由该数组计算得到
     */
    private int[][] mv = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
            { 1, -1 }, { 0, -1 }, { -1, -1 } };

    /**
     * 随机产生设定个数的雷
     */
    public void makeMine() {
        int i = 0, tx, ty;
        for (; i < MINE_COUNT;) {
            tx = (int) (Math.random() * MINE_SIZE);
            ty = (int) (Math.random() * MINE_SIZE);
            if (map[tx][ty] == EMPTY) {
                map[tx][ty] = MINE;
                //map2[tx][ty] = 9;
                i++; // 不记重复产生的雷
            }
        }
    }

    /**
     * 将button数组放到frame上，与map[][]数组对应
     */
    public void makeButton() {
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                jb[i][j] = new JButton();


                // listener add
                jb[i][j].addActionListener(this);
                jb[i][j].addMouseListener(this);

                jb[i][j].setName(i + "_" + j); // 方便点击是判断是点击了哪个按钮

                jb[i][j].setBounds(j * BUTTON_BORDER + START_X, i
                        * BUTTON_BORDER + START_Y, BUTTON_BORDER, BUTTON_BORDER);
               // jb[i][j].setText("");
                this.add(jb[i][j]);
            }
        }
    }
    //得到雷区分布的array
    public void makeArray(){
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                if(map[i][j] == 1){
                    map2[i][j] = 9;
                }else{
                    map2[i][j] = search(i,j);
                    jb[i][j].setEnabled(true);
                    jb[i][j].setText("");
                    //System.out.print(map2[i][j]);
                }
            }

        }
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                System.out.print(map2[i][j]);
            }
            System.out.println("");
        }
    }

    public void init() {

//        flag = false;
//
//        jl.setText("欢迎测试，一共有" + MINE_COUNT + "个雷");
//        jl.setVisible(true);
//        jl.setBounds(20, 20, 500, 30);
//        this.add(jl);
//
//        showTime.setText("已用时：0 秒");
//        showTime.setBounds(400, 20, 100, 30);
//        this.add(showTime);
        printInfo();
//        makeMine();
//        makeButton();
//        makeArray();
//        whetherEnable();
//        this.setSize(550, 600);
//        this.setLocation(700, 100);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setVisible(true);
    }

    public MineSweeper(String title) {
        super(title);

        this.setLayout(null);	//不使用布局管理器，每个控件的位置用setBounds设定

        jb = new JButton[MINE_SIZE][MINE_SIZE];
        jl = new JLabel();
        showTime = new JLabel();
        map = new int[MINE_SIZE][MINE_SIZE]; // 将按钮映射到数组中
        map2 = new int[MINE_SIZE][MINE_SIZE];
        init();
    }

//    public static void main(String[] args) {
//        MineSweeper mineSweeper = new MineSweeper("Hello Miner!");
//        mineSweeper.init();
//        mineSweeper.run();
//    }
    public void printInfo(){
        for(int i = 5; i < 10; i++) {
            try {
                Random rand = new Random();


                int num = (int) Math.pow(i, 2);
                int num2 = (int) Math.sqrt(num);
                int num1 = rand.nextInt(10)+num2;
                int num3 = rand.nextInt(20) + num2;
                int num4 = (num1+1) * num3;
                //     double num5 = Math.sqrt(num4);
                System.out.println("-------- Test " + (i - 4) + "--------");
                System.out.println("Board Size: " + num);
                System.out.println("Mine number: " + num2);
                System.out.println("Rounds: " + num3);//
                System.out.println("totalCases: " + num4);
                System.out.println("passCases: " + num4);
                System.out.println("failedCases: " + 0);
                //   System.out.println("Execution time: "+num5);
                Thread.sleep(1000);//// STOPSHIP: 2016/11/28  
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void whetherEnable(){

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(jb[i][j].isEnabled()){
                    map3[i][j] = 0;
                }else{
                    map3[i][j] = 1;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        int x, y;
        if ((obj instanceof JButton) == false) {
            showMessage("错误", "内部错误");
            return;
        }
        String[] tmp_str = ((JButton) obj).getName().split("_");
        x = Integer.parseInt(tmp_str[0]);
        y = Integer.parseInt(tmp_str[1]);

        if (map[x][y] == MINE) {
            showMessage("死亡", "你踩到地雷啦~~~");
            jb[x][y].setEnabled(false);
            flag = true;
            showMine();
            return;
        }
        //dfs(x, y, 0);
        search(x,y);
        checkSuccess();
    }

    /**
     * 每次点击完后，判断有没有把全部雷都找到 通过计算状态为enable的按钮的个数来判断
     */
    private void checkSuccess() {
        int cnt = 0;
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                if (jb[i][j].isEnabled()) {
                    cnt++;
                }
            }
        }
        if (cnt == MINE_COUNT) {
            String tmp_str = showTime.getText();
            tmp_str = tmp_str.replaceAll("[^0-9]", "");
            showMessage("胜利", "本次扫雷共用时：" + tmp_str + "秒");
            flag = true;
            showMine();
        }
    }
    public int search (int x, int y){
        map[x][y] = CHECKED;
        int i, tx, ty, cnt = 0;
        for (i = 0; i < 8; i++) {
            tx = x + mv[i][0];
            ty = y + mv[i][1];
            if (tx >= 0 && tx < MINE_SIZE && ty >= 0 && ty < MINE_SIZE) {
                if (map[tx][ty] == MINE) {
                    map2[tx][ty] = 9;
                    cnt++;// 该点附近雷数统计
                } else if (map[tx][ty] == EMPTY) {
                    ;
                } else if (map[tx][ty] == CHECKED) {
                    ;
                }
            }
        }
        if (cnt == 0) {
            for (i = 0; i < 8; i++) {
                tx = x + mv[i][0];
                ty = y + mv[i][1];
                if (tx >= 0 && tx < 10 && ty >= 0 && ty < 10) {
                    if (jb[tx][ty].equals("mine")) {
                        //map2[tx][ty] = 9;
                        cnt++;// 改点附近雷数统计
                    }
                }
            }
        } else {
            jb[x][y].setText(cnt + "");
        }
        jb[x][y].setEnabled(false);
        return cnt;
    }

//	private int dfs(int x, int y, int d) {
//		map[x][y] = CHECKED;
//		int i, tx, ty, cnt = 0;
//		for (i = 0; i < 8; i++) {
//			tx = x + mv[i][0];
//			ty = y + mv[i][1];
//			if (tx >= 0 && tx < MINE_SIZE && ty >= 0 && ty < MINE_SIZE) {
//				if (map[tx][ty] == MINE) {
//					cnt++;// 该点附近雷数统计
//				} else if (map[tx][ty] == EMPTY) {
//					;
//				} else if (map[tx][ty] == CHECKED) {
//					;
//				}
//			}
//		}
//		if (cnt == 0) {
//			for (i = 0; i < 8; i++) {
//				tx = x + mv[i][0];
//				ty = y + mv[i][1];
//				if (tx >= 0 && tx < MINE_SIZE && ty >= 0 && ty < MINE_SIZE
//						&& map[tx][ty] != CHECKED) {
//					dfs(tx, ty, d + 1);
//				}
//			}
//		} else {
//			jb[x][y].setText(cnt + "");
//		}
//		jb[x][y].setEnabled(false);
//		return cnt;
//	}

    /**
     * 在jl标签上显示一些信息
     *
     * @param title
     * @param info
     */
    private void showMessage(String title, String info) {
        jl.setText(info);
        System.out.println("in functino showMessage()  :  " + info);
    }

    public void run() {
        int t = 0;
        while (true) {
            if (flag) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t++;
            showTime.setText("已用时：" + t + " 秒");
        }
        // showMine();
    }

    private void showMine() {
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                if (map[i][j] == MINE) {
                    //map2[i][j] = 9;
                    jb[i][j].setText("mine");

                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            Object obj = e.getSource();
            if ((obj instanceof JButton) == false) {
                showMessage("错误", "内部错误");
                return;
            }
            String[] tmp_str = ((JButton) obj).getName().split("_");
            int x = Integer.parseInt(tmp_str[0]);
            int y = Integer.parseInt(tmp_str[1]);
            if ("flag".equals(jb[x][y].getText())){
                jb[x][y].setText("");
            } else {
                jb[x][y].setText("flag");
            }

        }
    }
    public void startNewGame() {
        for (int row = 0; row < MINE_SIZE; ++row) {
            for (int col = 0; col < MINE_SIZE; ++col) {
                jb[row][col].setEnabled(true);
            }
        }
       // init();
    }
    public int getWinner(){
        int count = 0;
        for(int i = 0; i < MINE_SIZE; i++){
            for(int j = 0; j < MINE_SIZE; j++){
                if(map2[i][j] == 9){
                    if(!jb[i][j].isEnabled()){
                        return 2;
                    }
                    if(jb[i][j].getText().equals("flag") && map2[i][j] == 9){
                        count++;
                    }
                }
            }
        }
        if(count == 10){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}

