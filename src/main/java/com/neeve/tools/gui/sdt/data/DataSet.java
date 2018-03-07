/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: DataSet.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.data;





import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



import com.neeve.tools.gui.sdt.view.graphpanel.VERTICAL_ACCESS;





public class DataSet<T extends Number>
{

	private List<T>				_data;
	private String				_seriesName;
	private XYSeries			_xySeries;
	private XYSeriesCollection	_xyDataset;
	private Boolean				_displayed;

	private int _axis;





	/**
	 *
	 */
	public DataSet(String name_)
	{
		_data = new ArrayList<T>();
		setSeriesName(name_);
		_xySeries = new XYSeries(name_);
		setAxis(1);
	}





	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size()
	{
		return _data.size();
	}





	/**
	 * @return
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty()
	{
		return _data.isEmpty();
	}





	/**
	 * @return
	 * @see java.util.List#iterator()
	 */
	public Iterator<T> iterator()
	{
		return _data.iterator();
	}





	/**
	 * @param e_
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(T e_)
	{
		return _data.add(e_);
	}





	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear()
	{
		_data.clear();
	}





	/**
	 * @param index_
	 * @return
	 * @see java.util.List#get(int)
	 */
	public T get(int index_)
	{
		return _data.get(index_);
	}





	/**
	 * 
	 */
	public final String getSeriesName()
	{
		return _seriesName;
	}





	/**
	 * 
	 */
	public final int getAxis()
	{
		return _axis;
	}





	/**
	 * 
	 */
	public final void setAxis(int axis_)
	{
		_axis = axis_;
	}





	/**
	 * 
	 */
	public final Boolean getDisplayed()
	{
		return _displayed;
	}





	/**
	 * 
	 */
	public final void setDisplayed(Boolean displayed_)
	{
		_displayed = displayed_;
	}





	/**
	 * 
	 */
	public final void setSeriesName(String seriesName_)
	{
		_seriesName = seriesName_;
	}





	public final XYSeries getDataSeries()
	{
		return getDataSeries(0, size());
	}





	public final XYSeries getDataSeries(int min_, int max_)
	{
		_xySeries.clear();
		int cntr = 0;
		for (int i = min_; i < max_; i++)
			_xySeries.add(cntr++, get(i));
		return _xySeries;
	}





	public final XYSeriesCollection getXYSeriesCollection()
	{
		return getXYSeriesCollection(0, size());
	}





	public final XYSeriesCollection getXYSeriesCollection(int max_, int min_)
	{
		_xyDataset = new XYSeriesCollection();
		_xyDataset.addSeries(getDataSeries(max_, min_));
		return _xyDataset;
	}


}
