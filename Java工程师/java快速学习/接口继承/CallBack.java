import java.awt.Toolkit;

/**
 * 接口回调函数 callback
 */

public interface ActionListener{
    void actionPerformed(ActionEvent event);
}

class TimerPrinter implements ActionListener{

    public void actionPerformed(ActionEvent event){
        System.out.println("At the tone,the time is"+new Data());
        Toolkit.getDefaultToolkit().beep();
    }
}