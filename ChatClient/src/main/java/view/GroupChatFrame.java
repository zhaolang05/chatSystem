package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GroupChatFrame extends JFrame
{

	private JPanel contentPane;
	public JTree tree;
	public JButton sendButton;
	public JButton closeButton;
	public JTextPane sendTextPane;
	public JTextPane receiveTextPane;
	public JTextArea groupNoticeTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GroupChatFrame frame = new GroupChatFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GroupChatFrame()
	{
		setTitle("My group chat");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel rightPanel = new JPanel();
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(204, 255, 204));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
				.addComponent(leftPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
		);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 153, 255));
		
		JPanel middlePanel = new JPanel();
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(204, 255, 255));
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 476, Short.MAX_VALUE)
				.addComponent(topPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
				.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
		);
		topPanel.setLayout(null);
		
		JLabel avatarLabel = new JLabel("");
		avatarLabel.setIcon(new ImageIcon(GroupChatFrame.class.getResource("/client/img/QQ_64.png")));
		avatarLabel.setBounds(6, 6, 64, 64);
		topPanel.add(avatarLabel);
		

		JLabel sendFileLabel = new JLabel("");
		sendFileLabel.setIcon(new ImageIcon(GroupChatFrame.class.getResource("/client/img/chat/fun_sendfile_54.png")));
		sendFileLabel.setBounds(193, 16, 54, 54);
		topPanel.add(sendFileLabel);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		receiveTextPane = new JTextPane();
		middlePanel.add(receiveTextPane);
		
		sendTextPane = new JTextPane();
		
		sendButton = new JButton("send");
		sendButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		closeButton = new JButton("close");
		closeButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		JLabel fontLabel = new JLabel("");
		fontLabel.setIcon(new ImageIcon(GroupChatFrame.class.getResource("/client/img/chat/fun_font_32.png")));
		
		JLabel emoticonsLabel = new JLabel("");
		emoticonsLabel.setIcon(new ImageIcon(GroupChatFrame.class.getResource("/client/img/chat/fun_face_32.png")));
		

		
		JLabel pictureLabel = new JLabel("");
		pictureLabel.setIcon(new ImageIcon(GroupChatFrame.class.getResource("/client/img/chat/fun_picture_32.png")));
		

		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addContainerGap(285, Short.MAX_VALUE)
					.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
				.addComponent(sendTextPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_bottomPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(fontLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emoticonsLabel)

					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pictureLabel)

					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bottomPanel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fontLabel)
						.addComponent(emoticonsLabel)			
						.addComponent(pictureLabel)
					)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sendTextPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		bottomPanel.setLayout(gl_bottomPanel);
		leftPanel.setLayout(gl_leftPanel);
		
		JPanel noticeLabel = new JPanel();
		noticeLabel.setBackground(new Color(0, 153, 255));
		noticeLabel.setBorder(new TitledBorder(null, "\u7FA4\u516C\u544A", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.RED));
		
		JScrollPane scrollPane = new JScrollPane();
		noticeLabel.setLayout(new BorderLayout(0, 0));
		
		groupNoticeTextArea = new JTextArea();
		groupNoticeTextArea.setLineWrap(true);
		groupNoticeTextArea.setForeground(new Color(255, 0, 0));
		groupNoticeTextArea.setText("group notice message");
		groupNoticeTextArea.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
		groupNoticeTextArea.setBackground(new Color(255, 204, 0));
		groupNoticeTextArea.setEnabled(false);
		noticeLabel.add(groupNoticeTextArea, BorderLayout.CENTER);
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(noticeLabel, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addComponent(noticeLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);
		
		tree = new JTree();
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
		rightPanel.setLayout(gl_rightPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
