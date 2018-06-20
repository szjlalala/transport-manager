package com.tms.util.excel;


import org.springframework.util.StringUtils;

public class MarkBGColorCell {
	
	private String displayValue;
	private MarkBGColor bgColor;
	
	public MarkBGColorCell(){}
	
	
	
	public MarkBGColorCell(String displayValue, MarkBGColor bgColor) {
		this.displayValue = displayValue;
		this.bgColor = bgColor;
	}



	public String getDisplayValue() {
		return displayValue;
	}


	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}


	public MarkBGColor getBgColor() {
		return bgColor;
	}


	public void setBgColor(MarkBGColor bgColor) {
		this.bgColor = bgColor;
	}




	public static enum MarkBGColor{
		PINK,        //粉红
		LIGHT_GREEN, //浅绿
		NAVY,        //深蓝
		WHITE,       //白色
		GRAY,        //灰色
		ROSE,		 //玫瑰色
		CORNFLOWER_BLUE,
	}




	@Override
	public String toString() {
		String value = StringUtils.isEmpty(displayValue)?"":displayValue;
		String color = bgColor!=null?bgColor.toString():"";
		return value+"/"+color;
	}
	
	
}
