import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class about extends JFrame{

	private static final long serialVersionUID = 1L;
	private JDialog dialog1;
	private JLabel label1;
	private JFormattedTextField formattedTextField1;
	private JTextPane textPane1;
	private JButton button1;
	
	public about(){
		
		dialog1 = new JDialog();
		label1 = new JLabel();
		formattedTextField1 = new JFormattedTextField();
		textPane1 = new JTextPane();
		button1 = new JButton();

		/* Frame Principale */
		dialog1.setPreferredSize(new Dimension(310, 315));
		dialog1.setBounds(500, 200, 310, 315);
		dialog1.setTitle("About");
		dialog1.setIconImage((new ImageIcon(GraficaLancelot.class.getResource("/immy/LancelotImmy.gif"))).getImage());	
		dialog1.setDefaultCloseOperation(0);
		Container dialog1ContentPane = dialog1.getContentPane();

		/* Immagine About */
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon(GraficaLancelot.class.getResource("/immy/LancelotAbout.gif"));
		label1.setIcon(icon);

		/* Nome Software */
		formattedTextField1.setBackground(new Color(240, 240, 240));
		formattedTextField1.setEditable(false);
		formattedTextField1.setFocusable(false);
		formattedTextField1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		formattedTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField1.setText("Lancelot Client v1.0");

		/* Testo */
		textPane1.setBackground(new Color(240, 240, 240));
		textPane1.setEditable(false);
		textPane1.setFocusable(false);
		textPane1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textPane1.setText("This software is created by Ser Caesar\n");
		
		scriviOutput("All Rights Reserved");
		scriviOutput("For questions, problems or comments please contact me at:");
		scriviOutput("neon_adam@hotmail.com");

		/* Bottone */
		button1.setText("Close");
		
		button1.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e){
	                close(e);
	           }
	     });

		GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
		dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
		dialog1ContentPaneLayout.setHorizontalGroup(
			dialog1ContentPaneLayout.createParallelGroup()
				.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
					.addGroup(dialog1ContentPaneLayout.createParallelGroup()
						.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
							.addGap(117, 117, 117)
							.addComponent(button1))
						.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(dialog1ContentPaneLayout.createParallelGroup()
								.addComponent(formattedTextField1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPane1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		dialog1ContentPaneLayout.setVerticalGroup(
			dialog1ContentPaneLayout.createParallelGroup()
				.addGroup(dialog1ContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(formattedTextField1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(textPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(button1)
					.addContainerGap())
		);
		dialog1.pack();
		dialog1.setVisible(true);
	}
	
	private void close(ActionEvent e){
        GraficaLancelot.frame.setEnabled(true);
        dialog1.removeNotify();
    }
	
	public void scriviOutput(String text)
	throws NullPointerException{
	
		String vecchia = textPane1.getText();
		textPane1.setText(vecchia+"\n"+text);
	}
}
