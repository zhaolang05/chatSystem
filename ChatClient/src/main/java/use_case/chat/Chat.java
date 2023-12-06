package use_case.chat;

import app.ChatClientApplication;
import comm.constant.MessageType;
import comm.event.Event;
import comm.event.EventListener;
import comm.utils.Base64Utils;
import interface_adapter.chat.ChatController;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;

import view.*;
import view.comm.FaceFrame;
import view.comm.MyLabel;
import view.comm.MyTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 *   The form of the chat, inherited from the ChatFrame
 */
@Slf4j
public class Chat extends ChatFrame implements EventListener
{
	

	public String friendName="";//friend name
	public String myName="";//my name


	int width = 680;// form width
	int height = 600;// form height
	private StyledDocument receiveDocument = null;// Used to store the text or image of the receiving frame
	private StyledDocument sendDocument = null;// Used to store text or images in the send box


	private ChatController controller;
	
	

	
	public String ImgPath = "";
	String screenCutImgName = "";

	public int faceIdx=-1;//Index of emoticons

	public Chat()
	{
		init();
	}
	/**
	 * start chat
	 * @param controller
	 * @param friendName
	 * @param myName
	 */
	public Chat(ChatController controller, String friendName, String myName)
	{
		this.controller=controller;
		this.friendName=friendName;
		this.myName=myName;
		this.setTitle("Chat with "+friendName);
		init();

	}


	public void init()
	{
		this.setMinimumSize(new Dimension(width, height));// Set the minimum size of the form


//			setTitle("chat with" + friendName+"("+friendIP+":"+friendTCPPort+")");//
		initLabelEvent();// Initializes mouse movement events for all labels on the form
		MyTools.setWindowsMiddleShow(this,width,height);//Set the form to be displayed in the center
		sendButton.setMnemonic(KeyEvent.VK_ENTER);// Set the shortcut key of the send button to Alt+Enter
//
		receiveDocument = receiveTextPane.getStyledDocument();
//		sendDocument = .getStyledDocument();
		receiveTextPane.insertIcon(MyTools.getIcon("/img/warning.png"));
		String warning="During the conversation, please do not trust remittances, winning information, strange phone calls, and do not use plug-in software.\n";
		try
		{
			SimpleAttributeSet warn= MyTextPane.getFontAttribute("Arial", 12, Color.blue, false, false);
			receiveDocument.insertString(receiveDocument.getLength(), warning, warn);
			// Set the top icon of the form
			this.setIconImage(ImageIO.read(ChatClientApplication.class.getResource("/img/QQ_64.png")));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Initializes mouse movement events for all labels on the form
	 */
	public void initLabelEvent()
	{
		new MyLabel(fontLabel).addEvent();
		new MyLabel(emoticonsLabel).addEvent();
		new MyLabel(sendPictureLabel).addEvent();
		new MyLabel(chatHistoryLabel).addEvent();
		new MyLabel(SendFileLabel).addEvent();
		new MyLabel(myFriendLabel).addEvent();

	}

	/*
	 * Send a message to a friend
	 */
	@Override
	public void sendMessage()
	{
		//message is text
		if ("".equals(ImgPath) && "".equals(screenCutImgName))
		{
			if(faceIdx<0)
			{
				this.controller.sendMessage(friendName,SendMessageTextPane.getText(), MessageType.SEND_MESSAGE);
        		setReceivePaneText(true,SendMessageTextPane.getText());//Displays messages sent by the user in the chat box
				SendMessageTextPane.setText("");// Clear the send box
			}
			else
			{
				new MyTextPane(receiveTextPane).addIcon(MyTools.getFaceByIdx(faceIdx), myName);
				this.controller.sendMessage(friendName,String.valueOf(faceIdx), MessageType.SEND_EMOTICONS);
				SendMessageTextPane.setText("");// Clear the send box
				faceIdx=-1;//After the picture emoji is posted, it is set to default
			}
		}
		else  if(!"".equals(ImgPath))
		{
			try
			{
				//Empty the send box
				SendMessageTextPane.setText("");// Clear the send box
				//The receiving box displays the picture
				receiveTextPane.insertIcon(new ImageIcon(ImageIO
						.read(new FileInputStream(ImgPath))));
				//The receiving box displays the picture
				File file = new File(ImgPath);
				String message= Base64Utils.fileToBase64(file);
				this.controller.sendMessage(friendName,message, MessageType.SEND_FILE);
				//After sending the image, leave the image path blank
				ImgPath ="";
			}
			catch (Exception ex)
			{
				log.error("file send error",ex);
			}

		}
		
	}


	/**
	 * Set the send box text
	 * 
	 * @param text Text that needs to be set
	 *
	 */
	public void setSendPaneText(String text)
	{
		try
		{
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sets the text of the receive box
	 * 
	 * @param text  Text that needs to be set
	 *
	 */
	public void setReceivePaneText(boolean isFromMyself, String text)
	{
		if (isFromMyself)
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			new MyTextPane(receiveTextPane).addText(myName+" "+time+ "\n",MyTextPane.getTimeAttribute());
			new MyTextPane(receiveTextPane).addText(text+ "\n",MyTextPane.getMyAttribute());
		}
		else
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			new MyTextPane(receiveTextPane).addText(friendName+" "+time+ "\n",MyTextPane.getTimeAttribute());
			new MyTextPane(receiveTextPane).addText(text+ "\n",MyTextPane.getFriendAttribute());
		}
	}
	/**
	 * Send the file
	 */
	public void sendFile()
	{

	}
	/* 
	 * 发生在窗体关闭之前
	 */
	public void beforeClose()
	{
		
	}
	public void selectFace()
	{
		new FaceFrame(this);
	}
	/*
	 * Send pictures
	 */
	public void sendImg()
	{
		JFileChooser chooser = new JFileChooser();

		// Add filters
		chooser.addChoosableFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				// TODO Auto-generated method stub
				return ".jpg/.png/.bmp";
			}

			@Override
			public boolean accept(File file)
			{
				// Get the file name
				String fileName = file.getName();
				if (file.isDirectory())
					return true;

				// Filter for file names
				if (fileName.endsWith(".jpg") || fileName.endsWith(".png")
						|| fileName.endsWith(".bmp"))
				{
					return true;
				}

				return false;
			}
		});

		int result = chooser.showOpenDialog(null);

		// Select file
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String filePath = chooser.getSelectedFile().getAbsolutePath();
			// Add to the image path
			ImgPath = filePath;
			try
			{
				int a=0;

				new FileInputStream(filePath);
//				new MyTextPane(receiveTextPane).addIcon(new ImageIcon(ImageIO
//						.read(new FileInputStream(filePath))),"file");
			}
			catch (FileNotFoundException e)
			{

				log.error("file not exist",e);
			}
//			catch (IOException e)
//			{
//				log.error("io error",e);
//
//			}

		}
		// 选择关闭是
		else
		{
//			dispose();
		}

	}
	
	




	@Override
	public void handleEvent(Event event) {

	}
}
