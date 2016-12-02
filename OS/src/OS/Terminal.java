package OS;

import java.awt.Color;
import java.awt.EventQueue;
import OS.MyInternalFrame;

import javax.swing.border.EmptyBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.*;

@SuppressWarnings("serial")
public class Terminal extends JFrame {

	private JInternalFrame contentPane;
	private JTextField textField;
	JEditorPane editorPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terminal frame = new Terminal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Terminal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new MyInternalFrame();
		contentPane.setVisible(true);
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.getContentPane().setLayout(null);
		
		//String w;
		
		
				
		editorPane = new JEditorPane();
		editorPane.setForeground(SystemColor.textText);
		editorPane.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 19));
		editorPane.setEnabled(false);
		editorPane.setBounds(0, 0, 450, 240);
		editorPane.setBackground(UIManager.getColor("CheckBox.select"));
		//final JScrollPane scrollPane = new JScrollPane(editorPane);
        //contentPane.getContentPane().add(scrollPane);
		contentPane.getContentPane().add(editorPane);
		
		/*textField_1 = new JTextField();
		textField_1.setBounds(0, 243, 450, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);*/
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textField.setBounds(0, 241, 450, 31);
		contentPane.getContentPane().add(textField);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println(e);
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{System.out.println(e);
					String []w = ("cmd /c"+textField.getText()).split(" ");
					textField.setText("");
					editorPane.setText("abc");
					try {
						final Process p=Runtime.getRuntime().exec(w);
						new Thread (new Runnable(){
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
								String line;//null;
								try {
									String q = new String("\n");
									while((line=input.readLine())!=null)
									{
										q = q + line + "\n";
										editorPane.setText(q);
										System.out.println(line);
									}
									//System.out.println(line);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}).start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		textField.setForeground(SystemColor.textText);
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setColumns(10);
		
	}
	public JInternalFrame return_frame()
	{
		return contentPane;
	}
}
