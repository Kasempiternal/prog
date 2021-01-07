package interfaceUser;

import java.awt.Desktop;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportarExcel {

	public void export(JTable table) throws Exception {
		System.out.println("Exportando...");

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos excel", "xls");

		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Guardar archivo");
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String ruta = chooser.getSelectedFile().toString().concat(".xls");
			try {
				File archivoXLS = new File(ruta);
				if (archivoXLS.exists()) {
					archivoXLS.delete();
				}
				archivoXLS.createNewFile();

				// El workbook sera nuestro excel
				Workbook libro = new HSSFWorkbook();
				// El outputstream sera para saber donde colocaremos los datos
				FileOutputStream archivo = new FileOutputStream(archivoXLS);

				// Crea una hoja en nuestro excel
				Sheet hoja = libro.createSheet("Excel 1");
				hoja.setDisplayGridlines(false);

				/**
				 * El siguiente bucle lo que hara es primero leer y recorrer la tabla y segundo
				 * crea las celdas que necesita para por ultimo escribir en ellas en el ultimo
				 * bucle
				 */

				// Recorre y lee
				for (int f = 0; f < table.getRowCount(); f++) {

					Row fila = hoja.createRow(f);
					// Crea las celdas necesarias
					for (int c = 0; c < table.getColumnCount(); c++) {

						Cell celda = fila.createCell(c);

						if (f == 0) {
							celda.setCellValue(table.getColumnName(c));
						}
					}
				}
				int filaInicio = 1;
				// Escribe en las celdas
				for (int f = 0; f < table.getRowCount(); f++) {

					Row fila = hoja.createRow(filaInicio);
					filaInicio++;
					for (int c = 0; c < table.getColumnCount(); c++) {
						Cell celda = fila.createCell(c);
						/**
						 * va escribiendo dependiendo del tipo de variable Por eso hay dps else
						 */
						// Double
						if (table.getValueAt(f, c) instanceof Double) {

							celda.setCellValue(Double.parseDouble(table.getValueAt(f, c).toString()));
							// Float
						} else if (table.getValueAt(f, c) instanceof Float) {

							celda.setCellValue(Float.parseFloat((String) table.getValueAt(f, c)));
							// String
						} else {

							celda.setCellValue(String.valueOf(table.getValueAt(f, c)));
						}
					}
				}
				// Escribe en libro(excel)
				libro.write(archivo);
				// Cierra el archivo
				archivo.close();
				// Abre los directorios de donde quiere exportar el archivo
				Desktop.getDesktop().open(archivoXLS);
				System.out.println("Archivo '.xls' exportado correctamente");
			} catch (IOException | NumberFormatException e) {

				throw e;
			}
		}
	}

}
