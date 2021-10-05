/** JavaHardwareMonitor
* The main class and gui for the final project
* @author Jonathan Wei and Allen Loukiantchenko
* @version Date: June 10th, 2021
*/
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.File;
import java.lang.management.*;
import javax.swing.JComponent;
import java.awt.Graphics;

public class JavaHardwareMonitor extends JFrame {

	// Declare static variables
	static JavaHardwareMonitor frame;
	JPanel contentPane;
	static JLabel lblcpuUsage;
	static JProgressBar systemRAMBar;
	static JProgressBar jvmRAMBar;
	static JLabel systemRAMLabel;
	static JLabel jvmRAMLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JavaHardwareMonitor();
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
	public JavaHardwareMonitor() {
		// Set properties of content pane
		setResizable(false);
		setTitle("Java Hardware Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// JPanel for the cpu information
		JPanel cpuPanel = new JPanel();
		cpuPanel.setBackground(new Color(224, 255, 255));
		cpuPanel.setBounds(0, 0, 330, 330);
		contentPane.add(cpuPanel);
		cpuPanel.setLayout(null);
		
		// Adding the cpu image
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(JavaHardwareMonitor.class.getResource("/FinalProjectTest/Untitled (5).png")));
		lblNewLabel.setBounds(15, 5, 310, 277);
		cpuPanel.add(lblNewLabel);
		
		// Label for cpu usage percentage
		lblcpuUsage = new JLabel("0%");
		lblcpuUsage.setForeground(new Color(0, 128, 0));
		lblcpuUsage.setFont(new Font("Arial Black", Font.BOLD, 26));
		lblcpuUsage.setHorizontalAlignment(SwingConstants.CENTER);
		lblcpuUsage.setBounds(123, 120, 84, 36);
		cpuPanel.add(lblcpuUsage);
		
		// CPU usage label
		JLabel lblNewLabel_1 = new JLabel("CPU USAGE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(0, 100, 0));
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(60, 271, 206, 43);
		cpuPanel.add(lblNewLabel_1);
		
		// JPanel for disk usage
		JPanel diskPanel = new JPanel();
		diskPanel.setBackground(new Color(255, 235, 205));
		diskPanel.setBounds(0, 330, 330, 241);
		contentPane.add(diskPanel);
		diskPanel.setLayout(null);
		
		// Adding the hard drive image
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(JavaHardwareMonitor.class.getResource("/FinalProjectTest/disk.png")));
		lblNewLabel_2.setBounds(27, 21, 320, 198);
		diskPanel.add(lblNewLabel_2);
		
		// Create title for disk usage
		JLabel lblNewLabel_3 = new JLabel("DISK USAGE");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_3.setForeground(new Color(153, 50, 204));
		lblNewLabel_3.setBounds(54, 172, 234, 29);
		diskPanel.add(lblNewLabel_3);
		
		// Create ChangeGUI class in order to enable switching between applications
		final ChangeGUI change = new ChangeGUI();
		
		// Button for disk usage
		JButton diskButton = new JButton("");
		diskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the disk usage expansion button is pressed, the application will switch over to the disk usage program
				change.changeToDiskUsage(frame);
			}
		});
		diskButton.setIcon(new ImageIcon(JavaHardwareMonitor.class.getResource("/FinalProjectTest/newexpand.png")));
		diskButton.setBounds(280, 11, 40, 40);
		diskPanel.add(diskButton);
		diskButton.setBackground(Color.LIGHT_GRAY);
		
		// JPanel for system memory
		JPanel systemMemPanel = new JPanel();
		systemMemPanel.setBackground(new Color(135, 206, 250));
		systemMemPanel.setBounds(330, 0, 329, 210);
		contentPane.add(systemMemPanel);
		systemMemPanel.setLayout(null);
		
		// Progress bar to display percentage of system memory used
		systemRAMBar = new JProgressBar();
		systemRAMBar.setForeground(new Color(0, 0, 0));
		systemRAMBar.setBackground(new Color(255, 255, 255));
		systemRAMBar.setFont(new Font("Arial", Font.BOLD, 20));
		systemRAMBar.setStringPainted(true);
		systemRAMBar.setBounds(15, 75, 300, 50);
		systemMemPanel.add(systemRAMBar);
		
		// System memory label
		JLabel lblNewLabel_4 = new JLabel("SYSTEM MEMORY");
		lblNewLabel_4.setForeground(new Color(255, 255, 0));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblNewLabel_4.setBounds(15, 16, 300, 46);
		systemMemPanel.add(lblNewLabel_4);
		
		// Label to display gigabytes used and gigabytes total of system memory
		systemRAMLabel = new JLabel("");
		systemRAMLabel.setForeground(new Color(255, 255, 0));
		systemRAMLabel.setHorizontalAlignment(SwingConstants.CENTER);
		systemRAMLabel.setFont(new Font("Arial Black", Font.BOLD, 25));
		systemRAMLabel.setBounds(15, 144, 300, 36);
		systemMemPanel.add(systemRAMLabel);
		
		// JPanel for jvm memory usage
		JPanel jvmMemPanel = new JPanel();
		jvmMemPanel.setBackground(new Color(255, 192, 203));
		jvmMemPanel.setBounds(330, 210, 329, 205);
		contentPane.add(jvmMemPanel);
		jvmMemPanel.setLayout(null);
		
		// Progress bar to display percentage of total jvm memory used
		jvmRAMBar = new JProgressBar();
		jvmRAMBar.setBackground(new Color(255, 255, 255));
		jvmRAMBar.setForeground(new Color(0, 0, 0));
		jvmRAMBar.setStringPainted(true);
		jvmRAMBar.setFont(new Font("Arial", Font.BOLD, 20));
		jvmRAMBar.setBounds(15, 75, 300, 50);
		jvmMemPanel.add(jvmRAMBar);
		
		// jvm memory label
		JLabel lblNewLabel_4_1 = new JLabel("JVM MEMORY");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_4_1.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblNewLabel_4_1.setBounds(15, 16, 300, 46);
		jvmMemPanel.add(lblNewLabel_4_1);
		
		//Label to display gigabytes used and gigabytes total of jvm memory
		jvmRAMLabel = new JLabel("");
		jvmRAMLabel.setForeground(new Color(30, 144, 255));
		jvmRAMLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jvmRAMLabel.setFont(new Font("Arial Black", Font.BOLD, 25));
		jvmRAMLabel.setBounds(15, 146, 300, 36);
		jvmMemPanel.add(jvmRAMLabel);
		
		// JPanel for system information section
		JPanel systemPanel = new JPanel();
		systemPanel.setBounds(330, 415, 329, 156);
		contentPane.add(systemPanel);
		systemPanel.setLayout(null);
		
		// Label to display the word system
		JLabel lblNewLabel_5 = new JLabel("SYSTEM");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_5.setBounds(0, 32, 329, 43);
		systemPanel.add(lblNewLabel_5);
		
		// Label to display the word information
		JLabel lblNewLabel_5_1 = new JLabel("INFORMATION");
		lblNewLabel_5_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_5_1.setBounds(0, 74, 329, 43);
		systemPanel.add(lblNewLabel_5_1);
		
		// Button for system information
		JButton systemButton = new JButton("");
		systemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change.changeToSystemInfo(frame);
			}
		});
		systemButton.setIcon(new ImageIcon(JavaHardwareMonitor.class.getResource("/FinalProjectTest/newexpand.png")));
		systemButton.setBackground(Color.LIGHT_GRAY);
		systemButton.setBounds(279, 11, 40, 40);
		systemPanel.add(systemButton);
		
		// Create UpdateVariables class, and call the start method
		// This class will enable a timer that will update the values of the hardware monitor every 100 milliseconds
		UpdateVariables update = new UpdateVariables();
		update.start();
	}
	
	/**
	 * UpdateVariables
	 * This class will allow the hardware monitor variables such as cpu usage to be constantly updated
	 */
	public class UpdateVariables {
		// Create variables that will be used
		OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		double systemCPULoad;
		Timer timer = new Timer();
		
		// Create timer task, this will be called every 100 milliseconds
		TimerTask task = new TimerTask() {
			public void run() {
				// Get system cpu load and update gui components
				systemCPULoad = bean.getSystemCpuLoad()*100;
				String text = Integer.toString((int)systemCPULoad);
				lblcpuUsage.setText(text + "%");
				
				// Get used system memory and update gui components
				double usedMemory = bean.getTotalMemorySize() - bean.getFreeMemorySize();
				int percentage = (int) ((usedMemory/(double) bean.getTotalMemorySize())*100);
				systemRAMBar.setValue(percentage);
				String format = String.format("%.1f/%.1f GB", (double)usedMemory/1073741824, (double)bean.getTotalMemorySize()/1073741824);
				systemRAMLabel.setText(format);
				
				// Get used jvm memory and update gui components
				MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
				usedMemory = (double)memoryMXBean.getHeapMemoryUsage().getUsed()/1073741824;
				percentage = (int)(usedMemory/(double)memoryMXBean.getHeapMemoryUsage().getMax()/1073741824)*100;
				jvmRAMBar.setValue(percentage);
				format = String.format("%.2f/%.2f GB", usedMemory, (double)memoryMXBean.getHeapMemoryUsage().getMax()/1073741824);
				jvmRAMLabel.setText(format);
			}
		};
		
		/**
		 * start
		 * This method will enable the timer that will call the timer task every 100 milliseconds
		 */
		public void start() {
			timer.scheduleAtFixedRate(task, 0, 100);
		}
	}
}