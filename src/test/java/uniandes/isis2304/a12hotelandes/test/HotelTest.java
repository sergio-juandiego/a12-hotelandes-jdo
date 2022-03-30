
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: A12HotelAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.a12hotelandes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.a12hotelandes.negocio.A12HotelAndes;
import uniandes.isis2304.a12hotelandes.negocio.VOHotel;

/**
 * Clase con los métdos de prueba de funcionalidad sobre HTA_HOTEL
 * @author Germán Bravo
 *
 */
public class HotelTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(HotelTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private A12HotelAndes a12HotelAndes;
	
    /* ****************************************************************
	 * 			Métodos de prueba para la tabla Hotel - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla Hotel
	 * 1. Adicionar un hotel
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un hotel por su identificador
	 * 4. Borrar un hotel por su nombre
	 */
    @Test
	public void CRDHotelTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Hotel");
			a12HotelAndes = new A12HotelAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Hotel incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Hotel incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de a12HotelAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los hoteles con la tabla vacía
			List <VOHotel> lista = a12HotelAndes.darVOHoteles();
			assertEquals ("No debe haber hoteles creados!!", 0, lista.size ());

			// Lectura de los hoteles con un hotel adicionado
			String nombreHotel1 = "Marriott";
			String ubicacionHotel1 = "Bogota";
			
			
			VOHotel Hotel1 = a12HotelAndes.adicionarHotel(nombreHotel1, ubicacionHotel1);
			lista = a12HotelAndes.darVOHoteles();
			assertEquals ("Debe haber un hotel creado !!", 1, lista.size ());
			//assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Hotel1, lista.get (0));

			// Lectura de los hoteles con dos hoteles adicionados
			String nombreHotel2 = "Hilton";
			String ubicacionHotel2 = "Medellín";
			
			
			VOHotel Hotel2 = a12HotelAndes.adicionarHotel (nombreHotel2, ubicacionHotel2);
			lista = a12HotelAndes.darVOHoteles();
			assertEquals ("Debe haber un hotel creado !!", 2, lista.size ());
			//assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Hotel1, lista.get (0));
			
			//assertTrue ("El primer hotel adicionado debe estar en la tabla", lista.contains(Hotel1));
			//assertTrue ("El segundo hotel adicionado debe estar en la tabla", Hotel2.equals (lista.get (0)) || Hotel2.equals (lista.get (1)));

			// Prueba de eliminación de un hotel, dado su identificador
			long tbEliminados = a12HotelAndes.eliminarHotelPorId (Hotel1.getId ());
			assertEquals ("Debe haberse eliminado un hotel !!", 1, tbEliminados);
			lista = a12HotelAndes.darVOHoteles();
			assertEquals ("Debe haber un solo hotel !!", 1, lista.size ());
			//assertFalse ("El primer hotel adicionado NO debe estar en la tabla", Hotel1.equals (lista.get (0)));
			//assertTrue ("El segundo hotel adicionado debe estar en la tabla", Hotel2.equals (lista.get (0)));
			
			// Prueba de eliminación de un hotel, dado su identificador
			tbEliminados = a12HotelAndes.eliminarHotelPorNombre (nombreHotel2);
			assertEquals ("Debe haberse eliminado un hotel !!", 1, tbEliminados);
			lista = a12HotelAndes.darVOHoteles();
			assertEquals ("La tabla debió quedar vacía !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Hotel.\n";
			msg += "Revise el log de a12HotelAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Hotel");
		}
		finally
		{
			a12HotelAndes.limpiarA12HotelAndes ();
    		a12HotelAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Hotel
     */
	@Test
	public void unicidadHotelTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del hotel");
			a12HotelAndes = new A12HotelAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Hotel incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Hotel incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de a12HotelAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los hoteles con la tabla vacía
			List <VOHotel> lista = a12HotelAndes.darVOHoteles();
			assertEquals ("No debe haber hoteles creados!!", 0, lista.size ());

			// Lectura de los hoteles con un hotel adicionado
			String nombreHotel1 = "Marriott";
			String ubicacionHotel1 = "Bogota";
			VOHotel Hotel1 = a12HotelAndes.adicionarHotel(nombreHotel1, ubicacionHotel1);
			lista = a12HotelAndes.darVOHoteles();
			assertEquals ("Debe haber un hotel creado !!", 1, lista.size ());

		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Hotel.\n";
			msg += "Revise el log de a12HotelAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Hotel");
		}    				
		finally
		{
			a12HotelAndes.limpiarA12HotelAndes ();
    		a12HotelAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "HotelTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
