
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.util.Iterator;

public class GameMap extends JPanel {
	private static final long serialVersionUID = 1L;
	Graphics g2d;
	public int startx;
	public int starty;
	public GameEvent event;
	public Images images;
	public void paint(Graphics graphics) {
    	super.paint(graphics);
    	g2d = (Graphics2D) graphics;
    	g2d.setColor(Color.green);
    	switch(event.gamestate)
    	{
    	case running:
    		displayMap();
    		break;
    	case basicmenu:
    		displayMenu();
    		break;
    	case optionmenu:
    		displayOption();
    		break;
    	case levelchoicemenu:
    		displayChoice();
    		break;
    	case gameover:
    		displayGameover();
    		break;
    	case dead:
    		displayDead();
    		break;
    	case win:
    		displayWin();
    		break;
    	case beginmovie:
    		if(event.moviechangetime<=0)					//按顺序播放开场动画
    		{
    			event.moviechangetime=50;
    			if(event.nowmovieimg==null)
    				event.nowmovieimg=images.img_gc_01;
    			else if(event.nowmovieimg.equals(images.img_gc_01))
    				event.nowmovieimg=images.img_gc_02;
    			else if(event.nowmovieimg.equals(images.img_gc_02))
    				event.nowmovieimg=images.img_gc_03;
    			else if(event.nowmovieimg.equals(images.img_gc_03))
    				event.nowmovieimg=images.img_gc_04;
    			else if(event.nowmovieimg.equals(images.img_gc_04))
    				event.nowmovieimg=images.img_gc_05;
    			else if(event.nowmovieimg.equals(images.img_gc_05))
    				event.nowmovieimg=images.img_gc_06;
    			else if(event.nowmovieimg.equals(images.img_gc_06))
    			{
    				event.nowmovieimg=null;
    				event.moviechangetime=0;
    				event.gamestate=Gamestate.running;
    				break;
    			}
    				
    		}
    		else
    		{
    			event.moviechangetime--;
    		}
    		displayMovie();
    		break;
    	case endmovie:
    		if(event.moviechangetime<=0)			//播放结束动画
    		{
    			event.moviechangetime=50;
    			if(event.nowmovieimg==null)
    				event.nowmovieimg=images.img_gc_07;
    			else if(event.nowmovieimg.equals(images.img_gc_07))
    				event.nowmovieimg=images.img_gc_08;
    			else if(event.nowmovieimg.equals(images.img_gc_08))
    				event.nowmovieimg=images.img_gc_09;
    			else if(event.nowmovieimg.equals(images.img_gc_09))
    				event.nowmovieimg=images.img_gc_10;
    			else if(event.nowmovieimg.equals(images.img_gc_10))
    				event.nowmovieimg=images.img_gc_11;
    			else if(event.nowmovieimg.equals(images.img_gc_11))
    				event.nowmovieimg=images.img_gc_12;
    			else if(event.nowmovieimg.equals(images.img_gc_12))
    				event.nowmovieimg=images.img_gc_13;
    			else if(event.nowmovieimg.equals(images.img_gc_13))
    			{
    				event.nowmovieimg=null;
    				event.moviechangetime=0;
    				event.gamestate=Gamestate.basicmenu;
    				break;
    			}
    		}
    		else
    		{
    			event.moviechangetime--;
    		}
    		displayMovie();
    		break;
    	case exit:
    	default:
    		displayEmpty();
    		break;
    	}
    }
	public void displayEmpty()
	{
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	public void displayMenu()
	{
		g2d.drawImage(images.img_title1,0, 0, getWidth(), getHeight(),this);
        g2d.drawImage(images.img_pointer,440, 140+event.menuchoice.choicenum*70, 30, 30,this);
	}
	public void displayOption()
	{
		g2d.drawImage(images.img_title2,0, 0, getWidth(), getHeight(),this);
		g2d.drawImage(images.img_pointer,140, getHeight()-60, 30, 30,this);
	}
	public void displayChoice()
	{
		g2d.drawImage(images.img_title3,0, 0, getWidth(), getHeight(),this);
		g2d.setColor(Color.white);
		g2d.fillRect(200,event.completestage*100+240,200,60);
		if(event.menuchoice.choicenum==event.menuchoice.maxchoicenum)
			g2d.drawImage(images.img_pointer,140, getHeight()-60, 30, 30,this);
		else
			g2d.drawImage(images.img_pointer,400,45+event.menuchoice.choicenum*100, 30, 30,this);
	}
	public void displayGameover()
	{
		g2d.drawImage(images.img_gameover,0, 0, getWidth(), getHeight(),this);
	}
	public void displayDead()
	{
		g2d.drawImage(images.img_dead,0, 0, getWidth(), getHeight(),this);
		for(int i=0;i<event.healthnum;i++)
			g2d.drawImage(images.img_pointer,270+i*60,130,40,40,this);
	}
	public void displayMovie()
	{
		if(event.nowmovieimg!=null)g2d.drawImage(event.nowmovieimg,0, 0, getWidth(), getHeight(),this);
		else displayEmpty();
	}
	public void displayWin()
	{
		if(event.nowstage==event.MAXSTAGE)
			g2d.drawImage(images.img_win1,0, 0, getWidth(), getHeight(),this);
		else
			g2d.drawImage(images.img_win2,0, 0, getWidth(), getHeight(),this);
	}
	public void displayMap()
	{
		startx = event.lead.x-getWidth()/2;
		starty = event.lead.y-getHeight()/2;
		if(startx+getWidth()>event.mapw)startx=event.mapw-getWidth();
		if(starty+getHeight()>event.maph)starty=event.maph-getHeight();
		if(startx<0)startx=0;
		if(starty<0)starty=0;
		g2d.setColor(Color.white);
    	g2d.fillRect(0, 0, event.mapw, event.maph);
    	Iterator<Unit> i = event.unitlist.iterator();
		while(i.hasNext())
		{
			Unit unit = i.next();
			int x = unit.x-unit.imgw/2-startx;
			int y = unit.y-unit.imgh/2-starty;
			int w = unit.imgw;
			int h = unit.imgh;
			Image tempimg=null;
			switch(unit.type)
			{
			case 0:					//Reimu
				unit.imagemove();
				if(unit.state==Unitstate.stand)
				{
					if(unit.images.isEmpty())
					{
						if(unit.faceto==1)
						{
							unit.images.push(new Anime(images.img1_1_1,6));
							unit.images.push(new Anime(images.img1_1_2,6));
						}
						else if(unit.faceto==3)
						{
							unit.images.push(new Anime(images.img1_3_1,6));
							unit.images.push(new Anime(images.img1_3_2,6));
						}
					}
				}
				else if(unit.state==Unitstate.walk)
				{
					if(unit.images.isEmpty())
					{
						if(unit.faceto==1)
						{
							unit.images.push(new Anime(images.img1_1_1,2));
							unit.images.push(new Anime(images.img1_1_4,5));
							unit.images.push(new Anime(images.img1_1_1,2));
							unit.images.push(new Anime(images.img1_1_3,5));
						}
						else if(unit.faceto==3)
						{
							unit.images.push(new Anime(images.img1_3_1,2));
							unit.images.push(new Anime(images.img1_3_4,5));
							unit.images.push(new Anime(images.img1_3_1,2));
							unit.images.push(new Anime(images.img1_3_3,5));
						}
					}
				}
				else if(unit.state==Unitstate.attack)
				{
					unit.state = Unitstate.stand;
					if(unit.faceto==1)
					{
						unit.images.push(new Anime(images.img1_1_5,3));
					}
					else if(unit.faceto==3)
					{
						unit.images.push(new Anime(images.img1_3_5,3));
					}
				}
				break;
			case 1:					//Cirno
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img2));
				break;
			case 2:					//floor1
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_floor1));
				break;
			case 3:					//Spell Card 1
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_sc1));
				break;
			case 4:					//Spell Card 2
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_sc2));
				break;
			case 5:					//floor2
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_floor2));
				break;
			case 6:					//floor3
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_floor3));
				break;
			case 7:					//money
				if(unit.images.isEmpty())unit.images.push(new Anime(images.img_money));
				break;
			case 8:					//savepoint
				if(unit.images.isEmpty())
				{
					if(unit.hpc==1){unit.images.clear(); unit.images.push(new Anime(images.img_floor2));}
					else {unit.images.clear();unit.images.push(new Anime(images.img_floor2_2));}
				}
				else
				{
					if(unit.hpc==1){unit.images.clear(); unit.images.push(new Anime(images.img_floor2));}
					else {unit.images.clear();unit.images.push(new Anime(images.img_floor2_2));}
				}
				break;
			default:
				break;
			}
			if(!unit.images.isEmpty())
			{
				tempimg=unit.images.peek().img;
				g2d.drawImage(tempimg, x, y, w, h,this);
			}
		}
		//g2d.setColor(Color.blue);
		//g2d.drawString("分数："+String.valueOf(event.score), getWidth()-80, 30);
	}
	public void drawLead(Graphics g2d)
	{
		g2d.draw3DRect(event.lead.x, event.lead.y, 12, 12, true);
	}
    public GameMap(GameEvent e) {
    	event = e;
    	images = new Images();
    	startx=starty=0;
    }
    
}