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





public class DataSet<T extends Number>
{
	private List<T>	_data;
	private String	_seriesName;





	/**
	 *
	 */
	public DataSet(String name_)
	{
		_data = new ArrayList<T>();
		setSeriesName(name_);
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
	public final void setSeriesName(String seriesName_)
	{
		_seriesName = seriesName_;
	}

}
