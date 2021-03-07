package mayton.game;

import static java.lang.Math.*;

import mayton.image.Raster;
import mayton.image.bmp.BitmapImportFilter;
import mayton.math.numeric;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.event.*;
import java.io.*;
import mayton.Utility;

public class QTree extends Frame implements Runnable
{
	SGrafGaussView SGV;

	Image SourceImage;

	Raster RastrSource;
	Raster TempObj;
	Raster temp;

	public String lastImageFileName="";


	int X=256;
	int Y=256;

	int yoffset=64;
	int xoffset=8;

	BitmapImportFilter BIF;

	FileInputStream		FIS;

	SGraf Graf1;

	protected boolean ShowSquares        = true;

	protected boolean ShowSquareFrame    = false;

	protected boolean ShowSource         = false;

	protected boolean ShowGaussBlitting  = false;



	public static void main (String args []) throws Exception
	{
		QTree mainFrame = new QTree(args[0]);
	}


	public void Prepare() throws Exception
	{

		FIS=new FileInputStream("images/"+lastImageFileName);

        BIF=BitmapImportFilter.createInstance();

        //TempObj=BitmapImportFilter.from(FIS);

		FIS.close();

		if (TempObj==null) throw new Exception("Cannot load picture!");

		int maxbound=max(Utility.extendToPowerOf2(TempObj.X),Utility.extendToPowerOf2(TempObj.Y));

		System.out.printf("maxbound = %d\n",maxbound);

		RastrSource=new Raster(maxbound,maxbound);

		RastrSource.copy(TempObj);

		//SourceImage=createImage(new MemoryImageSource(RastrSource.X,RastrSource.Y,RastrSource.pixels,0,RastrSource.X));

		SourceImage=createImage(new MemoryImageSource(TempObj.X, TempObj.Y, TempObj.pixels, 0, TempObj.X));

		System.out.print("Create Graf.....");

		Graf1=new SGraf(RastrSource,3.0,1);

		System.out.println("OK!");

		System.out.print("Create SGV.....");

		SGV=new SGrafGaussView(xoffset,yoffset,RastrSource.X+xoffset,RastrSource.Y+yoffset,Graf1);

		System.out.println("OK!");

		temp=null;

		repaint();

	}


	public QTree(String imagefilename) throws Exception
	{
		lastImageFileName=imagefilename;


        addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
				System.exit(0);
			}
		}
		);

		this.setTitle("Q-Tree demo 1.0");
		this.setVisible(true);
		//this.setResizable(false);
		this.setSize(800,600);

		// ?������� ���?

		MenuBar mbar=new MenuBar();

		this.setMenuBar(mbar);

		Menu MenuStart=new Menu("File");

		MenuItem MenuItemOpenBitmap,MenuItemOpenQTree,MenuShowSquares,MenuShowSquaresFrame,MenuExport,MenuShowGaussBlitting;

			MenuStart.add(MenuItemOpenBitmap=new MenuItem("Open Bitmap"));
			MenuStart.add(MenuItemOpenQTree =new MenuItem("Open QTree"));
			MenuStart.add(new MenuItem("-"));
			MenuStart.add(MenuExport=new MenuItem("Export"));
			MenuStart.add(new MenuItem("-"));


		mbar.add(MenuStart);

		MenuItem MenuShowSource;

		Menu MenuOptions=new Menu("Options");

			MenuOptions.add(MenuShowSource       = new MenuItem("Show Source"));
			MenuOptions.add(MenuShowSquares      = new MenuItem("Show Squares"));
			MenuOptions.add(MenuShowSquaresFrame = new MenuItem("Show Squares Frame"));
			MenuOptions.add(new MenuItem("-"));
			MenuOptions.add(MenuShowGaussBlitting= new MenuItem("Show Gauss Blitting"));


		mbar.add(MenuOptions);

		// ?������� ���������� ���?���

		MenuHandler handler=new MenuHandler(this);

			MenuItemOpenBitmap.addActionListener(handler);
			MenuItemOpenQTree.addActionListener(handler);
			MenuExport.addActionListener(handler);

			MenuShowSquares.addActionListener(handler);
			MenuShowSquaresFrame.addActionListener(handler);
			MenuShowSource.addActionListener(handler);
			MenuShowGaussBlitting.addActionListener(handler);

		Prepare();

	}


	public void paint(Graphics g)
	{

		if (ShowSource)
		{
			if (SourceImage!=null) {
				System.out.println("Point1");
				g.drawImage(SourceImage,xoffset,yoffset,Color.gray,this);
			}
		}
		if (Graf1!=null)
		{
			Graf1.paint(g,xoffset,yoffset,ShowSquares,ShowSquareFrame);
			if (ShowGaussBlitting)
			{
				System.out.println("Point2");
				SGV.paint(g);
			}
		}

	}

	public void start()
	{

	}

	public void stop()
	{

	}

	public void openAsBitmap()
	{
		/*
		JFileChooser chooser = new JFileChooser();
		ExampleFileView fileView = new ExampleFileView();
		fileView.putIcon("jpg", new ImageIcon("images/jpgIcon.jpg"));
		fileView.putIcon("gif", new ImageIcon("images/gifIcon.gif"));
		chooser.setFileView(fileView);
		*/
	}


	public void save()
	{
	        try{
			OutputStream OS=new FileOutputStream(lastImageFileName+".qtree");

			Graf1.Serialize(OS);

			OS.close();
		}
		catch(FileNotFoundException Ex)
		{
		}
		catch(IOException Ex)
		{
		}
	}


	public void changeShowGaussBlitting()
	{
		ShowGaussBlitting=ShowGaussBlitting?false:true;
		this.repaint();
	}

	public void changeShowSquares()
	{
		ShowSquares=ShowSquares?false:true;
		this.repaint();
	}

	public void changeShowSquareFrame()
	{
		ShowSquareFrame=ShowSquareFrame?false:true;
		this.repaint();
	}

	public void changeShowSource()
	{
		ShowSource=ShowSource?false:true;
		this.repaint();
	}


	public void run()
	{

	}

	public void dispose()
	{

	}



}



class MenuHandler implements ActionListener, ItemListener
{
	QTree QTreeObj;

	MenuHandler(QTree QTreeObj)
	{
		this.QTreeObj=QTreeObj;
	}

	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("> EVENT: "+ae.getActionCommand());

		if (ae.getActionCommand().equals("Export"))
		{
			QTreeObj.save();
		}

		if (ae.getActionCommand().equals("Show Gauss Blitting"))
		{
			QTreeObj.changeShowGaussBlitting();
		}

		if (ae.getActionCommand().equals("Show Squares")){
			QTreeObj.changeShowSquares();
		}

		if (ae.getActionCommand().equals("Show Squares Frame")) {

			QTreeObj.changeShowSquareFrame();
		}

		if (ae.getActionCommand().equals("Show Source"))  QTreeObj.changeShowSource();


	}


    public void itemStateChanged(ItemEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        QTreeObj.repaint();
    }
}





