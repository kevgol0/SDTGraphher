/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: FileChooserPanel.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view.graphpanel;





import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;



import javax.swing.JPanel;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



import com.neeve.tools.gui.sdt.data.DataSet;
import com.neeve.tools.gui.sdt.view.ColorPallette;
import com.neeve.tools.gui.sdt.view.SDTPanel;





public class GraphPanel extends SDTPanel implements ActionListener, MouseListener
{

	private static final long serialVersionUID = 1L;


	private GraphControls	_controls;
	private JPanel			_theGraph;




	public static class GraphPanelHelper
	{
		private static final GraphPanel _instance = new GraphPanel();
	}





	/**
	 *
	 */
	private GraphPanel()
	{
		super();
		setBackground(ColorPallette.SlateGrey);
		_controls = new GraphControls(100);
		_theGraph = new JPanel();
		add(_controls);
		add(_theGraph);
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	public void setBounds(int x_, int y_, int w_, int h_)
	{
		super.setBounds(x_, y_, w_, h_);
		int cH = (int) Math.round(h_ * 0.20);
		_controls.setBounds(5, h_ - cH - 5, w_ - 10, cH);
		_theGraph.setBounds(5, 5, w_ - 10, h_ - cH - 10);
	}





	/**
	 * 
	 */
	public static GraphPanel getInstance()
	{
		return GraphPanelHelper._instance;
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e_)
	{
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e_)
	{
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e_)
	{
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e_)
	{
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e_)
	{
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e_)
	{
	}





	/**
	 * 
	 */
	public void update1(DataSet<Integer> series1_)
	{
		if (series1_ == null)
			return;
		//create the series - add some dummy data
		XYSeries series1 = new XYSeries(series1_.getSeriesName());
		int cntr = 0;
		int i;
		Iterator<Integer> itr = series1_.iterator();
		while (itr.hasNext())
		{
			i = itr.next();
			series1.add(cntr++, i);
		}


		//create the datasets
		XYSeriesCollection dataset1 = new XYSeriesCollection();
		dataset1.addSeries(series1);

		//construct the plot
		XYPlot plot = new XYPlot();
		plot.setDataset(0, dataset1);

		//customize the plot with renderers and axis
		plot.setRenderer(0, new XYSplineRenderer());//use default fill paint for first series
		XYSplineRenderer splinerenderer = new XYSplineRenderer();
		splinerenderer.setSeriesFillPaint(0, Color.BLUE);
		plot.setRenderer(1, splinerenderer);
		plot.setRangeAxis(0, new NumberAxis("Series 1"));
		plot.setRangeAxis(1, new NumberAxis("Series 2"));
		plot.setDomainAxis(new NumberAxis("X Axis"));
		//plot.setBackgroundPaint(Paint.OPAQUE);

		//Map the data to the appropriate axis
		plot.mapDatasetToRangeAxis(0, 0);
		plot.mapDatasetToRangeAxis(1, 1);

		//generate the chart
		JFreeChart chart = new JFreeChart("MyPlot", getFont(), plot, true);
		//chart.setBackgroundPaint(ColorPallette.DarkGrey);
		JPanel chartPanel = new ChartPanel(chart);
		//chartPanel.setBackground(ColorPallette.DarkGrey);

		remove(_theGraph);
		_theGraph = new ChartPanel(chart);
		_theGraph = chartPanel;
		add(_theGraph);
		this.setPreferredSize(this.getPreferredSize());
	}





	public void update(DataSet<Integer> series1_)
	{
		if (series1_ == null)
			return;

		XYSeries series1 = new XYSeries(series1_.getSeriesName());
		int cntr = 0;
		int i;
		Iterator<Integer> itr = series1_.iterator();
		while (itr.hasNext())
		{
			i = itr.next();
			series1.add(cntr++, i);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		JFreeChart chart = ChartFactory.createScatterPlot(
				"Boys VS Girls weight comparison chart",
				"X-Axis", "Y-Axis", dataset);
		remove(_theGraph);
		_theGraph = new ChartPanel(chart);
		add(_theGraph);
	}
}
