package OS;
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import OS.cpu_scheduling;
import OS.MyInternalFrame;
import javax.swing.JInternalFrame;
/**
 * round_robin(a,n);
		priority_preemptive(a,n);
		sjf_preemptive(a,n);
		PriorityNonPreemptive(a,n);
		FCFS(a,n);
		SjfNonPreemptive(a,n)
 *
 */


public class cpu_gui {

	private JInternalFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	JLabel lblNewLabel;
	
	 static int[][] a;
     static int n;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cpu_gui window = new cpu_gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cpu_gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		cpu_scheduling algo= new cpu_scheduling();
		frame = new MyInternalFrame();
		frame.setVisible(true);
		frame.setBounds(600,600,600,600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 63, 397, 184);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PID", "Arrival Time", "Burst Time","Priority"
			}
		));
		
		JButton btnRandom = new JButton("Generate Random Input");
		btnRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

		((DefaultTableModel)table.getModel()).setRowCount(0);
		
				Object[] row = new Object[4];
				Random r=new Random();
				n=r.nextInt(10)+1;
				//System.out.println( "Number of processes: "+n+"\n");
				Random rand=new Random();
				a = new int[n][4];

				//process_id,arrival time,burst time,priority
				for (int j=0;j<n;j++)
				{
					a[j][0]=j+1;
				    a[j][1] = rand.nextInt(10);
				    a[j][2] = rand.nextInt(10)+1;
				    a[j][3] = j+1;//rand.nextInt(10)+1;
				}
				a[0][1]=0;
				Arrays.sort(a,new Comparator<int[]>(){
					//@Override
					public int compare(int[] a,int[] b) {
						return Integer.compare(a[1],b[1]);
					}});
				for ( int j =0; j<n;++j)
				{
					row[0] = a[j][0];
				    row[1] = a[j][1];
				    row[2] = a[j][2];
				    row[3] = a[j][3];
				    ((DefaultTableModel)table.getModel()).addRow(row);
				}
			}
		});
		btnRandom.setBounds(140, 12, 329, 25);
		frame.getContentPane().add(btnRandom);
		
		/****/JButton btnFCFS = new JButton("FCFS");
		btnFCFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String s = new String();
			s = algo.FCFS(a,n);
			JOptionPane.showMessageDialog(null,s);
			}
			});
		btnFCFS.setBounds(31, 285, 178, 25);
		frame.getContentPane().add(btnFCFS);
		
		
		/****/JButton btnSJF1 = new JButton("SJF(Preemp)");
		btnSJF1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String s = algo.sjf_preemptive(a,n);
			JOptionPane.showMessageDialog(null,s);
			}
			});
		
		btnSJF1.setBounds(221, 285, 168, 25);
		frame.getContentPane().add(btnSJF1);
		
		/****/JButton btnSJF2 = new JButton("SJF(non-preemp)");
		btnSJF2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = algo.SjfNonPreemptive(a,n);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		
		btnSJF2.setBounds(401, 285, 163, 25);
		frame.getContentPane().add(btnSJF2);
		
		/****/JButton btnPriorityPre = new JButton("Prior(preemp)");
		btnPriorityPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
 {
				String s = algo.priority_preemptive(a,n);
				JOptionPane.showMessageDialog(null,s);
			}
		});
		btnPriorityPre.setBounds(31, 344, 178, 25);
		frame.getContentPane().add(btnPriorityPre);		
		
		/****/JButton btnPriorNonPre = 	new JButton("Prior(non-preemp)");
		btnPriorNonPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = algo.SjfNonPreemptive(a,n);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnPriorNonPre.setBounds(221, 344, 168, 25);
		frame.getContentPane().add(btnPriorNonPre);
		
		JButton btnCompare = new JButton("Compare all scheduling algos");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] awt = algo.compareValues(a,n);
				int i = (int) (awt[6]);
				String s ="";
				String [] names = {"round robin","priority preemptive","sjf preemptive","SjfNonPreemptive","Priority Non Preemptive","FCFS"};
			for (int j=0;j<6;++j)
			{
				s += (names[j]+": \t"+Double.toString(awt[j])+"\n");
			}
			s += "\n\n BEST SCHEDULING ALGORITHM: \t"+names[i]+"\n";
			JOptionPane.showMessageDialog(null, s);
			
			}
		});
		btnCompare.setBounds(31, 399, 533, 25);
		frame.getContentPane().add(btnCompare);
		
		lblNewLabel = new JLabel(" ");
		lblNewLabel.setBounds(41, 436, 522, 124);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		/****/JButton btnRR = new JButton("RR");
		btnRR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String s = algo.round_robin(a,n);
			JOptionPane.showMessageDialog(null,s);
				
				
			}
		});
		btnRR.setBounds(401, 344, 163, 25);
		frame.getContentPane().add(btnRR);
		
		
		

	}
	public JScrollPane table() {
		return scrollPane;
	}
	public JInternalFrame return_frame()
	{
		return frame;
	}
}