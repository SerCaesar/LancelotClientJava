import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class handlerLancelot extends Thread
{
	
	protected static BufferedReader is;
    protected static BufferedWriter out;
    protected static Socket s;
    protected static String nomeAccount;
    static boolean sockdown;
    Thread father;

    public handlerLancelot(Thread father)
    {
        this.father = father;
    }

    public synchronized void run()
    {
        sockdown = true;
        try
        {
            SwingUtilities.invokeLater(new Runnable() {

                public void run()
                {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    GraficaLancelot.createAndShowGUI();
                }
            }
);
        }
        catch(Exception exception) { }
        do
        {
            if(sockdown)
                controllaConnessione(is);
            sockdown = false;
            String ricevuto = riceviMex(is);
            if(ricevuto == null)
            {
                GraficaLancelot.scriviOutput("Server is down");
                break;
            }
            try
            {
                String action = findField("ACTION", ricevuto);
                gestisciMessaggio(action, ricevuto);
            }
            catch(Exception e)
            {
                chiudiConnessione(s);
                GraficaLancelot.scriviClient("You are disconnected");
            }
        } while(true);
        return;
    }

    String riceviMex(BufferedReader is)
    {
        String str = "";
        boolean fermati = false;
        String bla;
        try
        {
            while((bla = is.readLine()) != null && !fermati) 
            {
                String splitter[] = bla.split("-n-");
                for(int i = 0; i < splitter.length - 1; i++)
                    str += splitter[i]+"\n";

                str += splitter[splitter.length - 1]+"\r\n";
                if(bla.equals("END"))
                    fermati = true;
            }
        }
        catch(IOException e)
        {
            GraficaLancelot.scriviOutput("WARNING: Server is down...");
        }
        return str;
    }

    static boolean inviaMex(BufferedWriter out, String mex)
    {
        try
        {
            String messaggio = "LANCELOT: "+nomeAccount+"\r\n";
            messaggio += "IP: "+InetAddress.getLocalHost().getHostAddress()+"\r\n";
            messaggio += mex;
            messaggio += "END\r\n";
            out.write(messaggio);
            out.newLine();
            out.flush();
        }
        catch(IOException e)
        {
            return false;
        }
        return true;
    }

    static String findField(String field, String messaggio)
    {
        String result[] = messaggio.split("\r\n");
        int numToken = result.length - 1;
        int i;
        for(i = 0; i < numToken; i++)
        {
            String a = result[i].substring(0, result[i].indexOf(": "));
            String b = result[i].substring(result[i].indexOf(": ") + 2, result[i].length());
            if(a.equals(field))
                return b;
        }

        if(i == numToken)
            return null;
        else
            return null;
    }

    static BufferedReader retInputStream()
    {
        return is;
    }

    static BufferedWriter retOutputStream()
    {
        return out;
    }

    static void chiudiConnessione(Socket s)
    {
        try
        {
            s.close();
            sockdown = true;
            GraficaLancelot.menuItem2.setEnabled(true);
        }
        catch(IOException ioexception) { }
    }

    static boolean apriConnessione(String ip, int port)
    {
        try
        {
            s = new Socket(InetAddress.getByName(ip), port);
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        }
        catch(Exception exc)
        {
            GraficaLancelot.scriviOutput("WARNING: Unable to contact server");
            GraficaLancelot.menuItem2.setEnabled(true);
            return false;
        }
        return true;
    }

    static void controllaConnessione(BufferedReader in)
    {
        boolean wait = true;
        while(wait) 
            try
            {
                s.getInputStream().available();
                wait = false;
            }
            catch(Exception e)
            {
                wait = true;
            }
    }

    private static String convertToHex(byte data[])
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < data.length; i++)
        {
            int halfbyte = data[i] >>> 4 & 0xf;
            int two_halfs = 0;
            do
            {
                if(halfbyte >= 0 && halfbyte <= 9)
                    buf.append((char)(48 + halfbyte));
                else
                    buf.append((char)(97 + (halfbyte - 10)));
                halfbyte = data[i] & 0xf;
            } while(two_halfs++ < 1);
        }

        return buf.toString();
    }

    public static String SHA1(String text)
    {
        byte sha1hash[] = new byte[40];
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return convertToHex(sha1hash);
    }

    void gestisciMessaggio(String action, String messaggio){
    	
        if(action.equals("Login")){
            nomeAccount = findField("ACCOUNT", messaggio);
            String foo = findField("DATA", messaggio);
            if(foo.equals("Login Not Accepted"))
                chiudiConnessione(s);
            GraficaLancelot.scriviOutput(foo);
            foo = findField("PATH", messaggio);
            GraficaLancelot.commandField2.setText(foo);
            return;
        }
        if(action.equals("Logout")){
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
            GraficaLancelot.scriviClient("You are disconnected");
            chiudiConnessione(s);
            return;
        }
        if(action.equals("CD")){
            String foo = findField("DATA", messaggio);
            if(foo != null)
                GraficaLancelot.scriviOutput(foo);
            foo = findField("PATH", messaggio);
            if(foo != null){
            	if (foo.length() < 50)
            		GraficaLancelot.commandField2.setText(foo);
            	else{
            		String foomezzi = foo.substring(0, 3) + "..." + foo.substring(foo.indexOf("\\", foo.length()-50), foo.length());
            		GraficaLancelot.commandField2.setText(foomezzi);
            	}
            		
            }
            return;
        }
        if(action.equals("GET"))
            if("OK".equals(findField("DATA", messaggio))){
                Thread downclient = new Thread(new getHandlerClient(Thread.currentThread(), Integer.parseInt(findField("PORT", messaggio))));
                downclient.start();
            } else{
                GraficaLancelot.scriviOutput(findField("DATA", messaggio));
            }
        if(action.equals("PUT")){
            Thread downclient = new Thread(new putHandlerClient(Thread.currentThread(), findField("FILE", messaggio)));
            downclient.start();
        }
        if(action.equals("Message"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("LS"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("INFO"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("CLIENT"))
            GraficaLancelot.scriviClient(findField("DATA", messaggio));
        if(action.equals("SENDTO"))
            GraficaLancelot.scriviOutput(findField("FROM", messaggio)+": "+findField("DATA", messaggio));
        if(action.equals("MKDIR"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("RM"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("MKFILE"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("RENAME"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("SHUTDOWN"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("RESTART"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("NEW_ACCOUNT"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("DEL_ACCOUNT"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("LIST_ACCOUNT"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("SAVE_ACCOUNT"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("NEW_PSW"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
        if(action.equals("KICK_ACCOUNT"))
            GraficaLancelot.scriviOutput(findField("DATA", messaggio));
    }
}
