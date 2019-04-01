package carco;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Draw extends Canvas implements Runnable,Speed{

	private static final long serialVersionUID = 1L;
	private int gap=1;	//��ʼ�ٶ�
    private int move = 0;
    //�ƶ��ٶȺͷ�����ֵ���ƣ���ֵ����
    private int mode;
    private Color color;
    private Color bound;
    
    public Draw(int mode) {
    	this.mode=mode;
    	switch(mode){
    	case 1: 
    		this.color = new Color(217,234,244);
    		this.bound = new Color(17,125,187);
    		break;
    	case 2:
    		this.color = new Color(219,237,206);
    		this.bound = new Color(143,200,103);
    		break;
    	case 3:
    		this.color = new Color(227,212,230);
    		this.bound = new Color(139,18,174);
    		break;
    	}
    }


	@Override
    public void update(Graphics g){

        Image ImageBuffer = createImage(windowFrame.getP_width(), windowFrame.getP_height());//����ͼ�λ�����  
        Graphics GraImage = ImageBuffer.getGraphics();//��ȡͼ�λ�������ͼ��������  
        paint(GraImage);//��paint�����б�д�Ļ�ͼ���̶�ͼ�λ�������ͼ  
        GraImage.dispose();//�ͷ�ͼ����������Դ  
        g.drawImage(ImageBuffer, 0, 0, this);//��ͼ�λ��������Ƶ���Ļ��  
    }
    
	@Override
    public void  paint(Graphics g) {
    	super.paint(g);
        setBackground(Color.WHITE);
        int width = 20;
        int x =windowFrame.getP_width();
        int y=windowFrame.getP_height();
        g.setColor(bound);
        g.drawLine(0,0,x,0);
        g.drawLine(0,0,0,y);
        g.drawLine(0,y,x,y);
        g.drawLine(x-1,0,x-1,y);  //���߿�
        g.setColor(color);
        for(int i = 1;;i++) {    
        	// ���Ƶ�i����ֱ��
            g.drawLine(i*width, 0,i*width,y);	            
            // ���Ƶ�i��ˮƽ��
            g.drawLine(0,i*width,x,i*width);
            if((i*width)>=y&&(i*width)>=x)break;
        }
        int height = y/2-2;
        g.setColor(bound);
        switch(mode) {
        case 1:
        	for (int i = 0; i < x; i++) {
                g.drawLine(i, (int) (height+2 - height * Math.sin((move + i) * Math.PI
                        / 180)), i +1, (int) (height - height * Math.sin((move + i +1)
                        * Math.PI / 180)));
            } break;
        case 2:
        	for (int i = 0; i < x; i++) {
                g.drawLine(i, (int) (height+2 - height * Math.cos((move + i) * Math.PI
                        / 180)), i +1, (int) (height - height * Math.cos((move + i +1)
                        * Math.PI / 180)));
            }break;
        case 3:
        	 for (int i = 0; i < x; i++) {
             	int y1= (int) (height+1 - height * Math.tan((move + i) * Math.PI / 180));
             	int y2= (int) (height-1 - height * Math.tan((move + i +1) * Math.PI / 180));
             	if(y1<y2)continue;//�ж��ٽ��
                 g.drawLine(i,y1, i +1,y2);
             }break;
        }
           
    }

    //���岻���ĸı��ٶ�
    @Override
    public void speedUp() {
    	gap++;
    }
    @Override
    public void slowDown() {
    	gap--;
    }
    @Override
    public void changeSpeed(int v) {
    	gap=v;
    }


	@Override
	public void run() {
		while (true) {
            move += gap;
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	

}
