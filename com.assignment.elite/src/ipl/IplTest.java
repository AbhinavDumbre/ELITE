package ipl;


public class IplTest {
public static void main(String[] args) {
	
	Ipl play1=new Ipl("Abhinav", "Batsman",25,250000);
	Ipl play2=new Ipl("Eshan", "All-Rounder",15,500000);
	Ipl play3=new Ipl("Abhimanyu", "Fielder",07,200000);
	IplImplementation li=new IplImplementation();
	li.addPlayer(play1);
	li.addPlayer(play2);
	li.addPlayer(play3);
	li.addToTeam("RCB", play3);
	li.addToTeam("RCB", play2);
		try {
			System.out.println(li.findByPlayer(25));
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Team:"+li.findByTeam("RCB"));
		li.addToIpl("RCB", true);
		li.addToIpl("CSK", true);
		li.addToIpl("SRH", true);
		li.addToIpl("MI", true);
		li.addToIpl("DC", true);
		li.addToIpl("RR", true);
		li.addToIpl("RPS", false);
		

		System.out.println(li.findIpl());
		System.out.println(li.findAllTeam());
	}
	
}

