package ManagerCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GeradorCSV {
	private String csvFilePath = "C:\\Users\\Cliente\\eclipse-workspace\\Job2_ED2\\arquivo.csv";
	private int numLines = 10000;

	public void gerarCSV() {
		try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
			csvWriter.append("nome,caminho,tipo,tamanho,data_criacao,data_modificacao");
			csvWriter.append("\n");

			Random random = new Random();

			for (int i = 0; i < numLines; i++) {
				String name = generateRandomString(8);
				String type = generateRandomFileType();
				String path = "C:\\Users\\Cliente\\eclipse-workspace\\Job2_ED2\\"+name+"."+type;
				int size = random.nextInt(1,100);
				LocalDateTime creationTime = generateRandomDate();
				LocalDateTime modificationTime = generateRandomDate();
				while ((creationTime.compareTo(modificationTime))>0) {
					modificationTime = generateRandomDate();
				}

				String[] metadata = { name, path, type, String.valueOf(size), creationTime.toString(),
						modificationTime.toString() };
				csvWriter.append(String.join(",", metadata));
				csvWriter.append("\n");
			}

			csvWriter.flush();
			System.out.println("Arquivo CSV gerado em " + csvFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String generateRandomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(length);

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}

		return sb.toString();
	}

	private static String generateRandomFileType() {
		String[] fileTypes = { "txt", "doc", "pdf", "jpg", "png" };
		Random random = new Random();
		int index = random.nextInt(fileTypes.length);
		return fileTypes[index];
	}

	private static LocalDateTime generateRandomDate() {
	    LocalDate startDate = LocalDate.of(2023, 4, 1);
	    LocalDate endDate = LocalDate.now();
	    long start = startDate.toEpochDay();
	    long end = endDate.toEpochDay();
	    
	    Random random = new Random();
	    long randomDay = start + random.nextInt((int) (end - start));
	    
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
	    LocalTime randomTime = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60));
	    
	    return LocalDateTime.of(randomDate, randomTime);
	}
}
