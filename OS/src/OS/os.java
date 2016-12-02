package OS;
import OS.MyInternalFrame;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import OS.calculator;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.JToolBar;
import javax.swing.*;
import OS.Terminal;
import OS.Explorer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import OS.scheduling;
@SuppressWarnings({ "unused", "serial" })
public class os extends JFrame
                               implements ActionListener {
    JDesktopPane desktop;
	public os() {
        
		super("Mini OS");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0,
                  screenSize.width ,
                  screenSize.height);
        desktop = new JDesktopPane();
    	scheduling scdl=new scheduling(desktop);
		scdl.start();
        desktop.setBackground(Color.DARK_GRAY);
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.DARK_GRAY);
        toolBar.addSeparator(new Dimension(350,100));
        toolBar.setBounds(0, 579, 1366, 93);
        Action action1 = new AbstractAction("Text Editor") {
            public void actionPerformed(ActionEvent evt){
            	Teditor obj=new Teditor();
            	
                JInternalFrame frame = obj.send_frame();
                frame.setVisible(true);
                scdl.q.add(frame);
                //desktop.add(frame);
                try {
                    frame.setSelected(true);
                } catch (java.beans.PropertyVetoException e) {}
            
            }
          };
          JButton c1 = new JButton(action1);
          c1.setToolTipText("Text Editor");
          c1.setOpaque(false);
          c1.setContentAreaFilled(false);
          c1.setBorderPainted(false);
          ImageIcon icon =new ImageIcon(os.class.getResource("/editor2.png"));
          Image img = icon.getImage() ;  
          Image newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
          icon = new ImageIcon( newimg );
          c1.setMaximumSize(new Dimension(100, 100));
         c1.setIcon(icon);
         /**/
          c1.setText(null);
          c1.setMargin(new Insets(25, 25, 25, 25));
       
        //  c1.setPreferredSize(new Dimension(100, 100));
          toolBar.add(c1);
          toolBar.addSeparator(new Dimension(5,100));
          Action action2 = new AbstractAction("Calculator") {
              public void actionPerformed(ActionEvent evt){
              	calculator obj=new calculator();
              	
                  JInternalFrame frame = obj.return_frame();
                  
                  frame.setVisible(true);
                  scdl.q.add(frame);
                  //desktop.add(frame);
                  try {
                      frame.setSelected(true);
                  } catch (java.beans.PropertyVetoException e) {}
              
              }
            };
            JButton c2 = new JButton(action2);
            c2.setToolTipText("Calculator");
            c2.setOpaque(false);
            c2.setContentAreaFilled(false);
            c2.setBorderPainted(false);
            c2.setMaximumSize(new Dimension(100, 100));
            icon =new ImageIcon(os.class.getResource("/calc3.png"));
            img = icon.getImage() ;  
            newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
            icon = new ImageIcon( newimg );
            c2.setIcon(icon);
            c2.setText(null);
            c2.setMargin(new Insets(0, 10, 1, 1));
            toolBar.add(c2);
            toolBar.addSeparator(new Dimension(5,100));
            Action action3 = new AbstractAction("Terminal") {
                public void actionPerformed(ActionEvent evt){
                	Terminal obj=new Terminal();
                    JInternalFrame frame = obj.return_frame();
                   
                    frame.setVisible(true);
                    scdl.q.add(frame);
                    //desktop.add(frame);
                    try {
                        frame.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {}
                }
              };
              JButton c3 = new JButton(action3);
              c3.setToolTipText("Terminal");
              c3.setOpaque(false);
              c3.setContentAreaFilled(false);
              c3.setBorderPainted(false);
              c3.setMaximumSize(new Dimension(100, 100));
             icon =new ImageIcon(os.class.getResource("/terminal.png"));
              img = icon.getImage() ;  
              newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
              icon = new ImageIcon( newimg );
              c3.setIcon(icon);
              c3.setText(null);
              c3.setMargin(new Insets(0, 10, 0, 0));
              toolBar.add(c3);
              toolBar.addSeparator(new Dimension(5,100));
              //toolBar.add(new JSeparator(20));
             
              Action action4 = new AbstractAction("File Explorer") {
                  public void actionPerformed(ActionEvent evt){
                	  Explorer obj = new Explorer(desktop);
                      JInternalFrame frame = obj.return_frame();
                      frame.setVisible(true);
                      scdl.q.add(frame);
                      //desktop.add(frame);
                     try {
                         frame.setSelected(true);
                      } catch (java.beans.PropertyVetoException e) {}
                  }
                };
                JButton c4 = new JButton(action4);
                c4.setToolTipText("File Explorer");
                c4.setOpaque(false);
                c4.setContentAreaFilled(false);
                c4.setBorderPainted(false);
                c4.setMaximumSize(new Dimension(100, 100));
                
                icon =new ImageIcon(os.class.getResource("/explorer.png"));
                img = icon.getImage() ;  
                newimg = img.getScaledInstance( 90, 90,  java.awt.Image.SCALE_SMOOTH ) ;  
                icon = new ImageIcon( newimg );
                c4.setIcon(icon);
                c4.setText(null);
                c4.setMargin(new Insets(0, 10, 0, 0));
                toolBar.add(c4);
                toolBar.addSeparator(new Dimension(5,100));
                Action action5 = new AbstractAction("CPU Scheduling") {
                    public void actionPerformed(ActionEvent evt){
                  	  cpu_gui obj = new cpu_gui();
                        JInternalFrame frame = obj.return_frame();
                        frame.setVisible(true);
                        scdl.q.add(frame);
                        //desktop.add(frame);
                       try {
                           frame.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {}
                    }
                  };
                  JButton c5 = new JButton(action5);
                  c5.setToolTipText("CPU Scheduling");
                  c5.setOpaque(false);
                  c5.setContentAreaFilled(false);
                  c5.setBorderPainted(false);
                  c5.setMaximumSize(new Dimension(100, 100));
                  icon =new ImageIcon(os.class.getResource("/activity.png"));
                  img = icon.getImage() ;  
                  newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
                  icon = new ImageIcon( newimg );
                  c5.setIcon(icon);
                  c5.setText(null);
                  c5.setMargin(new Insets(0, 10, 0, 0));
                  toolBar.add(c5);
                toolBar.addSeparator();
        desktop.add(toolBar);
        
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Shut Down");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Quit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        JMenuItem menuItem1 = new JMenuItem("Time");
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                menuItem1.setText(dateFormat.format(now.getTime()));
            }
        }).start();
        menuBar.add(menuItem1);
        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        if ("quit".equals(e.getActionCommand())) {
        	System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame.setDefaultLookAndFeelDecorated(true);
                os frame = new os();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}