package com.ssplugins.ssperm;

import com.ssplugins.ssperm.callback.ChangeCallback;
import com.ssplugins.ssperm.callback.ListCallback;
import com.ssplugins.ssperm.callback.NamedCallback;
import com.ssplugins.ssperm.util.Config;
import com.ssplugins.ssperm.util.Util;

abstract class PermissionHolder {
	
	private PermList permList;
	private Options options;
	
	private NamedCallback optionCallback = null;
	private ListCallback<String> permissionCallback = null;
	
	PermissionHolder(boolean group, String id) {
		Config config = (group ? Manager.getGroups() : Manager.getPlayer(id));
		permList = new PermList();
		permList.setCallback((item, add) -> {
			if (add) Util.addToList(config, id + ".permissions", item);
			else Util.removeFromList(config, id + ".permissions", item);
			AttMan attMan = Manager.get().getAttMan();
			if (group) attMan.groupUpdate(id, item, add);
			else attMan.playerUpdate(id, item, add);
			if (permissionCallback != null) permissionCallback.onUpdate(item, add);
		});
		options = new Options();
		options.setCallback(new ChangeCallback() {
			@Override
			public boolean onChange(String key, String oldValue, String newValue) {
				if (newValue == null) newValue = Util.getConfigNull(key);
				if (!Util.verify(key, newValue)) return false;
				config.set(id + ".options." + key, newValue);
				return true;
			}
			
			@Override
			public void afterChange(String name) {
				if (optionCallback != null) optionCallback.onCall(name);
			}
		});
		
		if (config.contains(id + ".permissions")) config.getConfig().getStringList(id + ".permissions").forEach(permList::addSilent);
		else if (group) config.setDefault(id + ".permissions", Util.noneList());
		Util.loadOptions(options, config, id);
		if (group) config.setDefault(id + ".inherits", Util.noneList());
		config.save();
	}
	
	void setOptionCallback(NamedCallback optionCallback) {
		this.optionCallback = optionCallback;
	}
	
	void setPermissionCallback(ListCallback<String> callback) {
		this.permissionCallback = callback;
	}
	
	PermList getPermList() {
		return permList;
	}
	
	Options getOptions() {
		return options;
	}
}
