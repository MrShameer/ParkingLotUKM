import java.util.*;

public class vehicle implements addons{
	public String Vehicle;
	public String plat_no;

	void vehicle(String vehicle, String platno){
		Vehicle=vehicle;
		plat_no=platno;
	}

	String getplat(){
		return plat_no;
	}

	String getvehicle(){
		return Vehicle;
	}

	public String data(){
		return Vehicle+" "+plat_no;
	}

	String transport(){
		return getvehicle()+" "+plat_no;
	}
}

class car extends vehicle{
	String car(){
		return "CAR "+plat_no;
	}
}

class motorcycle extends vehicle{
	String bike(){
		return "BIKE "+plat_no;
	}
}

class bus extends vehicle{
	String bus(){
		return "BUS "+plat_no;
	}
}

class lorry extends vehicle{
	String lorry(){
		return "LORRY "+plat_no;
	}
}