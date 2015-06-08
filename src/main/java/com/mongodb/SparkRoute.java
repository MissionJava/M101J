package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoute {

	public static void main(String[] args) {

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {

				return "Hello World\n";
			}
		});
		Spark.get(new Route("/test") {

			@Override
			public Object handle(Request request, Response response) {

				return "This is a test page\n";
			}
		});

		Spark.get(new Route("/echo/:thing") {

			@Override
			public Object handle(Request request, Response response) {

				return request.params(":thing");
			}
		});

	}

}
