/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.a12hotelandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.a12hotelandes.negocio.A12HotelAndes;
import uniandes.isis2304.a12hotelandes.negocio.VOCliente;
import uniandes.isis2304.a12hotelandes.negocio.VOHotel;
import uniandes.isis2304.a12hotelandes.negocio.VOTipoHabitacion;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazA12HotelAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazA12HotelAndesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private A12HotelAndes a12HotelAndes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazA12HotelAndesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        a12HotelAndes = new A12HotelAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			CRUD de Hotel
	 *****************************************************************/
    /**
     * Adiciona un hotel con la información dada por el usuario
     * Se crea una nueva tupla de tipoBebida en la base de datos, si un hotel con ese nombre no existía
     */
    public void adicionarHotel( )
    {
    	try 
    	{
    		String nombreHotel = JOptionPane.showInputDialog (this, "Nombre del hotel?", "Adicionar hotel", JOptionPane.QUESTION_MESSAGE);
    		String ubicacion = JOptionPane.showInputDialog (this, "Ubicacion del hotel?", "Adicionar hotel", JOptionPane.QUESTION_MESSAGE);
    		
    		if (nombreHotel != null && ubicacion != null)
    		{
        		VOHotel tb = a12HotelAndes.adicionarHotel(nombreHotel, ubicacion);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un hotel con nombre: " + nombreHotel);
        		}
        		String resultado = "En adicionarHotel\n\n";
        		resultado += "Tipo de bebida adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


    /**
     * Borra de la base de datos el hotel con el identificador dado po el usuario
     * Cuando dicho hotel no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarHotelPorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del hotel?", "Borrar hotel por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = a12HotelAndes.eliminarHotelPorId (idTipo);

    			String resultado = "En eliminar Hotel\n\n";
    			resultado += tbEliminados + " Hotel eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void eliminarHotelPorNombre( )
    {
    	try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del hotel?", "Borrar hotel por Nombre", JOptionPane.QUESTION_MESSAGE);
    		if (nombre != null)
    		{
    			
    			long tbEliminados = a12HotelAndes.eliminarHotelPorNombre (nombre);

    			String resultado = "En eliminar Hotel\n\n";
    			resultado += tbEliminados + " Hotel eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void cambiarUbicacionHotel( )
    {
    	try 
    	{
    		String idHotel = JOptionPane.showInputDialog (this, "id del hotel?", "Actualizar ubicacion hotel por Id", JOptionPane.QUESTION_MESSAGE);
    		String nuevaUbicacion = JOptionPane.showInputDialog (this, "nueva ubicacion del hotel?", "Actualizar ubicacion hotel por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idHotel != null && nuevaUbicacion != null)
    		{
    			
    			long tbCambiados = a12HotelAndes.cambiarUbicacionHotel(Long.parseLong(idHotel), nuevaUbicacion);

    			String resultado = "En actualizar ubicacion Hotel\n\n";
    			resultado += tbCambiados + " Hotel actualizados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    
    /* ****************************************************************
	 * 			CRUD de Cliente
	 *****************************************************************/
    /**
     * Adiciona un cliente con la información dada por el usuario
     * Se crea una nueva tupla de tipoBebida en la base de datos, si un cliente con ese nombre no existía
     */
    public void adicionarCliente( )
    {
    	try 
    	{
    		String nombreCliente = JOptionPane.showInputDialog (this, "Nombre del cliente?", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String tipoDoc = JOptionPane.showInputDialog (this, "Tipo de documento del cliente?", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String numDoc = JOptionPane.showInputDialog (this, "Numero documento del cliente?", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String diaEntrada = JOptionPane.showInputDialog (this, "Día de entrada del cliente? (Formato dd)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String mesEntrada = JOptionPane.showInputDialog (this, "Mes de entrada del cliente? (Formato mm)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String anioEntrada = JOptionPane.showInputDialog (this, "Año de entrada del cliente? (Formato yyyy)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		
    		String entradaConca = anioEntrada+ "-" + mesEntrada+"-"+diaEntrada;
    		Date diaEntradaDate = Date.valueOf(entradaConca);
    		
    		String diaSalida = JOptionPane.showInputDialog (this, "Día de salida del cliente? (Formato dd)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String mesSalida = JOptionPane.showInputDialog (this, "Mes de salida del cliente? (Formato mm)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		String anioSalida = JOptionPane.showInputDialog (this, "Año de salida del cliente? (Formato yyyy)", "Adicionar cliente", JOptionPane.QUESTION_MESSAGE);
    		
    		String salidaConca = anioSalida+ "-" + mesSalida+"-"+diaSalida;
    		Date diaSalidaDate = Date.valueOf(salidaConca);
    		
    		Boolean valoresNoNulos = nombreCliente != null && numDoc != null && diaEntrada != null && diaSalida != null && tipoDoc != null;
    		
    		if (valoresNoNulos)
    		{
        		VOCliente tb = a12HotelAndes.adicionarCliente(nombreCliente, tipoDoc, Integer.parseInt(numDoc), diaEntradaDate, diaSalidaDate);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un cliente con nombre: " + nombreCliente);
        		}
        		String resultado = "En adicionarCliente\n\n";
        		resultado += "Tipo de bebida adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void eliminarClientePorNombre( )
    {
    	try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del cliente?", "Borrar cliente por Nombre", JOptionPane.QUESTION_MESSAGE);
    		if (nombre != null)
    		{
    			
    			long tbEliminados = a12HotelAndes.eliminarClientePorNombre (nombre);

    			String resultado = "En eliminar Cliente\n\n";
    			resultado += tbEliminados + " Cliente eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void eliminarClientePorNumDoc( )
    {
    	try 
    	{
    		String numDocStr = JOptionPane.showInputDialog (this, "Id del cliente?", "Borrar cliente por Id", JOptionPane.QUESTION_MESSAGE);
    		if (numDocStr != null)
    		{
    			Integer numDoc = Integer.parseInt(numDocStr);
    			long tbEliminados = a12HotelAndes.eliminarClientePorNumDoc(numDoc);

    			String resultado = "En eliminar Cliente\n\n";
    			resultado += tbEliminados + " Cliente eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void cambiarNombreCliente( )
    {
    	try 
    	{
    		String idCliente = JOptionPane.showInputDialog (this, "id del cliente?", "Actualizar ubicacion cliente por Id", JOptionPane.QUESTION_MESSAGE);
    		String nuevoNombre = JOptionPane.showInputDialog (this, "nuevo nombre del cliente?", "Actualizar ubicacion cliente por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idCliente != null && nuevoNombre != null)
    		{
    			
    			long tbCambiados = a12HotelAndes.cambiarNombreCliente(Long.parseLong(idCliente), nuevoNombre);

    			String resultado = "En actualizar ubicacion Cliente\n\n";
    			resultado += tbCambiados + " Cliente actualizados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    /* ****************************************************************
	 * 			CRUD de TipoHabitacion
	 *****************************************************************/
    /**
     * Adiciona un tipo habitacion con la información dada por el usuario
     * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo habitacion con ese nombre no existía
     */
    public void adicionarTipoHabitacion( )
    {
    	try 
    	{
    		String nombreTipoHabitacion = JOptionPane.showInputDialog (this, "Nombre del tipo habitacion?", "Adicionar tipo habitacion", JOptionPane.QUESTION_MESSAGE);
    		String descripcion= JOptionPane.showInputDialog (this, "Descripcion del tipo habitacion?", "Adicionar tipo habitacion", JOptionPane.QUESTION_MESSAGE);
    		
    		
    		if (nombreTipoHabitacion!= null && descripcion!= null)
    		{
        		VOTipoHabitacion tb = a12HotelAndes.adicionarTipoHabitacion(nombreTipoHabitacion, descripcion);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un tipo habitacion con nombre: " + nombreTipoHabitacion);
        		}
        		String resultado = "En adicionarTipoHabitacion\n\n";
        		resultado += "Tipo de bebida adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


    /**
     * Borra de la base de datos el tipo habitacion con el identificador dado po el usuario
     * Cuando dicho tipo habitacion no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarTipoHabitacionPorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del tipo habitacion?", "Borrar tipo habitacion por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = a12HotelAndes.eliminarTipoHabitacionPorId (idTipo);

    			String resultado = "En eliminar TipoHabitacion\n\n";
    			resultado += tbEliminados + " TipoHabitacion eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void eliminarTipoHabitacionPorNombre( )
    {
    	try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del tipo habitacion?", "Borrar tipo habitacion por Nombre", JOptionPane.QUESTION_MESSAGE);
    		if (nombre != null)
    		{
    			
    			long tbEliminados = a12HotelAndes.eliminarTipoHabitacionPorNombre (nombre);

    			String resultado = "En eliminar TipoHabitacion\n\n";
    			resultado += tbEliminados + " TipoHabitacion eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void cambiarDescripcionTipoHabitacion( )
    {
    	try 
    	{
    		String idTipoHabitacion = JOptionPane.showInputDialog (this, "id del tipo habitacion?", "Actualizar ubicacion tipo habitacion por Id", JOptionPane.QUESTION_MESSAGE);
    		String nuevoNombre = JOptionPane.showInputDialog (this, "nueva descripcion del tipo habitacion?", "Actualizar ubicacion tipo habitacion por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoHabitacion != null && nuevoNombre != null)
    		{
    			
    			long tbCambiados = a12HotelAndes.cambiarDescripcionTipoHabitacion(Long.parseLong(idTipoHabitacion), nuevoNombre);

    			String resultado = "En actualizar ubicacion TipoHabitacion\n\n";
    			resultado += tbCambiados + " TipoHabitacion actualizados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de A12HotelAndes
	 */
	public void mostrarLogA12HotelAndes ()
	{
		mostrarArchivo ("a12HotelAndes.log");
	}

	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de a12HotelAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("a12HotelAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de a12HotelAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de a12HotelAndes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = a12HotelAndes.limpiarA12HotelAndes(); 
			
			// TODO MANDAR BIEN LOS BORRADOS
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Hotel eliminados\n";
			resultado += eliminados [1] + " Cliente eliminados\n";
			
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta aplicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Juan Yepes, Sergio Pardo\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
//    /**
//     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada hotel
//     * @param lista - La lista con los tipos de bebida
//     * @return La cadena con una líea para cada hotel recibido
//     */
//    private String listarTiposBebida(List<VOHotel> lista) 
//    {
//    	String resp = "Los tipos de bebida existentes son:\n";
//    	int i = 1;
//        for (VOHotel tb : lista)
//        {
//        	resp += i++ + ". " + tb.toString() + "\n";
//        }
//        return resp;
//	}

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y a12HotelAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazA12HotelAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazA12HotelAndesApp interfaz = new InterfazA12HotelAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
