package com.utilities

def fileContents = new File('C:/code/java/Custom/src/com/utilities/nl_split.txt').text
def start = ""
def end = ""
def newStr = ''<<''
fileContents.split(",").each {
	def id = it.trim()
	newStr <<= start << id.substring(0, id.length()) << end << "\n"
}
print newStr.toString().substring(0, newStr.toString().length()-1)
