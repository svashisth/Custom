package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/split_to_comma.txt').text
def start = "\'"
def end = "\'"
def newStr = ''<<''
fileContents.split("\n").each {
	def id = it.trim()
	newStr <<= start << id.substring(0, id.length()) << end << ","
}
print newStr.toString().substring(0, newStr.toString().length()-1)
