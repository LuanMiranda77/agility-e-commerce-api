package com.api.services.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Pedido;
import com.api.domain.enuns.EstatusPedido;

@Service
public class PedidoQueryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Pedido> findPedidosByEstatus(Date dtIni, Date dtFin, EstatusPedido estatusPedido){
		
		List<Pedido> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);
		
		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("estatus"), estatusPedido));
		
		pedidos = manager.createQuery(query).getResultList();
		
		return pedidos;
		
	} 
	
	

}
