package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Entre com o caminho do arquivo: ");
		String caminho = sc.nextLine();

		String[] tmp = null;
		List<Product> produto = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String line = br.readLine();
			while (line != null) {
				tmp = line.split(",");
				String nome = tmp[0];
				Double preco = Double.parseDouble(tmp[1]);
				Integer quantidade = Integer.parseInt(tmp[2]);
				produto.add(new Product(nome, preco, quantidade));
				line = br.readLine();
			}

			System.out.print("Digite o nome do seu arquivo gerado:");
			String caminho_novo = sc.nextLine();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho_novo))) {
				for (Product x : produto) {
					bw.write(x.getNome() + String.format("%.2f", x.Total()));
					bw.newLine();
				}
				System.out.println("Arquivo criado");
			} catch (IOException e) {
				System.out.println("Erro:" + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();
	}

}
