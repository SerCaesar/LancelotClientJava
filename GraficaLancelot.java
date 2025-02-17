import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class GraficaLancelot extends JPanel
    implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
    protected static final String textFieldString = "UserName";
    protected static final String passwordFieldString = "Password";
    protected static final String commandFieldString = "Cmd";
    protected static final String commandFieldString2 = "Path";
    protected static final String buttonString = "JButton";
    protected static String IpServer = "lancelot.myftp.org";
    protected static final int PortServer = 23232;
    protected static final int PortDati = 32323;
    protected JLabel actionLabel;
    protected JLabel actionLabel2;
    protected JPasswordField passwordField;
    protected boolean isVerbose;
    protected static JTextField commandField;
    protected static JTextField commandField2;
    String account;
    static JTextArea UtentiConnessi;
    static JTextArea OutputArea;
    static JFrame frame;
    static JMenuItem menuItem2;

    public GraficaLancelot()
    {
        setLayout(new BorderLayout());
        isVerbose = false;
        JTextField textField = new JTextField(10);
        textField.setActionCommand("UserName");
        textField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand("Password");
        passwordField.addActionListener(this);
        passwordField.setEnabled(false);
        JLabel textFieldLabel = new JLabel("UserName: ");
        textFieldLabel.setLabelFor(textField);
        JLabel passwordFieldLabel = new JLabel("Password: ");
        passwordFieldLabel.setLabelFor(passwordField);
        actionLabel = new JLabel("Type UserName then type Password");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        textControlsPane.setLayout(gridbag);
        JLabel labels[] = {
            textFieldLabel, passwordFieldLabel
        };
        JTextField textFields[] = {
            textField, passwordField
        };
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);
        c.gridwidth = 0;
        c.anchor = 17;
        c.weightx = 1.0D;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Info Account"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        UtentiConnessi = new JTextArea("You are disconnected");
        UtentiConnessi.setFont(new Font("Serif", 0, 16));
        UtentiConnessi.setLineWrap(true);
        UtentiConnessi.setWrapStyleWord(true);
        UtentiConnessi.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(UtentiConnessi);
        areaScrollPane.setVerticalScrollBarPolicy(20);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Users Connected"), BorderFactory.createEmptyBorder(5, 5, 5, 5)), areaScrollPane.getBorder()));
        OutputArea = new JTextArea("");
        OutputArea.setFont(new Font("Times New Roman", 0, 12));
        OutputArea.setLineWrap(true);
        OutputArea.setWrapStyleWord(true);
        OutputArea.setEditable(false);
        OutputArea.setAutoscrolls(true);
        JScrollPane areaScrollPane2 = new JScrollPane(OutputArea);
        areaScrollPane2.setVerticalScrollBarPolicy(20);
        areaScrollPane2.setPreferredSize(new Dimension(250, 250));
        areaScrollPane2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Output"), BorderFactory.createEmptyBorder(5, 5, 5, 5)), areaScrollPane2.getBorder()));
        commandField2 = new JTextField(30);
        commandField2.setActionCommand("Path");
        commandField2.addActionListener(this);
        commandField2.setEditable(false);
        JLabel commandFieldLabel2 = new JLabel("Path>");
        JLabel labels3[] = {
            commandFieldLabel2
        };
        JTextField commandFields2[] = {
            commandField2
        };
        commandField = new JTextField(30);
        commandField.setActionCommand("Cmd");
        commandField.addActionListener(this);
        JLabel commandFieldLabel = new JLabel("Cmd>");
        commandFieldLabel.setLabelFor(commandField);
        actionLabel2 = new JLabel("*Scripted By Ser Caesar*");
        actionLabel2.setFont(new Font("Times New Roman", 0, 12));
        actionLabel2.setBorder(BorderFactory.createEmptyBorder(10, 245, 0, 0));
        JPanel commandControlsPane = new JPanel();
        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        commandControlsPane.setLayout(gridbag2);
        JLabel labels2[] = {
            commandFieldLabel
        };
        JTextField commandFields[] = {
            commandField
        };
        addLabelTextRows(labels3, commandFields2, gridbag2, commandControlsPane);
        addLabelTextRows(labels2, commandFields, gridbag2, commandControlsPane);
        c2.gridwidth = 0;
        c2.anchor = 17;
        c2.weightx = 1.0D;
        commandControlsPane.add(actionLabel2, c2);
        commandControlsPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Command"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JPanel leftPane = new JPanel(new BorderLayout());
        JPanel rightPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, "First");
        leftPane.add(areaScrollPane, "Center");
        add(leftPane, "Before");
        rightPane.add(areaScrollPane2, "First");
        rightPane.add(commandControlsPane, "Center");
        add(rightPane, "After");
    }

    private void addLabelTextRows(JLabel labels[], JTextField textFields[], GridBagLayout gridbag, Container container)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = 13;
        int numLabels = labels.length;
        for(int i = 0; i < numLabels; i++)
        {
            c.gridwidth = -1;
            c.fill = 0;
            c.weightx = 0.0D;
            container.add(labels[i], c);
            c.gridwidth = 0;
            c.fill = 2;
            c.weightx = 1.0D;
            container.add(textFields[i], c);
        }

    }

    public synchronized void actionPerformed(ActionEvent e)
    {
        if("UserName".equals(e.getActionCommand()))
        {
            account = ((JTextField)e.getSource()).getText();
            passwordField.setEnabled(true);
            passwordField.setText("");
            passwordField.requestFocusInWindow();
        } else
        if("Password".equals(e.getActionCommand()))
        {
            String mex = "ACCOUNT: "+account+"\r\n";
            mex += "PASSWORD: "+handlerLancelot.SHA1(new String(((JPasswordField)e.getSource()).getPassword()))+"\r\n";
            mex += "ACTION: Login\r\n";
            menuItem2.setEnabled(false);
            scriviOutput("Connecting to server...");
            handlerLancelot.apriConnessione(IpServer, 23232);
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            passwordField.setEnabled(false);
            commandField.requestFocusInWindow();
        } else
        if("Cmd".equals(e.getActionCommand()))
        {
            JTextField source = (JTextField)e.getSource();
            try
            {
                handlerLancelot.s.getInputStream().available();
                boolean isCreated = creaMessaggio(source.getText());
                if(isCreated)
                {
                    if(isVerbose && !source.getText().equals("clear"))
                        scriviOutput("LANCELOT: "+source.getText());
                } else
                {
                    scriviOutput("LANCELOT: '"+source.getText()+"' Command not found");
                }
            }
            catch(Exception ex)
            {
            	ex.printStackTrace();
                scriviOutput("WARNING: Non sei connesso!");
            }
            commandField.setText("");
        }
    }

    public static void scriviOutput(String text)
    {
        OutputArea.append("\n"+text);
        OutputArea.setSelectionStart(OutputArea.getText().length());
    }

    public static void scriviClient(String text)
    {
        UtentiConnessi.setText(text);
    }

    private static void esci(ActionEvent e)
    {
        System.exit(0);
    }

    private static void cambiaIp(ActionEvent e)
    {
        frame.setEnabled(false);
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                new changeIp(GraficaLancelot.IpServer);
            }

        }
);
    }

    private static void manuale(ActionEvent e)
    {
    	frame.setEnabled(false);
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                try {
                	new manual();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

        }
);
    }

    private static void about(ActionEvent e)
    {
    	frame.setEnabled(false);
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                try {
                	new about();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

        }
);
    }

    static void createAndShowGUI()
    {
        frame = new JFrame("Lancelot");
        frame.setDefaultCloseOperation(3);
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menu1 = new JMenu();
        JMenuItem menuItem1 = new JMenuItem();
        JMenu menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        JMenu menu3 = new JMenu();
        JMenuItem menuItem3 = new JMenuItem();
        JMenuItem menuItem4 = new JMenuItem();
        menu1.setText("File");
        menu1.setMnemonic('F');
        menuItem1.setText("Exit");
        menuItem1.setIcon(new ImageIcon(GraficaLancelot.class.getResource("/immy/exit.gif")));
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                GraficaLancelot.esci(e);
            }

        }
);
        menu1.add(menuItem1);
        menuBar1.add(menu1);
        menu2.setText("Edit");
        menu2.setMnemonic('E');
        menuItem2.setText("Change Host or Ip");
        menuItem2.setIcon(UIManager.getIcon("FileView.computerIcon"));
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(78, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                GraficaLancelot.cambiaIp(e);
            }

        }
);
        menu2.add(menuItem2);
        menuBar1.add(menu2);
        menu3.setText("Help");
        menu3.setMnemonic('H');
        menuItem4.setText("Manual");
        menuItem4.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
        menuItem4.setAccelerator(KeyStroke.getKeyStroke(77, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                GraficaLancelot.manuale(e);
            }

        }
);
        menu3.add(menuItem4);
        menuItem3.setText("About");
        menuItem3.setIcon(new ImageIcon(GraficaLancelot.class.getResource("/immy/question.gif")));
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(65, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                GraficaLancelot.about(e);
            }

        }
);
        menu3.add(menuItem3);
        menuBar1.add(menu3);
        frame.setJMenuBar(menuBar1);
        frame.setLocationRelativeTo(frame.getOwner());
        frame.add(new GraficaLancelot());
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(350, 150, frame.getWidth(), frame.getHeight());
        frame.setIconImage((new ImageIcon(GraficaLancelot.class.getResource("/immy/LancelotImmy.gif"))).getImage());
    }

    boolean creaMessaggio(String action){
        String azione[] = action.split(" ");
        if(azione[0].equalsIgnoreCase(""))
            return true;
        if(azione[0].equalsIgnoreCase("verbose")){
            isVerbose = !isVerbose;
            scriviOutput("Verbose Mode: "+isVerbose);
            return true;
        }
        if(azione[0].equalsIgnoreCase("logout")){
            String mex = "ACTION: Logout\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("ls")){
            String mex = "ACTION: LS\r\n";
            if(azione.length > 1 && azione[1].equals("-a"))
                mex = "ACTION: LSALL\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("cd")){
            String mex = "ACTION: CD\r\n";
            if(azione.length > 1){
                String cartella = azione[1];
                for(int i = 2; i < azione.length; i++)
                    cartella += " "+azione[i];

                mex += "DATA: "+cartella+"\\\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("You must specify a directory");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("info")){
            String mex = "ACTION: INFO\r\n";
            if(azione.length > 1){
                mex += "DATA: "+azione[1]+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("You must specify a file or directory");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("clear")){
            OutputArea.setText("");
            return true;
        }
        if(azione[0].equalsIgnoreCase("pending")){
            String mex = "ACTION: Pending\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("sendto")){
            String mex = "ACTION: SENDTO\r\n";
            mex += "FROM: "+handlerLancelot.nomeAccount+"\r\n";
            if(azione.length > 2){
                mex += "TO: "+azione[1]+"\r\n";
                mex += "DATA: "+azione[2];
                for(int i = 3; i < azione.length; i++)
                    mex += " "+azione[i];

                mex += "\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: sendto 'Account' 'message'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("mkdir")){
            String mex = "ACTION: MKDIR\r\n";
            if(azione.length > 1){
                String dir = azione[1];
                for(int i = 2; i < azione.length; i++)
                    dir += " "+azione[i];

                mex += "DATA: "+dir+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("You must specify a name for directory");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("rm")){
            String mex = "ACTION: RM\r\n";
            if(azione.length > 1){
                String file = azione[1];
                for(int i = 2; i < azione.length; i++)
                    file += " "+azione[i];

                mex += "DATA: "+file+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("You must specify a file or directory");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("touch")){
            String mex = "ACTION: MKFILE\r\n";
            if(azione.length > 1){
                String file = azione[1];
                for(int i = 2; i < azione.length; i++)
                    file += " "+azione[i];

                mex += "DATA: "+file+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("You must specify a filename");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("rename")){
            String mex = "ACTION: RENAME\r\n";
            if(azione.length > 2){
                mex += "FILE: "+azione[1]+"\r\n";
                mex += "DATA: "+azione[2]+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: rename 'fileName' 'newFileName'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("get")){
            String mex = "ACTION: GET\r\n";
            if(azione.length > 1){
                String file = azione[1];
                for(int i = 2; i < azione.length; i++)
                    file += " "+azione[i];

                mex += "FILE: "+file+"\r\n";
                mex += "PORT: 32323\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: get 'fileName'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("put")){
            if(azione.length > 1){
                String path = azione[1];
                for(int i = 2; i < azione.length; i++)
                    path += " "+azione[i];

                String abpath = path.substring(0, path.lastIndexOf("\\") + 1);
                String file = path.substring(path.lastIndexOf("\\") + 1, path.length());
                boolean ok = false;
                try{
                File checkFile = new File(abpath);
                
                String listaFile[] = checkFile.list();
                for(int i = 0; i < listaFile.length && !ok; i++)
                    if(listaFile[i].equals(file))
                        ok = true;
                scriviOutput("path-> "+abpath+" file -> "+file);
                }catch(Exception e){
                	ok = false;
                }
                if(ok){
                    String mex = "ACTION: PUT\r\n";
                    mex += "FILE: "+file+"\r\n";
                    mex += "PORT: 32323\r\n";
                    mex += "DATA: "+path+"\r\n";
                    handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
                } else{
                    scriviOutput("File not found, make sure you have entered the complete path");
                    return true;
                }
            } else{
                scriviOutput("Use: put 'fileName'");
                return true;
            }
            return true;
        } 
        if(azione[0].equalsIgnoreCase("CreateAccount")){
            String mex = "ACTION: NEW_ACCOUNT\r\n";
            mex += "FROM: "+handlerLancelot.nomeAccount+"\r\n";
            if(azione.length > 3){
            	if ((Integer.parseInt(azione[3])== 0)||(Integer.parseInt(azione[3])== 1)){
            		mex += "NAME: "+azione[1]+"\r\n";
            		mex += "PSW: "+handlerLancelot.SHA1(azione[2])+"\r\n";
            		mex += "ADMIN: "+azione[3]+"\r\n";
            	}else{
            		scriviOutput("The field \"isAdmin\" must be only 0 or 1");
                    return false;
            	}

                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: CreateAccount 'Name' 'Password' 'isAdmin (0 or 1)'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("ChangePassword")){
            String mex = "ACTION: NEW_PSW\r\n";
            mex += "FROM: "+handlerLancelot.nomeAccount+"\r\n";
            if(azione.length > 2){
            		mex += "OLD: "+handlerLancelot.SHA1(azione[1])+"\r\n";
            		mex += "NEW: "+handlerLancelot.SHA1(azione[2])+"\r\n";
            }else{
            	scriviOutput("Use: ChangePassword 'oldPassword' 'newPassword'");
                return false;
            }

            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            
            return true;
        }
        if(azione[0].equalsIgnoreCase("DeleteAccount")){
            String mex = "ACTION: DEL_ACCOUNT\r\n";
            mex += "FROM: "+handlerLancelot.nomeAccount+"\r\n";
            if(azione.length > 1){
            	if (azione[1].equals(handlerLancelot.nomeAccount)){
            		scriviOutput("You can't delete your account!");
                    return true;
            	}
            	mex += "NAME: "+azione[1]+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: DeleteAccount 'Name'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("ListAccount")){
            String mex = "ACTION: LIST_ACCOUNT\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("SaveAccount")){
            String mex = "ACTION: SAVE_ACCOUNT\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("Kick")){
            String mex = "ACTION: KICK_ACCOUNT\r\n";
            mex += "FROM: "+handlerLancelot.nomeAccount+"\r\n";
            if(azione.length > 1){
            	if (azione[1].equals(handlerLancelot.nomeAccount)){
            		scriviOutput("You can't kick your account!");
                    return true;
            	}
            	mex += "NAME: "+azione[1]+"\r\n";
                handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            } else{
                scriviOutput("Use: KickAccount 'Name'");
                return false;
            }
            return true;
        }
        if(azione[0].equalsIgnoreCase("ShutDown")){
            String mex = "ACTION: SHUTDOWN\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }        
        if(azione[0].equalsIgnoreCase("DownLog")){
            String mex = "ACTION: DOWNLOG\r\n";
            mex += "PORT: 32323\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        if(azione[0].equalsIgnoreCase("Restart")){
            String mex = "ACTION: RESTART\r\n";
            handlerLancelot.inviaMex(handlerLancelot.retOutputStream(), mex);
            return true;
        }
        
        else{
            return false;
        }
    }
}
