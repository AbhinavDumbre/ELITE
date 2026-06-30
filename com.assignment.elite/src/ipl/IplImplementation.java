package ipl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IplImplementation implements ipldao {
	private List<Ipl> playing;
	private Map<String,List<Ipl>> teams;
	private List<String >selectIpl;

	public IplImplementation() {
		playing = new LinkedList<Ipl>();
		teams=new HashMap<>();
		selectIpl=new LinkedList<>();
	}

	public boolean addPlayer(Ipl player) {
		playing.add(player);
		return true;
	}

	@Override
	public boolean addToTeam(String teamname,Ipl player) {
		if(!teams.containsKey(teamname)) {
			teams.put(teamname,new LinkedList<>());
		}
		teams.get(teamname).add(player);
		return true;
	}
	public boolean addToIpl(String teamname,boolean iplselection) {
		if(iplselection) {
			selectIpl.add(teamname);
			System.out.println("Succesfully added to Ipl");
			return true;
		}else
			return false;
	}
	@Override
	public Ipl findByPlayer(int jerseyno) throws PlayerNotFoundException {
		for (Ipl play : playing) {
			if (jerseyno == play.getJerseyno()) {
				return play;
			} }
				throw new PlayerNotFoundException("Player Not Found");
			}

	
	@Override
	public List<Ipl> findByTeam(String teamname) {
	
			if(teams.containsKey(teamname)) {
				
				return teams.get(teamname);
			}return null;
		}
	public List<Ipl>findAllTeam(){
		for(int i=0;i<teams.size()-1;i++) {
		 return teams.get(i);
		}return null;
		
	}
	public List<String> findIpl(){
	  return selectIpl;
	}
	}


