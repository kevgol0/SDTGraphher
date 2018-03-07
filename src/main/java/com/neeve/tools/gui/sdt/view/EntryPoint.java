




package com.neeve.tools.gui.sdt.view;





import java.text.MessageFormat;



import javax.swing.SwingUtilities;



import com.neeve.ci.XRuntime;
import com.neeve.cli.annotations.Configured;
import com.neeve.root.RootConfig.ObjectConfig;
import com.neeve.trace.Tracer;
import com.neeve.trace.Tracer.Level;





/**
 *
 */
public class EntryPoint
{
	// ISSUES: having issue pulling this from config
	@Configured(property = "loglevel")
	private String _logLevel;

	//
	private Tracer _tracer;


	// main window
	private MainWindow _mainWindow;





	/**
	 *
	 */
	EntryPoint()
	{
		_tracer = ObjectConfig.createTracer(ObjectConfig.get("main"));
		_tracer.log(MessageFormat.format(": LogLevel:{0}", XRuntime.getValue("loglevel")), Level.INFO);
		_tracer.log(MessageFormat.format(": Full app name:{0}", XRuntime.getFullAppName()), Level.INFO);
		_tracer.log(MessageFormat.format(": Cfg dir:{0}", XRuntime.getConfigDirectory()), Level.INFO);

		if (_tracer.isEnabled(Level.FINER))
			_tracer.log("Startign main window", Level.FINER);
		MainWindow.getInstance();
	}





	private static void createAndShowGUI()
	{
		EntryPoint ep = new EntryPoint();
	}





	/**
	 * 
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
}
