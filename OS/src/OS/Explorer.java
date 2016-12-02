package OS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

//import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import java.lang.Math;
import java.util.HashMap;
import java.awt.CardLayout;

@SuppressWarnings("unused")
public class Explorer extends JFrame{

	JInternalFrame MainFrame;
	JDesktopPane desktop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Explorer frame = new Explorer(desktop);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	JTabbedPane tabbedPane;
	JPopupMenu popup; 
	
	class Components{
		public int i;
		String[] path;
		public Components()
		{
			i=0;
			path= new String[10];
		}
	}
	
	File[] listOfMainFolders;
	JButton[][] buttons = new JButton[50][50];
	JInternalFrame[] internalFrame = new JInternalFrame[100];
	HashMap<String,Components> Paths = new HashMap<String,Components>();
	//Change rootPath according to the directory you want your "OS" to be based on.
	final String rootPath = System.getProperty("user.dir")+File.separator +"OS";
	String path = System.getProperty("user.dir")+File.separator+ "images";
	String s;
	int ind;
	
	
	

	/**
	 * Create the frame.
	 */
	
	public String Join (Components c)
	{
		String out="";
		for(int i=0;i<c.i;++i)
		{
			out+=c.path[i]+File.separator;
		}
		return out;
	}
	public void refreshPane(int i, int flag,String p)
	{
		
		File f;
		if (flag==1)
		{
			//creating main tabs
			f = listOfMainFolders[i];
		}
		else if (flag==2)
		{
			//NEW FILE REFRESH
			Components c = Paths.get(tabbedPane.getTitleAt(i));
			String name = rootPath+File.separator+Join(c);
			f = new File(name);
			String n = tabbedPane.getTitleAt(i);
			ImageIcon icon = new ImageIcon(path+"small_"+n+".png");
			internalFrame[i] = new JInternalFrame(i+"frm");
			tabbedPane.addTab(n , icon, internalFrame[i],null);
			tabbedPane.remove(i);
		}
		else 
		{
			//FOLDER OPEN
			f = new File(p);
			String n = tabbedPane.getTitleAt(i);
			ImageIcon icon = new ImageIcon(path+"small_"+n+".png");
			internalFrame[i] = new JInternalFrame(i+"frm");
			tabbedPane.addTab(n , icon, internalFrame[i],null);
			tabbedPane.remove(i);
		}
		
		if (f.isDirectory())
		{
			File[] listOfFiles = f.listFiles(); //doubt
			int number = (int) (java.lang.Math.sqrt(listOfFiles.length));
			//System.out.println(listOfFiles.length);
			internalFrame[i].getContentPane().setLayout(new GridLayout(number+1,number+1,0,0));		
			internalFrame[i].setVisible(false);
			int j;
			for ( j=0;j<listOfFiles.length;++j){
				s=listOfFiles[j].getPath();
				//System.out.println(s);
				ImageIcon image;
				if(listOfFiles[j].isFile())
				{
					image = new ImageIcon(path+File.separator+"small_file.png");
				}
				else
				{
					image = new ImageIcon(path+File.separator+"small_folder.png");
				}
				
				buttons[i][j] = new JButton(" ");
				
				if(listOfFiles[j].isDirectory())
				{
					ind = tabbedPane.getSelectedIndex();
					
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String n=tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
						//System.out.println(n+' '+ind);
						Components t = Paths.get(n);
						t.path[t.i++] = e.getActionCommand();
						s = rootPath+File.separator+Join(t);
					//	System.out.println('*'+s);
						refreshPane(tabbedPane.getSelectedIndex(),3,s);
						
					}
					
				});}
				else 
				{
					final int m =j; 
					buttons[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Teditor obj=new Teditor();
					    	obj.open_file(listOfFiles[m]);
					    	JInternalFrame frame1=obj.send_frame();
					    	frame1.setVisible(true);
					        desktop.add(frame1);
							//System.out.println(" file opened ");
							
						//	System.out.println(" file opened "); //open in text editor
							
						}
					});
				}
				
				buttons[i][j].setSize(new Dimension(20, 20));


				buttons[i][j].setOpaque(false);
				buttons[i][j].setContentAreaFilled(false);
				buttons[i][j].setBorderPainted(false);

				buttons[i][j].setText(listOfFiles[j].getName());
				buttons[i][j].setIcon(image);
				buttons[i][j].setHorizontalTextPosition(AbstractButton.CENTER);
		        buttons[i][j].setVerticalTextPosition(AbstractButton.BOTTOM);
		        
		        buttons[i][j].addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){
					if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
						
						popup.show(e.getComponent(),
			                       e.getX(), e.getY());
					
					}
					}
						});
				internalFrame[i].getContentPane().add(buttons[i][j]);
	}
			buttons[i][j]= new JButton(" ");
			buttons[i][j].setOpaque(false);
			buttons[i][j].setContentAreaFilled(false);
			buttons[i][j].setBorderPainted(false);
			buttons[i][j].addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){
				if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
					
					popup.show(e.getComponent(),
		                       e.getX(), e.getY());
				
				}
				}
					});
			internalFrame[i].getContentPane().add(buttons[i][j]);
			
	
}
		
	}
	
		

		public Explorer(JDesktopPane desktop1) {
			
			desktop=desktop1;
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds( 700, 500, 700, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		MainFrame = new MyInternalFrame();
		getContentPane().add(MainFrame, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		MainFrame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		ImageIcon img;

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
				Components c = Paths.get(s);
				if(c.i!=1) c.i--;
				s = rootPath+File.separator+Join(c);
				refreshPane(tabbedPane.getSelectedIndex(),3,s);
			}
		});
		
		img = new ImageIcon(System.getProperty("user.dir")+File.separator+"images"+File.separator+"small_back.png");
		btnBack.setIcon(img);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		toolBar.add(btnBack);
		
		
		
		
		
	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	MainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	

	
	
	
		/*JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		tabbedPane.addTab("New tab", null, internalFrame, null);
		internalFrame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		internalFrame.setVisible(true);*/
		
		

		
		File Root = new File(rootPath);
		listOfMainFolders = Root.listFiles();
		

		
		popup = new JPopupMenu();
		
		JMenuItem createFile = new JMenuItem("New File");
		createFile.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				String name = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
				Components c = Paths.get(name);
				File f = new File(rootPath+File.separator+Join(c)+"new_file.txt");
				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int ind=tabbedPane.getSelectedIndex();
				refreshPane(ind,2,"");
				}
		});
	    popup.add(createFile);
	    
	    JMenuItem createFolder = new JMenuItem("New Folder");
	    createFolder.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				String name = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
				Components c = Paths.get(name);
				File f = new File(rootPath+File.separator+Join(c)+File.separator+"new_folder");
				f.mkdir();
				int ind=tabbedPane.getSelectedIndex();
				refreshPane(ind,2,"");
				
			}
		});
	    popup.add(createFolder);

		
		for (int i=0;i<listOfMainFolders.length;++i)
		{
		
			String name = listOfMainFolders[i].getName();
			//final int x=i;
			ImageIcon icon = new ImageIcon(path+File.separator+"small_"+name+".png");
			internalFrame[i] = new JInternalFrame(i+"frm");
			Components p = new Components();
			p.path[p.i++]=name;
			Paths.put(name,p);
			tabbedPane.addTab(name , icon, internalFrame[i],null);
			refreshPane(i,1,"");
	
		}
		

		MainFrame.setVisible(true);
	
}
	public JInternalFrame return_frame()
	{
		return MainFrame;
		
	}
}
	
