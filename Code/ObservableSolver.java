import java.util.ArrayList;
import java.util.List;

public class ObservableSolver {
    private List<RegleObserver> observers = new ArrayList<>();

    // Méthode pour ajouter un observateur
    public void addObserver(RegleObserver observer) {
        observers.add(observer);
    }

    // Méthode pour retirer un observateur
    public void removeObserver(RegleObserver observer) {
        observers.remove(observer);
    }

    // Notifier les observateurs après l'application d'une règle
    public void notifyObservers(int[][] grid, String regle) {
        for (RegleObserver observer : observers) {
            observer.onRegleAppliquee(grid, regle);
        }
    }
}
