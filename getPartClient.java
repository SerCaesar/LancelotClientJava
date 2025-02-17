import java.io.*;

public class getPartClient extends Thread
{
	
	protected ObjectInputStream ois;
    Thread father;

    public getPartClient(Thread father, ObjectInputStream is)
    {
        this.father = father;
        ois = is;
    }

    public synchronized void run()
    {
        try
        {
            File inFile = (File)ois.readObject();
            String comando = "ATTRIB +H "+inFile.getName();
            Runtime.getRuntime().exec(comando);
            long lung = ois.readLong();
            FileOutputStream fos = new FileOutputStream(inFile.getName());
            int bufSize = 5120;
            try
            {
                for(; lung > 0L; lung -= bufSize)
                {
                    byte b[] = (byte[])ois.readObject();
                    fos.write(b);
                    fos.flush();
                }

            }
            catch(Exception exception) { }
            fos.close();
            System.gc();
        }
        catch(Exception e)
        {
            GraficaLancelot.scriviOutput("Errore getPartClient: "+e.getMessage());
        }
    }
}
