package com.ssplugins.ssperm.events;

import com.ssplugins.ssperm.perm.SSPlayer;
import com.ssplugins.ssperm.util.SSEvent;
import org.bukkit.entity.Player;

public class PlayerModifyPermissionEvent extends SSEvent {
	
	private SSPlayer player;
	private String permission;
	private boolean add;
	
	public PlayerModifyPermissionEvent(SSPlayer player, String permission, boolean add) {
		this.player = player;
		this.permission = permission;
		this.add = add;
	}
	
	public Player getPlayer() {
		return player.getPlayer();
	}
	
	public SSPlayer getSSPlayer() {
		return player;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public boolean isAdd() {
		return add;
	}
}
