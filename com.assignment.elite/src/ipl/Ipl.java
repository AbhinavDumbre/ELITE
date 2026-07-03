package ipl;
/**
 * we need ipl wwho will show teams and we need team who elect then player who has id 
 */
public class Ipl{
	private String playername,role;
	private int jerseyno;
	private double winningamount;
	private String teamname;
	public Ipl(int jerseyno, String playername, String role, double winningamount) {
	    this.jerseyno = jerseyno;
	    this.playername = playername;
	    this.role = role;
	    this.teamname = teamname;
	    this.winningamount = winningamount;
	}
	@Override
	public String toString() {
		return "Ipl [playername=" + playername + ", role=" + role + ", jerseyno=" + jerseyno + ", winningamount="
				+ winningamount + ", teamname=" + teamname + "]";
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getJerseyno() {
		return jerseyno;
	}
	public void setJerseyno(int jerseyno) {
		this.jerseyno = jerseyno;
	}
	public double getWinningamount() {
		return winningamount;
	}
	public void setWinningamount(double winningamount) {
		this.winningamount = winningamount;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public Ipl() {
		
	}

	
}