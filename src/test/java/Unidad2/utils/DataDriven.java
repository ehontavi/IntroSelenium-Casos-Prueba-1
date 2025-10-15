package Unidad2.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	//public static void main(String[] args) throws IOException {
	public static ArrayList<String> getData(String nombreCasoDePrueba){
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		// Se instancia un objeto de tipo fileInputStream para especificar la ruta del
		// excel
        FileInputStream rutaArchivo = null;
        try {
            rutaArchivo = new FileInputStream(
                    "/Users/estebanhontavillaantich/IntroSelenium/src/test/resources/dataprueba/dataPrueba.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Se instancia un objeto de tipo libro excel con la ruta del archivo que sera
		// utilizado
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(rutaArchivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int sheets = excel.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			// System.out.println(excel.getSheetName(i));
			if (excel.getSheetName(i).equalsIgnoreCase("DataPruebas")) {
				// Encontre la hoja con la que quiero trabajar
				XSSFSheet hojaExcel = excel.getSheetAt(i);

				// iterador de filas de la hoja identificada
				Iterator<Row> filas = hojaExcel.iterator();

				// Se instancia un objeto fila con la primera fila de la hoja excel
				Row fila = filas.next();

				// Se instancia un iterador de celdas con la fila seleccionada
				Iterator<Cell> celda = fila.iterator();

				int k = 0;
				int columna = 0;
				while (celda.hasNext()) {
					Cell celdaSeleccionada = celda.next();

					if (celdaSeleccionada.getStringCellValue().equalsIgnoreCase("TituloCPs")) {
						// definir la columna identificada
						columna = k;
					}
					k++;
				}
				// System.out.println(columna);

				while (filas.hasNext()) {
					Row r = filas.next();

					if (r.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreCasoDePrueba)) {
						
						Iterator<Cell> cv = r.cellIterator();
						
						while(cv.hasNext()) {
							Cell c = cv.next();
							if(c.getCellType() == CellType.STRING)  {
								//System.out.println(c.getStringCellValue());
								a.add(c.getStringCellValue());
							}else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								//System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
							
							//
						}
					
						
					}
				}

			}
		}
		
		return a;

	}

}
