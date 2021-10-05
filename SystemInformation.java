/** SystemInformation
* A program that is part of the java hardware monitor, enables user to see their system information
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

public class SystemInformation extends JFrame {

	static SystemInformation frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SystemInformation();
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
	public SystemInformation() {
		// Set properties of content pane
		setTitle("System Information");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Title that is displayed at the top of the gui window
		JLabel titleLabel = new JLabel("System Information");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBounds(20, 10, 285, 50);
		contentPane.add(titleLabel);
		
		// Label to display cpu threads
		JLabel cpuLabel = new JLabel("CPU Threads:");
		cpuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		cpuLabel.setBounds(20, 157, 403, 24);
		contentPane.add(cpuLabel);
		
		// Get available processors and set value of cpu threads label
		OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		cpuLabel.setText("CPU Threads: " + Integer.toString(bean.getAvailableProcessors()));
		
		// Label to display total ram of system
		JLabel ramLabel = new JLabel("Total Memory:");
		ramLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		ramLabel.setBounds(20, 187, 403, 24);
		contentPane.add(ramLabel);
		
		// Get total system ram in GB and set the text of the corresponding label
		String temp = String.format("%.2f", (double)bean.getTotalPhysicalMemorySize()/1073741824);
		ramLabel.setText("Total Memory: " + temp + " GB");
		
		// Label to display operating system
		JLabel osLabel = new JLabel("Operating System:");
		osLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		osLabel.setBounds(20, 67, 451, 24);
		contentPane.add(osLabel);
		// Set value of os label
		osLabel.setText("Operating System: " + bean.getName());
		
		// Label to display the version of the operating system
		JLabel versionLabel = new JLabel("OS Version:");
		versionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		versionLabel.setBounds(20, 97, 623, 24);
		contentPane.add(versionLabel);
		// Set value of the label
		versionLabel.setText("OS Version: " + bean.getVersion());
		
		// Label to display the system architecture of the computer
		JLabel archLabel = new JLabel("System Architecture:");
		archLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		archLabel.setBounds(20, 127, 403, 24);
		contentPane.add(archLabel);
		// Set value of system architecture
		archLabel.setText("System Architecture: " + bean.getArch());
		
		// Label to display the committed virtual memory of the system in gb
		JLabel jvmLabel = new JLabel("Committed Virtual Memory:");
		jvmLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		jvmLabel.setBounds(20, 217, 623, 24);
		contentPane.add(jvmLabel);
		// Get the size of the committed virtual memory and set the value of the corresponding label
		temp = String.format("%.2f", (double)bean.getCommittedVirtualMemorySize()/1073741824);
		jvmLabel.setText("Committed Virtual Memory: " + temp + " GB");
		
		// Create ChangeGUI class in order to enable switching between applications
		final ChangeGUI change = new ChangeGUI();
		
		// Button that is used to go back to the main hardware monitor application
		JButton backButton = new JButton("Go Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When pressed, it will call a method that will change the gui window
				change.backFromSystemInfo();
			}
		});
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(456, 496, 173, 39);
		contentPane.add(backButton);
		
		// Label to display total swap space of system
		JLabel swapSpaceLabel = new JLabel("Total Swap Space:");
		swapSpaceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		swapSpaceLabel.setBounds(20, 247, 623, 24);
		contentPane.add(swapSpaceLabel);
		// Get the size of the swap space in gb and set the value of the corresponding label
		temp = String.format("Total Swap Space: %.2f GB", (double)bean.getTotalSwapSpaceSize()/1073741824);
		swapSpaceLabel.setText(temp);
	}
}