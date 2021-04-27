import java.util.*;

public class person implements addons{
	public String ID;
	public String Job;
	private String Parked;

	void person(String id, String job){
		ID=id;
		Job=job;
	}

	void person (String id, String job, String parked){
		ID=id;
		Job=job;
		Parked=parked;
	}

	String getid(){
		return ID;
	}

	String job(){
		return Job;
	}

	String parked(){
		return Parked;
	}

	void setid(String id){
		ID=id;
	}

	void setjob(String job){
		Job=job;
	}

	void setparked(String park){
		Parked=park;
	}

	public String data(){
		return ID+" "+Job;
	}
}

class staff extends person{
	void ordinary(){
		Job="O_STAFF";
	}

	void management(){
		Job="M_STAFF";
	}
}

class students extends person{
	void student(String work){
		Job=work;
	}
}

