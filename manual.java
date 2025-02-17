import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;


public class manual extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton button1;
	private JScrollPane scrollPane1;
	private JEditorPane editorPane1;
	
    public manual(){
    	
    	frame1 = new JFrame("Manual");
		panel2 = new JPanel();
		panel3 = new JPanel();
		button1 = new JButton();
		scrollPane1 = new JScrollPane();
		editorPane1 = new JEditorPane();
		
		frame1.setPreferredSize(new Dimension(500, 610));
		frame1.setBounds(450, 70, 500, 610);
		frame1.setIconImage((new ImageIcon(GraficaLancelot.class.getResource("/immy/LancelotImmy.gif"))).getImage());	
		frame1.setDefaultCloseOperation(0);
		
		Container frame1ContentPane = frame1.getContentPane();
		frame1ContentPane.setLayout(new GridLayout());
		
		panel2.setLayout(new GridLayout());
			
		button1.setText("Chiudi");
		
		editorPane1.setFont(new Font("Times New Roman", 0, 13));
		editorPane1.setEditable(false);
		editorPane1.setBackground(new Color(240, 240, 240));
		editorPane1.setText("\t\t*** User Manual ***\n");
		
		scriviOutput("----- List of User Commands:\n");
		
		scriviOutput("- CD <path>: Moves the current working directory");
		scriviOutput("- ChangePassword <old> <new>: Change the old password with a new");
		scriviOutput("- Clear: Clear the Output Area");
		scriviOutput("- GET <pathFile>: Download the specified file");
		scriviOutput("- Info <fileName/directoryName>: Get more info about a specified path");
		scriviOutput("- LogOut: Close the connection with Lancelot-Server");
		scriviOutput("- LS: Get list of Directories and Files in the current working directory");
		scriviOutput("- LS -a: As Above but also shows hidden Files and Directories");
		scriviOutput("- MKDIR <nameDir>: Create a new directory with name \"nameDir\"");
		scriviOutput("- Pending: Get all messages received offline");
		scriviOutput("- PUT <pathFile>: Send the specified file to Server in the current working directory");
		scriviOutput("- RM <fileName>: Delete the specified file");
		scriviOutput("- Rename <fileName> <newFileName>: Rename a specified file in \"newFileName\"");
		scriviOutput("- Sendto <account> <message>: Send a message to a specified account");
		scriviOutput("- Touch <fileName>: Create a new empty file in the current working directory");
		scriviOutput("- Verbose: Enable/Disable status message");
		
		scriviOutput("\n\n----- List of Admin Commands:\n");
		
		scriviOutput("- CreateAccount <name> <password> <isAdmin (0 or 1)>: Create a new account");
		scriviOutput("- DeleteAccount <name>: Delete an existing account");
		scriviOutput("- DownLog: Create and Download a logFile");
		scriviOutput("- Kick <name>: Close all connections to an online account");
		scriviOutput("- ListAccount: Shows list of all accounts");
		scriviOutput("- Restart: Close and Restart Lancelot-Server");
		scriviOutput("- SaveAccount: Save new accounts to account file");
		scriviOutput("- ShutDown: Close all active connections and shutdown Lancelot-Server");
		
		
		scrollPane1.setViewportView(editorPane1);
			
		GroupLayout panel3Layout = new GroupLayout(panel3);
		panel3.setLayout(panel3Layout);
		panel3Layout.setHorizontalGroup(
			panel3Layout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.CENTER, panel3Layout.createSequentialGroup()
					.addComponent(button1))
				.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
		);
		
		panel3Layout.setVerticalGroup(
			panel3Layout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(button1)
					.addContainerGap())
		);
		
		button1.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e){
	                close(e);
	           }
	     });
			
		panel2.add(panel3);
		frame1ContentPane.add(panel2);
		frame1.pack();
		frame1.setVisible(true);
    }
    
    private void close(ActionEvent e){
        GraficaLancelot.frame.setEnabled(true);
        frame1.removeNotify();
    }
    
    public void scriviOutput(String text)
    	throws NullPointerException{
    	
    		String vecchia = editorPane1.getText();
    		editorPane1.setText(vecchia+"\n"+text);
    }
}