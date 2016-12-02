package OS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;
import java.io.*;
import javax.swing.text.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import OS.MyInternalFrame;
import javax.swing.filechooser.FileSystemView.*;
import javax.swing.filechooser.*;
@SuppressWarnings({ "serial", "unused" })
public class Teditor extends JFrame {
	static int count=0;
	String path;
	///Custom Class to restrict file system to one directory.
	public class SingleRootFileSystemView extends FileSystemView
	{
		File root;
		File[] roots = new File[1];

		public SingleRootFileSystemView(File path)
		{
			super();

			try
			{
				root = path.getCanonicalFile();
				roots[0] = root;
			}
			catch(IOException e)
			{
				throw new IllegalArgumentException( e );
			}

			if ( !root.isDirectory() )
			{
				String message = root + " is not a directory";
				throw new IllegalArgumentException( message );
			}
		}

		@Override
		public File createNewFolder(File containingDir)
		{
			File folder = new File(containingDir, "New Folder");
			folder.mkdir();
			return folder;
		}

		@Override
		public File getDefaultDirectory()
		{
			return root;
		}

		@Override
		public File getHomeDirectory()
		{
			return root;
		}

		@Override
		public File[] getRoots()
		{
			return roots;
		}
	}
	////////////End Class
	protected UndoManager undoManager = new UndoManager();
	private static JInternalFrame frame;
	int saved=1;
	private String fname = "Untitled";
	String m=System.getProperty("user.dir");
	File root = new File(System.getProperty("user.dir")+File.separator + "OS");
	FileSystemView fsv = new SingleRootFileSystemView( root );
	private JFileChooser file_chooser = new JFileChooser(fsv);
	public JTextArea textArea = new JTextArea();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teditor window = new Teditor();
					window.setVisible(true);
					//window.frame.setVisible(true);
					//window.add(frame);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	
		
	}
	
	
	private void save_f(String fpath)
	{
		try
		{
		FileWriter new_file = new FileWriter(path);
		textArea.write(new_file);
		frame.setTitle(fname);
		saved=1;
		}
		catch(IOException e)
		{
			int er=1;
		}
		
		
	
	}
	private KeyListener k = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			saved = 0;
		}
	};
	private void save_as() {
		if(file_chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			fname=file_chooser.getSelectedFile().getName();
			path=file_chooser.getSelectedFile().getAbsolutePath();
			save_f(file_chooser.getSelectedFile().getAbsolutePath());
			
		}
	}
	private void save_old()
	{
		//saved=0;
		if(saved==0)
		{
			int confirm = JOptionPane.showConfirmDialog(null, 
	                "Save changes before you exit?", "",
	                JOptionPane.YES_NO_OPTION);
	
	            if (confirm == JOptionPane.YES_OPTION) {
	            		save_f(fname);
	            		
	            }
		}
	}
	public Teditor() {
		fname+=count++;
		fname+=".txt";
		
		 textArea.getDocument().addUndoableEditListener(
			        new UndoableEditListener() {
			          public void undoableEditHappened(UndoableEditEvent e) {
			            undoManager.addEdit(e.getEdit());
			         
			          }
			        });
		frame = new MyInternalFrame();
		frame.setVisible(true);
		try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
		setContentPane(frame);
	
		/*frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		frame.addInternalFrameListener(new InternalFrameAdapter() {
		    @Override  
			public void internalFrameClosing(InternalFrameEvent e) {
		        save_old();
		        frame.dispose();
		      }
		    });
		/*frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	save_old();
		    	dispose();
		    }
		});*/
		textArea.addKeyListener(k);
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		JMenuBar menu= new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu file=new JMenu("File");
		JMenu edit=new JMenu("Edit");
		JMenu help=new JMenu("Help");
		menu.add(file);
		menu.add(edit);
		menu.add(help);
		Action new_file = new AbstractAction("New") {
			public void actionPerformed(ActionEvent e) {
				save_old();
				textArea.setText(null);;
				
			}
		};
		
		file.add(new_file);
		Action open = new AbstractAction("Open") {
			public void actionPerformed(ActionEvent e) {
				save_old();
				if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					try {
						path=file_chooser.getSelectedFile().getAbsolutePath();
						
						FileReader r = new FileReader(file_chooser.getSelectedFile().getAbsolutePath());
						textArea.read(r,null);
						r.close();
						fname = file_chooser.getSelectedFile().getName();
						frame.setTitle(fname);
					}
					catch(IOException e1) {
						int er1=1;
					}
					
				}
				
			}
		};
		file.add(open);
		Action quit = new AbstractAction("Quit") {
			public void actionPerformed(ActionEvent e) {
				save_old();
				frame.dispose();
			}
		};
		file.add(quit);
		Action save = new AbstractAction("Save") {
			public void actionPerformed(ActionEvent e) {
				if(!fname.startsWith("Untitled"))
				{
					System.out.println(textArea.getText());
					save_f(fname);
				}
				else
					save_as();
			}
		};
		Action save_as = new AbstractAction("Save As") {
			public void actionPerformed(ActionEvent e) {
					save_as();
			}
		};
		file.add(save);
		file.add(save_as);
		ActionMap ed = textArea.getActionMap();
		Action cut = ed.get(DefaultEditorKit.cutAction);
		Action copy = ed.get(DefaultEditorKit.copyAction);
		Action paste = ed.get(DefaultEditorKit.pasteAction);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		Action undo = new AbstractAction("Undo") {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.undo();
			        } catch (CannotRedoException cre) {
			          cre.printStackTrace();
			        }
			}
		};
		Action redo = new AbstractAction("Redo") {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.redo();
			        } catch (CannotRedoException cre) {
			          cre.printStackTrace();
			        }
				
			}
		};
		edit.add(undo);
		edit.add(redo);
		frame.setTitle(fname);
		
		
	}
	public JInternalFrame send_frame()
	{
		return frame;
	}
	public void open_file(File f)
	{
		path=f.getAbsolutePath();
		try {
			
			FileReader r = new FileReader(f.getAbsolutePath());
			textArea.read(r,null);
			r.close();
			System.out.println(textArea.getText());
			try{
			fname = f.getName();
			frame.setTitle(fname);
			
			}catch(NullPointerException e2){}
			
		}
		catch(IOException e1) {
			int er1=1;
			System.out.println("Error");
		}
		//return frame;
	}
	
}


