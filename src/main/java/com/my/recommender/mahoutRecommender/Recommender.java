package com.my.recommender.mahoutRecommender;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.service.impl.RecommenderServiceImpl;

@Component
public class Recommender {

	static GenericUserBasedRecommender recommender = null;

	@Autowired
	RecommenderServiceImpl recommenderService;

	public GenericUserBasedRecommender buildRecommender(File file){
		if (recommender != null){
			return recommender;
		}else{
			DataModel model = null;
			try {
				model = new FileDataModel(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			UserSimilarity similarity = null;
			try {
				similarity = new PearsonCorrelationSimilarity(model);
			} catch (TasteException e) {
				e.printStackTrace();
			}
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, model);
			recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			return recommender;
		}
	}

	public GenericUserBasedRecommender getRecommender(){
		if (recommender != null){
			return recommender;
		}else{
			recommender = buildRecommender(recommenderService.getParsedDataFile());
			return recommender;
		}
	}

}
