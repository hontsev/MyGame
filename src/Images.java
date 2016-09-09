import java.awt.Image;
import java.awt.Toolkit;


public class Images {
	public Image img1;
	public Image img2;
	public Image img3;
	public Image img1_1_1;
	public Image img1_1_2;
	public Image img1_1_3;
	public Image img1_1_4;
	public Image img1_1_5;
	public Image img1_3_1;
	public Image img1_3_2;
	public Image img1_3_3;
	public Image img1_3_4;
	public Image img1_3_5;
	public Image img_title1;
	public Image img_title2;
	public Image img_title3;
	public Image img_gameover;
	public Image img_dead;
	public Image img_sc1;
	public Image img_sc2;
	public Image img_floor1;
	public Image img_floor2;
	public Image img_floor2_2;
	public Image img_floor3;
	public Image img_pointer;
	public Image img_money;
	public Image img_win1;
	public Image img_win2;
	public Image img_gc_01;
	public Image img_gc_02;
	public Image img_gc_03;
	public Image img_gc_04;
	public Image img_gc_05;
	public Image img_gc_06;
	public Image img_gc_07;
	public Image img_gc_08;
	public Image img_gc_09;
	public Image img_gc_10;
	public Image img_gc_11;
	public Image img_gc_12;
	public Image img_gc_13;
	public Images()
	{
		try{
	    	img1 =Toolkit.getDefaultToolkit().getImage("img/lm_01.png");
	    	img2 =Toolkit.getDefaultToolkit().getImage("img/nine_01.png");
	    	img3 =Toolkit.getDefaultToolkit().getImage("img/sc_01.png");
	    	img1_1_1=Toolkit.getDefaultToolkit().getImage("img/lm_07.png");
	    	img1_1_2=Toolkit.getDefaultToolkit().getImage("img/lm_11.png");
	    	img1_1_3=Toolkit.getDefaultToolkit().getImage("img/lm_08.png");
	    	img1_1_4=Toolkit.getDefaultToolkit().getImage("img/lm_09.png");
	    	img1_1_5=Toolkit.getDefaultToolkit().getImage("img/lm_10.png");
	    	img1_3_1=Toolkit.getDefaultToolkit().getImage("img/lm_02.png");
	    	img1_3_2=Toolkit.getDefaultToolkit().getImage("img/lm_03.png");
	    	img1_3_3=Toolkit.getDefaultToolkit().getImage("img/lm_05.png");
	    	img1_3_4=Toolkit.getDefaultToolkit().getImage("img/lm_06.png");
	    	img1_3_5=Toolkit.getDefaultToolkit().getImage("img/lm_04.png");
	    	img_title1=Toolkit.getDefaultToolkit().getImage("img/title_01.png");
	    	img_title2=Toolkit.getDefaultToolkit().getImage("img/title_02.png");
	    	img_title3=Toolkit.getDefaultToolkit().getImage("img/title_03.png");
	    	img_floor1=Toolkit.getDefaultToolkit().getImage("img/floor_01.png");
	    	img_floor2=Toolkit.getDefaultToolkit().getImage("img/floor_02.png");
	    	img_floor2_2=Toolkit.getDefaultToolkit().getImage("img/floor_02_2.png");
	    	img_floor3=Toolkit.getDefaultToolkit().getImage("img/floor_03.png");
	    	img_sc1=Toolkit.getDefaultToolkit().getImage("img/sc_01.png");
	    	img_sc2=Toolkit.getDefaultToolkit().getImage("img/sc_02.png");
	    	img_dead=Toolkit.getDefaultToolkit().getImage("img/dead_01.png");
	    	img_gameover=Toolkit.getDefaultToolkit().getImage("img/gameover_01.png");
	    	img_pointer=Toolkit.getDefaultToolkit().getImage("img/point_01.png");
	    	img_money=Toolkit.getDefaultToolkit().getImage("img/money_01.png");
	    	img_win1=Toolkit.getDefaultToolkit().getImage("img/win_01.png");
	    	img_win2=Toolkit.getDefaultToolkit().getImage("img/win_02.png");
	    	
	    	img_gc_01=Toolkit.getDefaultToolkit().getImage("img/gc_01.jpg");
	    	img_gc_02=Toolkit.getDefaultToolkit().getImage("img/gc_02.jpg");
	    	img_gc_03=Toolkit.getDefaultToolkit().getImage("img/gc_03.jpg");
	    	img_gc_04=Toolkit.getDefaultToolkit().getImage("img/gc_04.jpg");
	    	img_gc_05=Toolkit.getDefaultToolkit().getImage("img/gc_05.jpg");
	    	img_gc_06=Toolkit.getDefaultToolkit().getImage("img/gc_06.jpg");
	    	img_gc_07=Toolkit.getDefaultToolkit().getImage("img/gc_07.jpg");
	    	img_gc_08=Toolkit.getDefaultToolkit().getImage("img/gc_08.jpg");
	    	img_gc_09=Toolkit.getDefaultToolkit().getImage("img/gc_09.jpg");
	    	img_gc_10=Toolkit.getDefaultToolkit().getImage("img/gc_10.jpg");
	    	img_gc_11=Toolkit.getDefaultToolkit().getImage("img/gc_11.jpg");
	    	img_gc_12=Toolkit.getDefaultToolkit().getImage("img/gc_12.jpg");
	    	img_gc_13=Toolkit.getDefaultToolkit().getImage("img/gc_13.jpg");
	    	
    	}catch(Exception exc){
    		exc.printStackTrace();
    	}
	}
}