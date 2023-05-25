package AlgoritmosHash;

public abstract class HashTable <K, V>{
	public abstract int size();
	public abstract V get(K key);
	public abstract boolean insert(K key, V val);
	public abstract boolean remove(K key);

}
