package use_case.chat;



import app.ChatClientApplication;
import comm.entity.User;
import lombok.extern.slf4j.Slf4j;
import view.comm.MyTools;
import view.comm.MyTreeIcon;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyTree
{
	private JTree tree;//tree where your friends list is stored
	private DefaultTreeModel treeModel;//Friends tree data
	ArrayList<String> nodeImages;//Store the avatar of the node, for example: mask; 02_100.jpg
	private Map<String,List<User>> friendInfos=new HashMap<>();//Friend information for each group
	public MyTree(JTree tree,Map<String,List<User>> friendInfos)
	{
		this.tree=tree;
		this.friendInfos=friendInfos;
		init();//init friends list
	}
	public MyTree(JTree tree)
	{
		this.tree=tree;
		treeModel=(DefaultTreeModel) tree.getModel();
		
	}
	/**
	 * init tree
	 */
	public void init()
	{	
		tree.setRootVisible(false);//Set the root node to be invisible
        tree.setAutoscrolls(true);//Set up auto-scrolling
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//Set up a single select mode
        
        nodeImages=new ArrayList<String>();
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("root");
        treeModel=new DefaultTreeModel(root);
		for(Map.Entry<String,List<User>> entry:friendInfos.entrySet()){
			String group=entry.getKey();
			List<User> userList=entry.getValue();

        	DefaultMutableTreeNode node=new DefaultMutableTreeNode(group);
        
        	for(int j=0;j<userList.size();j++)
        	{
        		String friendName=userList.get(j).getName();//friend name
				String headImage=userList.get(j).getAvatar();
        		Integer state=userList.get(j).getOnline();
        		nodeImages.add(friendName+MyTools.SPLIT1+"/img/headImage/small/"+headImage+"_32.jpg");
        		node.add(new DefaultMutableTreeNode(friendName));
        	}
        	root.add(node);
        }
        tree.setCellRenderer(new MyTreeIcon(nodeImages));
        tree.setModel(treeModel);
		// 节点间不采用连接线
		tree.putClientProperty("JTree.lineStyle", "None");
		DefaultTreeCellRenderer treeCellRenderer;// Gets the draw object for the tree node
		treeCellRenderer = (DefaultTreeCellRenderer) tree
				.getCellRenderer();
		treeCellRenderer.setLeafIcon(null);// Set the leaf node to be free of icons
		treeCellRenderer.setClosedIcon(new ImageIcon(ChatClientApplication.class.getResource("/img/closeIcon.png")));// setting the icon when  node folding
		treeCellRenderer.setOpenIcon(new ImageIcon(ChatClientApplication.class.getResource("/img/openIcon.png")));//  setting the icon when  node is expanded
	}
	



	/**
	 * Add new friends to the tree group
	 * @param groupName
	 */
	public void addGroupToTree(String groupName)
	{
		DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(groupName);
		DefaultMutableTreeNode parent=(DefaultMutableTreeNode) treeModel.getRoot();
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
	}
	/**
	 * Add new friends to a specified group
	 * @param groupName
	 * @param friendName The name of the friend you want to add
	 */
	public  void addFriendToTree(String groupName,String friendName)
	{
		int groupIndex=treeModel.getIndexOfChild((DefaultMutableTreeNode)treeModel.getRoot(), new DefaultMutableTreeNode(groupName));
		DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(friendName);
		if(groupIndex!=-1)
		{	
			DefaultMutableTreeNode parent=(DefaultMutableTreeNode) treeModel.getChild(treeModel.getRoot(), groupIndex);
			treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		}
		else
			System.out.println("Friends group“"+groupName+"”not exist！");
		 	
	}
	/**
	 * Delete a friend in the tree
	 */
	public void deleteFriend(String groupName,String friendName)
	{
		DefaultMutableTreeNode root=(DefaultMutableTreeNode) treeModel.getRoot();
		for(int i=0;i<root.getChildCount();i++)
		{
			if(root.getChildAt(i).toString().startsWith(groupName))
			{
				for(int j=0;j<root.getChildAt(i).getChildCount();j++)
				{
					if(root.getChildAt(i).getChildAt(j).toString().startsWith(friendName))
					{
						System.out.println(root.getChildAt(i).getChildAt(j));
						DefaultMutableTreeNode node=(DefaultMutableTreeNode)root.getChildAt(i).getChildAt(j);
						node.removeFromParent();
						System.out.println("The deletion is successful！");
					}
					break;
				}
			}
			break;
		}
	}
	/**
	 * Open a friend group
	 * @param groupName
	 */
	public void openGroup(String groupName)
	{
		DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(groupName);
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));//Automatically opens to the current node
	}
}
