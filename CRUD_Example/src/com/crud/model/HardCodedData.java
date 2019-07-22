package com.crud.model;

import java.util.ArrayList;

public class HardCodedData {

	public ArrayList<String[]> getFirmCode(){
		
		ArrayList<String[]> firmArr = new ArrayList<String[]>();
		
		firmArr.add(new String[]{"AA", "AA"});
		firmArr.add(new String[]{"BB", "BB"});
		firmArr.add(new String[]{"CC", "CC"});
		firmArr.add(new String[]{"DD", "DD"});
		
		return firmArr;
	}
}
