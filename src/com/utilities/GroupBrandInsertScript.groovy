package com.utilities

import com.amerigroup.util.CSVParser;


class GroupBrandInsertScript {

	static main(args) {		
		use(CSVParser.class) {
			File file = new File("C:/code/java/Custom/src/com/trial/Groups_Site.csv")
			file.parseCSV { index, field ->
				//print index
				println "INSERT INTO GRGR_Brand_Mapping (GRGR_CK, GRGR_ID, GRGR_NAME, Brand, EffectiveDate, TerminationDate, CreateTime) VALUES(${field[0]},'${field[1]}','${field[2]}'," +
						"'${field[4]}',CONVERT(datetime,'${field[5]}',101),CONVERT(datetime,'${field[6]}',101),${field[7]})"
			}
		}
	}
}