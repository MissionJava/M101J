package com.mongodb;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {

	public static void main(String[] args) {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		// configure route
		Spark.get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {

				try {

					Map<String, Object> fruitMap = new HashMap<String, Object>();
					fruitMap.put("fruits",
							Arrays.asList("Apple", "Orange", "Banana", "Peach"));

					Template fruitPickerTemplate = configuration
							.getTemplate("fruitPicker.ftl");
					StringWriter writer = new StringWriter();
					fruitPickerTemplate.process(fruitMap, writer);

					return writer;
				} catch (Exception e) {
					halt(500);
					return null;
				}
			}
		});

		Spark.post(new Route("/favorite_fruit") {
			@Override
			public Object handle(Request request, Response response) {
				final String fruit = request.queryParams("fruit");
				if(fruit == null)
				{
					return "Why don't you pick one?";
				}
				else
				{
					return "Your favorite fruit is " + fruit;
				}
			}
		});

	}

}
