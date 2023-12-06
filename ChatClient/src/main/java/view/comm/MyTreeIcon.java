package view.comm;


import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class MyTreeIcon extends DefaultTreeCellRenderer 
{
//	If path is : username; image path, then it is the friend node
//	If path is : username; Picture path 1; image path 2, then represents the grouping node,
//	Picture path 1 indicates the unexpanded grouping, and image path 2 represents the expanded grouping
	ArrayList<String> nodeImages=null;
    public MyTreeIcon(ArrayList<String> nodeImages)
    {
    	this.nodeImages=nodeImages;
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) 
    {
        super.getTreeCellRendererComponent(tree, value, sel,expanded,
        		leaf, row, hasFocus);
        for(String str:nodeImages)
        {
        	String[] temp=str.split(MyTools.SPLIT1);
        	if(value.toString().startsWith(temp[0])&&!temp[0].equals(""))
        	{
        		 try
				{
					//Image grayImage = GrayFilter.createDisabledImage(ImageIO.read(Main.class.getResource(temp[1])));
					if(temp.length==2)//If path is : username; image path, then it is the friend node
	        			//this.setIcon(new ImageIcon(grayImage));
						this.setIcon(MyTools.getIcon(temp[1]));
	        		else if(temp.length==3)//If path is : username; Picture path 1; image path 2, then represents the grouping node,
	        		{
	        			if(!expanded)
	        				this.setIcon(MyTools.getIcon(temp[1]));
	        			else 
	        				this.setIcon(MyTools.getIcon(temp[2]));
	        		}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
        		
        	}
        }
        return this;
    }
}

