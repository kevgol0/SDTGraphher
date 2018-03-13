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





import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;



import com.neeve.lang.XIterator;
import com.neeve.ods.IStoreObject;
import com.neeve.ods.IStoreObjectFactory;
import com.neeve.ods.StoreObjectFactoryRegistry;
import com.neeve.rog.IRogMessage;
import com.neeve.rog.log.RogLog;
import com.neeve.rog.log.RogLogFactory;
import com.neeve.rog.log.RogLogQuery;
import com.neeve.rog.log.RogLogQueryEngine;
import com.neeve.rog.log.RogLogReader;
import com.neeve.rog.log.RogLogRepository;
import com.neeve.rog.log.RogLogResultSet;
import com.neeve.root.RootConfig.ObjectConfig;
import com.neeve.server.mon.SrvMonAppEngineStats;
import com.neeve.server.mon.SrvMonAppStats;
import com.neeve.server.mon.SrvMonFactory;
import com.neeve.server.mon.SrvMonHeartbeatMessage;
import com.neeve.server.mon.SrvMonHeartbeatTracer;
import com.neeve.server.mon.SrvMonIntHistogram;
import com.neeve.server.mon.SrvMonIntSeries;
import com.neeve.tools.gui.sdt.view.graphpanel.GraphPanel;
import com.neeve.tools.gui.sdt.view.metricspanel.MetricsPanel;
import com.neeve.trace.Tracer;
import com.neeve.trace.Tracer.Level;





public class DataManager
{
	private ConcurrentHashMap<String, DatasetArrays> _datasets;

	private int CONFIGURED_TEST_DATASET_POINT_NUMBER = 1000;

	private Tracer _logger;

