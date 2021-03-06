package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberData {
	static final Logger logger = LoggerFactory.getLogger(SubscriberData.class);
	private DbManager dbManager;
	
	public SubscriberData(){
		dbManager = DbManager.getInstance();
	}
	
	public List<String> getSubscriberPhoneNumbersForMatch(Match matchId, int msgLimit){
		List<String> users = null;
		try{
			users = dbManager.getUsersByMatch(matchId.getMatchId(), msgLimit);
		}
		catch(SQLException ex) {
			logger.error(ex.toString());
		}
		if(users == null){
			users = new ArrayList<String>();
		}
		return users;
	}
	
	public void saveCodeForUser(String phoneNum, String code) throws SQLException {
		try {
			dbManager.addCodeForUser(code, phoneNum);
		}
		catch(SQLException ex) {
			logger.error(ex.toString());
			throw ex;
		}
	}
	
	public String getLastCodeRequestTime(String phoneNum){
		String date = "2014-07-19 11:00:00", tmpDate;
		try{
			tmpDate = dbManager.getLatestCodeRequestDate(phoneNum);
			if (tmpDate != null) {
				date = tmpDate;
			}
		}
		catch(SQLException ex) {
			logger.error(ex.toString());
		}
		return date;
	}
	
	public void saveMatchForUser(String phoneNum, String matchId) throws SQLException{
		try {
			dbManager.addMatchForUser(matchId, phoneNum);
		}
		catch(SQLException ex) {
			logger.error(ex.toString());
			throw ex;
		}
	}
	
	public boolean userSubscribedTo(String phoneNum, String matchId){
		boolean alreadySubscribed = false;
		try {
			alreadySubscribed = dbManager.matchIsAlreadyAddedForUser(matchId, phoneNum);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return alreadySubscribed;
	}
	
	public String getCodeByUser(String phoneNum){
		String code = "000000";
		try {
			code = dbManager.getCodeByUser(phoneNum);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return code;
	}
	
	public void countSentMessageForUser(String phoneNum){
		try {
			dbManager.countMessageForUser(phoneNum);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public void removeMatchForUser(String user, String matchId){
		try {
			dbManager.removeMatchForUser(matchId, user);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	
}
