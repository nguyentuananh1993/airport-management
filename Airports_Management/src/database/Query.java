package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import ternarysearchtree.TernarySearchTree;
import fundamentals.Global;
import fundamentals.MyLinkedList;
import fundamentals.Util;
import graphs.Edge;
import graphs.SearchGraph;
import graphs.SearchGraph.Path;
import graphs.Vertex;
import model.AdminAircraftInfo;
import model.AdminAirlineInfo;
import model.AdminRealFlightInfo;
import model.AdminScheduleInfo;
import model.AdminStatisticAirlineByCountry;
import model.AdminStatisticAirlineTop10;
import model.AdminStatisticAirportByCountry;
import model.AdminStatisticAirportTop10;
import model.AdminStatisticEachAirport;
import model.GuestAirportInfo;
import model.AdminAirportInfo;
import model.GuestFlightInfo;
import model.ModRealFlight;
import model.ModScheduleInfo;
import model.ModStatisticsGeneralInfo;
import model.ModStatisticsTopAircraftInfo;
import model.ModStatisticsTopRouteInfo;
import model.MyDate;
import model.MyTimestamp;
import model.GuestPathInfo;
import model.GuestRouteInfo;
import model.AdminRouteInfo;

public class Query extends Connect {
	private Statement statement = null;
	private ResultSet resultset = null;

	public Query() {
		super(); }

	public Query(String db, String us, String pw) {
		super(db, us, pw); }

	public Query(String us, String pw) {
		super(us, pw); }

	private void executeQuery(String query) {
		try {
			statement = connect.createStatement();
			resultset = statement.executeQuery(query); }
		catch (Exception e) {} }

	public void Close() {
		try {
			if (resultset != null) resultset.close();
			if (statement != null) statement.close();
			Disconnect(); }
		catch (SQLException e) {} }

	/*
	 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * *\
	 * 
	 * Query function
	 * 
	 * \* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * *
	 */

	// Ham Check Login
	public boolean CheckLogin(String name, String password) {
		executeQuery("SELECT password FROM account WHERE name = '" + name + "'");
		try {
			while (resultset.next()) {
				if (resultset.getString("password").equals(password)) {
					return true; } }
			return false; }
		catch (SQLException e) {
			return false; } }

	public MyLinkedList<String> FindAirlineName(int id) {
		MyLinkedList<String> list = new MyLinkedList<String>();
		executeQuery("SELECT name FROM airline WHERE id = " + id);
		try {
			while (resultset.next()) {
				list.addLast(resultset.getString("name")); } }
		catch (Exception e) {}
		return list; }

	public MyLinkedList<String> FindAirportName(int id) {
		MyLinkedList<String> list = new MyLinkedList<String>();
		executeQuery("SELECT name FROM airport WHERE id = " + id);
		try {
			while (resultset.next()) {
				list.addLast(resultset.getString("name")); } }
		catch (Exception e) {}
		return list; }

