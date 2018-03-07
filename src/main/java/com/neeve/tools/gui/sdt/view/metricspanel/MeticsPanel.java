/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: MeticsPanel.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view.metricspanel;





import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



import com.neeve.tools.gui.sdt.data.DataManager;
import com.neeve.tools.gui.sdt.view.ColorPallette;
import com.neeve.tools.gui.sdt.view.MetricEntry;
import com.neeve.tools.gui.sdt.view.SDTPanel;





public class MeticsPanel extends SDTPanel
{

	private static final long serialVersionUID = 1L;


	private JScrollPane	_scrollP;
	private JTextField	_filterTxt;
	private JTable		_table;
	private MetricsTM	_tableModel;
	private JScrollPane	_sp;

	private int	_width;
	private int	_height;





	/**
	 *
	 */
	public MeticsPanel(int width_, int height_)
	{
		super();
		_width = width_ - 1;
		_height = height_;

		_filterTxt = new JTextField();
		int ph = _filterTxt.getPreferredSize().height;
		_filterTxt.setBounds(1, 1, _width, ph);

		_table = new JTable();
		_tableModel = new MetricsTM();
		_sp = new JScrollPane();
		_table.setModel(_tableModel);
		_sp.add(_table);
		_sp.setViewportView(_table);
		_sp.getViewport().setBackground(ColorPallette.DarkBlue);

		int h = _height - 2 - ph;
		_sp.setBounds(1, 1 + ph, _width, h);
		_table.setShowGrid(true);
		_table.setGridColor(Color.BLACK);
		_table.setVisible(true);
		_table.addKeyListener(this);
		_table.addMouseListener(this);
		_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		_table.setBackground(ColorPallette.DarkBlue);
		_table.setForeground(ColorPallette.White);


		add(_filterTxt);
		add(_sp);
		_filterTxt.addKeyListener(this);
		_sp.addMouseListener(this);

	}





	@Override
	public void setBounds(int x_, int y_, int w_, int h_)
	{
		int h = h_ - 2 - _filterTxt.getPreferredSize().height;
		super.setBounds(x_, y_, w_, h_);
		_sp.setBounds(1, 1 + _filterTxt.getPreferredSize().height, _width, h);
		_table.setBounds(1, 1 + _filterTxt.getPreferredSize().height, _width, h);
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e_)
	{
		int row = _table.getSelectedRow();
		MetricEntry me = _tableModel.getElementForRow(row);
		if (e_.getClickCount() == 2)
		{
			updateME(me);
		}

	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e_)
	{
		switch (e_.getKeyChar())
		{
		case KeyEvent.VK_ESCAPE:
			_tableModel.setFilter(null);
			_filterTxt.setText("");
			_filterTxt.requestFocus();
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			int row = _table.getSelectedRow();
			MetricEntry me = _tableModel.getElementForRow(row);
			updateME(me);
		default:
		}
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e_)
	{
		if (e_.getSource() instanceof JTextField)
		{
			if (e_.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				_tableModel.setFilter(null);
				_filterTxt.setText("");
			}
			if (e_.getKeyCode() == KeyEvent.VK_DOWN)
			{
				_table.requestFocus();
			}
			else
			{
				_tableModel.setFilter(_filterTxt.getText());
			}

		}
		else if (e_.getSource() instanceof JTable)
		{
			int row = _table.getSelectedRow();
			MetricEntry me = _tableModel.getElementForRow(row);
		}
	}





	private void updateME(MetricEntry entry_)
	{
		if (entry_ == null)
			return;
		boolean displayed = !entry_.getDisaplayed();
		entry_.setDisaplayed(displayed);
		DataManager.getInstance().update(entry_.getName(), displayed);
		_tableModel.fireTableDataChanged();
	}
}
