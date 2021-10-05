/** ChangeGUI
* A program that allows switching between applications of the java hardware monitor
* @author Jonathan Wei and Allen Loukiantchenko
* @version Date: June 10th, 2021
*/
public class ChangeGUI {
	// Declare static objects that will hold references to the different applications
	static JavaHardwareMonitor frame1;
	static SystemInformation frame2;
	static DiskUsage frame3;
	
	/**
	 * changeToSystemInfo
	 * Changes the application from the main hardware monitor to the system information program
	 * @param frame the frame to dispose of
	 */
	public void changeToSystemInfo(JavaHardwareMonitor frame) {
		// Will dispose of the proper javahardwaremonitor object, then create a new systeminformation object and display it
		if(frame1 != null) {
			frame1.dispose();
		}
		if(frame != null) {
			frame.dispose();
		}
		frame2 = new SystemInformation();
		frame2.setVisible(true);
	}
	
	/**
	 * backFromSystemInfo
	 * Disposes of the systeminformation window and switches back to the main hardwaremonitor program
	 */
	public void backFromSystemInfo() {
		frame2.dispose();
		frame1 = new JavaHardwareMonitor();
		frame1.setVisible(true);
	}
	
	/**
	 * changeToDiskUsage
	 * Changes the application from the main hardware monitor to the disk usage program
	 * @param frame the frame to dispose of
	 */
	public void changeToDiskUsage(JavaHardwareMonitor frame) {
		// Will dispose of the proper javahardwaremonitor object, then create a new diskusage object and display it
		if(frame1 != null) {
			frame1.dispose();
		}
		if(frame != null) {
			frame.dispose();
		}
		frame3 = new DiskUsage();
		frame3.setVisible(true);
	}
	
	/**
	 * backFromDiskUsage
	 * Disposes of the diskusage window and switches back to the main hardwaremonitor program
	 */
	public void backFromDiskUsage() {
		frame3.dispose();
		frame1 = new JavaHardwareMonitor();
		frame1.setVisible(true);
	}
}