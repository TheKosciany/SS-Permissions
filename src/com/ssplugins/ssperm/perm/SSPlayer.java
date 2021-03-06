package com.ssplugins.ssperm.perm;

import com.ssplugins.ssperm.util.Unloadable;
import org.bukkit.entity.Player;

import java.util.Set;

public interface SSPlayer extends Unloadable {
	
	Player getPlayer();
	
	String id();
	
	Permissions getPermissions();
	
	Set<String> getAllPermissions();
	
	Settings getSettings();
	
	Group getGroup();
	
	void resetGroups();
	
	String getChatFormat();
	
	void refreshChatFormat();
	
}
