package carco;
/**
 * @author Macro Tan
 * @date 2018.11.11
 * @project WaveSystem
 * ��������ѧ Javaѡ�޿γ̴���ҵ
 */
import javax.swing.JFrame;

public class Control {

	public static void main(String[] args) {
		windowFrame window = new windowFrame();
		window.frame.setVisible(true);
		window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.frame.setBounds(200,100, 500, 655);
	}	
}