package view.comm;

import app.ChatClientApplication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.player.Player;

/**
 * @author 
 * 一些经常调用的方法和枚举，都集中放在这里
 */
public class MyTools
{
	//下面三句话定义一些标志，示例如下：
	//login#马化腾;1234567
	//friends_info#我的好友,柳夏南,王林,吴志强;陌生人,胡锦涛,陈水扁
	public static final String FLAGEND = "#";//定义最当头标志结束的字符
	public static final String SPLIT1 = ";";//定义一级分割字符
	public static final String SPLIT2 = ",";//定义二级分割字符
	public static final String SPLIT3 = "&";//定义三级分割字符
	
	public static final int QQServerPort = 6776; // QQ服务器端口号
	public static final String QQServerIP = "127.0.0.1"; // 服务器IP地址
	// 数据库地址和密码
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/myqq?useUnicode=true&characterEncoding=utf-8";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PWD = "root";
	// public static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	// public static final String JDBC_USER = "test";
	// public static final String JDBC_PWD = "test";

	
	/**
	 * @author LXA
	 *定义一个标志的枚举
	 */
	public enum Flag
	{
		LOGIN,	//“登录”的标志
		SUCCESS,	//“成功”的标志
		FAILED,		//“失败”的标志
		USERINFO,	//“用户信息、好友列表”的标志
		FRIENDS_LIST,	//
		PORT,	//端口号的标志
		START_CHAT,	//聊天的标志
		MESSAGE,	//聊天内容的标志
		GET_FRIEND_INFO, //获取好友资料的标志
		REGISTER,  //注册
		SENDFILE, //发送文件的标志
		GETFILE_OK,
		QUN_CHAT,   //群聊
		PUBLIC_MESSAGE,  //公告
		SHOW_WINDOW,  //弹窗
		UNDERLINE_MESSAGE,//离线消息
		SENDIMG,//发送图片
		FACE//表情
	};
	
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 */
	public static void setWindowsMiddleShow(JFrame frame)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2, frame.getWidth(), frame.getHeight());
	}
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 * @param width 窗体的宽度
	 * @param height 窗体的高度
	 */
	public static void setWindowsMiddleShow(JFrame frame,int width,int height)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
	}
	
	/**
	 * change the skin
	 */
	public static void changeSkin()
	{
		try
		{
			UIManager.put("control", new Color(215, 255, 255));//控件背景色
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the picture based on the file path
	 * @param path  file path
	 * @return picture
	 */
	public static ImageIcon getIcon(String path)
	{
		try
		{

			ImageIcon icon=new ImageIcon(ImageIO.read(ChatClientApplication.class.getResource(path)));
			return icon;
		}
		catch (IOException e)
		{
			System.out.println("picture："+path+"not exist！");
			return null;
		}
	}
	
	/**
	 * Convert a string to an enumeration of the Flag type
	 * @param str 需要转的字符串
	 * @return 返回转换后的Flag枚举
	 */
	public static Flag stringToFlagEnum(String str)
	{
		return Enum.valueOf(Flag.class, str);
	}
	public static String getFaceByIdx(int idx)
	{
		String fileName="";
		if(idx<10)
    	{
    		fileName= "../img/face/f00"+idx+".png";//change the image path
    	}
    	else
    	{
    		fileName= "../img/face/f0"+idx+".png";
		}
		return fileName;
	}

	public static void playMsgSound()
	{
		try
		{
			FileInputStream file = new FileInputStream(ChatClientApplication.class.getResource("/audio/msg.mp3").getPath()); //initialize the FileInputStream
			Player player= new Player(file); //initialize the player
			player.play(); //start the player

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void playSystemSound()
	{
		try
		{
			FileInputStream file = new FileInputStream(ChatClientApplication.class.getResource("/audio/system.mp3").getPath()); //initialize the FileInputStream
			Player player= new Player(file); //initialize the player
			player.play(); //start the player
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
