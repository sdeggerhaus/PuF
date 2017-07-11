package solitaireModel;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mysql.jdbc.Statement;

import java.sql.Date;
import java.sql.Connection;

public class Database {

	private Connection con;
	private Timer tim;
	private TimerTask tTask;
	private boolean running;
	
	public static int currIndex = -1;
	
	public Database(){
		initialize();
	}
	
	private void initialize(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://rdbms.strato.de/DB3035441", "U3035441", "Deluxelusche1");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/solitaire", "root", "");
		} catch (ClassNotFoundException e) {System.out.println("no class");
		} catch (SQLException e) {System.out.println("no online connection");
		}finally{
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/solitaire", "root", "");
			} catch (ClassNotFoundException e) {System.out.println("no class");
			} catch (SQLException e) {System.out.println("no local connection");}
		}
	}
	
	public void addScore(int score){
		PreparedStatement stmt;
		LocalDateTime ldt = LocalDateTime.now();
		String actdate = ldt.getYear() + "-" + ldt.getMonthValue() + "-" + ldt.getDayOfMonth();
		try {
			String query = "INSERT INTO scores (SDate, SUser, SValue) VALUES ('" + actdate + "', 'xX_MinecraftSpieler420HD_Xx', " + score + ");"; 
			stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);			
			int changedRow = stmt.executeUpdate();
			if(changedRow == 1){
				ResultSet result = stmt.getGeneratedKeys();
				if(result.next()){
					currIndex = result.getInt(1);	
				}
			}
		} catch (SQLException e) { System.out.println("no add");			
		}
		
	}
	
	public void updateScore(int score, int rounds){
		if(currIndex != -1){
			PreparedStatement stmt;
			try {
				String query = "UPDATE scores SET SValue =" + score + ", SRounds = " + rounds + " WHERE SiD = " +  currIndex + ";"; 
				stmt = con.prepareStatement(query);			
				stmt.execute();
			} catch (SQLException e) { 
			}
		}
	}
	
	public void updateLefties(int l){
		if(currIndex != -1){
			PreparedStatement stmt;
			try {
				String query = "UPDATE scores SET SLefties = " + l + " WHERE SiD = " +  currIndex + ";"; 
				stmt = con.prepareStatement(query);			
				stmt.execute();
			} catch (SQLException e) { 
			}
		}
	}
	
	public Highscore[] getTableView(){
		PreparedStatement stmt;
		Highscore[] hArray = null;
		try {
			String query = "SELECT * FROM scores ORDER BY SValue ASC"; 
			stmt = con.prepareStatement(query);			
			ResultSet result = stmt.executeQuery();
			
			List<Highscore> highsc = new ArrayList<Highscore>();
			while(result.next()){
				String uname = result.getString(3);
				int sval = result.getInt(4);
				Date date = result.getDate(2);
				int rounds = result.getInt(5);
				int lefties = result.getInt(6);
				Highscore hs = new Highscore(sval, uname, date, rounds, lefties);
				highsc.add(hs);
			}
			hArray = new Highscore[highsc.size()];
			hArray = highsc.toArray(hArray);
			
		} catch (SQLException e) { System.out.println("no entry");
		}
		return hArray;
	}
	
	public int getActPlace(){
		int sval = 0;
		PreparedStatement stmt;
		try {
			String query = "SELECT COUNT(SValue) FROM scores WHERE SValue >= (SELECT SValue FROM scores WHERE SiD = "+currIndex+");";
			stmt = con.prepareStatement(query);			
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				sval = result.getInt(1);
			}
			
		} catch (SQLException e) { System.out.println("error act place");
		e.printStackTrace(System.err);
		}
		return sval;
	}
	
	public void delAtStart(){
		PreparedStatement stmt;
		try {
			String query = "DELETE FROM scores WHERE SLefties IS NULL;"; 
			stmt = con.prepareStatement(query);			
			int result = stmt.executeUpdate();
			System.out.println(result);
					
			
		} catch (SQLException e) { 
			e.printStackTrace(System.err);
		}
	}
	
	public void updateName(String name){
		System.out.println(currIndex);
		if(currIndex != -1){
			PreparedStatement stmt;
			try {
				String query = "UPDATE scores SET SUser = " + name + " WHERE SiD = " +  currIndex + ";"; 
				stmt = con.prepareStatement(query);			
				stmt.execute();
			} catch (SQLException e) { 
			}
		}
	}
		
}
