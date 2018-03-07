/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: AvailableMetricsListModel.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view;





import java.util.LinkedList;
import java.util.List;



import javax.swing.AbstractListModel;





public class AvailableMetricsListModel extends AbstractListModel<MetricEntry>
{

	private static final long serialVersionUID = 1L;

	private List<MetricEntry> _data;





	/**
	 *
	 */
	AvailableMetricsListModel()
	{
		_data = new LinkedList<MetricEntry>();
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize()
	{
		return _data.size();
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public MetricEntry getElementAt(int index_)
	{
		return _data.get(index_);
	}

}
