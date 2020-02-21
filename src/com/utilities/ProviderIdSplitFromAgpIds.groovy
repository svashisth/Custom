package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/agpIds.txt').text
//def start = "\""
def start = "\'"
//def start = ""
//def start = "<item>"
//def end = "\""
def end = "\'"
//def end = ""
//def end = "</item>"
def newStr = ''<<''
fileContents.split("\n").each {
	def id = it.trim()
	if(id.size() > 0)
		newStr <<= start << id.substring(0, id.length()) << end << ","
}
print newStr.toString().substring(0, newStr.toString().length()-1)
