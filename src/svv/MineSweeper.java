package svv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author
 */
public class MineSweeper extends JFrame implements ActionListener, Runnable,
        MouseListener, Configs {

    public static final long serialVersionUID = -2417758397965039613L;

    private boolean flag;
    JButton[][] jb;
    private JLabel jl;
    JLabel showTime;
    int[][] map;

    /**
     * �?测某点周围是否有雷，周围点的坐标可由该数组计算得�?
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
                i++; // 不记重复产生的雷
            }
        }
    }

    /**
     * 将button数组放到frame上，与map[][]数组相对�?
     */
    public void makeButton() {

        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                jb[i][j] = new JButton();

                jb[i][j].addActionListener(this);
                jb[i][j].addMouseListener(this);

                jb[i][j].setName(i + "_" + j); // 方便点击是判断点击了哪个按钮

                jb[i][j].setBounds(j * BUTTON_BORDER + START_X, i
                        * BUTTON_BORDER + START_Y, BUTTON_BORDER, BUTTON_BORDER);
                this.add(jb[i][j]);

            }
        }
    }

    public void init() {

        flag = false;

        jl.setText("welcome，total is" + MINE_COUNT);
        jl.setVisible(true);
        jl.setBounds(20, 20, 500, 30);
        this.add(jl);

        showTime.setText("time: 0 s");
        showTime.setBounds(400, 20, 100, 30);
        this.add(showTime);

        makeMine();
        makeButton();
        this.setSize(550, 600);
        this.setLocation(700, 100);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MineSweeper(String title) {
        super(title);

        this.setLayout(null);

        jb = new JButton[MINE_SIZE][MINE_SIZE];
        jl = new JLabel();
        showTime = new JLabel();
        map = new int[MINE_SIZE][MINE_SIZE]; // 将按钮映射到数组�?
    }

    public static void main(String[] args) {
        MineSweeper test = new MineSweeper("Hello Miner!");
        test.init();
        test.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        int x, y;
        if ((obj instanceof JButton) == false) {
            showMessage("", "inside error");
            return;
        }
        String[] tmp_str = ((JButton) obj).getName().split("_");
        x = Integer.parseInt(tmp_str[0]);
        y = Integer.parseInt(tmp_str[1]);

        if (map[x][y] == MINE) {

            showMessage("died", "you lose");
            flag = true;
            showMine();
            return;
        }else if (map[x][y] != MINE && map[x][y] != CHECKED){
            String number = Integer.toString(search(x,y));
            jb[x][y].setText(number);


        }

        //  dfs(x, y);

        checkSuccess();
    }

    /**
     * 每次点击完后，判断有没有把全部雷找到，�?�过计算状�?�为enable的按钮的个数来判�?
     */
    private int checkSuccess() {
        int cnt = 0;  //查看当前已经找到的雷�?
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
            showMessage("success", "total time:" + tmp_str + "s");
            flag = true;
            showMine();

        }
        return cnt;
    }

    public int search(int x, int y){
        int i, tx, ty, cnt = 0;

        for (i = 0; i < 8; i++) {
            tx = x + mv[i][0];
            ty = y + mv[i][1];
            if (tx >= 0 && tx < MINE_SIZE && ty >= 0 && ty < MINE_SIZE) {
                if (map[tx][ty] == MINE) {
                    cnt++;// 改点附近雷数统计
                } else if (map[tx][ty] == EMPTY) {
                    ;
                } else if (map[tx][ty] == CHECKED) {
                    ;

                }
            }
        }
        if(cnt == 0){
            jb[x][y].setText(cnt + "");
        }
        return cnt;
    }


    /**
     * 在j1标签上显示信�?
     *
     * @param title
     * @param info
     */
    void showMessage(String title, String info) {
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
            showTime.setText("spend time:" + t + " s");
        }

    }

    void showMine() {
        for (int i = 0; i < MINE_SIZE; i++) {
            for (int j = 0; j < MINE_SIZE; j++) {
                if (map[i][j] == MINE) {
                    jb[i][j].setText("mine");

                }
            }
        }
    }

    public void makeMove(int row, int col, String status){
        jb[row][col].setText(String.valueOf(status));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            Object obj = e.getSource();
            if ((obj instanceof JButton) == false) {
                showMessage("error", "inside error");
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
    public int getWinner(){

        if(checkSuccess() != 10){
            return 0;
        }
        for(int i = 0; i < MINE_SIZE; i++){
            for(int j = 0; j < MINE_SIZE; j++){
                if(jb[i][j].getText() == "mine"){
                    return 2;
                }
            }
        }
        return 1;
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
