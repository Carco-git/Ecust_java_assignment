package carco;
/**
 * @author Macro Tan
 * @date 2018.11.11
 * @project WaveSystem
 * 华东理工大学 Java选修课程大作业
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