	//The ROG transaction log
	private RogLog _persister;

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
		_logger = ObjectConfig.createTracer(ObjectConfig.get(getClass().getSimpleName()));
		//buildExampleDataSets();
	}





	public void openTLogFile(File file_)
	{

		String name = file_.getName().substring(0, file_.getName().length() - 4);
		Properties props = new Properties();
		props.setProperty("detactedPersist", "false");
		props.setProperty("storeRoot", file_.getParent());
		props.setProperty("autoRepair", String.valueOf(false));
		props.setProperty("logMode", "r");

		try
		{
			_persister = RogLogFactory.createLog(name, props);

			final File factoriesFile = new File(file_.getParent(), name + ".factories");
			if (factoriesFile.exists())
			{
				_logger.log("Facotries:" +  factoriesFile.getName(), Level.SEVERE);
				readFactories(factoriesFile);
			}
			else
			{
				_logger.log("Facotries do NOT exist!", Level.SEVERE);
			}
			_persister.open();

			RogLogQueryEngine queryEngine = RogLogFactory.createQueryEngine();
			queryEngine.setAutoIndexing(false);
			RogLogRepository repo = _persister.asRepository();
			repo.open();
			queryEngine.addRepository(repo, name);

			final RogLogQuery query = queryEngine.createQuery(
					"SELECT * from logs WHERE simpleClassName='SrvMonHeartbeatMessage'");
			final RogLogResultSet results = queryEngine.execute(query);

			StringBuilder sb = new StringBuilder();
			SrvMonHeartbeatTracer statsReader = new SrvMonHeartbeatTracer();
			while (results.next())
			{
				sb.setLength(0);
				IStoreObject object = results.getLogEntry().getObject();
				if (object instanceof IRogMessage)
				{
					SrvMonHeartbeatMessage srvMessage = (SrvMonHeartbeatMessage) object;
					statsReader.printStats(srvMessage, sb);
					XIterator<SrvMonAppStats> appStatsIt = srvMessage.getAppsStatsIterator();
					while (appStatsIt.hasNext())
					{
						SrvMonAppStats appStats = appStatsIt.next();
						System.out.println(appStats.toString());
						SrvMonAppEngineStats aes = appStats.getEngineStats();
						SrvMonIntSeries commiutSend = aes.getCommitSendLatencies();
						SrvMonIntHistogram hist = commiutSend.getRunningStats();
						parseSeries("mpproc", aes.getMsgPreProcLatencies());
						parseSeries("mproc", aes.getMsgProcessingLatencies());
						parseSeries("inout", aes.getInOutLatencies());
						parseSeries("cstart", aes.getCommitStartLatencies());
						parseSeries("tleg1", aes.getTransactionLeg1ProcessingTimes());
						parseSeries("tleg2", aes.getTransactionLeg2ProcessingTimes());
						parseSeries("tleg3", aes.getTransactionLeg3ProcessingTimes());
						
						
						/*formatSeriesForPrint("", "mpproc", aepEngineStats.getMsgPreProcLatencies(), sb);
						formatSeriesForPrint("", "mproc", aepEngineStats.getMsgProcessingLatencies(), sb);
						formatSeriesForPrint("", "msend", aepEngineStats.getMsgSendLatencies(), sb);
						formatSeriesForPrint("", "msendc", aepEngineStats.getMsgSendCoreLatencies(), sb);
						formatSeriesForPrint("", "cstart", aepEngineStats.getCommitStartLatencies(), sb);
						formatSeriesForPrint("", "csend", aepEngineStats.getCommitSendLatencies(), sb);
						formatSeriesForPrint("", "cstore", aepEngineStats.getCommitStoreLatencies(), sb);
						formatSeriesForPrint("", "cepilo", aepEngineStats.getCommitEpilogueLatencies(), sb);
						formatSeriesForPrint("", "cfull", aepEngineStats.getCommitFullLatencies(), sb);
						formatSeriesForPrint("", "tleg1", aepEngineStats.getTransactionLeg1ProcessingTimes(), sb);
						formatSeriesForPrint("", "tleg2", aepEngineStats.getTransactionLeg2ProcessingTimes(), sb);
						formatSeriesForPrint("", "tleg3", aepEngineStats.getTransactionLeg3ProcessingTimes(), sb);
						formatSeriesForPrint("", "inout", aepEngineStats.getInOutLatencies(), sb);
						formatSeriesForPrint("", "inack", aepEngineStats.getInAckLatencies(), sb);*/

					}
				}
			}

		}
		catch (Exception e_)
		{
			_logger.log(e_.toString(), Level.SEVERE);
		}
	}

	private void parseSeries(String name_, SrvMonIntSeries series_)
	{
		String fname = name_+"-r";
		DatasetArrays ds_r = _datasets.get(fname);
		
		if ( ds_r == null )
		{
			ds_r = new DatasetArrays(fname);
			_datasets.put(fname, ds_r);
			MetricsPanel.getInstance().addToTable(fname);
		}
		
		
		fname=name_+"-i";
		DatasetArrays ds_i = _datasets.get(fname);
		if ( ds_i == null )
		{
			ds_i = new DatasetArrays(fname);
			_datasets.put(fname, ds_i);
			MetricsPanel.getInstance().addToTable(fname);
		}
		
		ds_r.add(series_.getRunningStats());
		ds_i.add(series_.getIntervalStats());
	}




	/**
	 * Read the '.factories' from the location where the transaction log file is
	 * locate. Each of the className in the file is validated and registered
	 * with the RogLogReader.
	 * 
	 * @param factoriesFile
	 *            - Name of the ".factories' file, which is named similar to the
	 *            name of the stats tlog file
	 * @throws Exception
	 *             - If the ".factories" file cannot be read or does not exists.
	 */
	final private void readFactories(File factoriesFile) throws Exception
	{
		if (factoriesFile.exists())
		{
			final BufferedReader br = new BufferedReader(new FileReader(factoriesFile));
			try
			{
				String line;
				while ((line = br.readLine()) != null)
				{
					registerFactory(line.trim());
				}
			}
			finally
			{
				br.close();
			}
			StoreObjectFactoryRegistry.getInstance().registerObjectFactory(SrvMonFactory.class.getName());
		}
		else
		{
			throw new Exception("File not found: " + factoriesFile);
		}

	}





	/**
	 * Validate the className and register factory with RoglogReader. Class is
	 * validated using reflection if it contains the create(Properties) method.
	 * If a valid factory is found is it registered with Reader, if not
	 * appropriate exception is thrown
	 * 
	 * @param className
	 *            - Name of the factory class read from ".factories" file
	 * @throws Exception
	 *             -
	 */
	final private void registerFactory(String className) throws Exception
	{
		final Class<?> clazz = Class.forName(className);
		Method createMethod = null;
		try
		{
			Class<?>[] parameterTypes = new Class[1];
			parameterTypes[0] = Class.forName("java.util.Properties");
			createMethod = clazz.getMethod("create", parameterTypes);
		}
		catch (ClassNotFoundException cne)
		{
			throw new Exception("Failed to load java.util.Properties during instantiation of factory class");
		}
		catch (SecurityException se)
		{
			throw new Exception("Invalid factory class '" + className + "' access to create method is denied");
		}
		catch (NoSuchMethodException nsme)
		{
			throw new Exception("Invalid factory class '" + className + "' create() not found");
		}

		IStoreObjectFactory factory = null;
		try
		{
			try
			{
				Object[] parameters = new Object[1];
				parameters[0] = null;
				factory = (IStoreObjectFactory) createMethod.invoke(null, parameters);
				if (null == factory)
				{
					throw new Exception(
							"Invalid factory class '" + className + "' create() method returned a null object");
				}
				RogLogReader.registerFactory(factory);
			}
			catch (ClassCastException ce)
			{
				throw new Exception(
						"Invalid factory class '" + className + "' create() did not return a valid factory");
			}
		}
		catch (IllegalAccessException ile)
		{
			throw new Exception("Invalid factory class '" + className + "'");
		}
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


		DatasetArrays ds1;
		DatasetArrays ds2;
		Random rand = new Random();
		String keyI, keyR;
		for (String key : strs)
		{
			keyI = key + "_i";
			keyR = key + "_r";
			ds1 = new DatasetArrays(keyI);
			ds2 = new DatasetArrays(keyR);

			_datasets.put(keyI, ds1);
			_datasets.put(keyR, ds2);

			for (int i = 0; i < CONFIGURED_TEST_DATASET_POINT_NUMBER; i++)
			{
				ds1.add(PERCENTILE.pct50, rand.nextInt(1000));
				ds2.add(PERCENTILE.pct50, rand.nextInt(1000));
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
	public void update(String name_, Boolean isDisplayed_)
	{
		System.out.println("Generate graph for: " + name_);
		GraphPanel gp = GraphPanel.getInstance();
		if (isDisplayed_)
		{
			DatasetArrays ds = _datasets.get(name_);
			gp.addDataSet(ds);
		}
		else
		{
			_logger.log("Removed: " + name_, Level.WARNING);
			gp.removeDataSet(_datasets.get(name_));
		}
		gp.update();
	}





	/**
	 * 
	 */
	public Iterator<String> getKeysets()
	{
		return _datasets.keySet().iterator();
	}


}
