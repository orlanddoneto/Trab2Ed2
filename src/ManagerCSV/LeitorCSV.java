package ManagerCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entidade.Arquivo;

public class LeitorCSV {
	 private BufferedReader br = null;
	    private String line = "";
	    private String cvsSplitBy = ",";
	    private List<Arquivo> lines = new ArrayList<>();
	    private String csvFile = "C:\\Users\\Cliente\\eclipse-workspace\\Job2_ED2\\arquivo.csv";
	    

	    public List<Arquivo> getLines() {
	        return lines;
	    }


	    public void lerCSV(){
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        int cont = 0;

	        try {
	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) { //  (&& cont<=100000) aqui que digo quantos elementos eu vou querer, caso eu queira um teto
	                
	            	String[] data = line.split(cvsSplitBy);
	                
	                cont++;
	                if (cont==1){
	                    continue;
	                }
	                else
	                {             
	                    Arquivo produto = criaArquivo(data);
	                    lines.add(produto);
	                }
	              

	            }
	        }
	        catch (IOException e) {
	            System.out.println("Falha na leitura do CSV.");
	        }finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    }
	    
	    
	    private Arquivo criaArquivo(String[]data) {
	    	String nome = data[0];
	    	String caminho = data[1];
	    	String tipo = data[2];
	    	Double tamanho = Double.parseDouble(data[3]);
	    	LocalDate dataCriacao = LocalDate.parse(data[4]);
	    	LocalDate dataModificacao = LocalDate.parse(data[5]);
	    	
	    	return new Arquivo(nome, caminho, tipo, tamanho, dataCriacao, dataModificacao);
	    }
	   

}
