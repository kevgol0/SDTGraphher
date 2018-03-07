/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: GraphControls.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view.graphpanel;





import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Dictionary;
import java.util.Enumeration;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import com.neeve.tools.gui.sdt.view.ColorPallette;
import com.neeve.tools.gui.sdt.view.SDTPanel;





public class GraphControls extends SDTPanel implements ChangeListener
{

	private static final long serialVersionUID = 1L;



	private JButton		_btnChartTitle;
	private JSlider		_sldrRight;
	private JSlider		_sldrLeft;
	private JTextField	_sldrValLeft;
	private JTextField	_sldrValRight;





	public GraphControls(int width_)
	{
		super();
		TitledBorder border = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(ColorPallette.White),
				"Chart Controls");
		border.setTitleColor(ColorPallette.White);
		setBorder(border);

		_btnChartTitle = new JButton("Chart Title");
		_sldrLeft = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
		_sldrRight = new JSlider(JSlider.HORIZONTAL, 0, 1000, 1000);
		_sldrValLeft = new JTextField(3);
		_sldrValRight = new JTextField(3);

		_btnChartTitle.addActionListener(this);
		_sldrLeft.addChangeListener(this);
		_sldrRight.addChangeListener(this);
		_sldrValLeft.addKeyListener(this);
		_sldrValRight.addKeyListener(this);

		_sldrRight.setPaintTicks(true);
		_sldrRight.setForeground(ColorPallette.White);
		_sldrRight.setBackground(ColorPallette.White);
		_sldrRight.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		_sldrRight.setMinorTickSpacing(25);
		_sldrRight.setMajorTickSpacing(100);

		_sldrLeft.setPaintTicks(true);
		_sldrLeft.setForeground(ColorPallette.White);
		_sldrLeft.setBackground(ColorPallette.White);
		_sldrLeft.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		_sldrLeft.setMinorTickSpacing(25);
		_sldrLeft.setMajorTickSpacing(100);

		add(_sldrLeft);
		add(_sldrRight);
		add(_sldrValLeft);
		add(_sldrValRight);
		add(_btnChartTitle);

	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	public void setBounds(int x_, int y_, int w_, int h_)
	{
		super.setBounds(x_, y_, w_, h_);
		int txtW = _sldrValLeft.getPreferredSize().width;
		int txtH = _sldrValLeft.getPreferredSize().height;

		_sldrValLeft.setBounds(5, 20, txtW, txtH);
		_sldrValRight.setBounds(w_ - txtW - 10, 20, txtW, txtH);

		_sldrLeft.setBounds(5 + txtW, 20, (w_ / 2) - 10 - txtW, txtH);
		_sldrRight.setBounds((w_ / 2) + 5, 20, (w_ / 2) - 10 - txtW, txtH);

		_btnChartTitle.setBounds(5, 55, _btnChartTitle.getPreferredSize().width,
				_btnChartTitle.getPreferredSize().height);
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
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e_)
	{
	}
}
