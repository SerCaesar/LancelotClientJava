import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class getHandlerClient extends Thread
{
	
	protected static int DemonPort;
    protected static ObjectInputStream oin;
    Thread father;

    public getHandlerClient(Thread father, int port)
    {
        this.father = father;
        DemonPort = port;
    }

    public synchronized void run()
    {
        try
        {
            ServerSocket ss = new ServerSocket(DemonPort);
            Socket s = ss.accept();
            oin = new ObjectInputStream(s.getInputStream());
            File inFile = (File)oin.readObject();
            File saveFile = new File(inFile.getName());
            int nsplit = oin.readInt();
            System.gc();
            if(nsplit > 0)
            {
                Thread getPartClient[] = new Thread[nsplit];
                for(int i = 0; i < nsplit; i++)
                {
                    getPartClient[i] = new Thread(new getPartClient(Thread.currentThread(), oin));
                    getPartClient[i].start();
                    getPartClient[i].join();
                }

                riunisciParti(saveFile, nsplit);
            } else
            {
                long lung = oin.readLong();
                FileOutputStream fos = new FileOutputStream(saveFile);
                int bufSize = 5120;
                byte b[] = new byte[bufSize];
                try
                {
                    for(; lung > 0L; lung -= bufSize)
                    {
                        b = (byte[])oin.readObject();
                        fos.write(b);
                        fos.flush();
                    }

                }
                catch(Exception e)
                {
                    GraficaLancelot.scriviOutput("Catch: "+e.getMessage());
                }
                fos.close();
                System.gc();
            }
            s.close();
            ss.close();
            GraficaLancelot.scriviOutput("\n**** Download "+inFile.getName()+" completed ****\n");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    void riunisciParti(File destination, int nparti)
    {
        int bufSize = 5120;
        byte c[] = new byte[bufSize];
        try
        {
            FileOutputStream fos = new FileOutputStream(destination);
            for(int i = 0; i < nparti; i++)
            {
                FileInputStream fis = new FileInputStream(destination+"."+i);
                int amount;
                while((amount = fis.read(c, 0, bufSize)) != -1) 
                    fos.write(c, 0, amount);
                fos.flush();
                fis.close();
                File remFile = new File(destination+"."+i);
                remFile.delete();
            }

            fos.close();
        }
        catch(Exception e)
        {
            GraficaLancelot.scriviOutput("Errore riunisci: "+e.getMessage());
        }
    }
}
