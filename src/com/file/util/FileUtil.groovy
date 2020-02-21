package com.file.util;

//-- Find al sub-folder names in a folder  
new File("C:\\code\\pluto_work").eachDir() { dir ->
	println dir.getName()
}