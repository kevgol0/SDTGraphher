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



import com.neeve.server.mon.SrvMonIntHistogram;





public class DatasetArrays
{

	private List<Integer>		_data50;
	private List<Integer>		_data75;
	private List<Integer>		_data90;
	private List<Integer>		_data99;
	private List<Integer>		_data999;
	private List<Integer>		_data9999;
	private List<Integer>		_data99999;
	private String				_seriesName;
	private XYSeries			_xySeries;
	private XYSeriesCollection	_xyDataset;
	private Boolean				_displayed;

	private int _axis;





	/**
	 *
	 */
	public DatasetArrays(String name_)
	{
		setSeriesName(name_);
		_data50 = new ArrayList<Integer>();
		_data75 = new ArrayList<Integer>();
		_data90 = new ArrayList<Integer>();
		_data99 = new ArrayList<Integer>();
		_data999 = new ArrayList<Integer>();
		_data9999 = new ArrayList<Integer>();
		_data99999 = new ArrayList<Integer>();
		_xySeries = new XYSeries(name_);
		setAxis(1);
	}





	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size()
	{
		return _data50.size();
	}





	/**
	 * @return
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty(PERCENTILE pct_)
	{
		switch (pct_)
		{
		case pct50:
			return _data50.isEmpty();
		case pct75:
			return _data75.isEmpty();
		case pct90:
			return _data90.isEmpty();
		case pct99:
			return _data99.isEmpty();
		case pct999:
			return _data999.isEmpty();
		case pct9999:
			return _data9999.isEmpty();
		case pct99999:
			return _data99999.isEmpty();
		default:
			throw new IllegalArgumentException("Bad percintile");
		}
	}





	/**
	 * @return
	 * @see java.util.List#iterator()
	 */
	public Iterator<Integer> iterator(PERCENTILE pct_)
	{
		switch (pct_)
		{
		case pct50:
			return _data50.iterator();
		case pct75:
			return _data75.iterator();
		case pct90:
			return _data90.iterator();
		case pct99:
			return _data99.iterator();
		case pct999:
			return _data999.iterator();
		case pct9999:
			return _data9999.iterator();
		case pct99999:
			return _data99999.iterator();
		default:
			throw new IllegalArgumentException("Bad percintile");
		}
	}





	/**
	 * @param e_
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(PERCENTILE pct_, Integer e_)
	{
		switch (pct_)
		{
		case pct50:
			return _data50.add(e_);
		case pct75:
			return _data75.add(e_);
		case pct90:
			return _data90.add(e_);
		case pct99:
			return _data99.add(e_);
		case pct999:
			return _data999.add(e_);
		case pct9999:
			return _data9999.add(e_);
		case pct99999:
			return _data99999.add(e_);
		default:
			throw new IllegalArgumentException("Bad percintile");
		}
	}





	public boolean add(SrvMonIntHistogram histogram_)
	{
		if (histogram_ == null)
			return false;

		boolean rv = _data50.add(histogram_.getPct75());
		rv = rv && _data75.add(histogram_.getPct75());
		rv = rv && _data90.add(histogram_.getPct90());
		rv = rv && _data99.add(histogram_.getPct99());
		rv = rv && _data999.add(histogram_.getPct999());
		rv = rv && _data9999.add(histogram_.getPct9999());
		rv = rv && _data99999.add(histogram_.getPct9999());
		return rv;
	}





	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear()
	{
		_data50.clear();
		_data75.clear();
		_data90.clear();
		_data99.clear();
		_data999.clear();
		_data9999.clear();
		_data99999.clear();
	}





	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear(PERCENTILE pct_)
	{
		switch (pct_)
		{
		case pct50:
			_data50.clear();
			break;
		case pct75:
			_data75.clear();
			break;
		case pct90:
			_data90.clear();
			break;
		case pct99:
			_data99.clear();
			break;
		case pct999:
			_data999.clear();
			break;
		case pct9999:
			_data9999.clear();
			break;
		case pct99999:
			_data99999.clear();
			break;
		default:
			throw new IllegalArgumentException("Bad percintile");
		}
		return;
	}





	/**
	 * @param index_
	 * @return
	 * @see java.util.List#get(int)
	 */
	public Integer get(PERCENTILE pct_, int index_)
	{
		switch (pct_)
		{
		case pct50:
			return _data50.get(index_);
		case pct75:
			return _data75.get(index_);
		case pct90:
			return _data90.get(index_);
		case pct99:
			return _data99.get(index_);
		case pct999:
			return _data999.get(index_);
		case pct9999:
			return _data9999.get(index_);
		case pct99999:
			return _data99999.get(index_);
		default:
		}
		throw new IllegalArgumentException("Bad percintile");
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





	public final XYSeries getDataSeries(PERCENTILE pct_)
	{
		return getDataSeries(pct_, 0, size());
	}





	public final XYSeries getDataSeries(PERCENTILE pct_, int min_, int max_)
	{
		_xySeries.clear();
		int cntr = 0;
		for (int i = min_; i < max_; i++)
			_xySeries.add(cntr++, get(pct_, i));
		return _xySeries;
	}





	public final XYSeriesCollection getXYSeriesCollection(PERCENTILE pct_)
	{
		return getXYSeriesCollection(pct_, 0, size());
	}





	public final XYSeriesCollection getXYSeriesCollection(PERCENTILE pct_, int max_, int min_)
	{
		_xyDataset = new XYSeriesCollection();
		_xyDataset.addSeries(getDataSeries(pct_, max_, min_));
		return _xyDataset;
	}


}
