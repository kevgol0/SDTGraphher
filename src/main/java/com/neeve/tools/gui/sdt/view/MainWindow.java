/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *xpxu
 * FILE: MainWindow.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view;





import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.MessageFormat;



import javax.swing.JFrame;



import com.neeve.cli.annotations.Configured;
import com.neeve.root.RootConfig.ObjectConfig;
import com.neeve.tools.gui.sdt.data.DataManager;
import com.neeve.tools.gui.sdt.view.graphpanel.GraphPanel;
import com.neeve.tools.gui.sdt.view.metricspanel.MeticsPanel;
import com.neeve.trace.Tracer;
import com.neeve.trace.Tracer.Level;





public class MainWindow extends JFrame
{

	private static final long	serialVersionUID	= 1L;
	private static final int	FROM_BOTTOM			= 20;
	private static final int	FROM_RIGHT			= 0;
	private static final int	MIN_WIDTH			= 800;
	private static final int	MIN_HEIGHT			= 600;

	private int LEFT_PANNEL_WIDTH = 250;


	//
	private Tracer _logger;

	@Configured(property = "main_starting_width", defaultValue = "800")
	private int _width = MIN_WIDTH;

	@Configured(property = "main_starting_height", defaultValue = "600")
	private int _height = MIN_HEIGHT;



	// 
	private SDTPanel	_panel;
	private SDTPanel	_fileChooser;
	private SDTPanel	_metricsChooser;
	private SDTPanel	_grapher;



	/**
	 *
	 */
	private static class MainWindowHelper
	{
		public static MainWindow _instance = new MainWindow();
	}





	/**
	 * 
	 */
	public static MainWindow getInstance()
	{
		return MainWindowHelper._instance;
	}





	/**
	 *
	 */
	private MainWindow()
	{
		_logger = ObjectConfig.createTracer(ObjectConfig.get(getClass().getSimpleName()));


		//
		if (_logger.isEnabled(Level.FINEST))
			_logger.log("MainWindow::MainWindow", Level.FINEST);



		// initialize and populate
		initComponents();


		// my personal defaults
		setTitle(null);
		setSize(_width, _height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));


		getRootPane().addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				int h = getHeight();
				int w = getWidth();
				_panel.setSize(w - FROM_RIGHT, h - FROM_BOTTOM);
				_metricsChooser.setBounds(5, 90, LEFT_PANNEL_WIDTH, h - 120);
				_grapher.setBounds(LEFT_PANNEL_WIDTH + 10, 5, w - (LEFT_PANNEL_WIDTH + 20), h - 35);
			}
		});
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Frame#setTitle(java.lang.String)
	 */
	public void setTitle(String title_)
	{
		final String root = "SDTGui";
		if (title_ == null || title_.isEmpty())
		{
			super.setTitle(root);
		}
		else
		{
			super.setTitle(MessageFormat.format("{0} - {1}", root, title_));
		}

	}





	/**
	 * 
	 */
	private void initComponents()
	{
		Container contentPane = getContentPane();
		setLayout(null);

		_fileChooser = new FileChooserPanel(LEFT_PANNEL_WIDTH, 100);
		_metricsChooser = new MeticsPanel(LEFT_PANNEL_WIDTH, 280);
		_grapher = GraphPanel.getInstance();
		_panel = new SDTPanel();

		_panel.setLayout(null);
		_panel.setBounds(0, 0, _width, _height);
		_fileChooser.setBounds(5, 5, LEFT_PANNEL_WIDTH, 75);

		_panel.add(_fileChooser);
		_panel.add(_metricsChooser);
		_panel.add(_grapher);

		contentPane.add(_panel);
		//pack();
	}



}
