package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Achievement implements Subject{
	private HashMap<String, String> save;
	private static Achievement uniqueInstance;
	private ArrayList<OrbSever> orbsevers=new ArrayList<OrbSever>();
	private String hardCore;
	private String time;
	private Achievement() {
		this.save = new HashMap<String, String>();
		save.put("easy", "--");
		save.put("normal","--");
		save.put("hard", "--");
		
	}
	public static synchronized Achievement getInstance() {
	if(uniqueInstance==null) {
		uniqueInstance = new Achievement();
	}
	return uniqueInstance;
	
}
	public HashMap<String, String> getSave() {
		return save;
	}
	public void setSave(HashMap<String, String> save) {
		this.save = save;
		
	}
	
	
	public void setAchieture(String hardCore, String time) {
		if(save.get(hardCore)=="--") {
			this.save.put(hardCore, time);
			this.hardCore=hardCore;this.time=time;
			notifyOrbSever();
		}
		else {
			if(Integer.valueOf(save.get(hardCore))>Integer.valueOf(time)) {
				this.save.put(hardCore, time);
				notifyOrbSever();
			}
		}
		
		
	}
	@Override
	public void registerOrbSever(OrbSever o) {
		orbsevers.add(o);
		
	}
	@Override
	public void removeOrbSever(OrbSever o) {
		int j=orbsevers.indexOf(o);
		if(j>=0) {
			orbsevers.remove(j);
		}
		
	}
	@Override
	public void notifyOrbSever() {
		for(OrbSever ob : orbsevers) {
			OrbSever item = ob;
			item.achievement(hardCore, time);
		}
		
	}
	public ArrayList<String> key(){
		ArrayList<String>rs = new ArrayList<String>();
		for(Map.Entry<String, String> i : save.entrySet()) {
			rs.add(i.getKey());
		}
		return rs;
	}
	public ArrayList<String> value(){
		ArrayList<String>rs = new ArrayList<String>();
		for(Map.Entry<String, String> i : save.entrySet()) {
			rs.add(i.getValue());
		}
		return rs;
	}
	public static void main(String[] args) {
	
	}
	
}
