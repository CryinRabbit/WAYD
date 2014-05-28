package com.cryinrabbit.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 class Findit {
	
	public Findit(){		
	}
	
	public static HashSet<String> findWeek(String str){
		
		HashSet<String> evenWeekday=new HashSet<String>();
		String pattern=".{2,8}[1]?[0-9]:[0-5][0-9][P|A]M.{0,3}[1]?[0-9]:[0-5][0-9][P|A]M";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
        
        while(m.find()){
        	input=m.group();
        	evenWeekday.add(input);
        }
        
    
        return evenWeekday;
	}
	
	
	public static boolean findMo(String str){
		String pattern="Mo";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	
	public static boolean findTu(String str){
		String pattern="Tu";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	public static boolean findWe(String str){
		String pattern="We";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	public static boolean findTh(String str){
		String pattern="Th";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	public static boolean findFr(String str){
		String pattern="Fr";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	public static boolean findSa(String str){
		String pattern="Sa";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
       return m.find();
	}
	
	public static String findTime(String str){
		
		String pattern="[1]?[0-9]:[0-5][0-9][P|A]M.{0,3}[1]?[0-9]:[0-5][0-9][P|A]M";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
        
        while(m.find()){
        	input=m.group();
        }

        return input;
	}
	
public static String findStartTime(String str){
		
		String pattern="[1]?[0-9]:[0-5][0-9][P|A]M.";
		Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String input=null;
        
        while(m.find()){
        	input=m.group();
        }
        String input2="";
        for(int i=0; i<input.length()-1;i++){
        	input2+=input.charAt(i);
        }
        String pattern2="PM";
        Pattern r2 = Pattern.compile(pattern2);
        Matcher m2 = r2.matcher(input2);
        if(m2.find()){
        	String a=input2.replace("PM", "");
        	int b=Integer.parseInt(a.charAt(0)+"");
        	b=b+12;
        	String c=b+"";
        	for(int j=1;j<a.length();j++){
        		c=c+a.charAt(j);
        	}
        	input2=c;
        }
       
        	String pattern3="AM";
        	Pattern r3 = Pattern.compile(pattern3);
        	Matcher m3 = r3.matcher(input2);
        	if(m3.find()){
        	String a=input2.replace("AM", "");
        	input2=a;
        }
        
        return input2;
	}

	public static String findEndTime(String str){
		
		String pattern="[1]?[0-9]:[0-5][0-9][P|A]M";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(str);
	    String input=null;
	    
	    while(m.find()){
	    	input=m.group();
	    }
	    
	    String pattern2="PM";
	    Pattern r2 = Pattern.compile(pattern2);
	    Matcher m2 = r2.matcher(input);
	    if(m2.find()){
	    	String a=input.replace("PM", "");
	    	int b=Integer.parseInt(a.charAt(0)+"");
	    	b=b+12;
	    	String c=b+"";
	    	for(int j=1;j<a.length();j++){
	    		c=c+a.charAt(j);
	    	}
	    	input=c;
	    }
	   
	    	String pattern3="AM";
	    	Pattern r3 = Pattern.compile(pattern3);
	    	Matcher m3 = r3.matcher(input);
	    	if(m3.find()){
	    	String a=input.replace("AM", "");
	    	input=a;
	    }
	    
	    return input;
		}
	}	
	
 
 
 
 
 /*
public class Findit{
	public static void main(String[] args){
		String x="jhkjMoWe x2:00PM-4:00PM jlkjuop ljlk;j                                        ;;jlk ljklj;l ;l jklj kjkl MoWeFr 2:00PM-4:00PM jlkjuop ljlk;j ";
		Findit f=new Findit();
		HashSet<String> a=f.findWeek(x);
		int count=0;
		String[] schedule=new String[a.size()];
		Iterator iter = a.iterator();
		while (iter.hasNext()) {
			schedule[count++]=(String)iter.next();
			//System.out.println(iter.next());
		}
		
		for(int i=0;i<schedule.length;i++){
			String allWeekday="";
			for(int j=0;j<5;j++){
				allWeekday+=schedule[i].charAt(j);
				//System.out.println(allWeekday);
				//System.out.println(classTime);
				//System.out.println(schedule[i]);
			}
			
			String classTime=f.findTime(schedule[i]);
			if(f.findMo(schedule[i])){
				String event="Mo";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			if(f.findTu(schedule[i])){
				String event="Tu";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			if(f.findWe(schedule[i])){
				String event="We";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			if(f.findTh(schedule[i])){
				String event="Th";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			if(f.findFr(schedule[i])){
				String event="Fr";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			if(f.findSa(schedule[i])){
				String event="Sa";
				String startTime=f.findStartTime(schedule[i]);
				String endTime=f.findEndTime(schedule[i]);
				System.out.print(event+"\t");
				System.out.print(startTime+"\t");
				System.out.println(endTime);
				
			}
			
			
		}
	}
}
*/