import java.awt.Image;


public class Anime {
	public Image img;
	public int imgtime;
	public int lefttime;
	public Anime(Image image,int time)
	{
		img=image;
		imgtime=time;
		lefttime=imgtime;
	}
	public Anime(Image image)
	{
		img=image;
		imgtime=-1;
		lefttime=-1;
	}
	public boolean timego()
	{
		if(imgtime<1)return true;
		else
		{
			lefttime--;
			if(lefttime<0)return false;
			return true;
		}
		
	}
}
