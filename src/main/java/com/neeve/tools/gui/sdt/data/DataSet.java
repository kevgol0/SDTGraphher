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





import java.util.Iterator;
import java.util.ListIterator;





public abstract class DataSet<T>
{
	private String _name;





	/**
	 *
	 */
	public DataSet(String name_)
	{
		setName(name_);
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#size()
	 */
	public abstract int size();





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	public abstract boolean isEmpty();





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	public abstract Iterator<T> iterator(PERCENTILE pct_);





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	public abstract boolean add(PERCENTILE pct_, T e_);





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	public abstract void clear();





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	public abstract Integer get(PERCENTILE pct_, int index_);





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	public abstract T remove(PERCENTILE pct_, int index_);





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	public abstract ListIterator<T> listIterator(PERCENTILE pct_);





	/**
	 * 
	 */
	public final String getName()
	{
		return _name;
	}





	/**
	 * 
	 */
	public final void setName(String name_)
	{
		_name = name_;
	}

}
