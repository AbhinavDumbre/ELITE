package ipl;

import java.util.List;

public interface ipldao {

	public List<Ipl> findByTeam(String teamname);

	public Ipl findByPlayer(int jerseyno) throws PlayerNotFoundException;

	boolean addPlayer(Ipl player);

//	boolean addToTeam(String teamname, Ipl player);

	boolean addToTeam(String teamname, int jersey) throws PlayerNotFoundException;
}
