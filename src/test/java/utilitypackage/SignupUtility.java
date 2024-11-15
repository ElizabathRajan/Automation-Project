package utilitypackage;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SignupUtility {
	
	public static String getCellValue(String filePath,String sheetName,int row,int column)
	{
		try
		{
			FileInputStream fi=new FileInputStream(filePath);
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
			if(cell.getCellType()==CellType.STRING)
			{
				return cell.getStringCellValue();
			}
			else
			{
				return cell.getRawValue();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	public static int getRowCount(String filePath,String sheetName)
	{
		try(FileInputStream fi=new FileInputStream(filePath))
		{
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			return wb.getSheet(sheetName).getLastRowNum();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}

//    public static String getCellValue(String filePath, String sheetName, int row, int column) {
//        try (FileInputStream fi = new FileInputStream(filePath);
//             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
//            
//            XSSFSheet sheet = wb.getSheet(sheetName);
//            XSSFCell cell = sheet.getRow(row).getCell(column);
//            
//            if (cell.getCellType() == CellType.STRING) {
//                return cell.getStringCellValue();
//            } else {
//                return cell.getRawValue();  // Return the raw value if it's not a string
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";  // Return empty string if an error occurs
//        }
//    }
//
//    public static int getRowCount(String filePath, String sheetName) {
//        try (FileInputStream fi = new FileInputStream(filePath);
//             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
//            
//            return wb.getSheet(sheetName).getLastRowNum();  // Get the last row number
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//}
//
//
