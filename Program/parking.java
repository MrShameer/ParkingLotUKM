import java.util.*;

public class parking implements addons{
	public String area;
	public String name;
	public String type;
	public int space;
	private boolean isparkable=true;

	void parking(String n,String a,String t,int s){
		name=n;
		area=a;
		type=t;
		space=s;
	}

	String getname(){
		return name;
	}

	String gettype(){
		return type;
	}

	int getspace(){
		return space;
	}

	public String data(){
		return name+" "+area+" "+type+" "+space;
	}

	boolean parkable(int park){
		if(space==park){
			isparkable=false;
		}
		else{
			isparkable=true;
		}
		return isparkable;
	}
}