package AlgoritmosHash;

public interface HashTable <K, V>{
	public int size();
	public V get(K key);
	public boolean insert(K key, V val);
	public boolean remove(K key);
	public boolean resize(int novo_tam);
	

}
