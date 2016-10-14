import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


class Observable{

    public Observable(){
        m_subscribers = new LinkedList<ISubscribers>();
    }

    
    public void notifySubscribers(){
       Iterator<ISubscribers> it = m_subscribers.iterator();
        // Notifier tous les observers
       while(it.hasNext()){
           ISubscribers obs = it.next();
           obs.notifyMe();
       }
    }

    

    void addSubscriber(ISubscribers subscriber){
        // On ajoute un abonné à la liste en le plaçant en premier (implémenté en pull).
            // On pourrait placer cet observateur en dernier (implémenté en push, plus commun).
        m_subscribers.add(subscriber);
    }

    
    void removeSubscriber(ISubscribers subscriber){
        // Enlever un abonné a la liste
        m_subscribers.remove(subscriber);
    }

    

    private List<ISubscribers> m_subscribers;

}
