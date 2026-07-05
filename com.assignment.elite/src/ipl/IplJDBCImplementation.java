package ipl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IplJDBCImplementation implements ipldao {
	private List<Ipl> playing;
	private Map<String, List<Ipl>> teams;
	private List<String> selectIpl;

	public IplJDBCImplementation() {
		playing = new LinkedList<Ipl>();
		teams = new HashMap<>();
		selectIpl = new LinkedList<>();
	}

	public boolean addPlayer(Ipl player) {
		String sql = "INSERT INTO ipl (jerseyno, playername, role, teamname, winningamount) VALUES (?,?,?,?,?)";		try {
			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, player.getJerseyno());
			pre.setString(2, player.getPlayername());
			pre.setString(3, player.getRole());
			pre.setString(4, player.getTeamname());
			pre.setDouble(5, player.getWinningamount());
			pre.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

//		playing.add(player);
//		return true;
	}

	@Override
	public boolean addToTeam(String teamname, int jersey) throws PlayerNotFoundException {

		String sql = "UPDATE ipl SET teamname=? WHERE jerseyno=?";

		try {
			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setString(1, teamname);
			pre.setInt(2, jersey);

			int rows = pre.executeUpdate();

			if (rows > 0)
				return true;

			throw new PlayerNotFoundException("Player not found");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Ipl findByPlayer(int jerseyno) {

		String sql = "SELECT * FROM ipl WHERE jerseyno=?";

		try {
			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setInt(1, jerseyno);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {

				return new Ipl(rs.getInt("jerseyno"), rs.getString("playername"), rs.getString("role"),
						 rs.getDouble("winningamount"));
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

		
	}

	@Override
	public List<Ipl> findByTeam(String teamname) {

		List<Ipl> list = new LinkedList<>();

		String sql = "SELECT * FROM ipl WHERE teamname=?";

		try {

			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setString(1, teamname);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {

				list.add(new Ipl(rs.getInt("jerseyno"), rs.getString("playername"), rs.getString("role"),
						 rs.getDouble("winningamount")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Ipl> findAllTeam() {

		List<Ipl> list = new LinkedList<>();

		String sql = "SELECT * FROM ipl";

		try {

			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {

				list.add(new Ipl(rs.getInt("jerseyno"), rs.getString("playername"), rs.getString("role"),
						 rs.getDouble("winningamount")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public Optional<Ipl> findByWinning(double win) {

		String sql = "SELECT * FROM ipl WHERE winningamount=?";

		try {

			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setDouble(1, win);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {

				Ipl player = new Ipl(rs.getInt("jerseyno"), rs.getString("playername"), rs.getString("role"),
						 rs.getDouble("winningamount"));

				return Optional.of(player);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Ipl> sortByRole(String team, String role) {

		List<Ipl> list = new LinkedList<>();

		String sql = "SELECT * FROM ipl WHERE teamname=? AND role=?";

		try {

			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setString(1, team);
			pre.setString(2, role);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {

				list.add(new Ipl(rs.getInt("jerseyno"), rs.getString("playername"), rs.getString("role"),
						 rs.getDouble("winningamount")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean deletePlayer(int jersey) {

		String sql = "DELETE FROM ipl WHERE jerseyno=?";

		try {

			Connection conn = IPLJDBCFactory.getConnection();
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setInt(1, jersey);

			return pre.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<String> findIpl() {
		return selectIpl;
	}

}
