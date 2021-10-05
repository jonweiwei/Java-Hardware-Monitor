/** DiskUsage
* A program that is part of the java hardware monitor, enables user to scan disk usages
* @author Jonathan Wei and Allen Loukiantchenko
* @version Date: June 10th, 2021
*/
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DiskUsage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiskUsage frame = new DiskUsage();
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
	public DiskUsage() {
		// Set properties of content pane
		setTitle("Disk Usage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create ChangeGUI class in order to enable switching between applications
		final ChangeGUI change = new ChangeGUI();
		
		// Button that is used to go back to the main hardware monitor application
		JButton backButton = new JButton("Go Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When pressed, it will call a method that will change the gui window
				change.backFromDiskUsage();
			}
		});
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(456, 496, 173, 39);
		contentPane.add(backButton);
		
		// Progress bar that is used to show the percentage of disk space used
		final JProgressBar diskUsageBar = new JProgressBar();
		diskUsageBar.setStringPainted(true);
		diskUsageBar.setForeground(Color.BLACK);
		diskUsageBar.setFont(new Font("Arial", Font.BOLD, 20));
		diskUsageBar.setBackground(Color.WHITE);
		diskUsageBar.setBounds(20, 410, 300, 35);
		contentPane.add(diskUsageBar);
		
		// Title displayed at top of window
		JLabel titleLabel = new JLabel("Disk Usage");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBounds(20, 10, 285, 50);
		contentPane.add(titleLabel);
		
		// Label that prompts the user to enter in a drive
		JLabel diskLabel = new JLabel("Drive (C, D...):");
		diskLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		diskLabel.setBounds(20, 67, 451, 24);
		contentPane.add(diskLabel);
		
		// Text field that enables the user to enter in which drive they would like to be scanned
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(20, 100, 200, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Button that the user will press to get information about drive entered in the above text field
		JButton scanButton = new JButton("Scan Drive");
		scanButton.setFont(new Font("Arial", Font.PLAIN, 20));
		scanButton.setBackground(Color.LIGHT_GRAY);
		scanButton.setBounds(20, 150, 173, 39);
		contentPane.add(scanButton);
		
		// A label that will display the drive entered by the user
		final JLabel driveLabel = new JLabel("Drive:");
		driveLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		driveLabel.setBounds(20, 243, 451, 24);
		contentPane.add(driveLabel);
		
		// A label that will display the total space of the drive
		final JLabel totalSpaceLabel = new JLabel("Total Space:");
		totalSpaceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		totalSpaceLabel.setBounds(20, 273, 451, 24);
		contentPane.add(totalSpaceLabel);
		
		// A label that will display the free space of the drive
		final JLabel freeSpaceLabel = new JLabel("Free Space:");
		freeSpaceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		freeSpaceLabel.setBounds(20, 333, 451, 24);
		contentPane.add(freeSpaceLabel);
		
		// A label that will display the usable space of the drive
		final JLabel usableSpaceLabel = new JLabel("Usable Space:");
		usableSpaceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		usableSpaceLabel.setBounds(20, 363, 451, 24);
		contentPane.add(usableSpaceLabel);
		
		// A label that will display the free space of the drive and the total space of the drive
		final JLabel captionLabel = new JLabel("0.00 GB free of 0.00 GB");
		captionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		captionLabel.setBounds(20, 447, 451, 24);
		contentPane.add(captionLabel);
		
		// A label that will display the used space of the drive
		final JLabel usedSpaceLabel = new JLabel("Used Space:");
		usedSpaceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		usedSpaceLabel.setBounds(20, 303, 451, 24);
		contentPane.add(usedSpaceLabel);
		
		// Adding an action listener to the scan drive button
		scanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When pressed, the program will update the values and JLabels to match the corresponding scanned drive
				String s = textField.getText();
				driveLabel.setText("Drive: " + s.toUpperCase());
				// Create a File object that represents the drive entered by the user
				File drive = new File(s + ":");
				// Getting and setting values for JLabels
				totalSpaceLabel.setText(String.format("Total Space: %.2f GB", (double)drive.getTotalSpace()/1073741824));
				usedSpaceLabel.setText(String.format("Used Space: %.2f GB", (double)drive.getTotalSpace()/1073741824 - (double)drive.getFreeSpace()/1073741824));
				freeSpaceLabel.setText(String.format("Free Space: %.2f GB", (double)drive.getFreeSpace()/1073741824));
				usableSpaceLabel.setText(String.format("Usable Space: %.2f GB", (double)drive.getUsableSpace()/1073741824));
				double usedSpace = (double)drive.getTotalSpace()/1073741824 - (double)drive.getFreeSpace()/1073741824;
				double percentage = usedSpace/((double)drive.getTotalSpace()/1073741824);
				diskUsageBar.setValue((int)(percentage*100));
				captionLabel.setText(String.format("%.2f GB free of %.2f GB" , (double)drive.getFreeSpace()/1073741824, (double)drive.getTotalSpace()/1073741824));
			}
		});
	}
}