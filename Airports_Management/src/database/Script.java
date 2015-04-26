package database;

import java.sql.*;

import fundamentals.Global;

public class Script extends Connect{	
	Statement statement = null;
	
	public Script() {
		super();}
	
	public Script(String db, String us, String pw) {
		super(db,us,pw);}
	
	public Script(String us, String pw) {
		super(us,pw);}
	
	/* Drop Database */
	public void DropDatabase(String database) {
		String script = new String();
		script = "DROP DATABASE IF EXISTS " + database + "";
		executeScript(script); }
	
	/* Create new Database */
	public void CreateDatabase(String database) {
		DropDatabase(database);
		String script = new String();
		script = "CREATE DATABASE " + database + "";
		executeScript(script); }

	/* Drop Schema */
	public void DropSchema(String schema) {
		String script = new String();
		script = "DROP SCHEMA IF EXISTS " + schema + " CASCADE";
		executeScript(script); }
	
	/* Create new Schema */
	public void CreateSchema(String schema) {
		DropSchema(schema);
		String script = new String();
		script = "CREATE SCHEMA " + schema + "";
		executeScript(script); }
	
	/* Drop Table */
	public void DropTable(String table) {
		String script = new String();
		script = "DROP TABLE IF EXISTS " + table + " CASCADE";
		executeScript(script); }
	
	/* Create new Table with no attributes */
	public void CreateTable(String table) {
		DropTable(table);
		String script = new String();
		script = "CREATE TABLE " + table + " ()";
		executeScript(script); }
	
	/* Create new Table with attributes */
	public void CreateTable(String table, String attributes) {
		DropTable(table);
		String script = new String();
		String split[] = new String[Global.MAX_FIELDS * 2];
		String subsplit[] = new String[2];
		int i;
		script = "CREATE TABLE " + table + " (";
		split = attributes.split(Global.DELIMITER_FIELD);
		for(i=0; i<=split.length-1; i++) {
			subsplit = split[i].split(Global.DELIMITER_ATTRIBUTE);
			script = script + "" + subsplit[0] + " " + subsplit[1];
			if(i<=split.length-2) script = script + Global.DELIMITER_ATTRIBUTE + " ";} 
		script = script + ")";
		executeScript(script); }

	/* Create new Table with attributes and Key*/
	public void CreateTable(String table, String attributes, String key) {
		String split[] = new String[Global.MAX_FIELDS * 2];
		String subsplit[] = new String[2];
		int i;
		CreateTable(table,attributes);
		split = key.split(Global.DELIMITER_FIELD);
		for(String str:split) {
			subsplit = str.split(Global.DELIMITER_ATTRIBUTE);
			i=0;
			for(String substr:subsplit) {substr.isEmpty(); i++;}
			if(i == 1) AlterPrimaryKey(table,str);
			else if(i == 3) AlterForeignKey(table,str); } }

	/* Create new Table with attributes and Primary Key and Foreign Key*/
	public void CreateTable(String table, String attributes, String primarykey, String foreignkey) {
		CreateTable(table,attributes);
		AlterPrimaryKey(table,primarykey);
		AlterForeignKey(table,foreignkey); }
	
	/* Add Primary Key to a Table */
	public void AlterPrimaryKey(String table, String primarykey) {
		String script = new String();
		String split[] = new String[Global.MAX_FIELDS * 2];
		int i;
		script = "ALTER TABLE IF EXISTS " + table + " ADD PRIMARY KEY (";
		split = primarykey.split(Global.DELIMITER_ATTRIBUTE);
		for(i=0; i<=split.length-1; i++) {
			script = script + "" + split[i] + "";
			if(i<=split.length-2) script = script + Global.DELIMITER_ATTRIBUTE;	}
		script = script + ")";
		executeScript(script); }
	
	/* Add Foreign Key to a Table */
	public void AlterForeignKey(String table, String foreignkey) {
		String script = new String();
		String split[] = new String[Global.MAX_FIELDS * 2];
		String subsplit[] = new String[2];
		int i = 0, j, k;
		script = "ALTER TABLE IF EXISTS " + table + "";
		split = foreignkey.split(Global.DELIMITER_FIELD);
		for(String str:split) {str.isEmpty(); i++;}
		for(j=0; j<=i-1; j++) {
			script = script + " ADD FOREIGN KEY (";
			subsplit = split[j].split(Global.DELIMITER_ATTRIBUTE);
			k = 0;
			for(String str:subsplit) {
				if(k == 0) script = script + "" + str + ") "; 
				else if(k == 1) script = script + "REFERENCES " + str + "";
				else if(k == 2) script = script + " (" + str + ")";
				k++;}
			if(j<=i-2) script = script + Global.DELIMITER_ATTRIBUTE;}
		executeScript(script); }
	
	/* Insert values into a Table for all attributes */
	public void InsertIntoTable(String table, String attributes, String values) {
		String script = new String();
		String split[] = new String[Global.MAX_FIELDS * 2];
		int i;
		script = "INSERT INTO " + table + " ";
		if(attributes != null) {
			script = script + "(";
			split = attributes.split(Global.DELIMITER_ATTRIBUTE);
			for(i=0; i<=split.length-1; i++) {
				script = script + split[i];
				if(i<=i-2) script = script + Global.DELIMITER_ATTRIBUTE; }
			script = script + ") "; }
		script = script + "VALUES (";
		split = values.split(Global.DELIMITER_ATTRIBUTE);
		for(i=0; i<=split.length-1; i++) {
			//split[i] = split[i].replaceAll("'" + Global.DELIMITER_ATTRIBUTE + "''");
			//split[i] = split[i].replace('','\'');
			script = script + split[i];
			if(i<=split.length-2) script = script + Global.DELIMITER_ATTRIBUTE; }
		script = script + ")";
		executeScript(script); }

	/* Insert values into a Table */
	public void InsertIntoTable(String table, String values) {
		InsertIntoTable(table,null,values); }
	
	/* Insert values into a Table from array of values */
	public void InsertIntoTable(String table, String[] values) {
		for(int i = 0; i <= values.length-1; i++) {
			InsertIntoTable(table,null,values[i]); } }
	
	private void executeScript(String script) {
		try {
			statement = connect.createStatement();	
			statement.executeUpdate(script);
			Close(); }
		catch(Exception e) {} }
	
	public void Close() {
		try {
			statement.close();
			Disconnect(); }
		catch (SQLException e) {} }
	
}
