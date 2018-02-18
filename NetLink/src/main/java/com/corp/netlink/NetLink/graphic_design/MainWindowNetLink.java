package com.corp.netlink.NetLink.graphic_design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import aima.core.agent.Action;
import aima.core.environment.map.*;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctions;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.UniformCostSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import com.corp.netlink.NetLink.IA.RelationalGraph;
import com.corp.netlink.NetLink.loadOntology.Initialisation;
import com.corp.netlink.NetLink.request.Request;

import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.uninformed.UniformCostSearch;
import aima.core.agent.Action;
import aima.core.environment.map.Map;
import aima.core.environment.map.MapFunctions;
import aima.core.environment.map.MoveToAction;
import aima.core.environment.map.SimplifiedRoadMapOfPartOfRomania;

public class MainWindowNetLink {

	/******ATTRIBUTS******/
	private JFrame m_frame;
	private String m_source;
	private String m_destination;
	private boolean m_isUniformCostSearch;
	/** The initialisation. */
	public Initialisation initialisation= new Initialisation(); //Load the file and the ontology

	
	/******CONSTRUCTORS******/
	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainWindowNetLink() throws Exception {
		initializeframe();
	}
	
	/******GETTERS AND SETTERS******/
	public String getm_source(){
		return m_source;
	}
	public String getm_destination(){
		return m_destination;
	}
	public boolean getm_isUniformCostSearch(){
		return m_isUniformCostSearch;
	}
	
	/******METHODS******/
	/**
	 * Initialize the contents of the m_frame.
	 * @throws Exception 
	 */
	private void initializeframe() throws Exception {
		m_frame = new JFrame("NetLink");
		try {
			m_frame.setIconImage(ImageIO.read(new File("Images/PenroseColor.png")));
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}

		Container contentPane = m_frame.getContentPane();
		JPanel panel=new JPanel();
		panel.setLayout(null);
		JButton buttonConfirm=new JButton("confirm");
		buttonConfirm.setBounds(347, 171, 100, 30);
		JLabel lab1=new JLabel("Source");
		JLabel lab2=new JLabel("Destination");
		lab1.setBounds(16, 11, 324, 15);
		lab2.setBounds(455, 11, 336, 15);
		final JTextField jtf1 = new JTextField();
		final JTextField jtf2 = new JTextField();
		jtf1.setBounds(16, 31, 324, 30);
		jtf2.setBounds(455, 31, 336, 30);

		JLabel lab3=new JLabel("Strategy :");



		final JRadioButton randioButton1=new JRadioButton("Strategy 1: Go through the path where the relationships are stronger on average with the least possible intermediary.", true);
		final JRadioButton randioButton2=new JRadioButton("Strategy 2: Go through as few intermediaries as possible without considering the nature of the relationships between people.");

		ButtonGroup group = new ButtonGroup();
		group.add(randioButton1);
		group.add(randioButton2);

		lab3.setBounds(16, 71, 100, 15);
		randioButton1.setBounds(16, 91, 775, 15);
		randioButton2.setBounds(16, 111, 775, 30);

		panel.add(lab1);
		panel.add(lab2);
		panel.add(jtf1);
		panel.add(jtf2);
		panel.add(lab3);
		panel.add(buttonConfirm);
		panel.add(randioButton1);
		panel.add(randioButton2);
		contentPane.add(panel);

		JButton buttonSwitch = new JButton("<=>");
		buttonSwitch.setBounds(366, 35, 60, 23);
		panel.add(buttonSwitch);

		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_source = jtf1.getText();
				m_destination=jtf2.getText();
				if (randioButton1.isSelected()){
					m_isUniformCostSearch=true;
				}
				else {
					m_isUniformCostSearch=false;
				}
				System.out.println(m_source);
				System.out.println(m_destination);
				System.out.println(m_isUniformCostSearch);
				
				try {
					computeNetLink(m_source, m_destination, m_isUniformCostSearch);//Compute strategy
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});

		buttonSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textSource = jtf1.getText();
				String textDestination = jtf2.getText();
				jtf1.setText(textDestination);
				jtf2.setText(textSource);
			}});

		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setSize(825, 251);
		m_frame.setLocationRelativeTo(null);
		m_frame.setVisible(true);//Make the window visible
	}
	
	
	private void computeNetLink(String nameSource, String nameDestination, boolean strategy) throws Exception {
		Request constructGraphOfSourceAndDestination = new Request(nameSource, nameDestination);
		RelationalGraph graphMap = new RelationalGraph(constructGraphOfSourceAndDestination.getm_adjacencyMatrixSorted());

		// Get Locations ie the persons in the graph.
		List<String> listLoc = graphMap.getLocations();
		System.out.println("\n-List of all the persons:");
		for (String loc : listLoc) {
			System.out.println(loc);
		}
		System.out.println("");
		

		String nameSourceClear = nameSource.replaceAll("((h:)*|(dbo:)*)*", "");
		String nameDestinationClear = nameDestination.replaceAll("((h:)*|(dbo:)*)*", "");

		Problem<String, MoveToAction> problem = new GeneralProblem<>(nameSourceClear,
				MapFunctions.createActionsFunction(graphMap), MapFunctions.createResultFunction(),
				GoalTest.isEqual(nameDestinationClear),
				MapFunctions.createDistanceStepCostFunction(graphMap));

		SearchForActions<String, MoveToAction> search;
		if(strategy){
			search = new UniformCostSearch<>();
		}
		else{
			search = new BreadthFirstSearch<>();
		}
		
		SearchAgent<String, MoveToAction> agent = new SearchAgent<>(problem, search);

		List<Action> actions = agent.getActions();

		System.out.println("Path recommended to follow: " + actions.toString());
		System.out.println("Path cost of the path to follow: " + search.getMetrics().get(QueueSearch.METRIC_PATH_COST));
		
        SwingUtilities.invokeLater(new Runnable() {

     	   @Override
     	   public void run() {
     		   JFrame frame = new JFrame();
     		   frame.setAlwaysOnTop(true);//The windows will not be always on top
     		   frame.setLocationRelativeTo(null);//We now ask our window to position itself at the center.
     		   frame.setVisible(false);
     		   Robot r = null;
     		   try {

     			   r = new Robot();

     			} catch (AWTException e) {

     				e.printStackTrace();

     			}
     		   //Left clic 
     	       r.mousePress(InputEvent.BUTTON1_MASK);
     	       r.mouseRelease(InputEvent.BUTTON1_MASK);
     		   JOptionPane.showMessageDialog(frame, "Path recommended to follow:                   \n" + actions.toString() + "\n\nPath cost of the path to follow: " + search.getMetrics().get(QueueSearch.METRIC_PATH_COST), "NetLink", JOptionPane.INFORMATION_MESSAGE);
     	   }
     	});
	}

}
