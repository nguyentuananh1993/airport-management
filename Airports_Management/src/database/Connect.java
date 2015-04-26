package database;

import java.sql.*;

public class Connect {
	protected Connection connect = null;
	private String database = null, user = null, password = null, getdatabase = null;
	
	public Connect() {}
	
	/* Connect to postgreSQL */
	public Connect(String db, String us, String pw) {
		database = db;
		user = us;
		password = pw;
		getdatabase = "jdbc:postgresql://localhost:5432/" + database;
		GetConnect(); }

	/* Connect to database */
	public Connect(String us, String pw) {
		user = us;
		password = pw;
		getdatabase = "jdbc:postgresql://localhost:5432/";
		GetConnect(); }
	
	/* Reconnect to another database */
	public void Reconnect(String db) {
		database = db;
		getdatabase = "jdbc:postgresql://localhost:5432/" + database;
		GetConnect(); }
	
	/* Reconnect to postgreSQL */
	public void Reconnect() {
		database = null;
		getdatabase = "jdbc:postgresql://localhost:5432/";
		GetConnect(); }
	
	public void GetConnect(String db, String us, String pw) {
		database = db;
		user = us;
		password = pw;
		getdatabase = "jdbc:postgresql://localhost:5432/" + database;
		GetConnect(); }
	
	public void GetConnect(String us, String pw) {
		database = null;
		user = us;
		password = pw;
		getdatabase = "jdbc:postgresql://localhost:5432/";
		GetConnect(); }
	
	private void GetConnect() {
		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(getdatabase,user,password);
			if(database != null && database != "");
			else ; }
		catch(Exception e) {} }
	
	public void Disconnect() {
		try {
			if(connect != null) connect.close(); }
		catch (Exception e) {} }
}
