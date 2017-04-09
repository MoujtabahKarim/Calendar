import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KarimCalendar {

	Scanner input = new Scanner(System.in);
	Calendar cal = Calendar.getInstance();

	private JFrame frame;
	private JTextField currentYear;
	private static JTextField currentMonth;

	private static int Year;
	private static long numOfTabs = 0;
	private static int monthNumber = 1;
	private static String Month;
	private static boolean validInput = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KarimCalendar window = new KarimCalendar();
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
	public KarimCalendar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 121);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 434, 0);
		frame.getContentPane().add(label);

		JLabel lblThinkAbstract = new JLabel("THINK ABSTRACT");
		lblThinkAbstract.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblThinkAbstract.setBounds(141, 11, 204, 22);
		frame.getContentPane().add(lblThinkAbstract);

		JButton btnStart = new JButton("Click Me");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					Year = Integer.parseInt(currentYear.getText());
					Month = currentMonth.getText().toLowerCase();

				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Please enter a valid year ");

				}

				setMonth();

				Month = Month.toUpperCase();
				if (Year < 1583 || Year > 3000) {
					JOptionPane.showMessageDialog(null, "Please enter a valid year between 1583 - 2999");
					Year = 0;

				}

				printCalendar();

			}

			public void printCalendar() {
				clearScreen();

				String weekdays[] = { "Sun", "Mon", "Tue", "Wed", "Thurs", "Fri", "Sat" };

				System.out.println(Month + "\t\t\t\t" + Year);
				System.out.println();

				// Loop that prints weekdays
				for (int x = 0; x < weekdays.length; x++) {

					System.out.print(weekdays[x] + "\t");
				}

				System.out.println();
				printNumOfTabs();

				// Loop that prints the days
				for (int x = 1; x <= getAmountOfDays(); x++) {

					System.out.print(x + "\t");

					if ((x + numOfTabs) % 7 == 0) {
						System.out.println();
					}

				}

			}

			// Method used to print the number of tabs
			public void printNumOfTabs() {

				YearMonth ym = YearMonth.of(Year, monthNumber);

				String firstDay = ym.atDay(1).getDayOfWeek().name();

				for (int x = 0; x < setTabAmount(firstDay); x++) {
					System.out.print("\t");
				}

			}

			public int setTabAmount(String firstDay) {
				if (firstDay.charAt(2) == 'N') {
					numOfTabs = 0;
				}
				if (firstDay.charAt(0) == 'M') {
					numOfTabs = 1;
				}
				if (firstDay.charAt(2) == 'E') {
					numOfTabs = 2;
				}
				if (firstDay.charAt(2) == 'D') {
					numOfTabs = 3;
				}
				if (firstDay.charAt(2) == 'R') {
					numOfTabs = 4;
				}
				if (firstDay.charAt(2) == 'I') {
					numOfTabs = 5;
				}
				if (firstDay.charAt(2) == 'T') {
					numOfTabs = 6;
				}

				return (int) numOfTabs;
			}

			// Method used to determine the amount of days in the month
			public int getAmountOfDays() {

				YearMonth yearMonth = YearMonth.of(Year, monthNumber);
				int amountOfDaysInMonth = yearMonth.lengthOfMonth();
				return amountOfDaysInMonth;
			}

			// Method used to auto-correct and set the correct month
			private void setMonth() {
				if (Month.charAt(0) == 'j' && Month.charAt(1) == 'a') {
					Month = "January";
					monthNumber = 1;
				} else if (Month.charAt(0) == 'f' && Month.charAt(1) == 'e') {
					Month = "Feburary";
					monthNumber = 2;
				} else if (Month.charAt(0) == 'm' && Month.charAt(2) == 'r') {
					Month = "March";
					monthNumber = 3;
				} else if (Month.charAt(0) == 'a' && Month.charAt(1) == 'p') {
					Month = "April";
					monthNumber = 4;
				} else if (Month.charAt(0) == 'm' && Month.charAt(2) == 'y') {
					Month = "May";
					monthNumber = 5;
				} else if (Month.charAt(0) == 'j' && Month.charAt(2) == 'n') {
					Month = "June";
					monthNumber = 6;
				} else if (Month.charAt(0) == 'j' && Month.charAt(2) == 'l') {
					Month = "July";
					monthNumber = 7;
				} else if (Month.charAt(0) == 'a' && Month.charAt(2) == 'g') {
					Month = "August";
					monthNumber = 8;
				} else if (Month.charAt(0) == 's' && Month.charAt(1) == 'e') {
					Month = "September";
					monthNumber = 9;
				} else if (Month.charAt(0) == 'o') {
					Month = "October";
					monthNumber = 10;
				} else if (Month.charAt(0) == 'd') {
					Month = "December";
					monthNumber = 12;
				} else if (Month.charAt(0) == 'n') {
					Month = "November";
					monthNumber = 11;
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid month (THINK ABSTRACT)");
					Month = "INVALID MONTH";

				}

			}

			private void clearScreen() {
				for (int x = 0; x <= 15; x++) {
					System.out.println();
				}
			}

		});
		btnStart.setBounds(172, 44, 89, 23);
		frame.getContentPane().add(btnStart);

		currentYear = new JTextField();
		currentYear.setToolTipText("Enter Year\r\n");
		currentYear.setFont(new Font("Yu Gothic", Font.PLAIN, 11));
		currentYear.setText("Ex. 1990");
		currentYear.setColumns(10);
		currentYear.setBounds(277, 44, 147, 22);
		frame.getContentPane().add(currentYear);

		currentMonth = new JTextField();
		currentMonth.setToolTipText("Enter Month");
		currentMonth.setFont(new Font("Yu Gothic", Font.PLAIN, 11));
		currentMonth.setText("Ex. May");
		currentMonth.setColumns(10);
		currentMonth.setBounds(15, 45, 147, 22);
		frame.getContentPane().add(currentMonth);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(15, 28, 46, 14);
		frame.getContentPane().add(lblMonth);

		JLabel lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setBounds(355, 28, 69, 14);
		frame.getContentPane().add(lblYear);
	}

}
