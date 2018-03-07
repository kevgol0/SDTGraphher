/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: GraphPanel.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.view;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;





public class FileChooserPanel extends SDTPanel
{

	private static final long serialVersionUID = 1L;

	private JButton	_fileChooser;
	private JLabel	_fileLabel;

	private static final String BTN_FC_NAME = "BTN_FC";





	/**
	 *
	 */
	public FileChooserPanel(int width_, int height_)
	{
		super();
		setBackground(ColorPallette.Orange);

		_fileChooser = new JButton("File");
		_fileChooser.setName("BTN_FC");
		_fileLabel = new JLabel("Status: No file selected");
		_fileLabel.setHorizontalAlignment(JLabel.CENTER);


		int x = ((width_ / 2) - (_fileChooser.getPreferredSize().width / 2));
		int y = 1;
		_fileChooser.setBounds(x, y, _fileChooser.getPreferredSize().width, _fileChooser.getPreferredSize().height);


		x = ((width_ / 2) - (_fileLabel.getPreferredSize().width / 2));
		y += (PADDING + _fileChooser.getPreferredSize().height);
		_fileLabel.setBounds(1, y, width_, _fileLabel.getPreferredSize().height);

		add(_fileChooser);
		add(_fileLabel);
		_fileChooser.addActionListener(this);
	}





	/**
	 * 
	 */
	public String getCoordinates()
	{
		return _fileLabel.getBounds() + ", " + _fileChooser.getBounds();
	}





	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e_)
	{
		if (e_.getSource() instanceof JButton)
		{
			JButton src = (JButton) e_.getSource();
			if (BTN_FC_NAME.equals(src.getName()))
			{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text & CSV", "txt", "csv");
				chooser.setFileFilter(filter);
				int rv = chooser.showOpenDialog(this);
				if (rv == 0)
				{
					File selected = chooser.getSelectedFile();
					MainWindow.getInstance().setTitle(selected.getName());
				}
			}
		}
	}

}
