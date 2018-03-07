/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: MetricEntry.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view;





import java.text.MessageFormat;





public class MetricEntry implements Comparable<MetricEntry>
{
	private String	_name;
	private Boolean	_disaplayed;
	private int		_intervalDataPointCount;
	private int		_runningDataPoitnCount;





	/**
	 *
	 */
	public MetricEntry(String name_)
	{
		setName(name_);
		setDisaplayed(false);
	}





	/**
	 * 
	 */
	public String getName()
	{
		if (_name == null)
			return "";
		return _name;
	}





	/**
	 * 
	 */
	public void setName(String name_)
	{
		_name = name_;
	}





	/**
	 * 
	 */
	public Boolean getDisaplayed()
	{
		return _disaplayed;
	}





	/**
	 * 
	 */
	public void setDisaplayed(Boolean disaplayed_)
	{
		_disaplayed = disaplayed_;
	}





	/**
	 * 
	 */
	public int getIntervalDataPointCount()
	{
		return _intervalDataPointCount;
	}





	/**
	 * 
	 */
	public void setIntervalDataPointCount(int intervalDataPointCount_)
	{
		_intervalDataPointCount = intervalDataPointCount_;
	}





	/**
	 * 
	 */
	public int getRunningDataPoitnCount()
	{
		return _runningDataPoitnCount;
	}





	/**
	 * 
	 */
	public void setRunningDataPoitnCount(int runningDataPoitnCount_)
	{
		_runningDataPoitnCount = runningDataPoitnCount_;
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return MessageFormat.format("{0} [iCnt:{1}, rCnt:{2}]", _name, _intervalDataPointCount, _runningDataPoitnCount);
	}





	/** 
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MetricEntry o_)
	{
		return getName().compareTo(o_.getName());
	}





}
