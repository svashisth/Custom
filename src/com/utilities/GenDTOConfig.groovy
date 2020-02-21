package com.utilities


def count = 0
def str = "BATCH_RUN_TIME, UMI_RUN_TIME, UMUM_REF_ID, I_SOURCE, ERROR_DETAILS, I_SOURCE_AUTH_ID, I_AUTH_ID, I_BATCH_NO"
def new_s = ""
str.split(",").each { string ->
	println "<field name=\"" + toCamelCase(string.trim()) +"\" type=\""+ getType(string.trim()) +"\" javadoc=\"" + string + " column\" column=\"" + string.trim() + "\" />"
	count++;
}

static String toCamelCase( String text) {
	text = text.toLowerCase().replaceAll("(_)([A-Za-z0-9])", {   Object[] it ->
		it[2].toUpperCase()
	}
	)
	return text
}

static String toSnakeCase( String text ) {
	text.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
}

static String getType(String text) {
	if(text.endsWith("DT")) return "Date";
	if(text.endsWith("NO") || text.endsWith("ID")) return "int";
	return "String";
}

def qStr = "";
1.upto(count) { qStr += "? ," }	//includes 1 & 56

qStr = qStr.substring(0, qStr.length()-1);
println ""
println  qStr.count("?")
println ""
print qStr