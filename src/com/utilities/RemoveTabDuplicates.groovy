package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/duplicates.txt').text
def result = new File('C:/code/Custom/src/com/utilities/duplicates_result.csv')

def newStr = ''<<''
def data = [:]
fileContents.split("\n").each {
	def id = it.trim()
	if(data.get(id.split("\t")[0]) == null) {
		data.put(id.split("\t")[0], id.split("\t")[1].trim())
		result.append(id.split("\t")[0]+","+id.split("\t")[1].trim()+"\r\n")
	}
}

println data.size()
