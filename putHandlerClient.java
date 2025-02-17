import java.io.*;
import java.net.Socket;

public class putHandlerClient extends Thread
{
	
	protected static String fileName;
    protected static int nsplit;
    Thread father;

    public putHandlerClient(Thread father, String file)
    {
        this.father = father;
        fileName = file;
    }

    public synchronized void run()
    {
        try
        {
            Socket s = new Socket(GraficaLancelot.IpServer, 32323);
            File inputFile = new File(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            nsplit = split(inputFile);
            oos.writeInt(nsplit);
            oos.flush();
            if(nsplit != 0)
            {
                Thread getPartServer[] = new Thread[nsplit];
                for(int i = 0; i < nsplit; i++)
                {
                    getPartServer[i] = new Thread(new putPartClient(Thread.currentThread(), fileName, i, oos));
                    getPartServer[i].start();
                    getPartServer[i].join();
                }

            } else
            {
                oos.writeLong(inputFile.length());
                oos.flush();
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
                System.gc();
            }
            GraficaLancelot.scriviOutput("\n**** Upload completed ****\n");
            s.close();
        }
        catch(Exception e)
        {
            GraficaLancelot.scriviOutput("Errore putHandlerClient: "+e.getMessage());
        }
    }

    int split(File source)
    {
        int i = 0;
        try
        {
            FileInputStream fis = new FileInputStream(source);
            int bufSize = 5120;
            int maxSize = 0x30d4000;
            long length = source.length();
            byte b[] = new byte[bufSize];
            if(length > (long)maxSize)
                while(length > 0L) 
                {
                    FileOutputStream fos = new FileOutputStream(source+"."+Integer.toString(i));
                    int amount;
                    for(int size = 0; maxSize > size && (amount = fis.read(b, 0, bufSize)) != -1; size += amount)
                        fos.write(b, 0, bufSize);

                    fos.flush();
                    length -= maxSize;
                    fos.close();
                    System.gc();
                    i++;
                }
            fis.close();
        }
        catch(Exception e)
        {
            GraficaLancelot.scriviOutput("Errore split "+e.getMessage());
        }
        return i;
    }
}
