package com.trial

def fileContents = new File('C:/code/java/Custom/src/com/trial/codes.txt').text
def newStr = ''<<''
fileContents.split("\n").each {
	def id = it.trim()
	newStr <<= "\'" << id.substring(0, id.length()) << "\',"
}
print newStr.toString().substring(0, newStr.toString().length()-1)
