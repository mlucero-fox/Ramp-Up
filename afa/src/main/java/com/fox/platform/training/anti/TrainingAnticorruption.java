package com.fox.platform.training.anti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class TrainingAnticorruption {

	public void getDataFromAPI(String request, Handler<AsyncResult<JsonObject>> handler, Vertx vertx){
		
		WebClient client = WebClient.create(vertx, new WebClientOptions().setSsl(true));

		Future<JsonObject> future = Future.future();
		future.setHandler(handler);
		
	    client.post(443,"search-omnix-services-sh2266ar6ket7lqcnhj3dpzccu.us-east-1.es.amazonaws.com","/omnix_es/contentObjects/_search").putHeader("content-type", "application/json;charset=UTF-8").sendJsonObject(getQueryFromFile(request),response -> {
	      if (response.succeeded()) {
	        System.out.println("Got HTTP response with status " + response.result().statusCode() + " with data " +
	        		response.result().body().toString("ISO-8859-1"));
	        future.complete(response.result().bodyAsJsonObject());
	      } else {
	    	  System.out.println("ERROR: " + response.cause());
	        response.cause().printStackTrace();
	      }
	    });
		
	}
	
	private JsonObject getQueryFromFile(String param) {
		 String fileName = "./src/main/java/com/fox/platform/util/query.json";
		 
		 try {
			 
	        	String content = new String(Files.readAllBytes(Paths.get(fileName))).replaceAll("<<CountryId>>", param);
	        	JsonObject jsonObj = new JsonObject(content);
	        	return jsonObj;
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        return null;
	}

}
