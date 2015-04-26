package fundamentals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.Query;
import main.admin.AdminRightPanelEdit;

public class Util {
	
	public static Double getDistancefromLatLonbyKm(Double lat1, Double lon1, Double lat2, Double lon2) {
		Double dLat = Math.toRadians(lat2-lat1);
		Double dLon = Math.toRadians(lon2-lon1); 
		Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2); 
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		Double d = Global.EARTH_RADIUS * c;
		d = Double.parseDouble(String.format("%1$.2f", d));
		return d; }
	
	public static Date StringtoDate(String format, String string) {
		Date date;
		DateFormat df = new SimpleDateFormat(format);
		try {
			date = df.parse(string);
			return date; }
		catch (ParseException e) {return null;} }

	public static void adminClearData(){
		Global.LIST_ADMIN_AIRLINE.clear();
		Global.LIST_ADMIN_AIRPORT.clear();
		Global.LIST_ADMIN_AIRCRAFT.clear();
		Global.LIST_ADMIN_ROUTE.clear();
		Global.LIST_ADMIN_REALFLIGHT.clear();
		Global.LIST_ADMIN_SCHEDULE.clear();
		Global.COUNT = 0; }
	
	public static void modClearData(){
		Global.LIST_MOD_SCHEDULE.clear();
		Global.LIST_MOD_STATISTICS_GENERAL.clear();
		Global.LIST_MOD_STATISTICS_TOP_AIRCRAFT.clear();
		Global.LIST_MOD_STATISTICS_TOP_ROUTE.clear();
		Global.LIST_MOD_REAL_FLIGHT.clear(); }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compare(Object o1, Object o2) {
		return ((MyComparable)o1).compareTo(o2); }
	
	public static void setDisableButtonAdminEdit(){
		AdminRightPanelEdit.buttonAirlineAdd.setDisable(true);
		AdminRightPanelEdit.buttonAirlineDelete.setDisable(true);
		AdminRightPanelEdit.buttonAirlineEdit.setDisable(true);
		AdminRightPanelEdit.buttonAirportAdd.setDisable(true);
		AdminRightPanelEdit.buttonAirportDelete.setDisable(true);
		AdminRightPanelEdit.buttonAirportEdit.setDisable(true);
		AdminRightPanelEdit.buttonRouteAdd.setDisable(true);
		AdminRightPanelEdit.buttonRouteDelete.setDisable(true);
		AdminRightPanelEdit.buttonRouteEdit.setDisable(true);
		AdminRightPanelEdit.buttonScheduleAdd.setDisable(true);
		AdminRightPanelEdit.buttonScheduleDelete.setDisable(true);
		AdminRightPanelEdit.buttonScheduleEdit.setDisable(true);
		AdminRightPanelEdit.buttonAircraftInfoAdd.setDisable(true);
		AdminRightPanelEdit.buttonAircraftInfoDelete.setDisable(true);
		AdminRightPanelEdit.buttonAircraftInfoEdit.setDisable(true); }
	
	public static void ScanInformationFromDatabase(String... inf) {
		for(String s : inf) {
			switch(s) {
			case "AircraftID":
				if (Global.AIRCRAFT_ID == null || Global.EDITING_AIRCRAFT_ID == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.AIRCRAFT_ID = q.ScanAircraftID();
					Global.EDITING_AIRCRAFT_ID = false;
					q.Close(); }
				break;
			case "Airlines":
				if (Global.AIRLINES == null || Global.EDITING_AIRLINES == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.AIRLINES = q.ScanAirline();
					Global.EDITING_AIRLINES = false;
					q.Close(); }
				break;
			case "Airports":
				if (Global.AIRPORTS == null || Global.EDITING_AIRPORTS == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.AIRPORTS = q.ScanAirport();
					Global.EDITING_AIRPORTS = false;
					q.Close(); }
				break;
			case "CityCountry":
				if (Global.CITY_COUNTRY == null || Global.EDITING_CITY_COUNTRY == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.CITY_COUNTRY = q.ScanCityCountry();
					Global.EDITING_CITY_COUNTRY = false;
					q.Close(); }
				break;
			case "FlightID":
				if (Global.FLIGHT_ID == null || Global.EDITING_FLIGHT_ID == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.FLIGHT_ID = q.ScanFlightID();
					Global.EDITING_FLIGHT_ID = false;
					q.Close(); }
				break;
			case "IataAirlines":
				if (Global.IATA_AIRLINES == null || Global.EDITING_IATA_AIRLINES == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.IATA_AIRLINES = q.ScanIataAirline();
					Global.EDITING_IATA_AIRLINES = false;
					q.Close(); }
				break;
			case "IataAirports":
				if (Global.IATA_AIRPORTS == null || Global.EDITING_IATA_AIRPORTS == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.IATA_AIRPORTS = q.ScanIataAirport();
					Global.EDITING_IATA_AIRPORTS = false;
					q.Close(); }
				break;
			case "Routes":
				if (Global.ROUTES == null || Global.EDITING_ROUTES == true) {
					Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
					Global.ROUTES = q.ScanRoute();
					Global.EDITING_ROUTES = false;
					q.Close(); }
				break; } } }
}
