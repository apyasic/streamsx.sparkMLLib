package com.ibm.streamsx.sparkmllib;

import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;

import com.ibm.streams.operator.model.InputPortSet;
import com.ibm.streams.operator.model.OutputPortSet;

//@PrimitiveOperator(description="This operator provides support for analysis of incoming tuple data against Apache Spark's decision tree ensembles machine learning library.")
@InputPortSet(cardinality=1,description="This input port is required. The operator expects an attribute of type list<float64> that will be used as input to the gradient boosted trees algorithm.")
@OutputPortSet(cardinality=1,description="This output port is required. The operator passes through all attributes on the input port as-is to the output port. In addition, it expects an attribute called 'analysisResult' of type list<float64> or float64 depending on the 'analysisType' parameter.")
public class SparkEnsembleGradientBoostedTrees extends AbstractSparkMLlibListToDoubleOperator<GradientBoostedTreesModel> {


	@Override
	protected GradientBoostedTreesModel loadModel(SparkContext sc, String modelPath) {
		return GradientBoostedTreesModel.load(sc, modelPath);
	}
	
	@Override
	protected double performOperation(Vector features) {
		return getModel().predict(features);
	}	
	
}