	public MyLinkedList<Integer> FindAirlineID(String name) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		executeQuery("SELECT id FROM airline WHERE name = '" + name + "'");
		try {
			while (resultset.next()) {
				list.addLast(resultset.getInt("id")); }
			if (list.isEmpty())
				Global.MAIN_PANEL.setStatus("Cannot find airline '" + name + "'"); }
		catch (Exception e) {}
		return list; }

	public MyLinkedList<Integer> FindAirportID(String name) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		executeQuery("SELECT id FROM airport WHERE name = '" + name + "'");
		try {
			while (resultset.next()) {
				list.addLast(resultset.getInt("id")); }
			if (list.isEmpty())
				Global.MAIN_PANEL.setStatus("Cannot find airport '" + name + "'"); }
		catch (Exception e) {}
		return list; }
	
	/*
	 * Cac ham quet du lieu de dua vao combo box
	 */

	public TernarySearchTree ScanAircraftID() {
		TernarySearchTree id = new TernarySearchTree();
		executeQuery("SELECT id FROM aircraftinfo");
		String i;
		try {
			while(resultset.next()) {
				i = resultset.getString("id");
				if(i != null && i.length() != 0) id.put(i); } }
		catch (SQLException e) {}
		return id; }

	public TernarySearchTree ScanIataAirport() {
		TernarySearchTree iata = new TernarySearchTree();
		executeQuery("SELECT iata FROM airport");
		String i;
		try {
			while(resultset.next()) {
				i = resultset.getString("iata");
				if(i != null && i.length() != 0) iata.put(i); } }
		catch (SQLException e) {}
		return iata; }
	
	public TernarySearchTree ScanFlightID() {
		TernarySearchTree id = new TernarySearchTree();
		executeQuery("SELECT flight_id FROM schedule");
		String i;
		try {
			while(resultset.next()) {
				i = resultset.getString("flight_id");
				if(i != null && i.length() != 0) id.put(i); } }
		catch (SQLException e) {}
		return id; }

	
	public TernarySearchTree ScanIataAirline() {
		TernarySearchTree iata = new TernarySearchTree();
		executeQuery("SELECT iata FROM airline");
		String i;
		try {
			while(resultset.next()) {
				i = resultset.getString("iata");
				if(i != null && i.length() != 0) iata.put(i); } }
		catch (SQLException e) {}
		return iata; }

	public TernarySearchTree ScanCityCountry() {
		TernarySearchTree citycountry = new TernarySearchTree();
		executeQuery("SELECT city, country FROM airport");
		String c;
		try {
			while(resultset.next()) {
				c = resultset.getString("city");
				if(c.length() != 0) citycountry.put(c);
				c = resultset.getString("country");
				if(c.length() != 0) citycountry.put(c); } }
		catch (SQLException e) {}
		return citycountry; }

	public TernarySearchTree ScanAirport() {
		TernarySearchTree airport = new TernarySearchTree();
		executeQuery("SELECT name FROM airport");
		try {
			while(resultset.next()) {
				airport.put(resultset.getString("name")); } }
		catch (SQLException e) {}
		return airport; }

	public TernarySearchTree ScanAirline() {
		TernarySearchTree airline = new TernarySearchTree();
		executeQuery("SELECT name FROM airline");
		try {
			while(resultset.next()) {
				airline.put(resultset.getString("name")); } }
		catch (SQLException e) {}
		return airline; }
	
	public SearchGraph<Vertex, Edge> ScanRoute() {
		SearchGraph<Vertex, Edge> route = new SearchGraph<Vertex, Edge>();
		int v1, v2;
		Double lat1, lat2, lon1, lon2;
		String name1, name2;
		executeQuery("SELECT dep_airport_id, arr_airport_id, "
				+ "a1.name name1, a2.name name2, a1.latitude lat1, a1.longitude lon1, a2.latitude lat2, a2.longitude lon2 "
				+ "FROM route, airport a1, airport a2 "
				+ "WHERE dep_airport_id = a1.id AND arr_airport_id = a2.id "
				+ "ORDER BY dep_airport_id ASC, arr_airport_id ASC");
		try {
			while (resultset.next()) {
				v1 = resultset.getInt("dep_airport_id");
				v2 = resultset.getInt("arr_airport_id");
				name1 = resultset.getString("name1");
				name2 = resultset.getString("name2");
				lat1 = resultset.getDouble("lat1");
				lon1 = resultset.getDouble("lon1");
				lat2 = resultset.getDouble("lat2");
				lon2 = resultset.getDouble("lon2");
				route.addVertex(v1, new Vertex(name1));
				route.addVertex(v2, new Vertex(name2));
				route.setEdge(v1,v2,new Edge(Util.getDistancefromLatLonbyKm(lat1, lon1, lat2, lon2))); } }
		catch (Exception e) {}
		return route; }

	/*
	 * Cac ham cho cum man hinh Guest
	 */

	// Hàm tim kiem san bay
	public void GuestSearchAirport(String citycountry) {
		executeQuery("SELECT id,name,city,country,iata,timezone FROM airport"
				+ " WHERE city = '"	+ citycountry + "' OR country = '" + citycountry + "'");
		try {
			while (resultset.next()) {
				Global.COUNT++;
				Global.LIST_GUEST_AIRPORT.add(new GuestAirportInfo(resultset.getInt("id"),resultset.getString("name"),
						resultset.getString("city"), resultset.getString("country"), resultset.getString("iata"), 
						resultset.getDouble("timezone"))); } }
		catch (Exception e) {} }

	public void GuestSearchAirport(int id) {
		executeQuery("SELECT name,city,country,iata,timezone FROM airport WHERE id = " + id);
		try {
			while (resultset.next()) {
				Global.COUNT++;
				Global.LIST_GUEST_AIRPORT.add(new GuestAirportInfo(id, resultset.getString("name"), resultset.getString("city"),
						resultset.getString("country"), resultset.getString("iata"), 
						resultset.getDouble("timezone"))); } }
		catch (Exception e) {} }

	public void GuestSearchFlight(LinkedList<GuestFlightInfo> list, Date depdate, Date arrdate) {
		GuestFlightInfo f2;
		String q;
		for (GuestFlightInfo f1 : list) {
			q = "SELECT a1.id depairportid, a2.id arrairportid, a.id airlineid, "
					+ "a1.name depairportname, a2.name arrairportname, a.name airlinename, day, deptime, arrtime, duration "
					+ "FROM route r, schedule s, realflight rf, airport a1, airport a2, airline a "
					+ "WHERE r.id = s.route_id and s.flight_id = rf.flight_id AND "
					+ "r.dep_airport_id = a1.id AND r.arr_airport_id = a2.id AND airline_id = a.id";
			if (f1.getDepairportid() != 0) {q = q + " AND r.dep_airport_id = " + f1.getDepairportid(); }
			if (f1.getArrairportid() != 0) {q = q + " AND r.arr_airport_id = " + f1.getArrairportid(); }
			if (f1.getAirlineid() != 0) {q = q + " AND airline_id = " + f1.getAirlineid(); }
			if (depdate != null) {q = q + " AND day >= '" + depdate + "'"; }
			if (arrdate != null) {q = q + " AND day <= '" + arrdate + "'"; }
			executeQuery(q);
			try {
				while (resultset.next()) {
					f2 = new GuestFlightInfo(resultset.getInt("depairportid"),resultset.getInt("arrairportid"),resultset.getInt("airlineid"),
							resultset.getString("depairportname"),resultset.getString("arrairportname"),resultset.getString("airlinename"),
							new MyDate(resultset.getDate("day").getTime()),new MyTimestamp(resultset.getTimestamp("deptime").getTime()),
							new MyTimestamp(resultset.getTimestamp("arrtime").getTime()),
							resultset.getDouble("duration"));
					if (!GuestFlightInfo.CheckExistedFlightInfo(f2, Global.LIST_GUEST_FLIGHT)) {
						Global.LIST_GUEST_FLIGHT.add(f2);
						Global.COUNT++;	} }	} catch (Exception e) {} } }

	public void GuestSearchContinuousFlight(LinkedList<GuestFlightInfo> list,
			Date depdate, Date arrdate) {
		GuestFlightInfo f2 = null, f3;
		MyDate d;
		MyTimestamp t;
		String q1, q2;
		d = new MyDate(0);
		t = new MyTimestamp(0);
		for (GuestFlightInfo f1 : list) {
			q1 = "SELECT a1.id depairportid, a2.id arrairportid, a.id airlineid, "
					+ "a1.name depairportname, a2.name arrairportname, a.name airlinename, day, deptime, arrtime, duration "
					+ "FROM route r, schedule s, realflight rf, airport a1, airport a2, airline a "
					+ "WHERE r.id = s.route_id and s.flight_id = rf.flight_id AND "
					+ "r.dep_airport_id = a1.id AND r.arr_airport_id = a2.id AND airline_id = a.id";
			if (f1.getDepairportid() != 0) {q1 = q1 + " AND r.dep_airport_id = " + f1.getDepairportid(); }
			if (f1.getArrairportid() != 0) {q1 = q1 + " AND r.arr_airport_id = " + f1.getArrairportid(); }
			if (f1.getAirlineid() != 0) {q1 = q1 + " AND airline_id = " + f1.getAirlineid(); }
			if (depdate != null) {q1 = q1 + " AND day >= '" + depdate + "'"; }
			if (arrdate != null) {q1 = q1 + " AND day <= '" + arrdate + "'"; }
			q1 = q1 + " AND ((day > '" + d + "') OR (day = '" + d + "' AND deptime >= '" + t + "'))";
			q2 = new String(q1);
			q1 = q1 + " ORDER BY day, deptime, arrtime";
			q1 = q1 + " LIMIT 1";
			try {
				f2 = null;
				executeQuery(q1);
				while (resultset.next()) {
					f2 = new GuestFlightInfo(resultset.getInt("depairportid"),resultset.getInt("arrairportid"),resultset.getInt("airlineid"),
							resultset.getString("depairportname"),resultset.getString("arrairportname"),resultset.getString("airlinename"), 
							new MyDate(resultset.getDate("day").getTime()),
							new MyTimestamp(resultset.getTimestamp("deptime").getTime()), 
							new MyTimestamp(resultset.getTimestamp("arrtime").getTime()),resultset.getDouble("duration"));
					break; }
				if (f2 != null) {
					if (f2.getArrtime().compareTo(f2.getDeptime()) >= 0) d = f2.getDay();
					else d = new MyDate(f2.getDay().getTime() + Global.MILISECONDS_IN_A_DAY);
					t = f2.getArrtime();
					q2 = q2 + " AND day = '" + f2.getDay() + "' AND deptime = '" + f2.getDeptime() + "'";
					executeQuery(q2);
					while (resultset.next()) {
						f3 = new GuestFlightInfo(resultset.getInt("depairportid"),resultset.getInt("arrairportid"),resultset.getInt("airlineid"),
								resultset.getString("depairportname"),resultset.getString("arrairportname"),resultset.getString("airlinename"),
								new MyDate(resultset.getDate("day").getTime()),
								new MyTimestamp(resultset.getTimestamp("deptime").getTime()),
								new MyTimestamp(resultset.getTimestamp("arrtime").getTime()),resultset.getDouble("duration"));
						if (!GuestFlightInfo.CheckExistedFlightInfo(f3, Global.LIST_GUEST_FLIGHT)) {
							Global.LIST_GUEST_FLIGHT.add(f3);
							Global.COUNT++; } } }
				else break; }
			catch (Exception e) {} } }

	// Ham tim kiem Path
	public void GuestSearchPath(int departedairport, int arrivedairport,
			int intermediate) throws CloneNotSupportedException {
		MyLinkedList<Path<Vertex>> list = new MyLinkedList<Path<Vertex>>();
		Global.ROUTES.setVerticesLimit(intermediate);

		list = Global.ROUTES.ListofPath(departedairport, arrivedairport);
		if (!list.isEmpty()) {
			for (Path<Vertex> p : list) {
				Global.LIST_GUEST_PATH.add(new GuestPathInfo(p.getLength(), p.getPath()));	}
			Global.COUNT += list.size(); } }

	// Tim kiem lich trinh route
	public void GuestSearchRoute(int departedairport, int arrivedairport) {
		int id;
		String departedairportname = new String(), arrivedairportname = new String(), airlinename = new String();

		executeQuery("SELECT airline_id, a1.name departedairportname, a2.name arrivedairportname, a.name airlinename "
				+ "FROM route, airport a1, airport a2, airline a "
				+ "WHERE dep_airport_id = a1.id AND arr_airport_id = a2.id AND airline_id = a.id "
				+ "AND dep_airport_id = " + departedairport	+ " AND arr_airport_id = " + arrivedairport);
		try {
			while (resultset.next()) {
				Global.COUNT++;
				id = resultset.getInt("airline_id");
				departedairportname = resultset.getString("departedairportname");
				arrivedairportname = resultset.getString("arrivedairportname");
				airlinename = resultset.getString("airlinename");
				Global.LIST_GUEST_ROUTE.add(new GuestRouteInfo(
						departedairport,arrivedairport,id,departedairportname,arrivedairportname,airlinename)); } }
		catch (Exception e) {} }

	/*================================================================================================================*/
	// Cum query cho chuc nang cua mod



	public void ModDeleteSchedule(String id ){
		try{
			executeQuery("DELETE FROM schedule " + "WHERE flight_id LIKE '%"+id+"%';");	}
		catch (Exception e) {} }

	// Doi mat khau cua mod
	public void ModChangePass(String name, String pass) {
		executeQuery("UPDATE account SET password = '" + pass + "' WHERE name = '" + name + "'"); }
	
	// Cum thuc hien chuc nang thong ke cua mod
	
	public void ModStatisticsGeneral(String name, Date fromday, Date today) {
		int people = 0;
		int flight = 0;
		int route = 0;
		executeQuery("SELECT sum(numpeople) sumpeople FROM realflight WHERE day >= '" + fromday 
				+ "' AND day <= '" + today + "' AND flight_id IN ( SELECT flight_id"
	        	+ " FROM schedule WHERE route_id IN ( SELECT route.id FROM route, airline WHERE route.airline_id"
	        	+ " = airline.id AND airline.name = '" + name + "'))");
		try {
			while(resultset.next()) {
				people += resultset.getInt("sumpeople"); } }
		catch (SQLException e) {}

		executeQuery("SELECT count(flight_id) sumflight FROM realflight "
				+ "WHERE day >= '" + fromday + "' AND day <= '" + today + "' AND  numpeople > '0' "
						+ "AND flight_id IN ( SELECT flight_id FROM schedule WHERE route_id IN ( SELECT id "
						+ "FROM route WHERE airline_id IN ( SELECT id FROM airline WHERE name = '" + name + "'))) ;");
		try {
			while(resultset.next()) {
				flight += resultset.getInt("sumflight"); } }
		catch (SQLException e) {}

		executeQuery("SELECT count(route_id) sumroute FROM schedule "
				+ "WHERE flight_id IN (SELECT flight_id FROM realflight WHERE  day >= '" + fromday + "' "
						+ "AND day <= '" + today + "' AND  numpeople > '0' AND flight_id IN (SELECT flight_id "
								+ "FROM schedule WHERE route_id IN (SELECT id "
								+ "FROM route WHERE airline_id IN (SELECT id "
								+ "FROM airline WHERE name = '" + name + "'))));");
		try {
			while(resultset.next()) {
				route += resultset.getInt("sumroute"); } }
		catch (SQLException e) {}
		
		Global.COUNT++;
		Global.LIST_MOD_STATISTICS_GENERAL.add(new ModStatisticsGeneralInfo(
				new MyDate(fromday.getTime()),new MyDate(today.getTime()),people,flight,route)); }
	
	public void ModStatisticsTopRoute(String name, Date fromday, Date today){
		executeQuery("with tmp AS ("
				+ "SELECT flight_id, count(flight_id) as numflight, sum(numpeople) as numpeople "
				+ "FROM realflight WHERE day >= '"+ fromday +"' AND day <= '"+ today +"' "
						+ "AND numpeople > '0' AND flight_id IN ("
						+ "SELECT flight_id FROM schedule "
						+ "WHERE route_id IN (SELECT id FROM route WHERE airline_id = ("
						+ "SELECT id FROM airline WHERE name = '"+ name +"'))) GROUP BY (flight_id)), "
								+ "tam AS (SELECT schedule.flight_id as flight_id, schedule.route_id, "
								+ "dep_airport_iata as dep, arr_airport_iata as arr "
								+ "FROM route, schedule "
								+ "WHERE route.id = schedule.route_id AND airline_id = ("
								+ "SELECT id FROM airline WHERE name = '"+ name+"')) "
										+ "SELECT tam.dep dep, tam.arr arr, tmp.numflight numflight, tmp.numpeople numpeople "
										+ "FROM tam, tmp "
										+ "WHERE tmp.flight_id = tam.flight_id;");
		try {
			while (resultset.next()) {
				Global.LIST_MOD_STATISTICS_TOP_ROUTE.add(new ModStatisticsTopRouteInfo(
					new MyDate(fromday.getTime()),new MyDate(today.getTime()),
					resultset.getString("dep"),resultset.getString("arr"),
					resultset.getInt("numflight"),resultset.getInt("numpeople")));
			    Global.COUNT++; } }
		catch (Exception e) {} }
	
	public void ModStatisticsTopAircraft(String name, Date fromday, Date today){
		Global.COUNT = 0;
		executeQuery("with tmp AS (SELECT flight_id, count(flight_id) as count FROM realflight "
				+ "WHERE numpeople > '0' AND day >= '"+ fromday +"' AND day <= '"+ today+ "' AND flight_id IN ( "
						+ "SELECT flight_id FROM schedule WHERE route_id IN ( "
						+ "SELECT id FROM route WHERE airline_id IN ( "
						+ "SELECT id FROM airline WHERE name = '"+ name +"'))) GROUP BY (flight_id)), "
								+ "tam AS (SELECT flight_id, aircraftinfo.name as name FROM schedule, aircraftinfo "
								+ "WHERE aircraftinfo.id = schedule.equip_id AND route_id IN ( "
								+ "SELECT id FROM route WHERE airline_id IN ( "
								+ "SELECT id FROM airline WHERE name = '"+ name + "'))) "
										+ "SELECT tam.name ten,sum(tmp.count) count FROM tam, tmp "
										+ "WHERE tam.flight_id = tmp.flight_id GROUP BY (ten);");
		try {
			while (resultset.next()) {
				Global.LIST_MOD_STATISTICS_TOP_AIRCRAFT.add(new ModStatisticsTopAircraftInfo(
						new MyDate(fromday.getTime()),new MyDate(today.getTime()),
						resultset.getString("ten"),resultset.getInt("count")));
				    Global.COUNT++;}}
		catch (Exception e) {} }
	
	
	// Cum statistics cho admin
	public void AdminStatisticsAirport() {
		executeQuery("SELECT country, count(id) as num FROM airport GROUP BY country ORDER BY (num) DESC;");
		try{ 
			while (resultset.next()){
				Global.LIST_ADMIN_STATISTICS_AIRPORT_BY_COUNTRY.add(new AdminStatisticAirportByCountry(
						resultset.getString("country"),resultset.getInt("num")));}}
		catch (Exception e) {} }
	
	public void AdminStatisticsAirline() {
		executeQuery("SELECT country, count(id) as num FROM airline WHERE active = 't' GROUP BY country;");
		try{ 
			while (resultset.next()){
				String name = resultset.getString("country");
				int num = resultset.getInt("num");
				Global.LIST_ADMIN_STATISTICS_AIRLINE_BY_COUNTRY.add(new AdminStatisticAirlineByCountry(name,num));}}
		catch (Exception e) {} }

	
	//Statistics cho Each Airport
	public void AdminStatisticsEachAirport(String name, Date fromday, Date today){
		int depofguest = 0;
		int arrofguest = 0;
		int guest = 0;
		int numofroute = 0;
		
		executeQuery("with bangtam1 AS (SELECT flight_id, sum(numpeople) as num FROM realflight "
				+ "WHERE day >= '"+ fromday +"' AND day <= '"+ today +"' GROUP BY (flight_id)), "
				+ "bangtam2 AS (SELECT schedule.route_id, bangtam1.num FROM schedule, bangtam1 "
				+ "WHERE bangtam1.flight_id = schedule.flight_id) SELECT sum(num) "
				+ "FROM route, bangtam2, airport WHERE route.id = bangtam2.route_id AND "
				+ "route.dep_airport_id = airport.id AND airport.name = '"+ name +"';");
		try {
			while (resultset.next()) {
				depofguest += resultset.getInt("sum"); } }
		catch (Exception e) {}
		
		executeQuery("with bangtam1 AS (SELECT flight_id, sum(numpeople) as num FROM realflight "
				+ "WHERE day >= '" + fromday +"' AND day <= '"+ today +"' GROUP BY (flight_id)), "
				+ "bangtam2 AS (SELECT schedule.route_id, bangtam1.num FROM schedule, bangtam1 "
				+ "WHERE bangtam1.flight_id = schedule.flight_id) SELECT sum(num) "
				+ "FROM route, bangtam2, airport WHERE route.id = bangtam2.route_id AND "
				+ "route.arr_airport_id = airport.id AND airport.name = '"+ name +"';");
		try {
			while (resultset.next()) {
				arrofguest += resultset.getInt("sum"); } }
		catch (Exception e) {}
		
		guest = arrofguest + depofguest;
		
		executeQuery("with tmp1 AS (SELECT count(id) as num1 FROM route "
				+ "WHERE route.dep_airport_id IN (SELECT id FROM airport WHERE name = '"+ name +"')), "
				+ "tmp2 AS ( SELECT count(id) as num2 FROM route "
				+ "WHERE route.arr_airport_id IN (SELECT id FROM airport WHERE name = '"+ name +"')) "
				+ "SELECT tmp1.num1 + tmp2.num2 as sum FROM tmp1, tmp2;");
		try {
			while (resultset.next()) {
				numofroute += resultset.getInt("sum"); } }
		catch (Exception e) {}
		Global.LIST_ADMIN_STATISTICS_EACH_AIRPORT.add(new AdminStatisticEachAirport(
				new MyDate(fromday.getTime()),new MyDate(today.getTime()),name,guest,numofroute)); }
	
	//Statistics cho AirportTop10
	public void AdminStatisticsAirportTop10Search(Date fromday, Date today){
		executeQuery("with tmp AS ( SELECT flight_id, sum(numpeople) as sum FROM realflight "
				+ "WHERE day >= '"+ fromday+"' AND day <= '" + today+"' GROUP BY (flight_id)), tam AS ( "
				+ "SELECT route_id, tmp.sum as sum FROM tmp, schedule WHERE tmp.flight_id = schedule.flight_id), "
				+ "ketqua AS( SELECT dep_airport_id as dep, arr_airport_id as arr, sum FROM tam, route WHERE tam.route_id = route.id),"
				+ "ketqua1 AS (SELECT dep, sum(sum) as kqua1 FROM ketqua GROUP BY (dep)), ketqua2 AS ( SELECT arr, sum(sum) as kqua2 "
				+ "FROM ketqua GROUP BY (arr)), ketqua3 AS ( SELECT dep, kqua1 FROM ketqua1 UNION ALL SELECT arr, kqua2 FROM ketqua2) "
				+ "SELECT airport.name as name, sum(ketqua3.kqua1) as tong FROM ketqua3, airport WHERE ketqua3.dep = airport.id "
				+ "GROUP BY(airport.name) ORDER BY (tong) DESC;");
		try {
			while (resultset.next()) {
				Global.LIST_ADMIN_STATISTICS_AIRPORT_TOP_10.add(new AdminStatisticAirportTop10(
					new MyDate(fromday.getTime()),new MyDate(today.getTime()),
					resultset.getString("name"),resultset.getInt("tong"))); }}
		catch (Exception e) {} }
	
	//Statistics cho AirlineTop10
	public void AdminStatisticsAirlineTop10Search(Date fromday, Date today){
		executeQuery("with tmp AS (SELECT flight_id, sum(numpeople) as sum "
				+ "FROM realflight WHERE day >= '"+ fromday+"' AND day <= '" + today +"'"
				+ "GROUP BY (flight_id)), tam AS (SELECT route_id, tmp.sum as sum "
				+ "FROM tmp, schedule WHERE tmp.flight_id = schedule.flight_id) "
				+ "SELECT airline.name as name, sum(sum) as sum FROM tam, route, airline "
				+ "WHERE tam.route_id = route.id AND route.airline_id = airline.id AND airline.active = 't' "
				+ "GROUP BY (airline.name) ORDER BY (sum) DESC;");
		try {
			while (resultset.next()) {
				Global.LIST_ADMIN_STATISTICS_AIRLINE_TOP_10.add(new AdminStatisticAirlineTop10(
					new MyDate(fromday.getTime()),new MyDate(today.getTime()),
					resultset.getString("name"),resultset.getInt("sum"))); }}
		catch (Exception e) {} }
	
	/*=========================================================================================================================================*/
	
	
	/*=============================================
	 * Cac ham cho cum man hinh Admin EDIT
	 *=============================================*/
	
	//Ham dung hien thi toan bo bang Airline
	public void AdminShowAirline(){
		executeQuery("SELECT * FROM airline ORDER BY id ASC");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_AIRLINE.add(
						new AdminAirlineInfo(resultset.getInt("id"), resultset.getString("name"), 
								resultset.getString("iata"),resultset.getString("country"), 
								resultset.getBoolean("active"))); } }
		catch (Exception e) {} }


	//Ham dung hien thi toan bo bang Airport
	public void AdminShowAirport(){
		executeQuery("SELECT * FROM airport ORDER BY id ASC");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_AIRPORT.add(
						new AdminAirportInfo(resultset.getInt("id"), resultset.getString("name"), 
						resultset.getString("city"),resultset.getString("country"), 
						resultset.getString("iata"),resultset.getDouble("latitude"),resultset.getDouble("longitude"),
						resultset.getInt("timezone"), resultset.getString("dst"))); } }
		catch (Exception e) {} }
		
		//Ham dung hien thi toan bo bang Routes
	public void AdminShowRoute(){
		executeQuery("SELECT * FROM route ORDER BY id ASC");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_ROUTE.add(
						new AdminRouteInfo(resultset.getInt("id"), resultset.getInt("airline_id"),resultset.getString("airline_iata"),
								resultset.getInt("dep_airport_id"), resultset.getString("dep_airport_iata"), resultset.getInt("arr_airport_id"),
								resultset.getString("arr_airport_iata"))); } }
		catch (Exception e) {} }

	public Integer SearchRouteId(String dep, String arr) {
		executeQuery("SELECT id as data FROM route WHERE dep_airport_iata='"+dep+"' AND arr_airport_iata='"+arr+"'"); 
		try {
			resultset.next();
			if( resultset.getInt("data")!=0)
				return resultset.getInt("data");
			else return 0;}
		catch (Exception e) {return 0;} } 

		//Ham dung hien thi toan bo bang Schedule
	public void AdminShowSchedule(){
		executeQuery("SELECT schedule.flight_id as flight_id, schedule.deptime as deptime, schedule.arrtime as arrtime, "
				+ "schedule.equip_id as equip_id, schedule.repeat as repeat, schedule.duration as duration, "
				+ "route.dep_airport_iata, route.arr_airport_iata FROM schedule,route WHERE schedule.route_id=route.id ORDER BY flight_id ASC");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_SCHEDULE.add(
						new AdminScheduleInfo(resultset.getString("flight_id"),
								resultset.getString("dep_airport_iata"),resultset.getString("arr_airport_iata"),
								resultset.getString("deptime"),resultset.getString("arrtime"),resultset.getString("equip_id"),
								resultset.getInt("repeat"),resultset.getDouble("duration"))); } }
		catch (Exception e) {} }
		
	//Ham dung hien thi toan bo bang RealFlight
	public void AdminShowRealFlight(){
		executeQuery("SELECT * FROM realflight ORDER BY flight_id ASC");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_REALFLIGHT.add(
						new AdminRealFlightInfo(resultset.getString("flight_id"),resultset.getDate("day"),resultset.getInt("numpeople"))); } }
		catch (Exception e) {} }
	
	
	//Ham dung hien thi toan bo bang Aircraft
	public void AdminShowAircraft(){
		executeQuery("SELECT * FROM aircraftinfo");
		try{
			Util.adminClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_ADMIN_AIRCRAFT.add(
						new AdminAircraftInfo(resultset.getString("id"),resultset.getString("name"),resultset.getInt("numofseat"))); } }
		catch (Exception e) {} }
		/****************************
	delete info               ***
	*****************************/
	// delete thong tin bang Airline
	public void AdminDeleteAirline(int id ){
			try{
			executeQuery("DELETE FROM airline " + "WHERE id="+id+" ;"); }
		catch (Exception e) {} }
		// delete thong tin bang AircraftInfo
	public void AdminDeleteAircraftInfo(String id ) {
		try{
			executeQuery("DELETE FROM aircraftinfo " + "WHERE id='"+id+"';"); }
		catch (Exception e) {} }
	
	// delete thong tin bang airport
	public void AdminDeleteAirport(int id ){
		try{
			executeQuery("DELETE FROM airport "	+ "WHERE id="+id+";"); }
		catch (Exception e) {} }
	
	// delete thong tin bang RealFlight
	public void AdminDeleteRealFlight(String id ) {
		try{
			executeQuery("DELETE FROM realflight " + "WHERE flight_id='%"+id+"%';"); }
		catch (Exception e) {} }
	
	// delete thong tin bang Route
	public void AdminDeleteRoute(int id ){
		try{
			executeQuery("DELETE FROM route " + "WHERE id="+id+";"); }
		catch (Exception e) {} }
	
	// delete thong tin bang schedule
	public void AdminDeleteSchedule(String id ){
		try{
			executeQuery("DELETE FROM schedule " + "WHERE flight_id LIKE '%"+id+"%';");	}
		catch (Exception e) {} }
	/****************************
	update info               ***
	 *****************************/

	// update thong tin bang Airline
	public void AdminUpdateAirline(AdminAirlineInfo data,int id ){
		try{
			executeQuery("UPDATE airline "
					+ "SET name='"+data.getName()+"', iata='"+data.getIata()+"', country='"+data.getCountry()+"', active="
					+data.getActive().toString()+" "
					+ "WHERE id="+id+";"); }
		catch (Exception e) {} }
	

	//anhnt	
	
	public Integer SearchAircraftId(String dep,boolean exe){ 
		Integer tmp=0;
		try{
			executeQuery("SELECT count(*) as data FROM aircraftinfo where id='"+dep+"'");
			resultset.next();
			if(resultset.getInt("data")==0)
				tmp=1; }
		catch (Exception e) {}
		if(exe) return 1;
		else return tmp; } 

	// update thong tin bang AircraftInfo
	public void AdminUpdateAircraftInfo(AdminAircraftInfo data,String id ){
		try{
			executeQuery("UPDATE aircraftinfo "
				+ "SET name='"+data.getName()+"', numofseat="+data.getNumofseat().toString()+" "
				+ "WHERE id='"+data.getId()+"';"); }
		catch (Exception e) {} }
	
	// update thong tin bang airport
	public void AdminUpdateAirport(AdminAirportInfo data,int id ){
		try{
			executeQuery("UPDATE airport SET name='"+data.getName()+"', city='"+data.getCity()+"', country='"+data.getCountry()+"', iata='"+data.getIata()+"', latitude= "+ data.getLatitude()+", longitude= "+data.getLongitude()+", timezone="+data.getTimezone()+", dst='"+data.getDst()+"' WHERE id="+id+";"); }
		catch (Exception e) {} }
	// update thong tin bang Route
	public void AdminUpdateRoute(AdminRouteInfo data,int id ){
		try{
			executeQuery("UPDATE route "
				+ "SET airline_id="+data.getAirline_id().toString()+", airline_iata= '"+data.getAirline_iata()
				+"', dep_airport_id="+data.getDep_airport_id().toString()
				+", dep_airport_iata= '"+data.getDep_airport_iata()+"', arr_airport_id="+data.getArr_airport_id().toString()
				+",arr_airport_iata='"+data.getArr_airport_iata()+"'"
				+ "WHERE id="+id+";"); }
		catch (Exception e) {} }
	
	public void AdminUpdateSchedule(AdminScheduleInfo data,String dep,String arr ){
		try{
			executeQuery("UPDATE schedule "
				+ "SET route_id='"+SearchRouteId(dep,arr).toString()+"', deptime='"+data.getDeptime().toString()
				+"', arrtime='"+data.getArrtime().toString()+"', equip_id='"+data.getEquip_id()
				+"', repeat="+data.getRepeat().toString()+", duration="+data.getDuration().toString()+" "
				+ "WHERE flight_id = '"+SearchRouteId(dep,arr).toString()+"';"); }
		catch (Exception e) {} }
	//insert thong tin bang airline
	public void AdminInsertAirline(int num,AdminAirlineInfo data){
		try{
			executeQuery("INSERT INTO airline(id, name, iata, country, active) VALUES("+num+",'"+data.getName()+
					"','"+data.getIata()+"','"+data.getCountry()+"',"+data.getActive()+");"); }
		catch (Exception e) {} }
	
	//insert thong tin vao aircraft
	public void AdminInsertAircraft(AdminAircraftInfo data){
		try{
			executeQuery("INSERT INTO aircraftinfo(id, name, numofseat) VALUES ('"
					+data.getId()+"','"+data.getName()+"',"+data.getNumofseat()+");"); }
		catch (Exception e) {} }
	
	//insert thong tin vao airport
	public void AdminInsertAirport(AdminAirportInfo data){
		try{
			executeQuery("INSERT INTO airport(id, name, city, country, iata, latitude, longitude, timezone, dst) VALUES ("
					+data.getId()+",'"+data.getName()+"','"+data.getCity()+"','"+data.getCountry()+"','"+data.getIata()
					+"',"+data.getLatitude().toString()+","+data.getLongitude().toString()+","
					+data.getTimezone().toString()+",'"+data.getDst()+"');"); }
		catch (Exception e) {} }
	
	//insert thong tin vao route
	public void AdminInsertRoute(AdminRouteInfo data){
		try{
			executeQuery("INSERT INTO route(id, airline_id, airline_iata, dep_airport_id, dep_airport_iata, "
					+ "arr_airport_id, arr_airport_iata) VALUES ("
					+data.getId().toString()+","+data.getAirline_id().toString()+",'"+data.getAirline_iata()+"',"
					+data.getDep_airport_id().toString()+",'"+data.getDep_airport_iata()
					+"',"+data.getArr_airport_id().toString()+",'"+data.getArr_airport_iata()+"');"); }
		catch (Exception e) {} }
	//
