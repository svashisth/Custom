package com.utilities;

// Java program to move a mouse from the initial
// location to a specified location
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.commons.lang3.RandomUtils;

class TidalTask extends Frame implements ActionListener {
	// Frame
	static JFrame f;

	// textField
	static TextField x, y;

	static TextArea ax;

	static volatile boolean keepGoing = true;

	static volatile long startTime = 0;

	static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

	final static int[] BRK_TIME = new int[] { 4, 2, 3, 5, 7 };

	// final static int[] BRK_TIME = new int[] { 1, 2, 3, 4 };

	// main function
	public static void main(String args[]) throws AWTException, InterruptedException
	{
		// object of class
		TidalTask rm = new TidalTask();

		// create a frame
		f = new JFrame("Orion");

		// set the frame to close on exit
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create textfield
		x = new TextField(7);
		y = new TextField(7);

		ax = new TextArea();
		// create a button
		Button b = new Button("OK");
		Button bStart = new Button("Start");
		Button bPause = new Button("Pause");

		// add actionListener
		b.addActionListener(rm);
		bStart.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				keepGoing = true;
				if (threadExecutor.isShutdown() || threadExecutor.isTerminated())
				{
					threadExecutor = Executors.newSingleThreadExecutor();
				}
				threadExecutor.execute(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							ax.append("\n\nStarting now....");
							keepMoving();
						}
						catch (AWTException | InterruptedException e1)
						{
							e1.printStackTrace();
						}
					}
				});
			}
		});
		bPause.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ax.append("\n\nPausing now....");
				threadExecutor.shutdown();
				keepGoing = false;
			}
		});
		// create a panel
		Panel p = new Panel();

		// add items to panel
		p.add(x);
		p.add(y);
		p.add(b);
		p.add(bStart);
		p.add(bPause);

		ax.setBounds(50, 50, 200, 200);
		ax.setSize(150, 200);
		// ax.setEditable(false);
		ax.setEnabled(false);

		p.add(ax);
		
		p.add(new Label("Stop after", Label.LEFT));
		final JTextField tmVal = new JTextField("10", 4);
		p.add(tmVal);
		final String[] items = { "seconds", "minutes" };
		final JComboBox<String> comboBox = new JComboBox<String>(items);
		p.add(comboBox);
		JButton bSubmit = new JButton("Submit");
		p.add(bSubmit);
		bSubmit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
				String text = tmVal.getText();
				Integer timeVal = Integer.valueOf(text);
				long delay = timeVal;
				String selectedItem = (String) comboBox.getSelectedItem();
				TimeUnit unit = TimeUnit.SECONDS;
				if (selectedItem.equalsIgnoreCase(items[0]))
				{
					unit = TimeUnit.SECONDS;
				}
				else if (selectedItem.equalsIgnoreCase(items[1]))
				{
					unit = TimeUnit.MINUTES;
				}
				ax.append("\nScheduled stop after " + delay + " " + unit);
				scheduledExecutorService.schedule(new Runnable()
				{
					@Override
					public void run()
					{
						ax.append("\n\nExecuting scheduled task now....");
						System.out.println("\n\nExecuting scheduled task now....");
						threadExecutor.shutdown();
						keepGoing = false;
					}
				}, delay, unit);

			}
		});

		f.add(p);

		// setsize of frame
		f.setSize(480, 280);

		f.show();

		keepMoving();
	}

	private static void keepMoving() throws AWTException, InterruptedException
	{
		Robot r = new Robot();
		// java - get screen size using the Toolkit class
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// the screen height
		double h = screenSize.getHeight();
		// the screen width
		double w = screenSize.getWidth();

		startTime = System.currentTimeMillis();

		while (true)
		{
			if (!keepGoing)
			{
				break;
			}

			double h1 = RandomUtils.nextDouble(10, h);
			double w1 = RandomUtils.nextDouble(10, w);
			System.out.println("new rand values \t" + h1 + ", " + w1);

			Point p = MouseInfo.getPointerInfo().getLocation();
			int xi = p.x;
			int yi = p.y;

			int h2 = (int) h1;
			int w2 = (int) w1;
			x.setText("" + h2);
			y.setText("" + w2);
			ax.append("\nNew random location (" + h2 + ", " + w2 + "). Current Mouse Location (" + xi + ", " + yi + ")");

			while (xi != h2 || yi != w2)
			{
				// move the mouse to the other point
				r.mouseMove(xi, yi);

				if (xi < h1)
				{
					xi++;
				}
				if (yi < w1)
				{
					yi++;
				}

				if (xi > h1)
				{
					xi--;
				}
				if (yi > w1)
				{
					yi--;
				}
				Thread.sleep(30);
			}
			long currentTime = System.currentTimeMillis();

			int nextRndPick = RandomUtils.nextInt(0, 3);
			int breakTime = BRK_TIME[nextRndPick];
			long elapsedTime = currentTime - startTime;
			long elapsedTimeInMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
			ax.append("\n---------------------------------------");
			ax.append("\nChoosen break time\t" + breakTime + " mins\nCurrent elapsed time " + elapsedTimeInMinutes + " mins");

			if (elapsedTimeInMinutes >= breakTime)
			{
				ax.append("\nIt's break time. Working since\t" + elapsedTimeInMinutes + " mins");
				long millis = TimeUnit.MINUTES.toMillis(RandomUtils.nextInt(4, 8)); //break time values
				ax.append("\n" + new Date() + " : Going away for \t" + millis / (1000 * 60) + " mins");
				ax.append("\n---------------------------------------");
				Thread.sleep(millis);
				startTime = System.currentTimeMillis();
			}
			else
			{
				int nextInt = RandomUtils.nextInt(2000, 10000);
				ax.append("\nSleeping for " + nextInt / 1000 + "s");
				ax.append("\n");
				Thread.sleep(nextInt);
			}
		}
	}

	// At app start
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("actionPerformed : AM I CALLED??");
		try
		{
			Robot r = new Robot();
			int xi1, yi1, xi, yi;

			// get initial loction
			Point p = MouseInfo.getPointerInfo().getLocation();
			xi = p.x;
			yi = p.y;

			// get x and y points
			xi1 = Integer.parseInt(x.getText());
			yi1 = Integer.parseInt(y.getText());
			int i = xi, j = yi;

			startTime = System.currentTimeMillis();
			// slowly move the mouse to detined location
			while (i != xi1 || j != yi1)
			{
				// move the mouse to the other point
				r.mouseMove(i, j);

				if (i < xi1)
				{
					i++;
				}
				if (j < yi1)
				{
					j++;
				}

				if (i > xi1)
				{
					i--;
				}
				if (j > yi1)
				{
					j--;
				}

				// wait
				Thread.sleep(30);
			}

		}
		catch (Exception evt)
		{
			System.err.println(evt.getMessage());
		}
	}
}
