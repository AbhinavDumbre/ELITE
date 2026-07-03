package ipl;
import java.util.stream.*;
import java.nio.file.DirectoryStream.Filter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public boolean addToTeam(String teamname,int jersey) throws PlayerNotFoundException {
		
		playing.stream().filter(n->n.getJerseyno()==jersey).findFirst().orElseThrow(()-> new PlayerNotFoundException("Player not found"));
//	    playing.setTeamname(teamname);
		
		return true;
//		if(!teams.containsKey(teamname)) {
//			teams.put(teamname,new LinkedList<>());
//		}
//		teams.get(teamname).add(player);
//		return true;
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
		return playing.stream().filter(n->n.getJerseyno()==jerseyno).findFirst().orElseThrow(()->new PlayerNotFoundException("Player not found")); 
//		for (Ipl play : playing) {
//			if (jerseyno == play.getJerseyno()) {
//				return play;
//			} }
//				throw new PlayerNotFoundException("Player Not Found");
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
	public Optional<Ipl> findByWinning(double win){
		return playing.stream().filter(n->n.getWinningamount()==win).findFirst();
	}
	public List<Ipl> sortByRole(String team,String role) throws PlayerNotFoundException{
		if (playing.isEmpty()) {
		    throw new PlayerNotFoundException("No players found");
		}

		return playing.stream().filter(n->n.getRole().equals(role)&&n.getTeamname().equals(team)).toList();

	}
	}


