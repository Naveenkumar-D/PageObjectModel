package utilities;


import java.lang.reflect.Method;
import java.util.Hashtable;


import org.testng.annotations.DataProvider;

import base.BaseTest;

public class TestUtil extends BaseTest {
	
	@DataProvider(name = "dp1")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Hashtable<String, String> table = null;

		Object[][] data = new Object[rows - 1][1];
		for (int rowNum = 1; rowNum < rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(0, colNum), excel.getCellData(rowNum, colNum));
				data[rowNum - 1][0] = table;
			}
		}
		return data;
	}

}
