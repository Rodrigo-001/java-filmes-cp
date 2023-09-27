package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.model.bean.Filme;
import br.com.fiap.model.dao.Conexao;
import br.com.fiap.model.dao.FilmeDAO;

public class FilmeController {
	public String insereFilme(String codigo, String titulo, String genero, String produtora) 
			throws ClassNotFoundException , SQLException  {		
		String resultado;
		Connection con = Conexao.abrirConexao();
		Filme filmeBean = new Filme();		
		filmeBean.setCodigo(codigo);
		filmeBean.setTitulo(titulo);
		filmeBean.setGenero(genero);
		filmeBean.setProdutora(produtora);		
		FilmeDAO filmeDao = new FilmeDAO(con);
		resultado = filmeDao.inserir(filmeBean);
		Conexao.fecharConexao(con);
		return resultado;	
		
	}
	
	public String alteraFilme(String codigo, String titulo, String genero, String produtora) 
			throws ClassNotFoundException , SQLException {		
		String resultado;
		Connection con = Conexao.abrirConexao();
		FilmeDAO filmeDao = new FilmeDAO(con);
		Filme filmeBean = new Filme();		
		filmeBean.setCodigo(codigo);
		filmeBean.setTitulo(titulo);
		filmeBean.setGenero(genero);
		filmeBean.setProdutora(produtora);		
		resultado = filmeDao.alterar(filmeBean);	
		Conexao.fecharConexao(con);
		return resultado;	
	}
	
	public String excluiFilme(String codigo) 
			throws ClassNotFoundException , SQLException {		
		String resultado;
		Connection con = Conexao.abrirConexao();
		Filme filmeBean = new Filme();		
		filmeBean.setCodigo(codigo);
		FilmeDAO filmeDao = new FilmeDAO(con);
		resultado = filmeDao.excluir(filmeBean);	
		Conexao.fecharConexao(con);
		return resultado;	
	}
	
	public String listaTodosFilmes() 
			throws ClassNotFoundException , SQLException {				
		String resultados = "";
		Filme filme = new Filme();	//instancia classe Filme				
		ArrayList<Filme> listarFilmes;	//arraylist para receber dados dos filmes
		Connection con = Conexao.abrirConexao();				
		FilmeDAO filmeDao = new FilmeDAO(con); 	
		
		//chama método de FilmeDAO
		listarFilmes = filmeDao.listarTodos();
		for (Filme index : listarFilmes) {
			resultados += " Filme: \n"
					+ "Código do Filme: " +index.getCodigo() + "\n"
					+ "Título do Filme: " + index.getTitulo() + "\n"
					+ "Gênero do Filme: " + index.getGenero() + "\n"
					+ "Produtora do Filme: " + index.getProdutora() + "\n\n";					
		
		}		
		Conexao.fecharConexao(con);
		if(resultados=="") {
			return "Não há filmes cadastrados";
		}
		
		return resultados;
	}
	
}
