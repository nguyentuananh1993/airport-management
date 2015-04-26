package fundamentals;

import ternarysearchtree.TernarySearchTree;
import graphs.Edge;
import graphs.SearchGraph;
import graphs.Vertex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.AdminLeftPanel;
import main.GuestLeftPanel;
import main.MainPanel;
import main.MainStage;
import main.ModLeftPanel;
import main.admin.AdminRightPanelAccount;
import main.admin.AdminRightPanelBackupAndRestore;
import main.admin.AdminRightPanelEdit;
import main.admin.AdminRightPanelStatistics;
import main.guest.GuestRightPanelSearchAirport;
import main.guest.GuestRightPanelSearchFlight;
import main.guest.GuestRightPanelSearchPath;
import main.guest.GuestRightPanelSearchRoute;
import main.mod.ModRightPanelAccount;
import main.mod.ModRightPanelEditSchedule;
import main.mod.ModRightPanelRealFlight;
import main.mod.ModRightPanelStatistics;
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
import model.GuestPathInfo;
import model.GuestRouteInfo;
import model.AdminRouteInfo;
import model.ModStatisticsTopAircraftInfo;
import model.ModStatisticsTopRouteInfo;

// Khai bao cac bien toan cuc trong chuong trinh
public class Global {

	public static final int REF_LIMIT = 30000; //max reference
	public static final int MAX_FIELDS = 20;
	public static final int VERTICES_LIMIT = 5;
	public static final int MILISECONDS_IN_A_DAY = 86400000;
	public static final Double INFINITY = Double.POSITIVE_INFINITY;
	public static final Double EARTH_RADIUS = 6367.445; //km
	public static final String DELIMITER_FIELD = ";";
	public static final String DELIMITER_ATTRIBUTE = ",";
	
// Cum bien cho dang nhap	
	public static final String DATABASE = "airports_management";
	public static final String ADMIN = "administrator"; // admin
	public static final String ADMIN_PASSWORD = "hedspi"; // admin password
	public static final String POSTGRES_ADMIN = "admin"; // postgres role admin
	public static final String POSTGRES_ADMIN_PASSWORD = "admin"; // postgres role admin password
	public static final String POSTGRES_MOD = "mod"; // postgres role admin
	public static final String POSTGRES_MOD_PASSWORD = "mod"; // postgres role admin password
	public static final String POSTGRES_GUEST = "guest"; // postgres role admin
	public static final String POSTGRES_GUEST_PASSWORD = "guest"; // postgres role admin password

	public static int COUNT = 0;
	public static String USER_NAME; //current user name
	
//	Bien luu tru thong tin
	public static boolean EDITING_AIRCRAFT_ID = false;
	public static boolean EDITING_AIRLINES = false;
	public static boolean EDITING_AIRPORTS = false;
	public static boolean EDITING_CITY_COUNTRY = false;
	public static boolean EDITING_FLIGHT_ID = false;
	public static boolean EDITING_IATA_AIRLINES = false;
	public static boolean EDITING_IATA_AIRPORTS = false;
	public static boolean EDITING_ROUTES = false;
	public static TernarySearchTree AIRCRAFT_ID;
	public static TernarySearchTree AIRLINES;
	public static TernarySearchTree AIRPORTS;
	public static TernarySearchTree CITY_COUNTRY;
	public static TernarySearchTree FLIGHT_ID;
	public static TernarySearchTree IATA_AIRLINES;
	public static TernarySearchTree IATA_AIRPORTS;
	public static SearchGraph<Vertex, Edge> ROUTES;
	
