package com.tsunderebug.speedrun4j.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NameStyle {

	private String style;
	private Map<String, String> color;
	@SerializedName("color-from") private Map<String, String> colorFrom;
	@SerializedName("color-to") private Map<String, String> colorTo;


}
