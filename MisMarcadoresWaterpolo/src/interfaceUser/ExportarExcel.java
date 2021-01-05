package interfaceUser;

import java.io.*;
import javax.swing.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class ExportarExcel {
	
	
	private File file;
	private String archivo;
	private JTable table;



	public ExportarExcel(JTable table, File file, String archivo) {
		// TODO Auto-generated constructor stub
			this.file=file;
			this.table=table;
			this.archivo=archivo;
	}



	public boolean export(){
		
		try{
			
			System.out.print("Exportando..."); 
			
			//Creamos el archivo deseado
			DataOutputStream out=new DataOutputStream(new FileOutputStream(file));
			
			/**
			 * El writable workbook sera la representacion del excel
			 * El OutputStream es para saber donde va colocar los datos
			 */
			WritableWorkbook ww = Workbook.createWorkbook(out);
			
			//Coloca el nombre del archivo en el archivo y tambien en la hoja de excel)
			WritableSheet ws = ww.createSheet(archivo, 0);
			
			/**
			 * Hacemos dos 'for' para en uno recorrer la tabla 
			 * y en otro escribir en el excel y sus celdas
			 */
			System.out.println("Leyendo...");
			System.out.print("Escribiendo..."); 
			 
			//Recorre
			for(int i=0;i< table.getRowCount();i++){
				//Escribe
				for(int j=0;j<table.getColumnCount();j++){
					Object objeto=table.getValueAt(i,j);
					ws.addCell(new Label(j, i, String.valueOf(objeto)));
				}
			}
			
			//Con el write escribrimos en el archivo
			ww.write();
			System.out.print("Cerramos el WritableWorkbook y DataOutputStream"); 
			//Con los close crreamos los archivos
			ww.close();
			out.close();
			return true;
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(WriteException ex1){
			ex1.printStackTrace();
		}
		System.out.print("Error al exportar el archivo");
		return false;
	}



	public void impresion(){
		String Ruta = System.getProperty("user.dir") + "\\";
		 file = new File(Ruta);
		ExportarExcel excel = new ExportarExcel(table,file,"tablaimporte");
		        excel.export();
	 }
}
	    	 
	       
	
	

