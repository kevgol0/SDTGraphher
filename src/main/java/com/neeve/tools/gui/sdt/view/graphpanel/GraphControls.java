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





import java.awt.event.ActionEvent;
import java.text.MessageFormat;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import com.neeve.root.RootConfig.ObjectConfig;
import com.neeve.tools.gui.sdt.view.ColorPallette;
import com.neeve.tools.gui.sdt.view.SDTPanel;
import com.neeve.trace.Tracer;
import com.neeve.trace.Tracer.Level;





public class GraphControls extends SDTPanel implements ChangeListener
{

	private static final long	serialVersionUID	= 1L;
	private Tracer				_logger;


	private JButton		_btnChartTitle;
	private JSlider		_sldrBottom;
	private JSlider		_sldrTop;
	private JTextField	_sldrValTop;
	private JTextField	_sldrValBottom;

	private static final String	SLDR_TOP_NAME		= "SLDR_TOP";
	private static final String	SLDR_BOTTOM_NAME	= "SLDR_BOTTOM";

	private int	_min;
	private int	_max;





	public GraphControls(int width_)
	{
		super();
		_logger = ObjectConfig.createTracer(ObjectConfig.get(getClass().getSimpleName()));
		TitledBorder border = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(ColorPallette.White),
				"Chart Controls");
		border.setTitleColor(ColorPallette.White);
		setBorder(border);

		_btnChartTitle = new JButton("Chart Title");
		_sldrTop = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
		_sldrBottom = new JSlider(JSlider.HORIZONTAL, 0, 1000, 1000);
		_sldrValTop = new JTextField(3);
		_sldrValBottom = new JTextField(3);

		_sldrTop.setName(SLDR_TOP_NAME);
		_sldrBottom.setName(SLDR_BOTTOM_NAME);


		_btnChartTitle.addActionListener(this);
		_sldrTop.addChangeListener(this);
		_sldrBottom.addChangeListener(this);
		_sldrValTop.addKeyListener(this);
		_sldrValBottom.addKeyListener(this);

		_sldrBottom.setPaintTicks(true);
		_sldrBottom.setForeground(ColorPallette.White);
		_sldrBottom.setBackground(ColorPallette.White);
		_sldrBottom.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		_sldrBottom.setMinorTickSpacing(25);
		_sldrBottom.setMajorTickSpacing(100);

		_sldrTop.setPaintTicks(true);
		_sldrTop.setForeground(ColorPallette.White);
		_sldrTop.setBackground(ColorPallette.White);
		_sldrTop.putClientProperty("JSlider.isFilled", Boolean.TRUE);
		_sldrTop.setMinorTickSpacing(25);
		_sldrTop.setMajorTickSpacing(100);

		add(_sldrTop);
		add(_sldrBottom);
		add(_sldrValTop);
		add(_sldrValBottom);
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
		int txtW = _sldrValTop.getPreferredSize().width;
		int txtH = _sldrValTop.getPreferredSize().height;

		_sldrValTop.setBounds(5, 20, txtW, txtH);
		_sldrTop.setBounds(5 + txtW, 15, w_ - txtW - txtW - 10, txtH);

		_sldrBottom.setBounds(5 + txtW, 35, w_ - txtW - txtW, txtH);
		_sldrValBottom.setBounds(w_ - txtW, 40, txtW, txtH);

		_btnChartTitle.setBounds(5, 75, _btnChartTitle.getPreferredSize().width,
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
		String title = JOptionPane.showInputDialog(
				this,
				"Enter the secret code to continue (label)",
				"Secret code needed (title)",
				JOptionPane.INFORMATION_MESSAGE);
		GraphPanel.getInstance().setChartTitle(title);
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e_)
	{
		if (e_.getSource() instanceof JSlider)
		{
			JSlider sldr = (JSlider) e_.getSource();
			int value = sldr.getValue();
			if (SLDR_TOP_NAME.equals(sldr.getName()))
			{
				if (value > _sldrBottom.getValue())
				{
					_sldrValTop.setText(Integer.toString(value));
					setMin(getMax());
					_sldrTop.setValue(getMax());
				}
				else
				{
					_sldrValTop.setText(Integer.toString(value));
					setMin(value);
				}
			}
			else if (SLDR_BOTTOM_NAME.equals(sldr.getName()))
			{
				_sldrValBottom.setText(Integer.toString(value));
				setMax(value);
			}


			if (_logger.isEnabled(Level.INFO))
			{
				_logger.log(
						MessageFormat.format("Min:{0}, Max:{1}", getMin(), getMax()),
						Level.INFO);
			}
			GraphPanel.getInstance().adjustGraphBounds(getMin(), getMax());
		}
	}





	/**
	 * 
	 */
	public void uppdateSliderCounts(int min_, int max_)
	{
		_sldrTop.setValue(0);
		_sldrTop.setMaximum(max_);
		_sldrBottom.setValue(max_);
		_sldrBottom.setMaximum(max_);
		_sldrValTop.setText(Integer.toString(0));
		_sldrValBottom.setText(Integer.toString(max_));
	}





	/**
	 * 
	 */
	public final int getMin()
	{
		return _min;
	}





	/**
	 * 
	 */
	public final void setMin(int min_)
	{
		_min = min_;
	}





	/**
	 * 
	 */
	public final int getMax()
	{
		return _max;
	}





	/**
	 * 
	 */
	public final void setMax(int max_)
	{
		_max = max_;
	}




}
