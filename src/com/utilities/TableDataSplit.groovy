package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/tableData.csv').text
def newStr = ''<<''
fileContents.split("\n").each {
	//	def id = it.trim()
	//	newStr <<= "\'" << id.substring(0, id.length()) << "\',"
	//	println it.trim()
	def tabSplitted =  it.trim().replaceAll("\t", ",")
	//	println tabSplitted
	def comSplitted =  tabSplitted.split(",")
	//	println comSplitted[3]
	println "INSERT INTO PreCertSpecialProc (grgr_id, cspi_id, proc_gcp_link, create_time, eff_date, term_date) VALUES('${comSplitted[0]}','${comSplitted[1]}','${comSplitted[2]}',getdate(), CONVERT(datetime,'${comSplitted[3]}',120),CONVERT(datetime,'${comSplitted[4]}',120))"
}
