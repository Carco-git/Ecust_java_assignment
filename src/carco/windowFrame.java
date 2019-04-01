package carco;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.FlowLayout;
import java.awt.Font;
@SuppressWarnings("deprecation")
public class windowFrame{
	
	public JFrame frame;
	Draw sin= new Draw(1);
	Draw cos= new Draw(2);
	Draw tan= new Draw(3);
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	mPanel panel_2 = new mPanel();
	JPanel panel_3 = new JPanel();
	JPanel panel_top = new JPanel();
	mPanel panel_sin = new mPanel(new Color(217,234,244),new Color(17,125,187));
	mPanel panel_cos = new mPanel(new Color(219,237,206),new Color(143,200,103));
	mPanel panel_tan = new mPanel(new Color(227,212,230),new Color(139,18,174));
	JPanel panel_4 = new JPanel();
	JButton btnStart = new JButton("开始");
	JButton btnStop = new JButton("暂停");
	JButton btnPause = new JButton("继续");
	JCheckBox rdbtnSinX = new JCheckBox("Sin x");
	JCheckBox rdbtnCosX = new JCheckBox("Cos x");
	JCheckBox rdbtnTanX = new JCheckBox("Tan x");
	Thread DrawCos1 = new Thread(cos);
	Thread DrawSin2 = new Thread(sin);
	Thread DrawTan3 = new Thread(tan);
	
    private static int p_width;
	private static int p_height;
	public static int getP_width() {
		return p_width;
	}

	public static int getP_height() {
		return p_height;
	}

	public windowFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("动态曲线");
		frame.setBackground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
//		开始停止部分 
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnStart.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(btnStart);
		btnStart.addActionListener(new onClick());
		btnStop.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(btnStop);
		btnStop.addActionListener(new onClick());
		btnPause.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(btnPause);
		btnPause.addActionListener(new onClick());
		panel_1.setBackground(Color.WHITE);
		
		
//		函数选择部分		
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		rdbtnSinX.setFont(new Font("Consolas", Font.PLAIN, 12));
		rdbtnSinX.setBackground(new Color(255, 255, 255));
		panel_1.add(rdbtnSinX);
		rdbtnSinX.setName("sin");
		rdbtnSinX.addItemListener(new onCheck(rdbtnSinX));
		rdbtnCosX.setFont(new Font("Consolas", Font.PLAIN, 12));
		rdbtnCosX.setBackground(new Color(255, 255, 255));

		
		rdbtnCosX.setName("cos");
		rdbtnCosX.addItemListener(new onCheck(rdbtnCosX));
		panel_1.add(rdbtnCosX);
		rdbtnTanX.setFont(new Font("Consolas", Font.PLAIN, 12));
		rdbtnTanX.setBackground(new Color(255, 255, 255));
		
		rdbtnTanX.setName("tan");
		rdbtnTanX.addItemListener(new onCheck(rdbtnTanX));
		panel_1.add(rdbtnTanX);

		
//		图形绘制部分开始
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		panel_sin.setLayout(new CardLayout(0,0));
		panel_cos.setLayout(new CardLayout(0,0));
		panel_tan.setLayout(new CardLayout(0,0));
		panel_3.add(panel_sin);
		panel_3.add(panel_cos);
		panel_3.add(panel_tan);
		
//		加入滑动条以实现速度调节
		panel_4.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		scrollBar.addAdjustmentListener(new AdjustmentListener() {			
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int v = -scrollBar.getValue();								//反转滚动条方向
				if(v==0)v++;												//防止出现停滞的情况
				changeSpeed(v);
			}


		});
		scrollBar.setToolTipText("滑动以改变速度");
		scrollBar.setBlockIncrement(5);
		scrollBar.setBackground(new Color(255, 255, 255));
		scrollBar.setMinimum(-100);
		
		panel_4.add(scrollBar);
		
	}


	/*
	 * 区分各个按钮并执行对应操作
	 */	
	class onClick implements ActionListener{
		JButton mButton;
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==btnStart) {
				 try {
					DrawCos1.start();
					DrawSin2.start();
					DrawTan3.start();
					}
				catch(IllegalThreadStateException e1) {
					JOptionPane.showMessageDialog(mButton, "你已经开始了", null, 1);	
				}
			}
			if(e.getSource()==btnStop) {
				DrawCos1.suspend();
				DrawSin2.suspend();		
				DrawTan3.suspend();
			}
			if(e.getSource()==btnPause) {
				DrawCos1.resume();
				DrawSin2.resume();
				DrawTan3.resume();
			}
		}
		
	}
	

    private final JScrollBar scrollBar = new JScrollBar();
	class mPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		public mPanel(Color color,Color bound) {
			super();
			this.color=color;
			this.bound=bound;
		}
		public mPanel() {
			super();
		};
	    private Color color;
	    private Color bound;
		@Override
		    public void paint(Graphics g) {
		        super.paint(g);
		        
		        this.setBackground(Color.WHITE);

				p_width = this.getWidth();
				p_height = this.getHeight();
				g.setColor(bound);
				
				int x=p_width;
				int y=p_height;
		        g.drawLine(0,0,x,0);
		        g.drawLine(0,0,0,y);
		        g.drawLine(0,y,x-1,y);
		        g.drawLine(x-1,0,x-1,y);
		        
				int width = 20;
				g.setColor(color);
				for(int i = 1;;i++) {
		            // 绘制第i条竖直线
		            g.drawLine(i*width, 0,i*width,p_height);	            
		            // 绘制第i条水平线
		            g.drawLine(0,i*width,p_width,i*width);
		            if((i*width)>=p_height&&(i*width)>=p_width)break;
		        }


		    }
	}

	class onCheck implements ItemListener {
		JCheckBox mBox;
		public onCheck(JCheckBox mBox) {
			this.mBox=mBox;
		}
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			System.out.println(mBox.getName());
			switch(mBox.getName()) {
				case "sin":{sinChecked();break;}
				case "cos":cosChecked();break;
				case "tan":tanChecked();break;
			}
		}
		private void sinChecked() {
			if(rdbtnSinX.isSelected()){
				DrawSin2.resume();
				panel_sin.add(sin);
				panel_sin.updateUI();				
	        }
			else {
				if(DrawSin2.isAlive())DrawSin2.suspend();	
				panel_sin.remove(sin);
				panel_sin.updateUI();
			}
		}
		private void cosChecked() {
			if(rdbtnCosX.isSelected()){
				DrawCos1.resume();
				panel_cos.add(cos);
				panel_cos.updateUI();
	        }
			else {
				if(DrawCos1.isAlive())DrawCos1.suspend();	
				panel_cos.remove(cos);
				panel_cos.updateUI();
			}
		}
		
		private void tanChecked() {
			if(rdbtnTanX.isSelected()){
				DrawTan3.resume();
				panel_tan.add(tan);
				panel_tan.updateUI();
	        }
			else {
				if(DrawTan3.isAlive())DrawTan3.suspend();	
				panel_tan.remove(tan);
				panel_tan.updateUI();
			}
		}
	}
	
	private void changeSpeed(int v) {
		sin.changeSpeed(v);
		cos.changeSpeed(v);
		tan.changeSpeed(v);
		
	}


}


