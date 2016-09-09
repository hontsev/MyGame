import java.awt.Image;
import java.awt.Rectangle;
import java.util.*;


public class GameEvent {
	public int unitid;
	public int mapw;
	public int maph;
	public int score;
	public int completestage;
	public int nowstage;
	public final int MAXSTAGE=2;
	public Unit lead;
	public int jumpstate;
	public int healthnum;
	public List<Unit> unitlist;
	public List<Unit> removelist;
	public Unit savepoint;
	public Menuchoice menuchoice;
	public Gamestate gamestate;
	public Image nowmovieimg;
	public int moviechangetime;
	public Quadtree quad;
	public void click()
	{
		switch(gamestate)
		{
		case basicmenu:						//开始菜单
			switch(menuchoice.choicenum)
			{
			case 1:
				gamestate = Gamestate.levelchoicemenu;
				if(completestage==0)menuchoice=new Menuchoice(2);
				else if(completestage<2)menuchoice = new Menuchoice(completestage+2);
				else menuchoice = new Menuchoice(3);
				break;
			case 2:
				gamestate = Gamestate.optionmenu;
				menuchoice = new Menuchoice(1);
				break;
			case 3:
				gamestate = Gamestate.exit;
				break;
			default:break;
			}
			break;
		case optionmenu:
			gamestate = Gamestate.basicmenu;
			menuchoice = new Menuchoice(3);
			menuchoice.choicenum=2;
			break;
		case levelchoicemenu:
			if(menuchoice.choicenum>0&&menuchoice.choicenum<=menuchoice.maxchoicenum-1)
			{
				mapInit(menuchoice.choicenum);
				healthnum=3;
				if(completestage==0)gamestate=Gamestate.beginmovie;
				else gamestate=Gamestate.running;
			}
			else 
			{
				gamestate=Gamestate.basicmenu;
				menuchoice = new Menuchoice(3);
			}
			break;
		case dead:
			//mapInit(nowstage);
			mapRestart(nowstage);
			gamestate=Gamestate.running;
			break;
		case gameover:
			nowstage=0;
			gamestate=Gamestate.basicmenu;
			menuchoice = new Menuchoice(3);
			break;
		case win:
			if(nowstage<MAXSTAGE)
			{
				if(completestage<nowstage)completestage=nowstage;
				nowstage++;
				mapInit(nowstage);
				gamestate=Gamestate.running;
			}
			else if(completestage==MAXSTAGE-1)
			{
				completestage=MAXSTAGE;
				nowstage=0;
				gamestate=Gamestate.endmovie;
				menuchoice = new Menuchoice(3);
			}
			else
			{
				nowstage=0;
				gamestate=Gamestate.basicmenu;
				menuchoice = new Menuchoice(3);
			}
			break;
		case beginmovie:
			nowmovieimg=null;
			moviechangetime=0;
			gamestate=Gamestate.running;
			break;
		case endmovie:
			nowmovieimg=null;
			moviechangetime=0;
			gamestate=Gamestate.basicmenu;
			break;
		default:break;
		}
	}
	public void move(Unit unit,int V1,int V2)
	{
		unit.a1=0;
		if(V1!=0){if(unit.state!=Unitstate.walk ||unit.v1*V1 < 0)unit.imagebreak();unit.v1=V1;unit.state=Unitstate.walk;}
		if(V2!=0)unit.v2=V2;
	}
	public void jump(Unit unit)
	{
		if(unit.id==lead.id)
		{
			if(jumpstate<2){move(unit, 0, -27);jumpstate++;}
		}
	}
	public void stop(Unit unit)
	{
		//unit.a1=-1;
		unit.v1=0;
		unit.imagebreak();
		unit.state = Unitstate.stand;
	}
	public Unit createUnit(int x,int y,int type)
	{
		unitid++;
		Unit u = new Unit(x,y,type);
		u.id=unitid;
		unitlist.add(u);
		return u;
	}
	public double getLength(Unit unit1,Unit unit2)
	{
		double len;
		len = Math.sqrt((unit1.x-unit2.x)*(unit1.x-unit2.x)+(unit1.y-unit2.y)*(unit1.y-unit2.y));
		return len;
	}
	public void shoot(Unit unit, int x, int y, int shootunittype,int speed)
	{
		double dx =x-unit.x;
		double dy= y-unit.y;
		double l = Math.sqrt(dx*dx+dy*dy);
		double len = Math.max(unit.hith, unit.hitw)*1.42;
		Unit missile = createUnit((int)(unit.x+len*dx/l),(int)(unit.y+len*dy/l),shootunittype);
		//display();
		//if(x<unit.x)missile.faceto
		missile.v1=(int)(dx*speed/l);
		missile.v2=(int)(dy*speed/l);
	}
	public void shoot(Unit unit, double direction, int shootunittype,int speed)
	{
		double dx =Math.cos(direction);
		double dy= Math.sin(direction);
		double l = Math.sqrt(dx*dx+dy*dy);
		double len = Math.max(unit.hith, unit.hitw)*1.42;
		Unit missile = createUnit((int)(unit.x+len*dx/l),(int)(unit.y+len*dy/l),shootunittype);
		//if(x<unit.x)missile.faceto
		missile.v1=(int)(dx*speed/l);
		missile.v2=(int)(dy*speed/l);
	}
	public void findAndAttack(Unit unit)
	{
		List<Unit> arr = new ArrayList<Unit>(unitlist);
		Iterator<Unit> i = arr.iterator();
		while(i.hasNext())
		{
			Unit unit2 = i.next();
			if(unit2.id==unit.id)continue;
			else if(unit2.id==lead.id && getLength(unit,unit2)<500)
			{
				//shoot(unit,unit2.x,unit2.y,4,10);
				shoot(unit,0*Math.PI/180,4,10);
				shoot(unit,30*Math.PI/180,4,10);
				shoot(unit,60*Math.PI/180,4,10);
				shoot(unit,90*Math.PI/180,4,10);
				shoot(unit,120*Math.PI/180,4,10);
				shoot(unit,150*Math.PI/180,4,10);
				shoot(unit,180*Math.PI/180,4,10);
				shoot(unit,210*Math.PI/180,4,10);
				shoot(unit,240*Math.PI/180,4,10);
				shoot(unit,270*Math.PI/180,4,10);
				shoot(unit,300*Math.PI/180,4,10);
				shoot(unit,330*Math.PI/180,4,10);
			}
		}
	}
	public Unit judge(int des,Unit unit1,List<Unit> hitlists)
	{
		Unit stopunit = null;
		Iterator<Unit> i = hitlists.iterator();
		while(i.hasNext())
		{
			Unit unit2 = i.next();
			if(unit2.id == unit1.id)continue;
			if(des == 1)			//左侧碰撞
			{
				if(unit2.x<unit1.x
				&& unit1.x+unit1.v1-unit2.x < unit2.hitw/2+unit1.hitw/2 
				&& Math.abs(unit2.y-unit1.y) < unit2.hith/2+unit1.hith/2)
				{
					stopunit = unit2;
					if(unit1.v1!=0)unit1.x=unit2.x+unit2.hitw/2+unit1.hitw/2;
					break;
				}
			}
			else if(des == 3)			//右侧碰撞
			{
				if(unit1.x<unit2.x
				&& unit2.x-unit1.x-unit1.v1 < unit2.hitw/2+unit1.hitw/2 
				&& Math.abs(unit2.y-unit1.y) < unit2.hith/2+unit1.hith/2)
				{
					stopunit = unit2;
					if(unit1.v1!=0)unit1.x=unit2.x-unit2.hitw/2-unit1.hitw/2;
					break;
				}
			}
			else if(des == 2)			//上侧碰撞
			{
				if(unit2.y<unit1.y
				&& unit1.y-unit2.y+unit1.v2 < unit2.hith/2+unit1.hith/2 
				&& Math.abs(unit2.x-unit1.x) < unit2.hitw/2+unit1.hitw/2)
				{
					stopunit = unit2;
					////unit1.y+=unit2.hith/2;
					if(unit1.v2!=0)unit1.y=unit2.y+unit2.hith/2+unit1.hith/2;
					break;
				}
			}
			else if(des == 4)			//下侧碰撞
			{
				if(unit1.y<unit2.y
				&& unit2.y-unit1.y-unit1.v2 < unit2.hith/2+unit1.hith/2 
				&& Math.abs(unit2.x-unit1.x) < unit2.hitw/2+unit1.hitw/2)
				{
					stopunit = unit2;
					////unit1.y-=unit2.hith/2;
					if(unit1.v2!=0)unit1.y=unit2.y-unit2.hith/2-unit1.hith/2;
					break;
				}
			}
		}
		return stopunit;
	}
	public void shoot( Unit unit )
	{
		unit.imagebreak();
		unit.state=Unitstate.attack;
		if(unit.id==lead.id)
		{
			Unit cunit;
			if(unit.faceto==1){cunit= createUnit(unit.x-unit.hitw/2-10,unit.y,3);move(cunit,-20,0);}
			else if(unit.faceto==3){cunit= createUnit(unit.x+10+unit.hitw/2,unit.y,3);move(cunit,20,0);}
		}
		
	}
	public void win()
	{
		healthnum=3;
		gamestate = Gamestate.win;
	}
	public void hit(Unit unit1,Unit unit2)				//有单位碰撞时分类讨论处理
	{
		if(unit1.id==lead.id)
		{
			if(unit2.attv==0)			//主角碰地板，跳跃状态重置
				jumpstate=0;
			else if(unit2.attv>0)			//主角碰子弹，减血（直接死）
				lead.hp-=unit2.attv;
			if(unit2.type==7)			//主角碰钱袋，本关胜利
			{
				win();
			}
			else if(unit2.type==6)			//主角碰刺，扣血
			{
				lead.hp-=1;
			}
			else if(unit2.type==8)			//主角碰存储点，更新存储点
			{
				setSavepoint(unit2);
			}
		}
		else if(unit1.attv>0)
		{
			if(unit2.id==lead.id)		//敌人子弹碰主角，消失并扣血
			{
				unit2.hp-=unit1.attv;
				kill(unit1);
			}
			else if(unit2.attv==0)			//敌人子弹碰地板，消失
			{
				kill(unit1);
			}
			else if(unit2.attv<0)			//敌人子弹碰主角弹，相消
			{
				kill(unit1);
				kill(unit2);
			}
		}
		else if(unit1.attv<0 && unit2.id!=lead.id)			//主角发射子弹，则消失并扣对方血
		{
			unit2.hp+=unit1.attv;
			kill(unit1);
		}
	}
	public void kill(Unit unit)
	{
		if(unit.score>0)score+=unit.score;
		removeunit(unit);
	}
	public void removeunit(Unit unit)
	{
		removelist.add(unit);
	}
	public void healthjudge(Unit unit)
	{
		if(unit.hp<=0)
		{
			if(unit.id==lead.id)
			{
				if(score>=10)score-=10;
				else score=0;
				healthnum--;
				if(healthnum>0)
				{
					gamestate=Gamestate.dead;
				}
				else
				{
					gamestate=Gamestate.gameover;
				}
			}
			else
			{
				kill(unit);
			}
		}
		else 
		{
			unit.hp+=unit.hpc;
		}
	}
	public void display()			/* 遍历并处理每个地图上的单位 */
	{
		quad.clear();
		for (int i = 0; i < unitlist.size(); i++) {
		    quad.insert(unitlist.get(i));		//将碰撞矩形插入四叉树
		}
		unitlist.removeAll(removelist);
		removelist.clear();
		List<Unit> tl = new ArrayList<Unit>(unitlist);
		Iterator<Unit> i = tl.iterator();
		while(i.hasNext())				//遍历地图上的单位，进行动作
		{
			Unit unit = i.next();
			healthjudge(unit);
			if(unit.x+unit.v1<=0||unit.y+unit.v2<=0||unit.x+unit.v1>=mapw||unit.y+unit.v2>=maph)
			{
				if(unit.hpc<0)removeunit(unit);
			}
			List<Unit> hitUnits = new ArrayList<Unit>();
			quad.retrieve(hitUnits, unit);
			if(unit.attv!=0)
			{
				Unit unit2=judge(1,unit,hitUnits);
				if(unit2!=null)
				{
					//unit.v2=0;
					hit(unit,unit2);
				}
				unit2=judge(2,unit,hitUnits);
				if(unit2!=null)
				{
					//unit.v2=0;
					hit(unit,unit2);
				}
				unit2=judge(3,unit,hitUnits);
				if(unit2!=null)
				{
					//unit.v2=0;
					hit(unit,unit2);
				}
				unit2=judge(4,unit,hitUnits);
				if(unit2!=null)
				{
					//unit.v2=0;
					hit(unit,unit2);
				}
				
			}
			if(unit.x+unit.v1>0&&unit.x+unit.v1<mapw)
			{
				int des;
				if(unit.v1<0)des = 1;
				else des = 3;
				Unit unit2=judge(des,unit,hitUnits);
				if(unit2==null)
				{
					unit.x+=unit.v1;	//水平方向位移
					if(unit.v1>0)unit.faceto = 3;
					else if(unit.v1<0)unit.faceto = 1;
				}
				else
				{
					//unit.v1=0;
					hit(unit,unit2);
				}
			}
			if(unit.v1!=0)
			{
				if(unit.a1<0)	//减速
				{
					if(Math.abs(unit.v1)+unit.a1<=0)
					{
						unit.v1=0;
						unit.a1=0;
					}
					else
					{
						if(unit.v1<0)unit.v1-=unit.a1;
						else unit.v1+=unit.a1;
					}
				}
			}
			else 	//加速
			{
				if(unit.a1<0)unit.a1=0;
				
				if(unit.v1<0)unit.v1+=unit.a1;
				else unit.v1-=unit.a1;
			}
			if(unit.weight)			//下落
			{
				if(unit.v2<=18)unit.v2+=3;
				else unit.v2=18;
			}
			if(unit.y+unit.v2>0&&unit.y+unit.v2<maph)
			{
				int des;
				if(unit.v2<0)des=2;
				else des=4;
				Unit unit2=judge(des,unit,hitUnits);
				if(judge(des,unit,hitUnits)==null)
					unit.y+=unit.v2;	//竖直方向位移
				else
				{
					//unit.v2=0;
					hit(unit,unit2);
				}
			}
			else
			{
				unit.v2=0;
			}
			if(unit.type==1)
			{
				if(unit.stoptime<=0)
				{
					unit.stoptime=50;
					findAndAttack(unit);
				}
				else 
				{
					unit.stoptime--;
				}
			}
		}
		unitlist.removeAll(removelist);
	}
	public GameEvent(int w,int h)
	{
		menuchoice  = new Menuchoice(3,1);
		maph=h;
		mapw=w;
		gamestate = Gamestate.basicmenu;
		completestage = 0;
		unitid=0;
		menuchoice.choicenum=1;
		healthnum=3;
		jumpstate=0;
		score=0;
		nowmovieimg=null;
		moviechangetime=0;
	}
	private void mapInit(int num)			/* 加载某个关卡的地图 */
	{
		quad = new Quadtree(0, new Rectangle(0,0,mapw,maph));
		unitlist = new ArrayList<Unit>();
		removelist = new ArrayList<Unit>();
		nowstage = num;
		switch(num)
		{
		case 1:
			for(int i=0;i<=mapw/40;i++)
				createUnit(i*40,maph-20,2);
			createUnit(550,maph-300,1);
			createUnit(700,maph-200,1);
			createUnit(400,maph-60,6);
			createUnit(440,maph-60,6);
			createUnit(600,maph-60,6);
			createUnit(640,maph-60,6);
			createUnit(850,maph-60,7);
			setSavepoint(createUnit(100,maph-60,8));
			createUnit(500,maph-60,8);
			createUnit(750,maph-60,8);
			break;
		case 2:
			for(int i=0;i<=mapw/40;i++)
				createUnit(i*40,maph-20,2);
			createUnit(300,maph-200,1);
			createUnit(400,maph-250,1);
			createUnit(500,maph-240,1);
			createUnit(600,maph-60,1);
			createUnit(800,maph-250,1);
			createUnit(400,maph-60,6);
			createUnit(850,maph-60,7);
			setSavepoint(createUnit(50,maph-60,8));
			createUnit(300,maph-60,8);
			createUnit(500,maph-60,8);
			break;
			default:break;
		}
		InitLead(40,maph-65);
	}
	public void mapRestart(int num)
	{
		removelist.clear();
		List<Unit> tl = new ArrayList<Unit>(unitlist);
		Iterator<Unit> i = tl.iterator();
		while(i.hasNext())				//删除弹幕
		{
			Unit unit = i.next();
			if(unit.attv!=0)removeunit(unit);
		}
		InitLead(savepoint.x,savepoint.y-55);
	}
	private void InitLead(int x ,int y)
	{
		if(lead!=null)removeunit(lead);
		lead = createUnit(x,y,0);
		lead.faceto=3;
	}
	private void setSavepoint(Unit unit)
	{
		if(savepoint!=null)savepoint.hpc=1;
		savepoint=unit;
		savepoint.hpc=2;
	}
}