	// Khung dieu khien chinh
	public static Stage PRIMARY_STAGE;
	public static MainStage MAIN_STAGE;
	public static MainPanel MAIN_PANEL;
	
// Cum bien cho cac man hinh admin
	public static FXMLLoader LOADER_ADMIN_LEFT_PANEL;
	public static FXMLLoader LOADER_ADMIN_RIGHT_PANEL_STATISTICS;
	public static FXMLLoader LOADER_ADMIN_RIGHT_PANEL_BACKUP_AND_RESTORE;
	public static FXMLLoader LOADER_ADMIN_RIGHT_PANEL_EDIT;
	public static FXMLLoader LOADER_ADMIN_RIGHT_PANEL_ACCOUNT;
// Cum khung cho man hinh admin 	
	public static BorderPane PANE_ADMIN_LEFT_PANEL;
	public static BorderPane PANE_ADMIN_RIGHT_PANEL_ACCOUNT;
	public static BorderPane PANE_ADMIN_RIGHT_PANEL_BACKUP_AND_RESTORE;
	public static BorderPane PANE_ADMIN_RIGHT_PANEL_EDIT;
	public static BorderPane PANE_ADMIN_RIGHT_PANEL_STATISTICS;
	
//	Cum bien cho man hinh mod
	public static FXMLLoader LOADER_MOD_LEFT_PANEL;
	public static FXMLLoader LOADER_MOD_RIGHT_PANEL_EDIT_SCHEDULE;
	public static FXMLLoader LOADER_MOD_RIGHT_PANEL_REAL_FLIGHT;
	public static FXMLLoader LOADER_MOD_RIGHT_PANEL_STATISTICS;
	public static FXMLLoader LOADER_MOD_RIGHT_PANEL_ACCOUNT;
// Cum khung cho man hinh mod 
	public static BorderPane PANE_MOD_LEFT_PANEL;
	public static BorderPane PANE_MOD_RIGHT_PANEL_ACCOUNT;
	public static BorderPane PANE_MOD_RIGHT_PANEL_EDIT_SCHEDULE;
	public static BorderPane PANE_MOD_RIGHT_PANEL_REAL_FLIGHT;
	public static BorderPane PANE_MOD_RIGHT_PANEL_STATISTICS;
		
// Cum bien cho man hinh guest
	public static FXMLLoader LOADER_GUEST_LEFT_PANEL;
	public static FXMLLoader LOADER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT;
	public static FXMLLoader LOADER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT;
	public static FXMLLoader LOADER_GUEST_RIGHT_PANEL_SEARCH_PATH;
	public static FXMLLoader LOADER_GUEST_RIGHT_PANEL_SEARCH_ROUTE;
// Cum khung cho man hinh guest
	public static BorderPane PANE_GUEST_LEFT_PANEL;
	public static BorderPane PANE_GUEST_RIGHT_PANEL_SEARCH_FLIGHT;
	public static BorderPane PANE_GUEST_RIGHT_PANEL_SEARCH_AIRPORT;
	public static BorderPane PANE_GUEST_RIGHT_PANEL_SEARCH_PATH;
	public static BorderPane PANE_GUEST_RIGHT_PANEL_SEARCH_ROUTE;
	
// Admin
	public static AdminLeftPanel CONTROLLER_ADMIN_LEFT_PANEL;
	public static AdminRightPanelAccount CONTROLLER_ADMIN_RIGHT_PANEL_ACCOUNT;
	public static AdminRightPanelBackupAndRestore CONTROLLER_ADMIN_RIGHT_PANEL_BACKUP_AND_RESTORE;
	public static AdminRightPanelEdit CONTROLLER_ADMIN_RIGHT_PANEL_EDIT;
	public static AdminRightPanelStatistics CONTROLLER_ADMIN_RIGHT_PANEL_STATISTICS;
// Danh sach du lieu Admin
	public static ObservableList<AdminAirlineInfo> LIST_ADMIN_AIRLINE = FXCollections.observableArrayList();
	public static ObservableList<AdminRouteInfo> LIST_ADMIN_ROUTE = FXCollections.observableArrayList();
	public static ObservableList<AdminAirportInfo> LIST_ADMIN_AIRPORT = FXCollections.observableArrayList();
	public static ObservableList<AdminRealFlightInfo> LIST_ADMIN_REALFLIGHT = FXCollections.observableArrayList();
	public static ObservableList<AdminAircraftInfo> LIST_ADMIN_AIRCRAFT = FXCollections.observableArrayList();
	public static ObservableList<AdminScheduleInfo> LIST_ADMIN_SCHEDULE = FXCollections.observableArrayList();
	
// Mod 	
	public static ModLeftPanel CONTROLLER_MOD_LEFT_PANEL;
	public static ModRightPanelAccount CONTROLLER_MOD_RIGHT_PANEL_ACCOUNT;
	public static ModRightPanelEditSchedule CONTROLLER_MOD_RIGHT_PANEL_EDIT_SCHEDULE;
	public static ModRightPanelRealFlight CONTROLLER_MOD_RIGHT_PANEL_REAL_FLIGHT;
	public static ModRightPanelStatistics CONTROLLER_MOD_RIGHT_PANEL_STATISTICS;
// Danh sach du lieu Mod
	public static ObservableList<ModScheduleInfo> LIST_MOD_SCHEDULE = FXCollections.observableArrayList();
	public static ObservableList<ModRealFlight> LIST_MOD_REAL_FLIGHT = FXCollections.observableArrayList();
	public static ObservableList<ModStatisticsGeneralInfo> LIST_MOD_STATISTICS_GENERAL = FXCollections.observableArrayList();
	public static ObservableList<ModStatisticsTopRouteInfo> LIST_MOD_STATISTICS_TOP_ROUTE = FXCollections.observableArrayList();
	public static ObservableList<ModStatisticsTopAircraftInfo> LIST_MOD_STATISTICS_TOP_AIRCRAFT = FXCollections.observableArrayList();
	
// Guest
	public static GuestLeftPanel CONTROLLER_GUEST_LEFT_PANEL;
	public static GuestRightPanelSearchAirport CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT;
	public static GuestRightPanelSearchFlight CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT;
	public static GuestRightPanelSearchRoute CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE;
	public static GuestRightPanelSearchPath CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH;
//	Danh sach du lieu Guest
	public static ObservableList<GuestAirportInfo> LIST_GUEST_AIRPORT = FXCollections.observableArrayList();
	public static ObservableList<GuestFlightInfo> LIST_GUEST_FLIGHT = FXCollections.observableArrayList();
	public static ObservableList<GuestPathInfo> LIST_GUEST_PATH = FXCollections.observableArrayList();
	public static ObservableList<GuestRouteInfo> LIST_GUEST_ROUTE = FXCollections.observableArrayList();
	
	// Cac ban cho cum admin
	public static ObservableList<AdminStatisticAirportByCountry> LIST_ADMIN_STATISTICS_AIRPORT_BY_COUNTRY = FXCollections.observableArrayList();
	public static ObservableList<AdminStatisticAirportTop10> LIST_ADMIN_STATISTICS_AIRPORT_TOP_10 = FXCollections.observableArrayList();
	public static ObservableList<AdminStatisticAirlineByCountry> LIST_ADMIN_STATISTICS_AIRLINE_BY_COUNTRY = FXCollections.observableArrayList();
	public static ObservableList<AdminStatisticAirlineTop10> LIST_ADMIN_STATISTICS_AIRLINE_TOP_10 = FXCollections.observableArrayList();
	public static ObservableList<AdminStatisticEachAirport> LIST_ADMIN_STATISTICS_EACH_AIRPORT = FXCollections.observableArrayList();
		
}
