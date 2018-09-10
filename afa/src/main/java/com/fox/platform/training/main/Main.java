package com.fox.platform.training.main;
import com.fox.platform.training.serv.ProxyTrainingVerticle;
import com.fox.platform.training.serv.RestTrainingVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class Main extends AbstractVerticle {

	public static void main(String [] args)
	{
		 Vertx vertx = Vertx.vertx();
		 
		 vertx.deployVerticle(ProxyTrainingVerticle.class.getName());
		 
		 vertx.deployVerticle(RestTrainingVerticle.class.getName());
	}
	
}
