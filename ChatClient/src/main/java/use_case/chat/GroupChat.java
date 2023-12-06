package use_case.chat;



import view.ChatMainView;
import view.GroupChatFrame;
import view.comm.MyTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

public class GroupChat extends GroupChatFrame
{
//	CS_TCP cs_TCP=null;
ChatMainView main=null;
	
	public GroupChat(ChatMainView main)
	{
		this.main=main;
		this.tree.setModel(main.tree.getModel());
		this.tree.setCellRenderer(main.tree.getCellRenderer());
//		this.cs_TCP=main.cs_TCP;
		MyTools.setWindowsMiddleShow(this);
		this.setTitle("My Official Chat Room (Current Users:"+main.userNameLabel.getText()+")");
		addEvent();
		this.setVisible(true);
	}
	public void addEvent()
	{
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nowTime= DateFormat.getTimeInstance().format(new Date());
//				cs_TCP.sendMessage(Flag.QUN_CHAT+MyTools.FLAGEND+main.userNameLabel.getText()+MyTools.SPLIT1+nowTime+MyTools.SPLIT1+sendTextPane.getText());
				sendTextPane.setText("");
			}
		});
		closeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();//关闭聊天室
			}
		});
	}
	public void receiveMessage(String message)
	{
		String[] temp=message.split(MyTools.SPLIT1);
		if(temp[0].equals(main.userNameLabel.getText()))
		{
			new MyTextPane(receiveTextPane).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(receiveTextPane).addText(temp[2]+"\n", MyTextPane.getMyAttribute());
//		}
//		else
//		{
//			new MyTextPane(receiveTextPane).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
//			new MyTextPane(receiveTextPane).addText(temp[2]+"\n", MyTextPane.getFriendAttribute());
		}
	}
	public void showPublicMessage(String message)
	{
		groupNoticeTextArea.setText(message);
	}
}
