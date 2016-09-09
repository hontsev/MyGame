import java.util.*;


public class Unit {
	public int id;
	public String name;
	public int x;
	public int y;
	public int v1;
	public int v2;
	public int a1;
	public int a2;
	public int imgw;
	public int imgh;
	public int hitw;
	public int hith;
	public Unitstate state;
	public int type;
	public int faceto;
	public Stack<Anime> images;
	public double hp;
	public double hpc;
	public double attv;
	public int score;
	public boolean weight;
	public int stoptime;
	public Unit(int tx, int ty,int Type)
	{
		x=tx;
		y=ty;
		a1=a2=0;
		v1=v2=0;
		stoptime=0;
		state=Unitstate.stand;
		faceto = 1;
		images=new Stack<Anime>();
		type=Type;
		hp=0;
		hpc=0;
		attv=0;
		weight=true;
		score=0;
		loadInfo();
	}
	private void loadInfo()
	{
		switch(type)
		{
		case 0:	
			name="Reimo";
			hitw=25;
			hith=50;
			imgw=100;
			imgh=50;
			hp=1;
			hpc=0;
			break;
		case 1:
			name="Cirno";
			hitw=40;
			hith=40;
			imgw=30;
			imgh=45;
			hp=2;
			hpc=0;
			score=9;
			weight=false;
			break;
		case 2:
			name="floor1";
			imgw=40;
			imgh=40;
			hitw=40;
			hith=40;
			hp=1222;
			weight=false;
			break;
		case 3:
			name="SpellCard1";
			hitw=20;
			hith=12;
			imgw=30;
			imgh=18;
			hp=1;
			hpc=-0.05;
			attv=-1;
			weight=false;
			break;
		case 4:
			name="SpellCard2";
			hitw=8;
			hith=8;
			imgw=15;
			imgh=15;
			hp=1;
			hpc=-0.02;
			attv=1;
			weight=false;
			break;
		case 5:
			name="floor2";
			imgw=40;
			imgh=40;
			hitw=40;
			hith=40;
			hp=12;
			weight=false;
			break;
		case 6:
			name="floor3";
			imgw=40;
			imgh=40;
			hitw=40;
			hith=40;
			hp=22222;
			weight=false;
			break;
		case 7:
			name="money";
			imgw=40;
			imgh=40;
			hitw=40;
			hith=40;
			hp=99999;
			weight=false;
			break;
		case 8:
			name="savepoint";
			imgw=20;
			imgh=20;
			hitw=20;
			hith=20;
			hp=99999;
			hpc=1;
			weight=true;
			break;
		default:
			break;
		}
	}
	public void imagebreak()
	{
		images.clear();
	}
	public void imagemove()
	{
		if(images.isEmpty())return;
		if(!images.peek().timego())images.pop();
	}
}
