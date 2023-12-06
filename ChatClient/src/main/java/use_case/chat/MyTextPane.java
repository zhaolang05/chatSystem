package use_case.chat;



import view.comm.MyTools;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class MyTextPane
{
	JTextPane textPane=null;
	StyledDocument  document=null;
	
	public MyTextPane(JTextPane textPane)
	{
		this.textPane=textPane;
		document=this.textPane.getStyledDocument();
	}
	public void addText(String text,SimpleAttributeSet font)
	{
		try
		{
			document.insertString(document.getLength(), text, font);
			StyleConstants.setIcon(getMyAttribute(), MyTools.getIcon("/img/QQ_64.png"));
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}
	public void addIcon(String imagePath,String friendName)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friendName+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(MyTools.getIcon(imagePath));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @param image
	 * @param friendName
	 */
	public void addIcon(Image image,String friendName)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friendName+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(new ImageIcon(image));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Get a  font
	 * @param name font name
	 * @param size  font size
	 * @param color font color
	 * @param bold Whether to bold or not
	 * @param underline Whether or not to underline
	 * @return Returns the obtained font
	 */
	public static SimpleAttributeSet getFontAttribute(String name, int size, Color color,
			boolean bold, boolean underline)
	{
		SimpleAttributeSet attribute = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attribute, name);
		StyleConstants.setFontSize(attribute, size);
		StyleConstants.setForeground(attribute, color);
		StyleConstants.setBold(attribute, bold);
		StyleConstants.setUnderline(attribute, underline);
		return attribute;
	}
	public static SimpleAttributeSet getMyAttribute()
	{
		return getFontAttribute("Arial", 22, Color.red, false, true);
	}
	public static SimpleAttributeSet getFriendAttribute()
	{
		return getFontAttribute("Arial", 16, Color.blue, false, false);
	}
	public static SimpleAttributeSet getTimeAttribute()
	{
		return getFontAttribute("Arial", 14, Color.DARK_GRAY, false, false);
	}
}
