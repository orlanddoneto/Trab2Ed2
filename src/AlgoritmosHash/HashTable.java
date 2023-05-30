package AlgoritmosHash;

import Entidade.Arquivo;

public interface HashTable{
	public int size();
	public Arquivo get(String key);
	public boolean insert(String key, Arquivo val);
	public boolean remove(String key);
	public boolean resize(int novo_tam);
	

}
