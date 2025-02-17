import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class changeIp extends JFrame
{
	
	private static final long serialVersionUID = 1L;
    private JFrame dialog1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;

    public changeIp(String newip)
    {
        dialog1 = new JFrame("Change Host \\ Ip");
        textField1 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        Container dialog1ContentPane = dialog1.getContentPane();
        dialog1ContentPane.setLayout(new GridBagLayout());
        dialog1.setVisible(true);
        dialog1.setIconImage((new ImageIcon(GraficaLancelot.class.getResource("/immy/LancelotImmy.gif"))).getImage());
        dialog1.setDefaultCloseOperation(0);
        textField1.setText(newip);
        textField1.setToolTipText("Write Hostname address or IP like \"127.0.0.1\"");
        dialog1ContentPane.add(textField1, new GridBagConstraints(2, 1, 5, 1, 10D, 0.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
        textField1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ok(e);
            }
        }
);
        button1.setText("Ok");
        dialog1ContentPane.add(button1, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(5, 15, 5, 5), 0, 0));
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ok(e);
            }
        }
);
        button2.setText("Default");
        dialog1ContentPane.add(button2, new GridBagConstraints(5, 2, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(5, 35, 5, 5), 0, 0));
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                cancel(e);
            }
        }
);
        dialog1.pack();
        dialog1.setSize(200, 110);
        dialog1.setLocationRelativeTo(null);
    }

    private void ok(ActionEvent e)
    {
        GraficaLancelot.IpServer = textField1.getText();
        GraficaLancelot.frame.setEnabled(true);
        dialog1.removeNotify();
    }

    private void cancel(ActionEvent e)
    {
        GraficaLancelot.IpServer = "lancelot.myftp.org";
        GraficaLancelot.frame.setEnabled(true);
        dialog1.removeNotify();
    }
}
