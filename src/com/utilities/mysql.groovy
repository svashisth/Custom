package com.utilities

import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/test", "root", "root", "com.mysql.jdbc.Driver")

def updateCounts = sql.withBatch("insert into test.ti(amount, tr_date) values (?, ?)") { ps ->
	(1..10000).each { i ->
		ps.addBatch([3*i, getDate()])
		ps.addBatch([5*i, getDate()])
		ps.addBatch(i, getDate())
	}
}

println "FINISHED ..... "

public Date getDate() {
	def dateA = Date.parse("dd-MM-yyyy", "01-01-2015")
	int range = 365
	def randomInterval = new Random().nextInt(range)
	def dateB = dateA.plus(randomInterval)
	return dateB
}