/****************************************************************************
 * Copyright (C) Kevin A. Goldstein R. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kevin A. Goldstein, 2018
 *
 * FILE: DataManager.java
 * DSCRPT: 
 ****************************************************************************/





package com.neeve.tools.gui.sdt.data;





import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;



import com.neeve.tools.gui.sdt.view.graphpanel.GraphPanel;





public class DataManager
{
	private ConcurrentHashMap<String, DataSet<Integer>> _datasets;

	private int CONFIGURED_TEST_DATASET_POINT_NUMBER = 1000;



	public static class DMHelper
	{
		private static final DataManager _instance = new DataManager();
	}





	/**
	 *
	 */
	private DataManager()
	{
		_datasets = new ConcurrentHashMap<>();
		buildExampleDataSets();
	}





	/**
	 * 
	 */
	private void buildExampleDataSets()
	{
		String[] strs =
		{
				"TTS1", "c2o", "w2i", "o2p", "o3p", "li7", "bau", "kar", "kev", "yan"
		};


		DataSet<Integer> ds1;
		DataSet<Integer> ds2;
		Random rand = new Random();
		String keyI, keyR;
		for (String key : strs)
		{
			keyI = key + "_i";
			keyR = key + "_r";
			ds1 = new DataSet<Integer>(keyI);
			ds2 = new DataSet<Integer>(keyR);

			_datasets.put(keyI, ds1);
			_datasets.put(keyR, ds2);

			for (int i = 0; i < CONFIGURED_TEST_DATASET_POINT_NUMBER; i++)
			{
				ds1.add(rand.nextInt(1000));
				ds2.add(rand.nextInt(1000));
			}
		}
	}





	/**
	 * 
	 */
	public static DataManager getInstance()
	{
		return DMHelper._instance;
	}





	/**
	 * 
	 */
	public void update(String name_)
	{
		System.out.println("Generate graph for: " + name_);
		GraphPanel.getInstance().update(_datasets.get(name_));

	}





	/**
	 * 
	 */
	public Iterator<String> getKeysets()
	{
		return _datasets.keySet().iterator();
	}


}
