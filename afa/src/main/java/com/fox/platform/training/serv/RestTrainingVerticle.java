package com.fox.platform.training.serv;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RestTrainingVerticle extends AbstractVerticle{
	
	  @Override
	    public void start() {
		  
		  Router router = Router.router(vertx);
		  router.route().handler(BodyHandler.create());
		  router.get("/channels/:country").handler(context -> processRequest(context));

		  vertx.createHttpServer()
          .requestHandler(router::accept)
          .listen(8080);
		  
	    }
	  
	  
	  private void processRequest(final RoutingContext context) {
		 

		  String country = context.request().getParam("country");
		  
		  if (country == null) {
			    context.response().setStatusCode(400).end();
			  } else {
			    
				  vertx.eventBus().<JsonObject>send("TrainingAddress2",
						  country, replyHandler -> {
			                	if (replyHandler.succeeded()) {
			                        context.response().end(replyHandler.result().body().toString());
			                      } else {
						                context.response().setStatusCode(500).end(replyHandler.cause().getMessage());
			                      }
			        });

			  }
		  }

}
