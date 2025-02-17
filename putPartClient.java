import java.io.*;

public class putPartClient extends Thread
{
	
	protected int part;
    protected String fileName;
    protected ObjectOutputStream oos;
    Thread father;

    public putPartClient(Thread father, String file, int part, ObjectOutputStream os)
    {
        this.father = father;
        fileName = file;
        this.part = part;
        oos = os;
    }

    public synchronized void run()
    {
        try
        {
            Thread.sleep(1000L);
            File inputFile = new File(fileName+"."+part);
            oos.reset();
            oos.writeObject(inputFile);
            oos.flush();
            oos.writeLong(inputFile.length());
            oos.flush();
            System.gc();
            FileInputStream fis = new FileInputStream(inputFile);
            byte b[] = new byte[5120];
            int len = 0;
            long punt = 0L;
            while((len = fis.read(b)) > 0) 
            {
                punt += len;
                byte bufOut[];
                if(len < 5120)
                {
                    bufOut = new byte[len];
                    for(int i = 0; i < len; i++)
                        bufOut[i] = b[i];

                } else
                {
                    bufOut = (byte[])b.clone();
                }
                oos.writeObject(bufOut);
                oos.flush();
            }
            fis.close();
            inputFile.delete();
            System.gc();
        }
        catch(Exception e)
        {
            GraficaLancelot.scriviOutput("Errore putPartClient: "+e.getMessage());
        }
    }
}