//insert thong tin vao schedule
	public void AdminInsertSchedule(AdminScheduleInfo data){
		try{
			executeQuery("INSERT INTO schedule(flight_id, route_id, deptime, arrtime, equip_id, repeat, duration) VALUES ('"
					+data.getFlight_id()+"',"+SearchRouteId(data.getDep_airport_iata(),data.getArr_airport_iata())+",'"
					+data.getDeptime()+"','"+data.getArrtime()+"','"+data.getEquip_id()
					+"',"+data.getRepeat()+","+data.getDuration()+");");
		}
		catch (Exception e) {} }
	
	public Integer AdminAirlineCount() {
		executeQuery("SELECT max(id) as data FROM airline;"); 
		try{ 
			resultset.next(); 
			return resultset.getInt("data"); }
		catch (Exception e) {return null;} }
	
	public Integer AdminAirportCount() { 
		executeQuery("SELECT max(id) as data FROM airport;"); 
		try{ 
			resultset.next();
			return resultset.getInt("data"); }
		catch (Exception e) {return null;} }
	
	public Integer AdminRouteCount() {
		executeQuery("SELECT max(id) as data FROM route;"); 
		try{
			resultset.next(); 
			return resultset.getInt("data"); }
		catch (Exception e) {return null;} } 
	
	// tim kiem ban ghi	cho truong flight_id trong bang schedule
	public Integer findSchedule(String name,boolean exe){
		Integer tmp=0;
		try{
			executeQuery("SELECT count(*) as data FROM schedule WHERE flight_id='"+name+"';");
			resultset.next();
			if(resultset.getInt("data")==0)
				tmp=1; }
		catch (Exception e) {}
		if(exe) return 1;
		else return tmp; }
	
	/*===========================================
	 * cac ham cho cum Admin Account
	 *==========================================*/
	// tim kiem ban ghi cho truong name trong account
	public Integer AdminAccountFindAccount(String name){
		Integer tmp=0;
		try{
			executeQuery("SELECT COUNT(*) as data FROM account WHERE name ='"+name+"' GROUP BY name;");
			resultset.next();
			if(resultset.getInt("data")==1)
				tmp=1;} 
		catch (Exception e) {}
		return tmp; }
	
	//thay the pass va reset pass trong account
	public void AdminAccountModifierAccount(String username, String password){
		try{
			executeQuery("UPDATE account set password='"+password+"' WHERE name='"+username+"'"); }
		catch (Exception e) {} }
	
	// xoa 1 ban ghi trong account
	public void AdminAccountDeleteAccount(String username){
		try{
			executeQuery("DELETE FROM account where name='"+username+"';"); }
		catch (Exception e) {} }
	
	public Integer ModFindAirlineId(String name) {
		executeQuery("SELECT id as data FROM airline WHERE name='"+name+"';"); 
		try{
			resultset.next(); 
			return resultset.getInt("data"); }
		catch (Exception e) {return null;} } 
	
	// hien thi bang Schedule
	public void ModShowScheduleData(String name){
		executeQuery("SELECT schedule.flight_id as flight_id, schedule.route_id as route_id,schedule.deptime as deptime, "
				+ "schedule.arrtime as arrtime, schedule.equip_id as equip_id, schedule.repeat as repeat, schedule.duration as duration  "
					+"FROM schedule,route,airline "
					+"WHERE airline.id = route.airline_id AND schedule.route_id=route.id AND airline.id="+ModFindAirlineId(name)+";");
		try{
			Util.modClearData();
			Global.COUNT=0;
			while(resultset.next()){
				Global.COUNT++;
				Global.LIST_MOD_SCHEDULE.add(new ModScheduleInfo(resultset.getString("flight_id"),
						resultset.getInt("route_id"),resultset.getString("deptime"),resultset.getString("arrtime"),
						resultset.getString("equip_id"),resultset.getInt("repeat"),resultset.getDouble("duration"))); } }
		catch (Exception e) {} }
	//update schedule mod
	
	public void ModUpdateSchedule(ModScheduleInfo data,String id ){
		try{
		executeQuery("UPDATE schedule "
			+ "SET route_id='"+data.getRoute_id()+"', deptime='"+data.getDeptime().toString()
			+"', arrtime='"+data.getArrtime().toString()+"', equip_id='"+data.getEquip_id()
			+"', repeat="+data.getRepeat().toString()+", duration="+data.getDuration().toString()+" "
			+ "WHERE flight_id = '"+id+"';"); }
	catch (Exception e) {} }
	
	public void ModShowRealFlightData(String name){
		executeQuery("SELECT realflight.flight_id as flight_id, realflight.day as day, realflight.numpeople as numpeople "
				+"FROM realflight, airline, route, schedule "
				+"WHERE airline.id=route.airline_id and route.id=schedule.route_id and schedule.flight_id = realflight.flight_id"
				+ " and airline.name='"+name+"';");
	try{
		Util.modClearData();
		Global.COUNT=0;
		while(resultset.next()){
			Global.COUNT++;
			Global.LIST_MOD_REAL_FLIGHT.add(new ModRealFlight(resultset.getString("flight_id"),resultset.getString("day"),
					resultset.getInt("numpeople"))); } }
	catch (Exception e) {} }
	
	public void ModInsertRealFlight(ModRealFlight data){
		try{
			executeQuery("INSERT INTO realflight(flight_id, day, numpeople) VALUES ('"+data.getFlight_id()+"', '"
					+data.getDay()+"', "+data.getNumpeople()+");");	}
		catch (Exception e) {} }
		
	public void ModUpdateRealFlight(ModRealFlight data,String id){
		try{
			executeQuery("UPDATE realflight SET numpeople="+data.getNumpeople()+" WHERE flight_id='"+id+"';"); }
		catch (Exception e) {} }
	
	// tim kiem ban ghi	cho truong flight_id trong bang schedule
	public void ModRealFlightDelete(String flight_id, String day) {
		try{
		executeQuery("DELETE FROM realflight WHERE flight_id='"+flight_id+"' AND day='"+day+"';"); }
		catch (Exception e) {} }
	
	public Integer SearchAirlineId(String name) {
		executeQuery("SELECT id as data FROM airline WHERE active='true' AND iata='"+name+"';"); 
		try{ 
			resultset.next();
			return resultset.getInt("data"); }
		catch (Exception e) {return 0;} } 
	
	public Integer SearchAirportId(String name) { 
		executeQuery("SELECT id as data FROM airport WHERE iata='"+name+"';");
		try{
			resultset.next();
			return resultset.getInt("data"); } 
		catch (Exception e) {return 0;} } 
	
	public Integer CheckAirlineIata(String name) { 
		executeQuery("SELECT count(*) as data FROM airline WHERE iata='"+name+"' AND active='true';"); 
		try{
			resultset.next(); 
			return resultset.getInt("data"); } 
		catch (Exception e) {return 0;} }
	
	public Integer CheckAirportIata(String name) {
		executeQuery("SELECT count(*) as data FROM airport WHERE iata='"+name+"';");
		try{ 
			resultset.next();
			return resultset.getInt("data"); } 
		catch (Exception e) {return 0;} }
	
	public Integer CheckFlightId(String name) {
		executeQuery("SELECT count(*) as data FROM schedule WHERE flight_id='"+name+"';"); 
		try{ 
			resultset.next();
			if ( resultset.getInt("data")!=0) return 1;
			else return 0; 
			} 
		catch (Exception e) {return 0;} }
	
	public Integer CheckEquipId(String name) {
		executeQuery("SELECT count(*) as data FROM aircraftinfo WHERE id='"+name+"';");
		try{ 
			resultset.next();
			return resultset.getInt("data"); } 
		catch (Exception e) {return 0; }}
	
	public Integer findNumberOfSeat(String name) {
		executeQuery("SELECT aircraftinfo.numofseat as data FROM schedule,aircraftinfo WHERE schedule.equip_id=aircraftinfo.id AND schedule.flight_id='"+name+"'");
		try{ 
			resultset.next();
			return resultset.getInt("data"); } 
		catch (Exception e) {return 0; }}
	
	public void	InsertAccount(Integer id, String name){
		try{
			executeQuery("INSERT INTO account (id, name, password) VALUES ("+id+", '"+name+"', '123456');");	
		}catch (Exception e) {}
	}
	
	public boolean checkAirlineIataAvailable(String iata){
		executeQuery("SELECT count(*) as data FROM airline WHERE active='true' AND iata='"+iata+"';");
		try{ 
			resultset.next();
			if(resultset.getInt("data")!=0)
			return true;
			else return false;
		} 
		catch (Exception e) {return false; }
	}
	public boolean CheckFlightAirlineAvailable(String airline, String flight_id){
		executeQuery("SELECT count(*) as data FROM airline, route,schedule WHERE airline.id=route.airline_id AND route.id=schedule.route_id AND schedule.flight_id='"+flight_id+"' AND airline.name='"+airline+"';");
		try{ 
			resultset.next();
			if(resultset.getInt("data")!=0)
			return true;
			else return false;
		} 
		catch (Exception e) {return false; }
	}
	public boolean checkScheduleAvailable(String flight_id, String date){
		executeQuery("SELECT count(*) as data FROM realflight WHERE flight_id='"+flight_id+"' AND day='"+date+"';");
		try{ 
			resultset.next();
			if(resultset.getInt("data")!=0)
			return true;
			else return false;
		} 
		catch (Exception e) {return false; }
	}
	public boolean checkAirportIata(String iata){
		executeQuery("SELECT count(*) as data FROM airport WHERE iata='"+iata+"';");
		try{ 
			resultset.next();
			if(resultset.getInt("data")!=0)
			return true;
			else return false;
		} 
		catch (Exception e) {return false; }
	}
}


