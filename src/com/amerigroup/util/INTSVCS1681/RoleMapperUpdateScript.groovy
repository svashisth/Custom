package com.amerigroup.util.INTSVCS1681

import com.amerigroup.util.CSVParser

class RoleMapperUpdateScript {

	static main(args) {
		println "-- Plan/Role Mapper updates --"
		use(CSVParser.class) {
//			File file = new File("C:/work/CNR/INTSVCS-1681/PRM_IT_20150609.csv")
//			File file = new File("C:/work/SNOW/SNOW-361/PRM_IT_20150913.csv")
			def list1 = [], list2 = [];
//			File file = new File("C:/work/SNOW/SNOW-1705/PRM Update File-24DEC15.csv")
			File file = new File("C:/code/java/Custom/resources/PRM.IT.xlsx.csv")
			file.parseCSV { index, field ->
				def col = field[2]
				if(col && ! col.equalsIgnoreCase("N/A"))
					println index + " INSERT INTO #pcr VALUES ('${field[0].trim()}', '${field[1].trim()}', '${field[2].trim()}');"
				else 
					println index
//				list1.add("INSERT INTO #pcr VALUES ('${field[0]}', '${field[1]}', '${field[2]}');");
			}
			
			/*
			File file1 = new File("C:/work/SNOW/SNOW-1705/PRM_IT_20151125.csv")
			file1.parseCSV { index,field ->
				//println "INSERT INTO #pcr VALUES ('${field[0]}', '${field[1]}', '${field[2]}');"
//				list2.add("INSERT INTO #pcr VALUES ('${field[0]}', '${field[1]}', '${field[2]}');");
			}
//			def conflict = list1.minus(list2);
//			println conflict.size
*/		
			}
	}

}
