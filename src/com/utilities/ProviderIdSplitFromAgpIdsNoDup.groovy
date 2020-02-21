package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/agpIds.txt').text
//def start = "\""
def start = "\'"
//def start = "<item>"
//def end = "\""
def end = "\'"
//def end = "</item>"
def newStr = ''<<''
def set = new TreeSet<String>();
def items = 0;
fileContents.split("\n").each {
	def id = it.trim()
	if(id.size() > 0)
		set.add(id);
	items++;
}
println "Total items " << items << " ,set size " << set.size()
set.each { id->
	newStr <<= start << id.substring(0, id.length()) << end << ","
}
print newStr.toString().substring(0, newStr.toString().length()-1)
