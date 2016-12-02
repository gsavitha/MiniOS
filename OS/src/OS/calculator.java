package OS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.TitledBorder;

public class calculator extends JFrame 
{

	private JInternalFrame contentPane;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					calculator frame = new calculator();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	int flag = -1;
	int ans = 0;
	int opr = 0;
	String num1 = new String("0");
	String num2 = new String("0");
	String exp = new String("0");
	int flagd = 0;

	/**
	 * Create the frame.
	 */
	public calculator()
	{

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new MyInternalFrame();
		contentPane.setVisible(true);
		contentPane.setTitle("Calculator");
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, -18, 462, 73);
		contentPane.add(panel);
		panel.setLayout(null);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(6, 18, 450, 49);
		panel.add(editorPane);
		editorPane.setForeground(UIManager.getColor("Button.darkShadow"));
		editorPane.setFont(new Font("Trattatello", Font.PLAIN, 40));
		editorPane.setEnabled(false);
		editorPane.setEditable(false);
		editorPane.setBackground(UIManager.getColor("CheckBox.select"));
		
		//editorPane.setText(exp);

		JButton button_1 = new JButton("1");
		button_1.setBounds(26, 69, 49, 43);
		//button_1.setBackground(SystemColor.desktop);
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "1";
					Integer.toString(Integer.parseInt(num1));
					exp = exp + "1";
				}

				else
				{
					num2 = num2 + "1";
					exp = exp + "1";
				}
				
				//System.out.println(exp);
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_1);

		JButton button_2 = new JButton("2");
		button_2.setBounds(77, 69, 49, 43);
		button_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "2";
					exp = exp + "2";
				}

				else
				{
					num2 = num2 + "2";
					exp = exp + "2";
				}
				
				//System.out.println(exp);
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_2);

		JButton button_3 = new JButton("3");
		button_3.setBounds(126, 69, 49, 43);
		button_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "3";
					exp = exp + "3";				}

				else
				{
					num2 = num2 + "3";
					exp = exp + "3";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_3);

		JButton button_4 = new JButton("4");
		button_4.setBounds(26, 124, 49, 43);
		//button_4.setBackground(SystemColor.desktop);
		button_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "4";
					exp = exp + "4";
				}

				else
				{
					num2 = num2 + "4";
					exp = exp + "4";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_4);

		JButton button_5 = new JButton("5");
		button_5.setBounds(77, 124, 49, 43);
		button_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "5";
					exp = exp + "5";
				}

				else
				{
					num2 = num2 + "5";
					exp = exp + "5";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_5);

		JButton button_6 = new JButton("6");
		button_6.setBounds(126, 124, 49, 43);
		button_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(flag==-1)
				{
					num1 = num1 + "6";
					exp = exp + "6";
				}

				else
				{
					num2 = num2 + "6";
					exp = exp + "6";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_6);

		JButton button_7 = new JButton("7");
		button_7.setBounds(26, 179, 49, 43);
		button_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "7";
					exp = exp + "7";
				}

				else
				{
					num2 = num2 + "7";
					exp = exp + "7";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_7);

		JButton button_8 = new JButton("8");
		button_8.setBounds(77, 179, 49, 43);
		button_8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(flag==-1)
				{
					num1 = num1 + "8";
					exp = exp + "8";
				}

				else
				{
					num2 = num2 + "8";
					exp = exp + "8";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_8);

		JButton button_9 = new JButton("9");
		button_9.setBounds(126, 179, 49, 43);
		button_9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(flag==-1)
				{
					num1 = num1 + "9";
					exp = exp + "9";
				}

				else
				{
					num2 = num2 + "9";
					exp = exp + "9";				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_9);

		JButton button_0 = new JButton("0");
		button_0.setBounds(77, 229, 49, 43);
		button_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(flag==-1)
				{
					num1 = num1 + "0";
					exp = exp + "0";
				}

				else
				{
					num2 = num2 + "0";
					exp = exp + "0";
				}
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(button_0);

		JButton add = new JButton("+");
		add.setBounds(243, 124, 57, 42);
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opr = -10;
				exp = exp + " + ";
				flag=0;
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(add);

		JButton sub = new JButton("-");
		sub.setBounds(243, 179, 57, 42);
		sub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				opr = -20;
				exp = exp + " - ";
				flag=0;
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(sub);

		JButton mul = new JButton("*");
		mul.setBounds(344, 179, 57, 42);
		mul.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opr = -30;
				exp = exp + " * ";
				flag=0;
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(mul);

		JButton div = new JButton("/");
		div.setBounds(344, 124, 57, 42);
		div.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opr = -40;
				exp = exp + " / ";
				flag=0;
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(div);

		JButton clear = new JButton("Clear");
		clear.setBounds(226, 70, 88, 40);
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				num1="0";
				num2="0";
				ans=0;
				opr=0;
				exp = "0";
				flag = -1;
				
				editorPane.setText(exp);
			}
		});
		contentPane.add(clear);

		JButton answer = new JButton("Answer");
		answer.setBounds(326, 70, 88, 40);
		answer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println(exp);
				//System.out.println(num1);
				//System.out.println(num2);
				//System.out.println(opr);
				
				if(opr==-10)
				{
					ans = (Integer.parseInt(num1) + Integer.parseInt(num2));
				}
				
				if(opr==-20)
				{
					ans = (Integer.parseInt(num1) - Integer.parseInt(num2));
				}
				
				if(opr==-30)
				{
					ans = (Integer.parseInt(num1) * Integer.parseInt(num2));
				}
				
				if(opr==-40)
				{
					if(Integer.parseInt(num2)!=0)
						ans = (Integer.parseInt(num1) / Integer.parseInt(num2));
					
					else
					{
						flagd = -1;
						ans = 0;
					}
				}

				//System.out.println(ans);
				exp = num1 = Integer.toString(ans);
				num2 = "0";
				
				if(flagd==0)
				{
					editorPane.setText(Integer.toString(ans));
				}
				
				else
				{
					editorPane.setText("Error");
					flagd = 0;
				}
			}
		});
		contentPane.add(answer);
	}
	public JInternalFrame return_frame()
	{
		return contentPane;
	}
	
}
