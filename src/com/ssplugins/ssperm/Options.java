package com.ssplugins.ssperm;

import com.ssplugins.ssperm.callback.Callback;
import com.ssplugins.ssperm.callback.ChangeCallback;
import com.ssplugins.ssperm.perm.Settings;
import com.ssplugins.ssperm.util.Util;
import org.bukkit.ChatColor;

class Options implements Settings {
	
	private String prefix;
	private String suffix;
	private ChatColor nameColor;
	private ChatColor nameFormat;
	private ChatColor chatColor;
	private ChatColor chatFormat;
	
	private ChangeCallback callback = null;
	
	Options() {
		prefix = null;
		suffix = null;
		nameColor = null;
		nameFormat = null;
		chatColor = null;
		chatFormat = null;
	}
	
	void setCallback(ChangeCallback callback) {
		this.callback = callback;
	}
	
	@Override
	public String getPrefix() {
		return Util.color(prefix);
	}
	
	@Override
	public boolean setPrefix(String prefix) {
		if (!Callback.handle(callback, "prefix", this.prefix, prefix)) return false;
		if (prefix.equalsIgnoreCase(Util.getConfigNull("prefix"))) prefix = null;
		this.prefix = prefix;
		if (callback != null) callback.afterChange("prefix");
		return true;
	}
	
	@Override
	public String getSuffix() {
		return Util.color(suffix);
	}
	
	@Override
	public boolean setSuffix(String suffix) {
		if (!Callback.handle(callback, "suffix", this.suffix, suffix)) return false;
		if (suffix.equalsIgnoreCase(Util.getConfigNull("suffix"))) suffix = null;
		this.suffix = suffix;
		if (callback != null) callback.afterChange("suffix");
		return true;
	}
	
	@Override
	public ChatColor getNameColor() {
		return nameColor;
	}
	
	@Override
	public boolean setNameColor(ChatColor color) {
		if (!Callback.handle(callback, "nameColor", this.nameColor, color)) return false;
		if (color != null && String.valueOf(color.getChar()).equalsIgnoreCase(Util.getConfigNull("nameColor"))) color = null;
		nameColor = color;
		if (callback != null) callback.afterChange("nameColor");
		return true;
	}
	
	@Override
	public ChatColor getNameFormat() {
		return nameFormat;
	}
	
	@Override
	public boolean setNameFormat(ChatColor format) {
		if (!Callback.handle(callback, "nameFormat", this.nameFormat, format)) return false;
		if (format != null && String.valueOf(format.getChar()).equalsIgnoreCase(Util.getConfigNull("nameFormat"))) format = null;
		nameFormat = format;
		if (callback != null) callback.afterChange("nameFormat");
		return true;
	}
	
	@Override
	public ChatColor getChatColor() {
		return chatColor;
	}
	
	@Override
	public boolean setChatColor(ChatColor color) {
		if (!Callback.handle(callback, "chatColor", this.chatColor, color)) return false;
		if (color != null && String.valueOf(color.getChar()).equalsIgnoreCase(Util.getConfigNull("chatColor"))) color = null;
		chatColor = color;
		if (callback != null) callback.afterChange("chatColor");
		return true;
	}
	
	@Override
	public ChatColor getChatFormat() {
		return chatFormat;
	}
	
	@Override
	public boolean setChatFormat(ChatColor format) {
		if (!Callback.handle(callback, "chatFormat", this.chatFormat, format)) return false;
		if (format != null && String.valueOf(format.getChar()).equalsIgnoreCase(Util.getConfigNull("chatFormat"))) format = null;
		chatFormat = format;
		if (callback != null) callback.afterChange("chatFormat");
		return true;
	}
	
	@Override
	public void unsafeSet(String name, String value) {
		if (value.equalsIgnoreCase(Util.getConfigNull(name))) value = null;
		switch (name) {
			case "prefix":
				prefix = value;
				break;
			case "suffix":
				suffix = value;
				break;
			case "nameColor":
				nameColor = (value == null ? null : ChatColor.getByChar(value));
				break;
			case "nameFormat":
				nameFormat = (value == null ? null : ChatColor.getByChar(value));
				break;
			case "chatColor":
				chatColor = (value == null ? null : ChatColor.getByChar(value));
				break;
			case "chatFormat":
				chatFormat = (value == null ? null : ChatColor.getByChar(value));
				break;
			default:
				break;
		}
	}
}
