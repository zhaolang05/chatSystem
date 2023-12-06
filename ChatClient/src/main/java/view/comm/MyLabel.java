package view.comm;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyLabel
{
	//Provisions: The image index in the regular state is 1, the mouse entry is 2, the mouse press is 3, and the mouse release is still 2
	JLabel jLabel=null;
	String fileName="";
	String extension="";
	int mode=1;//A mode of 1 indicates an image, and a mode of 2 indicates a Boder
	Color backColor=null;//Label's background color of the parent container
	/**
	 * Instantiate a label based on a specified number of images
	 * @param fileName The path to the image, excluding the image index and extension, for example
	 * If the image name is img/QQ_1.png, it should be written as ".. /img/QQ"
	 * @param extension extension, excluding the preceding dot "."
	 */
	public MyLabel(JLabel jLabel,String fileName,String extension)
	{
		this.jLabel=jLabel;
		this.fileName=fileName;
		this.extension=extension;
	}
	public MyLabel(JLabel jLabel)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=this.jLabel.getParent().getBackground();
		setEtchedBorder(backColor);//Set the default border color of the label to the same as the background color to achieve the effect of unrealistic borders
	}
	public MyLabel(JLabel jLabel,Color color)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=color;
		setEtchedBorder(backColor);
	}

	/**
	 * Set it as a kind of border, I don't know what the Chinese translation is
	 * @param color
	 */
	public void setEtchedBorder(Color color)
	{
		jLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(color, color));
	}
	/**
	 * Add an event to the jLabel
	 */
	public void addEvent()
	{
		jLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_1."+extension));
				else 
					setEtchedBorder(backColor);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_3."+extension));
				else 
					jLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(
							javax.swing.border.BevelBorder.LOWERED));
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
		});
	}
}
