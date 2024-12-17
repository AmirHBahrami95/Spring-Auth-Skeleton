package com.amir.app.utils;

import java.util.Map;

public class DbUtils {
	
	public static String expandUpdateQuery(Map<String,String> args) {
		StringBuffer buff=new StringBuffer();
		int size=args.size();
		int i=0;
    for (Map.Entry<String,String> entry : args.entrySet()) {
    	if(i!=0)buff.append(",");
    	buff.append(entry.getKey()+"=:"+entry.getKey());
    	i++;
    }
    return buff.toString();
	}
	
	public static String expandInsertQuery(Map<String,String> args) {
		StringBuffer buff=new StringBuffer();
		int size=args.size();
		int i=0;
    for (Map.Entry<String,String> entry : args.entrySet()) {
    	if(i!=0)buff.append(",");
    	buff.append(entry.getKey());
    	i++;
    }
    return buff.toString();
	}
	
	public static String expandInsertValues(Map<String,String> args) {
		StringBuffer buff=new StringBuffer();
		int size=args.size();
		int i=0;
    for (Map.Entry<String,String> entry : args.entrySet()) {
    	if(i!=0)buff.append(",");
    	buff.append(":"+entry.getKey());
    	i++;
    }
    return buff.toString();
	}
	
}
