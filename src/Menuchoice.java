
public class Menuchoice {
	public int choicenum;
	public int maxchoicenum;
	public Menuchoice(int max)
	{
		maxchoicenum=max;
		choicenum = 1;
	}
	public Menuchoice(int max,int c)
	{
		maxchoicenum=max;
		choicenum=c;
	}
	public void move(int num)
	{
		if(num<0)
		{
			while(choicenum+num<=0)
			{
				choicenum+=maxchoicenum;
			}
			choicenum+=num;
		}
		else
		{
			while(choicenum+num>maxchoicenum)
			{
				choicenum-=maxchoicenum;
			}
			choicenum+=num;
		}
	}
}
