/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: MetricsTM.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view.metricspanel;





import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



import javax.swing.table.AbstractTableModel;



import com.neeve.tools.gui.sdt.data.DataManager;
import com.neeve.tools.gui.sdt.view.MetricEntry;




class TempMetricHolder
{
	private int			_index;
	private MetricEntry	_entry;





	public TempMetricHolder(int i_, MetricEntry entry_)
	{
		setIndex(i_);
		setEntry(entry_);
	}





	/**
	 * 
	 */
	public final int getIndex()
	{
		return _index;
	}





	/**
	 * 
	 */
	public final void setIndex(int index_)
	{
		_index = index_;
	}





	/**
	 * 
	 */
	public final MetricEntry getEntry()
	{
		return _entry;
	}





	/**
	 * 
	 */
	public final void setEntry(MetricEntry entry_)
	{
		_entry = entry_;
	}
}





public class MetricsTM extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;

	private List<MetricEntry>	_data;
	private List<MetricEntry>	_filteredData;
	private String				_filter;

	private TempMetricHolder _tmpMetric;





	public MetricsTM()
	{
		_data = new LinkedList<MetricEntry>();
		_filteredData = new LinkedList<MetricEntry>();
		_filter = "";
		_tmpMetric = new TempMetricHolder(-1, null);


		Iterator<String> itr = DataManager.getInstance().getKeysets();
		while (itr.hasNext())
		{
			_data.add(new MetricEntry(itr.next()));
		}
		setFilter(null);
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount()
	{
		return _filteredData.size();
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount()
	{
		return 4;
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex_, int columnIndex_)
	{
		if (rowIndex_ >= _filteredData.size())
			return null;

		MetricEntry me = null;
		if (rowIndex_ == _tmpMetric.getIndex())
		{
			me = _tmpMetric.getEntry();
		}
		else
		{
			me = _filteredData.get(rowIndex_);
			_tmpMetric.setIndex(rowIndex_);
			_tmpMetric.setEntry(me);
		}

		switch (columnIndex_)
		{
		case 0:
			return me.getDisaplayed();
		case 1:
			return me.getName();
		case 2:
			return Integer.toString(me.getIntervalDataPointCount());
		case 3:
			return Integer.toString(me.getRunningDataPoitnCount());

		default:
			return "";
		}
	}





	/*@Override
	public Class getColumnClass(int column) {
	return getValueAt(0, column).getClass();
	}*/
	@Override
	public Class getColumnClass(int column)
	{
		switch (column)
		{
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		default:
			return String.class;
		}
	}





	/**
	 * 
	 */
	public void setFilter(String filter_)
	{
		// is it a clear event?
		_filteredData.clear();
		if (filter_ == null)
		{
			_filteredData.addAll(_data);
			Collections.sort(_filteredData);
			_tmpMetric.setIndex(-1);
		}
		else
		{
			// nope filter all the stuff
			_filter = filter_.toLowerCase();
			for (MetricEntry m : _data)
			{
				if (m.getName().toLowerCase().contains(_filter))
					_filteredData.add(m);
			}
		}
		fireTableDataChanged();
	}





	/**
	 * 
	 */
	public String getFilter()
	{
		return _filter;
	}





	/**
	 * 
	 */
	public MetricEntry getElementForRow(int row_)
	{
		if (row_ < 0 || row_ >= _filteredData.size())
			return null;
		return _filteredData.get(row_);
	}

}
