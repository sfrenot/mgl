import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


class YoutubeChannel extends Observable{
   // Le code de la boucle while en environnement Threadé
    public void run()
    {
        while(true)
        {
            if(m_newVideo)
                notifySubscribers();
        }
    }
    private boolean m_newVideo;
}
