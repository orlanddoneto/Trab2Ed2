package Servicos;

import java.time.LocalDate;
import java.util.Comparator;

import Entidade.Arquivo;

public class Comparador implements Comparator<Arquivo>{
	private int criterio;

	public Comparador(int criterio) {
		this.criterio = criterio;
	}

	@Override
	public int compare(Arquivo c1, Arquivo c2) {
		
		if(c1.chave(criterio).getClass().equals(String.class)){
			return ((String)c1.chave(criterio)).compareTo((String)c2.chave(criterio));
		}
		
		if(c1.chave(criterio).getClass().equals(Integer.class)){
			return ((Integer)c1.chave(criterio)).compareTo((Integer)c2.chave(criterio));
		}
		
		if(c1.chave(criterio).getClass().equals(Double.class)){
			return ((Double)c1.chave(criterio)).compareTo((Double)c2.chave(criterio));
		}
		if(c1.chave(criterio).getClass().equals(LocalDate.class)) {
			return ((LocalDate)c1.chave(criterio)).compareTo((LocalDate)c2.chave(criterio));
		}
		return 0;
		
	}

}
