public class Lancelot extends Thread
{

    public Lancelot()
    {
    }

    public static void main(String args[])
    {
        Thread client = new Thread(new handlerLancelot(Thread.currentThread()));
        client.start();
        try
        {
            client.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
