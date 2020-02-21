package com.utilities

def fileContents = new File('C:/code/Custom/src/com/utilities/compare1.txt').text

def set = new TreeSet<String>();
def dups = []
def items = 0;
fileContents.split("\n").each {
	def id = it.trim()
	if(id.size() > 0) {
		if(set.contains(id)) {
			dups.add(id)
		}
		set.add(id);
	}
	items++;
}
println "Total items " << items << " ,set size " << set.size()

def list = [];
list.addAll(set);

println "Duplicates " + dups

println "list1 " + list

def fileContents2 = new File('C:/code/Custom/src/com/utilities/compare2.txt').text
def set2 = new TreeSet<String>();
def dups2 = []
items = 0;
fileContents2.split("\n").each {
	def id = it.trim()
	if(id.size() > 0) {
		if(set2.contains(id)) {
			dups2.add(id)
		}
		set2.add(id);
	}
	items++;
}
println "Total items " << items << " ,set size " << set2.size()

def list2 = [];
list2.addAll(set2);

println "list2 " + list2

println  "Missing in list " << list2 - list