package com.fox.platform.training.serv;

import com.fox.platform.training.repo.TrainingRepository;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;


public class ProxyTrainingVerticle extends AbstractVerticle  {
	 
	  private TrainingRepository repository;

	
	@Override
	  public void start() {

		vertx.eventBus().consumer("TrainingAddress2", this::onMessage);

	  }

	 
	 private void onMessage(final Message<String> message) {
		 
		 Future<JsonObject> response = Future.future();
		 processRequest(message, response);
		 
		 response.setHandler(asyncResult -> {
			 
			 if(asyncResult.succeeded()) {
				 message.reply(asyncResult.result());
			 } else {
				 message.fail(500,asyncResult.cause().toString());
			 }
			 
		 });

	 }
	 
	 public void processRequest(final Message<String> message, final Future<JsonObject> response) {
		 
		repository = new TrainingRepository();

		repository.getData(message.body(), vertx, result -> {
			if(result.succeeded()){
				System.out.println("Success process request ");
				System.out.println(result.result());

				response.complete(result.result());
			} else {
				System.out.println("Failed process request ");

				response.fail(result.cause());
			}
		});
		
		
	}
}
