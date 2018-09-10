package com.fox.platform.training.repo;

import com.fox.platform.training.anti.TrainingAnticorruption;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class TrainingRepository {

	private TrainingAnticorruption trainingAnti;
	
	public void getData(String request,  Vertx vertx, Handler<AsyncResult<JsonObject>> handler) {
		
		trainingAnti = new TrainingAnticorruption();
		
		trainingAnti.getDataFromAPI(request, handler, vertx);
		
	}
	
}
