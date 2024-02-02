import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class GUI implements ActionListener{
	
	// private vars
	private JFrame frame; // frame to hold contents
	private JPanel panel; // contents
	private JButton buttonF; // final button
	private JButton buttonI; // initial button
	private JButton buttonW; // word button
	private JLabel labelI;  // input button
	private JLabel labelW; // word button
	
	private JTextArea aGrid;
	
	private int words; // size of array
	private String[] list; // array
	private String[][] grid; // grid for word hunt
	
	private ImageIcon imageOne;
	
	private WordHunt w1;
	
	public GUI()
	{
		// init private vars
		frame = new JFrame();
		panel = new JPanel();
		buttonF = new JButton("Make .txt file");
		imageOne = new ImageIcon("word hunt.jpg");
		
		labelI = new JLabel("Welcome to the Word Hunt generator, please enter the amount of inputs from 0 to 10.");
		labelW = new JLabel("Enter the words");
		buttonI = new JButton("Click here to enter a value");
		buttonW = new JButton("Click here to enter your words");
		buttonF = new JButton("Click here to create a file of the word hunt");
		
		aGrid = new JTextArea(2,500);

		// frame
		frame.setTitle("Word Hunt"); // sets title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends GUI when hitting exit
		frame.pack(); // sets the window to match a certain aspect ratio
		frame.setVisible(true); // makes the GUI visible
		frame.setSize(1600, 900); // sets the resolution
		frame.setIconImage(imageOne.getImage()); // sets the icon
		
		// button
		buttonI.setPreferredSize(new Dimension(200,200));
		buttonI.setFocusable(false);
		buttonI.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		buttonW.setFocusable(false);
		buttonW.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		buttonF.setFocusable(false);
		buttonF.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// label
		labelI.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelW.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// text area
		aGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
		aGrid.setPreferredSize(new Dimension(100,100));
		JScrollPane scrollPane = new JScrollPane(aGrid);
		// panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(labelI);
		panel.add(buttonI);
		panel.add(labelW);
		panel.add(buttonW);
		// panel.add(aGrid);
		panel.add(buttonF);
		panel.add(aGrid);
		
		frame.add(panel);
		frame.setLocationRelativeTo(null); // centers the GUI

		buttonI.addActionListener(this);
		buttonW.addActionListener(this);
		buttonF.addActionListener(this);
	}
	
	public void createGrid()
	{
		w1 = new WordHunt(list);
		w1.setUp();
		grid = w1.getGrid();
		
		for(String[] s1: grid)
		{
			for(String s2: s1)
				{
					aGrid.append(s2);
				}
			aGrid.append("\n");
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonI) // if the button is clicked - 
		{
			String count = JOptionPane.showInputDialog(null, "Enter a number please.");
			words = Integer.valueOf(count);
			list = new String[words];
		}
		
		if(e.getSource() == buttonW) // if the button is clicked - 
		{
			for(int i = 0; i < words; i++)
			{
				String temp = JOptionPane.showInputDialog(null, "Enter a word please.");
				list[i] = temp;
			}
			createGrid();
		}
		
		if(e.getSource() == buttonF) // if the button is clicked - 
		{
			try {
				w1.printFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI();
	}
}
